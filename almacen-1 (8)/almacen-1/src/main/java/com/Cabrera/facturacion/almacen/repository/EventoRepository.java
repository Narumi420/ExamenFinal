package com.Cabrera.facturacion.almacen.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cabrera.facturacion.almacen.entity.Evento;



@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findByNombreContaining(String nombre, Pageable pageable);
}

