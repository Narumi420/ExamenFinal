package com.Cabrera.facturacion.almacen.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import com.Cabrera.facturacion.almacen.converter.CategoriaConverter;
import com.Cabrera.facturacion.almacen.dto.CategoriaDto;
import com.Cabrera.facturacion.almacen.entity.Categoria;
import com.Cabrera.facturacion.almacen.service.CategoriaService;
import com.Cabrera.facturacion.almacen.service.PdfService;
import com.Cabrera.facturacion.almacen.util.WrapperResponse;

@RestController
@RequestMapping("/v1/categorias")
//localhost:8090/v1/categorias versionado en la URI
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@Autowired
	private CategoriaConverter converter;
	
	@Autowired
	private PdfService pdfService;



	@GetMapping
	public ResponseEntity<List<CategoriaDto>> findAll(
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			){
		Pageable page = PageRequest.of(pageNumber, pageSize);				
		List<CategoriaDto> categorias = converter.fromEntity(service.findAll());
		
		return new WrapperResponse(true, "success", categorias).createResponse(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDto> create (@RequestBody CategoriaDto categoria){
		Categoria categoriaEntity=converter.fromDTO(categoria);
		CategoriaDto registro = converter.fromEntity(service.save(categoriaEntity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDto> update(@PathVariable("id") int id,@RequestBody CategoriaDto categoria){
		Categoria categoriaEntity=converter.fromDTO(categoria);
		CategoriaDto registro = converter.fromEntity(service.save(categoriaEntity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> findById(@PathVariable("id") int id){
		CategoriaDto registro=converter.fromEntity(service.findById(id));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
	}
	
	@GetMapping("/report")
	public ResponseEntity<byte[]> generarReport(){
		List<CategoriaDto> categorias = converter.fromEntity(service.findAll());
		LocalDateTime fecha = LocalDateTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String fechaFormato = fecha.format(formato);

		//Crear el contexto de Thymeleaf con los datos
		Context context = new Context();
		context.setVariable("registros", categorias);
		context.setVariable("fecha", fechaFormato);

		//Llamar al servicio de PDF para crear el PDF
		byte[] pdfBytes = pdfService.createPdf("CategoriaReport", context);

		//Configuramos los encabezados de la respuesta
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("inline", "reporte-categorias.pdf");

		return ResponseEntity.ok().headers(headers).body(pdfBytes);

	}
	
	
	
}
