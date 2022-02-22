package com.esliceu.forum.controllers;

import com.esliceu.forum.models.Categoria;
import com.esliceu.forum.services.CategoryServiceImpl;
import com.esliceu.forum.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriesController {

    @Autowired
    CategoryServiceImpl service;


    @GetMapping("/categories") //Enseñar todas las categorias
    public List <Categoria> showAllCategories(){
        return service.findAll();
    }
/*
    @PostMapping("/categories") //Crear categoria
    public void createCategorie(){}

    @GetMapping("/categories/${categorySlug}") //Dentro de una categoria
    public void showInsideCategorie(){}

    @PutMapping("/categories/${categorySlug}") // Actualizar categoria
    public void updateCategory(){}

    @DeleteMapping("/categories/${categorySlug}") //Borrar categoria
    public void deleteCategory(){}

    @GetMapping("/categories/${filters.categorySlug}/topics") // Mostrar topics
    public void showAlltopics(){}

    @GetMapping("/topics/${topicId}") // Obtener un topic
    public void getTopic(){}

    @PostMapping("/topics") // Crear un topic
    public void createTopic(){}

    @GetMapping("/topics/${topicId}") //dentro de topic(hilo)
    public void showInsidetopic(){}

    @PutMapping("/topics/${topicId}") //Actualizar topic
    public void updateTopic(){}

    @DeleteMapping("/topics/${topicId}") // Borrar topic
    public void deleteTopic(){}

    @PostMapping("/topics/${topicId}/replies") //añadir reply
    public void addReply(){}

    @DeleteMapping("/topics/${topicId}/replies/${replyId}") //Borrar reply
    public void deleteReply(){}

    @PutMapping("/topics/${topicId}/replies/${replyId}") // Actualizar reply
    public void updateReply(){}
*/
}
