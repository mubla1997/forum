package com.esliceu.forum.services;

import com.esliceu.forum.models.Categoria;
import com.esliceu.forum.repositories.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoriaRepo categoriaRepo;

    @Override
    public List <Categoria> findAll() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria createCategory(Categoria categoria) {
        return categoriaRepo.save(categoria);
    }

    @Override
    public Categoria findByTitle(String title) {
        return categoriaRepo.findAllByTitle(title);
    }
}
