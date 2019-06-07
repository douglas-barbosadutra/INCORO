package it.incoro.app.service.mapper;

import it.incoro.app.domain.*;
import it.incoro.app.service.dto.ThingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Thing and its DTO ThingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ThingMapper extends EntityMapper<ThingDTO, Thing> {


}
