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

    @PreAuthorize("hasAnyRole('User','Moderator','Admin')")
    @PostMapping("/categories")
    public Map <String,Object> createCategory(@RequestBody Map<String,Object> data){
        String title = (String) data.get("title");

        if(title.contains("/")){
            data.put("title", title.replace("/",""));
        }
        data.put("slug",title);
        Categoria categoria = new Categoria();
        categoria.setTitle((String) data.get("title"));
        categoria.setDescription((String) data.get("description"));
        categoria.setSlug((String) data.get("title"));

        service.createCategory(categoria);
        return data;
    }

    @PreAuthorize("hasAnyRole('User','Moderator','Admin')")
   @PutMapping("/categories/{title}")
    public Map <String,Object> updateCategory(@PathVariable String title, @RequestBody Map<String,Object> newData){
        Categoria categoria = service.findByTitle(title);
        categoria.setTitle((String) newData.get("title"));
        categoria.setDescription((String) newData.get("description"));
        categoria.setSlug((String) newData.get("title"));

        service.createCategory(categoria);
        return newData;
    }

    @PreAuthorize("hasAnyRole('Moderator','Admin')")
    @DeleteMapping("/categories/{title}")
    public String deleteCategory(@PathVariable String title){
        service.deleteCategory(title);
        return "ok";
    }
}
