package it.incoro.app.service.mapper;

import it.incoro.app.domain.*;
import it.incoro.app.service.dto.ActioneventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Actionevent and its DTO ActioneventDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActioneventMapper extends EntityMapper<ActioneventDTO, Actionevent> {


}
