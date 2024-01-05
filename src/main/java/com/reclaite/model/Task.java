package com.reclaite.model;

public interface Task extends Modifiable {

    int getId();

    String getTitle();

    String getDescription();

    TaskStatus getStatus();

}
