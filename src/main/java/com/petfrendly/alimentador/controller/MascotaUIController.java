package com.petfrendly.alimentador.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petfrendly.alimentador.Entidades.Mascota;
import com.petfrendly.alimentador.Evento.KafkaProducer;

@Controller
@RequestMapping("/mascotas")
public class MascotaUIController {

    private final KafkaProducer producer;

    public MascotaUIController(KafkaProducer producer) {
        this.producer = producer;
    }

    // Mostrar el formulario de registro
    @GetMapping("/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("mascota", new Mascota());
        return "registro";
    }

    // Enviar el evento a Kafka desde el formulario
    @PostMapping("/registrar")
    public String registrarMascota(Mascota mascota, Model model) {
        // Enviar la mascota como evento a Kafka
        producer.enviarEvento("mascota_registrada", mascota);
        model.addAttribute("mensaje", "Evento de registro enviado correctamente.");
        return "registro";
    }
}
