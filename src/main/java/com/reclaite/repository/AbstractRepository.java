package com.reclaite.repository;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.reclaite.PostgresConnection;
import com.reclaite.model.User;
import lombok.Getter;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

@Getter
public abstract class AbstractRepository<I, V> implements Repository<I, V> {

    private final Connection connection;
    private final Cache<String, User> cache;

    public AbstractRepository(long duration, TimeUnit unit, RemovalListener<String, User> removalListener) {
        this.connection = PostgresConnection.getConnection();
        cache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(duration, unit)
                .removalListener(removalListener)
                .build();
    }

}
