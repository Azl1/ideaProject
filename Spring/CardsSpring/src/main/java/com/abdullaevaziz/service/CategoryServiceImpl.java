package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Category;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private UserService userService;

    @Autowired
    public void setCategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Override
    public void add(long userId, Category category) {
        User user = this.userService.get(userId);
        category.setUser(user);
        try {
            this.categoryRepository.save(category);
        } catch (Exception e) {
            throw new IllegalArgumentException("Category has already added!");
        }
    }

    @Override
    public Category get(long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category does not exists!"));
    }

    @Override
    public List<Category> getUser(long userId) {
        return this.categoryRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Category is not exists userId!"));
    }

    @Override
    public List<Category> get() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category update(Category category) {
        Category old = this.get(category.getId());
        old.setName(category.getName());
        try {
            this.categoryRepository.save(old);
            return category;
        } catch (Exception e) {
            throw new IllegalArgumentException("Category is already exists!");
        }
    }

    @Override
    public Category delete(long id) {
        Category category = get(id);
        this.categoryRepository.deleteById(id);
        return category;
    }
}
