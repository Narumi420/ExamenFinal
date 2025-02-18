package com.Cabrera.facturacion.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Cabrera.facturacion.almacen.entity.Evento;
import com.Cabrera.facturacion.almacen.exception.GeneralException;
import com.Cabrera.facturacion.almacen.exception.NoDataFoundException;
import com.Cabrera.facturacion.almacen.exception.ValidateException;
import com.Cabrera.facturacion.almacen.repository.EventoRepository;
import com.Cabrera.facturacion.almacen.service.EventoService;
import com.Cabrera.facturacion.almacen.validator.EventoValidator;
@Service

public class EventoServiceImpl implements EventoService {
    @Autowired
    private EventoRepository repository;
    @Transactional(readOnly=true)
    @Override
    public List<Evento> findAll(Pageable page) {
         try {
            List<Evento> registros = repository.findAll(page).toList();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    public List<Evento> findAll() {
        try {
            List<Evento> registros = repository.findAll();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evento> findByNombre(String nombre, Pageable page) {
        try {
            List<Evento> registros = repository.findByNombreContaining(nombre, page);
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Evento findById(int id) {
        try {
          Evento evento= repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            return evento;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public Evento save(Evento evento) {
        try {
           EventoValidator.save(evento);

            if(evento.getId() == 0) {
                Evento nuevo = repository.save(evento);
                return nuevo;
            }

           Evento registro = repository.findById(evento.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            registro.setNombre(evento.getNombre());
            registro.setDescripcion(evento.getDescripcion());
            registro.setFecha_inicio(evento.getFecha_inicio());
            registro.setFecha_fin(evento.getFecha_fin());
            registro.setCosto(evento.getCosto());
            repository.save(registro);

            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
          Evento registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            repository.delete(registro);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

}
