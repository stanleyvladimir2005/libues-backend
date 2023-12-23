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
import sv.edu.ues.libues.dto.EditorialDTO;
import sv.edu.ues.libues.exceptions.ModelNotFoundException;
import sv.edu.ues.libues.model.Editorial;
import sv.edu.ues.libues.service.IEditorialService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/editorials")
public class EditorialController {

    @Autowired
    private IEditorialService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<EditorialDTO>> findAll(){
        List<EditorialDTO> editorial = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(editorial, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EditorialDTO> findById(@PathVariable("id") Long id){
        EditorialDTO dtoResponse;
        Editorial editorial = service.findById(id);
        if (editorial == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            dtoResponse = convertToDto(editorial);
        return new ResponseEntity<>(dtoResponse, OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@Valid @RequestBody EditorialDTO editorialDTO) {
        Editorial editorial = service.save(convertToEntity(editorialDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(editorial.getIdEditorial()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@Valid @RequestBody EditorialDTO editorialDTO) {
        Editorial editorial = service.findById(editorialDTO.idEditorial());
        if (editorial == null)
            throw new ModelNotFoundException("ID NOT FOUND:" + editorialDTO.idEditorial());
        return new ResponseEntity<>(service.update(convertToEntity(editorialDTO)),OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Editorial editorial = service.findById(id);
        if (editorial == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            service.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping(value="/pageableEditorial", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EditorialDTO>> listPageable(Pageable pageable) {
        Page<EditorialDTO> editorialDTO = service.listPageable(pageable).map(this::convertToDto);
        return new ResponseEntity<>(editorialDTO, OK);
    }

    private EditorialDTO convertToDto(Editorial obj){
        return mapper.map(obj, EditorialDTO.class);
    }

    private Editorial convertToEntity(EditorialDTO dto){
        return mapper.map(dto, Editorial.class);
    }
}