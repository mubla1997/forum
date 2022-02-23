package com.esliceu.forum.controllers;

import com.esliceu.forum.models.Categoria;
import com.esliceu.forum.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CategoriesController {

    @Autowired
    CategoryServiceImpl service;


    @GetMapping("/categories")
    public List <Categoria> showAllCategories(){
        return service.findAll();
    }


    @PostMapping("/categories")
    public Map <String,Object> createCategory(@RequestBody Map<String,Object> data){
        String title = (String) data.get("title");

        if(title.contains("/")){
            data.put("title", title.replace("/",""));
        }

        Categoria categoria = new Categoria();
        categoria.setTitle((String) data.get("title"));
        categoria.setDescription((String) data.get("description"));
        categoria.setSlug((String) data.get("title"));

        service.createCategory(categoria);
        return data;
    }
}
