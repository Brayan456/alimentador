package com.petfrendly.alimentador.Evento;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petfrendly.alimentador.Entidades.Comida;

@Service
public class ComidaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ComidaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarEvento(Comida comida) {
        try {
            String mensaje = objectMapper.writeValueAsString(comida);
            kafkaTemplate.send("registro_comida", mensaje);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
