package com.app.data.repository.generic;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    T save(T item);
    T update(ID id, T item);
    List<T> saveAll(List<T> items);
    Optional<T> findById(ID id);
    List<T> findLast(int n);
    List<T> findAll();
    List<T> findAllById(List<ID> ids);
    T delete(ID id);
    List<T> deleteAllById(List<ID> ids);
    List<T> deleteAll();
}
