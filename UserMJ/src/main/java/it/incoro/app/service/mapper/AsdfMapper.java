package it.incoro.app.service.mapper;

import it.incoro.app.domain.*;
import it.incoro.app.service.dto.AsdfDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Asdf and its DTO AsdfDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AsdfMapper extends EntityMapper<AsdfDTO, Asdf> {


}
