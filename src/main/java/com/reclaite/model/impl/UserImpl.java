package com.reclaite.model.impl;

import com.reclaite.model.Task;
import com.reclaite.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserImpl implements User {

    private final int id;
    private final String username;
    private final String sessionToken;
    private final List<Task> tasks;

    private boolean modified;

    public UserImpl(int id, String username, String sessionToken) {
        this.id = id;
        this.username = username;
        this.sessionToken = sessionToken;
        this.tasks = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getSessionToken() {
        return sessionToken;
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public void setModified(boolean flag) {
        this.modified = flag;
    }

    @Override
    public void onClear() {
        if (modified) {

        }
    }

}
