package sv.edu.ues.libues.controller;

import jakarta.validation.Valid;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sv.edu.ues.libues.dto.ProviderDTO;
import sv.edu.ues.libues.exceptions.ModelNotFoundException;
import sv.edu.ues.libues.model.Provider;
import sv.edu.ues.libues.service.IProviderService;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/providers")
public class ProviderController {

    @Autowired
    private IProviderService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> findAll(){
        val provider = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(provider, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProviderDTO> findById(@PathVariable("id") Long id){
        ProviderDTO dtoResponse;
        val Provider = service.findById(id);
        if (Provider == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            dtoResponse = convertToDto(Provider);
        return new ResponseEntity<>(dtoResponse, OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@Valid @RequestBody ProviderDTO providerDTO) {
        val provider = service.save(convertToEntity(providerDTO));
        val location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(provider.getIdProvider()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@Valid @RequestBody ProviderDTO providerDTO) {
        val provider = service.findById(providerDTO.idProvider());
        if (provider == null)
            throw new ModelNotFoundException("ID NOT FOUND:" + providerDTO.idProvider());
        return new ResponseEntity<>(service.update(convertToEntity(providerDTO)),OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        val provider = service.findById(id);
        if (provider == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            service.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping(value="/pageableProvider", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProviderDTO>> listPageable(Pageable pageable) {
        val providerDTO = service.listPageable(pageable).map(this::convertToDto);
        return new ResponseEntity<>(providerDTO, OK);
    }

    private ProviderDTO convertToDto(Provider obj){
        return mapper.map(obj, ProviderDTO.class);
    }

    private Provider convertToEntity(ProviderDTO dto){
        return mapper.map(dto, Provider.class);
    }
}