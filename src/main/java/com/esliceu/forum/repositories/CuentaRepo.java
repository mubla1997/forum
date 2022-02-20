package com.esliceu.forum.repositories;

import com.esliceu.forum.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepo extends JpaRepository<Cuenta, Integer> {
    Cuenta findByEmail(String email);

}
