package com.sergax.springboot_restapi.service;

import java.util.List;

/**
 * by Aksenchenko Serhii on 18.04.2022
 */
public interface GenericService<T, ID> {

    List<T> getAll();

    T findById(ID id);

    T create(T t);

    T update(T t);

    void delete(ID id);

}
