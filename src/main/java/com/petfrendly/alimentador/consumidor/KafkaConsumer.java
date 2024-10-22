package com.petfrendly.alimentador.consumidor;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petfrendly.alimentador.Entidades.Mascota;
import com.petfrendly.alimentador.MascotaRepository;

@Service
public class KafkaConsumer {

    private final MascotaRepository mascotaRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaConsumer(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @KafkaListener(topics = "mascota_registrada", groupId = "perreras_group")
    public void consumirEvento(String mensaje) {
        try {
            Mascota mascota = objectMapper.readValue(mensaje, Mascota.class);
            mascotaRepository.save(mascota);
            System.out.println("Mascota y dueño registrados: " + mascota.getNombre() + ", " + mascota.getDueno().getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
