package com.petfrendly.alimentador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.petfrendly.alimentador.Entidades.Comida;

public interface ComidaRepository extends JpaRepository<Comida, Long> {
}
