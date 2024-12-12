package com.Cabrera.facturacion.almacen.converter;

import org.springframework.stereotype.Component;

import com.Cabrera.facturacion.almacen.dto.EventoDto;
import com.Cabrera.facturacion.almacen.entity.Evento;


@Component
public class EventoConverter extends AbstractConverter<Evento, EventoDto>{

    @Override
    public EventoDto fromEntity(Evento entity) {
        if (entity == null) return null;

        return EventoDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .costo(entity.getCosto())
                .fecha_inicio(entity.getFecha_inicio())
                .fecha_fin(entity.getFecha_fin())
                .build();
    }

    @Override
    public Evento fromDTO(EventoDto dto) {
        if (dto == null) return null;

        return Evento.builder()
        .id(dto.getId())
        .nombre(dto.getNombre())
        .descripcion(dto.getDescripcion())
        .costo(dto.getCosto())
        .fecha_inicio(dto.getFecha_inicio())
        .fecha_fin(dto.getFecha_fin())
        .build();
    }

}
