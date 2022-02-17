package com.esliceu.forum.repositories;

import com.esliceu.forum.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepo extends JpaRepository<Cuenta, Integer> {
    Cuenta findByUsername(String username);
}
