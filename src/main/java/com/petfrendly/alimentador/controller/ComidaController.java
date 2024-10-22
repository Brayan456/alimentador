package com.petfrendly.alimentador.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.petfrendly.alimentador.Entidades.Comida;
import com.petfrendly.alimentador.Evento.ComidaProducer;

@Controller
@RequestMapping("/comidas")
public class ComidaController {

    private final ComidaProducer producer;

    public ComidaController(ComidaProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/registrar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("comida", new Comida());
        return "registro_comida";
    }

    @PostMapping("/registrar")
    public String registrarComida(Comida comida, Model model) {
        producer.enviarEvento(comida);
        model.addAttribute("mensaje", "Evento de registro de comida enviado correctamente.");
        return "registro_comida";
    }
}
