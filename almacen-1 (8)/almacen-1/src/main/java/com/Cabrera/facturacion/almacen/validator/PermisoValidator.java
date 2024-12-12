package com.Cabrera.facturacion.almacen.validator;

import org.springframework.stereotype.Component;

import com.Cabrera.facturacion.almacen.entity.Permiso;
import com.Cabrera.facturacion.almacen.exception.ValidateException;

@Component
public class PermisoValidator {
	public static void save(Permiso registro) {
		if(registro.getNombre()==null || registro.getNombre().trim().isEmpty()) {
			throw new ValidateException("El nombre es requerido");
		}
		if(registro.getNombre().length()>70) {
			throw new ValidateException("El nombre no debe exceder los 70 caracteres");
		}
	}
}
