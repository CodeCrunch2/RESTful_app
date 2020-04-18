package com.mkudryavtsev.repository;

import java.util.List;

public interface GenericRepository<T, ID>  {
    void save(T t);
    void delete(T t);
    void update(T t);
    List<T> getAll();
    T getById(ID id);
}
