package br.com.erudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonServices;
import br.com.erudio.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

// @CrosOrigin esse aqui é uma maneira de colocar o CORs, mas pra todo controller, o que não é o ideal
@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/{id}",
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a Person", description = "Finds a Person",
		tags = {"People"},
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))
				),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public PersonVO findById(@PathVariable(value = "id")Long id)  {
		
		return service.findById(id);
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all People", description = "Finds all People",
			tags = {"People"},
			responses = {
					// Para uma resposta de sucesso, é bom customizar essa resposta (Content)
					@ApiResponse(description = "Success", responseCode = "200", 
							content = {
									@Content(
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
											)
							}),
					//No caso de uma resposta negativa, pode manter somente o @Content, só alterado o codigo e descrição
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public List<PersonVO> findAll()  {
		
		return service.findAll();
	}
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://erudio.com.br"})
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Add a new Person", description = "Add a new Person by passing in a JSON, XML or YML representation of the person",
		tags = {"People"},
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public PersonVO create(@RequestBody PersonVO person)  {
		return service.create(person);
	}
	
	@PutMapping(
			consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
			produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Updates a Person", description = "Updates a Person by passing in a JSON, XML or YML representation of the person",
		tags = {"People"},
		responses = {
				@ApiResponse(description = "Updated", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))
				),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
			)
	public PersonVO update(@RequestBody PersonVO person)  {
		
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes a Person", description = "Deletes a Person by passing in a JSON, XML or YML representation of the person",
		tags = {"People"},
		responses = {
				@ApiResponse(description = "No content", responseCode = "204", 	content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
			}
	)
	public ResponseEntity<?> deleteById(@PathVariable(value = "id")Long id)  {
		service.delete(id);
		return ResponseEntity.noContent().build();
//		ResponseEnity serve para dar a resposta com status 204, em vez de 200.
	}


}
