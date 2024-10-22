package com.petfrendly.alimentador.consumidor;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.petfrendly.alimentador.Entidades.Mascota;
import com.petfrendly.alimentador.MascotaRepository;

import java.util.Optional;

@Service
public class MascotaPerdidaConsumer {

    private final MascotaRepository mascotaRepository;

    public MascotaPerdidaConsumer(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @KafkaListener(topics = "mascota_perdida", groupId = "perreras_group")
    public void consumirEvento(String idMascota) {
        Optional<Mascota> mascotaOpt = mascotaRepository.findById(Long.parseLong(idMascota));

        if (mascotaOpt.isPresent()) {
            Mascota mascota = mascotaOpt.get();
            System.out.println("Mascota perdida: " + mascota.getNombre());
            System.out.println("Dueño: " + mascota.getDueno().getNombre());
            System.out.println("Teléfono: " + mascota.getDueno().getTelefono());
            System.out.println("Ubicación del Perro: " + "se encuentra en el callao");
        } else {
            System.out.println("No se encontró ninguna mascota con el ID: " + idMascota);
        }
    }
}
