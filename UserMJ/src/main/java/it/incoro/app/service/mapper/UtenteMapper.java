package it.incoro.app.service.mapper;

import it.incoro.app.domain.*;
import it.incoro.app.service.dto.UtenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Utente and its DTO UtenteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UtenteMapper extends EntityMapper<UtenteDTO, Utente> {


}
