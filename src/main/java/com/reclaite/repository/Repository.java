package com.reclaite.repository;

import lombok.NonNull;

public interface Repository<I, V> {

    void add(@NonNull V value);

    void update(@NonNull V value);

    V get(@NonNull I identifier);

    V delete(@NonNull I identifier);

}
