package com.Cabrera.facturacion.almacen.dto;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventoDto {
    private int id;
    private String nombre;
    private String descripcion;
    private Date fecha_inicio;
    private Date fecha_fin;
    private double costo;
}


