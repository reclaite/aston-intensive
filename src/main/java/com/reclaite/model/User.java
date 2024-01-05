package com.reclaite.model;

import java.util.List;

public interface User extends Modifiable {

    int getId();

    String getUsername();

    String getSessionToken();

    List<Task> getTasks();
}
