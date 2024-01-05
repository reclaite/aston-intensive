package com.reclaite.repository;

import com.google.common.cache.Cache;
import com.reclaite.PostgresConnection;
import com.reclaite.model.User;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class UserRepository extends AbstractRepository<String, User> {

    public UserRepository() {
        super(1, TimeUnit.MINUTES, notification -> {
            Connection connection = PostgresConnection.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (username, session_token) " +
                            "VALUES (?, ?) ON CONFLICT (user_id) " +
                            "DO UPDATE SET username = excluded.username, " +
                            "session_token = excluded.session_token")) {
                User user = notification.getValue();
                if (user == null || !user.isModified()) {
                    return;
                }

                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getSessionToken());

                preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void add(@NonNull User value) {
        getCache().put(value.getSessionToken(), value);
    }

    @Override
    public void update(@NonNull User value) {
        User previous = getCache().getIfPresent(value.getSessionToken());
        if (value.equals(previous)) {
            return;
        }

        getCache().put(value.getSessionToken(), value);
        value.setModified(true);
    }

    @Override
    public User get(@NonNull String identifier) {
        User user = getCache().getIfPresent(identifier);
        if (user == null) {
            try (PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM users WHERE session_token = ?")) {
                statement.setString(1, identifier);
                statement.execute();
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }
        }
        return user;
    }

    @Override
    public User delete(@NonNull String identifier) {
        Cache<String, User> cache = getCache();
        User user = cache.getIfPresent(identifier);
        cache.invalidate(identifier);
        return user;
    }

}
