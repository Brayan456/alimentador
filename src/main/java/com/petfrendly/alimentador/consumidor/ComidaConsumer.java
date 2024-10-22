package com.petfrendly.alimentador.consumidor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petfrendly.alimentador.Entidades.Comida;
import com.petfrendly.alimentador.repository.ComidaRepository;

@Service
public class ComidaConsumer {

    private final ComidaRepository comidaRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ComidaConsumer(ComidaRepository comidaRepository) {
        this.comidaRepository = comidaRepository;
    }

    @KafkaListener(topics = "registro_comida", groupId = "perreras_group")
    public void consumirEvento(String mensaje) {
        try {
            Comida comida = objectMapper.readValue(mensaje, Comida.class);
            comidaRepository.save(comida);
            System.out.println("Comida registrada: " + comida.getDescripcion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}