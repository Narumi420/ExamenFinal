package com.Cabrera.facturacion.almacen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Cabrera.facturacion.almacen.entity.Categoria;
import com.Cabrera.facturacion.almacen.exception.GeneralException;
import com.Cabrera.facturacion.almacen.exception.NoDataFoundException;
import com.Cabrera.facturacion.almacen.exception.ValidateException;
import com.Cabrera.facturacion.almacen.repository.CategoriaRepository;
import com.Cabrera.facturacion.almacen.service.CategoriaService;
import com.Cabrera.facturacion.almacen.validator.CategoriaValidator;

@Service
public class CategoriaServiceImpl implements CategoriaService{
	@Autowired
	private CategoriaRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		try {
			List<Categoria> registros=repository.findAll();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}		
	}
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> findAll(Pageable page) {
		try {
			List<Categoria> registros=repository.findAll(page).toList();
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Categoria> finByNombre(String nombre, Pageable page) {
		try {
			List<Categoria> registros=repository.findByNombreContaining(nombre,page);
			return registros;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria findById(int id) {
		try {
			Categoria registro=repository.findById(id).
					orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID"));
			return registro;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	@Transactional
	public Categoria save(Categoria categoria) {
		try {
			
			CategoriaValidator.save(categoria);
			//Nuevo registro
			if (categoria.getId()==0) {
				Categoria nuevo=repository.save(categoria);
				return nuevo;				
			}
			//editar registro
			Categoria registro=repository.findById(categoria.getId()).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID"));
			registro.setNombre(categoria.getNombre());
			registro.setDescripcion(categoria.getDescripcion());
			repository.save(registro);
			return registro;
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		try {
			Categoria registro=repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID"));
			repository.delete(registro);
		} catch (ValidateException | NoDataFoundException e) {
			throw e;
		}
		catch (GeneralException e) {
			throw new GeneralException("Error del servidor");
		}
		
	}

	
}
