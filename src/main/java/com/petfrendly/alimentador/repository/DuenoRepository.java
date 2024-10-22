package com.petfrendly.alimentador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.petfrendly.alimentador.Entidades.Dueno;

public interface DuenoRepository extends JpaRepository<Dueno, Long> {
}
