package com.petfrendly.alimentador.consumidor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.petfrendly.alimentador.Entidades.Mascota;
import com.petfrendly.alimentador.MascotaRepository;

import java.util.Optional;

@Service
public class MascotaPerdidaConsumer {

    @Autowired
    private JavaMailSender mailSender;  // Inyección de dependencia para el servicio de correo

    private final MascotaRepository mascotaRepository;

    public MascotaPerdidaConsumer(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @KafkaListener(topics = "mascota_perdida", groupId = "perreras_group")
    public void consumirEvento(String mensaje) {
        String[] partes = mensaje.split(",");
        String idMascota = partes[0];
        String latitud = partes[1];
        String longitud = partes[2];

        Optional<Mascota> mascotaOpt = mascotaRepository.findById(Long.parseLong(idMascota));

        if (mascotaOpt.isPresent()) {
            Mascota mascota = mascotaOpt.get();
            String nombreMascota = mascota.getNombre();
            String nombreDueno = mascota.getDueno().getNombre();
            String correoDueno = mascota.getDueno().getContacto();  // Supongamos que el dueño tiene un correo

            String correoMensaje = "Hola " + nombreDueno + ", tu mascota " + nombreMascota +
                                   " ha sido encontrada. Ubicación: Latitud " + latitud +
                                   ", Longitud " + longitud;

            enviarCorreo(correoDueno, "Mascota Perdida Encontrada", correoMensaje);
            System.out.println("Correo enviado a " + correoDueno);
            System.out.println("Mensaje: " + correoMensaje);
        } else {
            System.out.println("No se encontró ninguna mascota con el ID: " + idMascota);
        }
    }

    private void enviarCorreo(String destinatario, String asunto, String mensaje) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(destinatario);
        email.setSubject(asunto);
        email.setText(mensaje);
        mailSender.send(email);
    }
}
