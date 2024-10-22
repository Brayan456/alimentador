package com.petfrendly.alimentador.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petfrendly.alimentador.Entidades.Mascota;
import com.petfrendly.alimentador.Evento.KafkaProducer;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final KafkaProducer producer;

    public MascotaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarMascota(@RequestBody Mascota mascota) {
        producer.enviarEvento("mascota_registrada", mascota);
        return ResponseEntity.ok("Evento de registro enviado correctamente.");
    }
}
