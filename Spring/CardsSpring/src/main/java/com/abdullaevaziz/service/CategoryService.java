package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Category;

import java.util.List;

public interface CategoryService {

    void add(long userId, Category category);

    Category get(long id);

    List<Category> getUser(long userId);

    List<Category> get();

    Category update(Category category);

    Category delete(long id);

}
