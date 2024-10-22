package com.petfrendly.alimentador.Evento;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petfrendly.alimentador.Entidades.Mascota;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarEvento(String topic, Mascota mascota) {
        try {
            String mensaje = objectMapper.writeValueAsString(mascota);
            kafkaTemplate.send(topic, mensaje);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
