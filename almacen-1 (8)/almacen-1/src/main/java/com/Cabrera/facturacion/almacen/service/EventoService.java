package com.Cabrera.facturacion.almacen.service;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.Cabrera.facturacion.almacen.entity.Evento;



public interface EventoService {
public List<Evento> findAll(Pageable page);
    public List<Evento> findAll();
    public List<Evento> findByNombre(String nombre, Pageable page);
    public Evento findById(int id);
    public Evento save(Evento evento);
    public void delete(int id);
}
