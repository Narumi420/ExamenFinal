package com.Cabrera.facturacion.almacen.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cabrera.facturacion.almacen.entity.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
	List<Permiso> findByNombreContaining(String nombre,Pageable page);
}
