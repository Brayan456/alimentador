package com.petfrendly.alimentador;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petfrendly.alimentador.Entidades.Mascota;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
