package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "personajes")
public class Personaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String biografia;

    @ManyToOne(
        fetch = FetchType.EAGER
    )
    @JoinColumn(
        name = "id_serie"
    )
    private Serie serie;

    public Personaje() {
    }   

    public Personaje(Long id, String nombre, String biografia, Serie serie) {
        this.id = id;
        this.nombre = nombre;
        this.biografia = biografia;
        this.serie = serie;
    }

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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    

}





