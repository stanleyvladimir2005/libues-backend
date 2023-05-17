package sv.edu.ues.libues.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sv.edu.ues.libues.dto.AuthorDTO;
import sv.edu.ues.libues.exceptions.ModelNotFoundException;
import sv.edu.ues.libues.model.Author;
import sv.edu.ues.libues.service.IAuthorService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    @Autowired
    private IAuthorService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAll(){
        List<AuthorDTO> author = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorDTO> findById(@PathVariable("id") Long id){
        AuthorDTO dtoResponse;
        Author author = service.findById(id);
        if (author == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            dtoResponse = convertToDto(author);
        return new ResponseEntity<>(dtoResponse, OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@Valid @RequestBody AuthorDTO authorDTO) {
        Author author = service.save(convertToEntity(authorDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(author.getIdAuthor()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@Valid @RequestBody AuthorDTO authorDTO) {
        Author author = service.findById(authorDTO.getIdAuthor());
        if (author == null)
            throw new ModelNotFoundException("ID NOT FOUND:" + authorDTO.getIdAuthor());
        return new ResponseEntity<>(service.update(convertToEntity(authorDTO)),OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Author autor = service.findById(id);
        if (autor == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            service.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping(value="/pageableAuthor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AuthorDTO>> listPageable(Pageable pageable) {
        Page<AuthorDTO> authorDTO = service.listPageable(pageable).map(this::convertToDto);
        return new ResponseEntity<>(authorDTO, OK);
    }

    private AuthorDTO convertToDto(Author obj){
        return mapper.map(obj, AuthorDTO.class);
    }

    private Author convertToEntity(AuthorDTO dto){
        return mapper.map(dto, Author.class);
    }
}