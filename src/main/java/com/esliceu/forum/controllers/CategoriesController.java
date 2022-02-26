package com.esliceu.forum.controllers;

import com.esliceu.forum.models.Categoria;
import com.esliceu.forum.models.Topic;
import com.esliceu.forum.services.CategoryServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CategoriesController {

    @Autowired
    CategoryServiceImpl categoryService;


    @GetMapping("/categories")
    public List <Categoria> showAllCategories(){
        return categoryService.findAll();
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

        categoryService.createCategory(categoria);
        return data;
    }

    @GetMapping("/categories/{title}")
    public String getTopics(@PathVariable String title) throws JsonProcessingException {

        Categoria categoria = categoryService.findByTitle(title);

        return new ObjectMapper().writeValueAsString(categoria);
    }

    @PreAuthorize("hasAnyRole('User','Moderator','Admin')")
    @PutMapping ("/categories/{title}")
    public Map <String,Object> updateCategory(@PathVariable String title, @RequestBody Map<String,Object> newData){
        Categoria categoria = categoryService.findByTitle(title);
        categoria.setTitle((String) newData.get("title"));
        categoria.setDescription((String) newData.get("description"));
        categoria.setSlug((String) newData.get("title"));

        categoryService.createCategory(categoria);
        return newData;
    }

    @PreAuthorize("hasAnyRole('Moderator','Admin')")
    @DeleteMapping("/categories/{title}")
    public String deleteCategory(@PathVariable String title){
        categoryService.deleteCategory(title);
        return "ok";
    }

    @GetMapping("/categories/{title}/topics")
    public List<Topic> getAllTopics(@PathVariable String title) {

        Categoria categoria = categoryService.findByTitle(title);

        return null;
    }
}
