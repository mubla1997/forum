package com.esliceu.forum.services;

import com.esliceu.forum.models.Categoria;

import java.util.List;

public interface CategoryService {
    public List<Categoria> findAll();
    public Categoria createCategory(Categoria categoria);
}
