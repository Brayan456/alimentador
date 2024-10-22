package com.petfrendly.alimentador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petfrendly.alimentador.Evento.MascotaPerdidaProducer;

@Controller
@RequestMapping("/mascotas")
public class MascotaPerdidaController {

    private final MascotaPerdidaProducer producer;

    public MascotaPerdidaController(MascotaPerdidaProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/perdida")
    public String mostrarFormulario() {
        return "perdida";
    }

    @PostMapping("/perdida")
    public String reportarPerdida(@RequestParam Long id, Model model) {
        producer.enviarEvento(id);
        model.addAttribute("mensaje", "Evento de mascota perdida enviado correctamente.");
        return "perdida";
    }
}
