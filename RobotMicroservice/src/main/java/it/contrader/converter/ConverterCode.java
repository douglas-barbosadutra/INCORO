package it.contrader.converter;

import java.util.ArrayList;
import java.util.List;
import it.contrader.dto.CodeDTO;
import it.contrader.dto.BehaviorDTO;
import it.contrader.model.Behavior;
import it.contrader.model.Code;

public class ConverterCode {
	
public static CodeDTO toDTO(Code code) {
	CodeDTO codeDTO = null;
	if(code != null) {
		codeDTO = new CodeDTO();
		codeDTO.setIdCode(code.getIdCode());
		codeDTO.setName(code.getName());
		codeDTO.setBody(code.getBody());
		codeDTO.setType(code.getType());
		BehaviorDTO behaviorDTO = new BehaviorDTO();
		behaviorDTO = ConverterBehavior.toDTO(code.getBehavior());	
		codeDTO.setBehavior(behaviorDTO);
		}
	return codeDTO;
}
	
public static Code toEntity(CodeDTO codeDTO) {
	Code code = null;
	if (codeDTO != null) {
		code = new Code();
		code.setIdCode(codeDTO.getIdCode());
		code.setName(codeDTO.getName());
		code.setBody(codeDTO.getBody());
		code.setType(codeDTO.getType());	
		Behavior behavior = new Behavior();
		behavior = ConverterBehavior.toEntity(codeDTO.getBehavior());	
		code.setBehavior(behavior);
		}
	return code;
}
	
public static List<CodeDTO> toListDTO(List<Code> list) {
	List<CodeDTO> listCodeDTO = new ArrayList<>();
	if (!list.isEmpty()) {
		for (Code code : list) {
			listCodeDTO.add(ConverterCode.toDTO(code));
		}
	}
	return listCodeDTO;
}
	
public static List<Code> toListEntity(List<CodeDTO> listCodeDTO) {
	List<Code> list = new ArrayList<>();
	if (!listCodeDTO.isEmpty()) {
		for (CodeDTO codeDTO : listCodeDTO) {
			list.add(ConverterCode.toEntity(codeDTO));
		}
	}
	return list;
	}
}