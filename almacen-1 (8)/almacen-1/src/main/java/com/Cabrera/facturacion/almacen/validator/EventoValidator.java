package com.Cabrera.facturacion.almacen.validator;


import java.util.Date;

import com.Cabrera.facturacion.almacen.entity.Evento;
import com.Cabrera.facturacion.almacen.exception.ValidateException;

public class EventoValidator {
    public static void save(Evento registro) {

        if(registro.getNombre() == null || registro.getNombre().trim().isEmpty()) {
            throw new ValidateException("El nombre es requerido");
        }
        if(registro.getNombre().length() > 70) {
            throw new ValidateException("El nombre no debe exceder los 70 caracteres");
        }

        if(registro.getDescripcion() == null || registro.getDescripcion().trim().isEmpty()) {
            throw new ValidateException("La descripcion no puede ser nula o vacia");
        }

        if(registro.getCosto() <= 0) {
            throw new ValidateException("El costo no puede ser menor o igual a cero");
        }

        if(registro.getFecha_inicio() == null) {
            throw new ValidateException("La fecha de inicio es requerida");
        }

        if(registro.getFecha_fin() == null) {
            throw new ValidateException("La fecha de fin es requerida");
        }

        if(registro.getFecha_fin().before(registro.getFecha_inicio())) {
            throw new ValidateException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
    }
}

