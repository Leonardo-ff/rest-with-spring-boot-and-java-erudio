package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;
	
	
	public List<PersonVO> findAll() {
		logger.info("Finding all people!");
		
// aqui o DozerMapper vai pegar a entity do repository e e ira transformar cada item da lista em um PersonVO 2 parametro
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	

	public PersonVO findById(Long id) {
		
		logger.info("Finding one person!");

		var entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID!"));
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!");
		// recebo um VO e transformo em uma entity
		var entity = DozerMapper.parseObject(person, Person.class);
		// o repository.save() retorna um entity, e desse retorno eu transformo ele em um VO novamente e passo como retorno
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one person with V2!");
//		 aqui foi feito uma nova versão para de VO e em vez de usar o Dozer, criamos um mapper customizado.
//		 só para mostrar como o Dozer, ou semelhantes, auxiliam nessa troca de objetos e tambem facilita na construção de objetos mais 
//		complexos, como algum que possua um objeto dentro do outro, como um Fornecedor que possui um objeto Endereço
		var entity = mapper.convertVoToEntity(person);
		var vo = mapper.convertEntityToVo(repository.save(entity));
		
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!");
		
		Person entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID!"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no records found for this ID!"));
		
		repository.delete(entity);
		
	}
	
	
	
}
