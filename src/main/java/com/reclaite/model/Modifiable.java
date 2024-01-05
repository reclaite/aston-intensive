package com.reclaite.model;

public interface Modifiable {

    boolean isModified();

    void setModified(boolean flag);

    void onClear();

}
