package com.reclaite.model.impl;

import com.reclaite.model.Task;
import com.reclaite.model.TaskStatus;

public class TaskImpl implements Task {

    private final int id;
    private final String title;
    private final String description;
    private final TaskStatus status;

    private boolean modified;

    public TaskImpl(int id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public TaskStatus getStatus() {
        return status;
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

    }

}
