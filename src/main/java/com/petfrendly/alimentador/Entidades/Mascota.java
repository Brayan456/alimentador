package com.petfrendly.alimentador.Entidades;

import jakarta.persistence.*;

@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipo;
    private double peso;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dueno_id")
    private Dueno dueno;

    private String lugarVivienda;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public String getLugarVivienda() {
        return lugarVivienda;
    }

    public void setLugarVivienda(String lugarVivienda) {
        this.lugarVivienda = lugarVivienda;
    }
}
