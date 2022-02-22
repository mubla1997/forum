package com.esliceu.forum.repositories;

import com.esliceu.forum.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepo extends JpaRepository <Categoria, Integer> {
}
