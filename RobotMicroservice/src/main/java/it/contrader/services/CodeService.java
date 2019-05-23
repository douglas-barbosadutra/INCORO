package it.contrader.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.ConverterCode;
import it.contrader.dao.CodeRepository;
import it.contrader.dto.CodeDTO;
import it.contrader.model.Code;

@Service
public class CodeService {
private CodeRepository codeRepository;
	
	@Autowired
	public CodeService(CodeRepository codeRepository) {
		this.codeRepository = codeRepository;
	}

	public List<CodeDTO> getLisCodeDTO() {
		return ConverterCode.toListDTO((List<Code>)codeRepository.findAll());
	}

	public CodeDTO getCodeDTOById(int id) {
		return ConverterCode.toDTO(codeRepository.findCodeByIdCode(id));
	}

	public boolean insertCode(CodeDTO codeDTO) {
		return codeRepository.save(ConverterCode.toEntity(codeDTO)) != null;
	}

	public boolean updateCode(CodeDTO codeDTO) {
		return codeRepository.save(ConverterCode.toEntity(codeDTO)) != null;
	}
	
	public void deleteCodeById(Integer id) {
		codeRepository.deleteById(id);
	}

	public List<CodeDTO> findCodeeDTOByName(String name) {
		final List<Code> list = codeRepository.findAllByName(name);
		final List<CodeDTO> codeDTOs = new ArrayList<>();
		list.forEach(i -> codeDTOs.add(ConverterCode.toDTO(i)));
		return codeDTOs;	
	} 

}