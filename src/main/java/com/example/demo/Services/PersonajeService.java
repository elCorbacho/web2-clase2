package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.repositories.PersonajeRepository;
import com.example.demo.models.Personaje;



@Service
public class PersonajeService {
    
    @Autowired
    private PersonajeRepository personajeRepository;
    
    //listar
    public List<Personaje> listarTodos() {
        return personajeRepository.findAll();
    }
    
    //guardar
    public Personaje guardar( Personaje personaje) {
        return personajeRepository.save(personaje);
    }
    
    //obtener por id
    public Personaje obtenerPersonajePorId(Long id) {
        return personajeRepository.findById(id).orElse(null);
    }
    
    //eliminar
    public void eliminarPersonaje(Long id) {
        personajeRepository.deleteById(id);
    }
}
