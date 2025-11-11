package com.example.demo.models;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "series")

public class Serie {
    
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
        )

    private Long id;
    
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private int year;

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "serie",
        cascade = {CascadeType.ALL}
    )


    
    private List<Personaje> Personajes;

    public Serie (long id,String titulo, int year, List<Personaje> personajes) {
        this.id = id;
        this.titulo = titulo;
        this.year = year;
        Personajes = personajes;
    }

    public Serie() {
    }
    




    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public List<Personaje> getPersonajes() {
        return Personajes;
    }
    public void setPersonajes(List<Personaje> personajes) {
        Personajes = personajes;
    }
        
}


    


    

    
