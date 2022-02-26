package com.esliceu.forum.services;

import com.esliceu.forum.models.Categoria;

import java.util.List;

public interface CategoryService {
    List<Categoria> findAll();
    void createCategory(Categoria categoria);
    Categoria findByTitle(String title);
    void deleteCategory(String title);
}
