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
import sv.edu.ues.libues.dto.AreaDTO;
import sv.edu.ues.libues.exceptions.ModelNotFoundException;
import sv.edu.ues.libues.model.Area;
import sv.edu.ues.libues.service.IAreaService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/areas")
public class AreaController {

    @Autowired
    private IAreaService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<AreaDTO>> findAll(){
        List<AreaDTO> Area = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(Area, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AreaDTO> findById(@PathVariable("id") Long id){
        AreaDTO dtoResponse;
        Area area = service.findById(id);
        if (area == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            dtoResponse = convertToDto(area);
        return new ResponseEntity<>(dtoResponse, OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@Valid @RequestBody AreaDTO areaDTO) {
        Area area = service.save(convertToEntity(areaDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(area.getIdArea()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@Valid @RequestBody AreaDTO areaDTO) {
        Area area = service.findById(areaDTO.getIdArea());
        if (area == null)
            throw new ModelNotFoundException("ID NOT FOUND:" + areaDTO.getIdArea());
        return new ResponseEntity<>(service.update(convertToEntity(areaDTO)),OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Area area = service.findById(id);
        if (area == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            service.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping(value="/pageableArea", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AreaDTO>> listPageable(Pageable pageable) {
        Page<AreaDTO> areaDTO = service.listPageable(pageable).map(this::convertToDto);
        return new ResponseEntity<>(areaDTO, OK);
    }

    private AreaDTO convertToDto(Area obj){
        return mapper.map(obj, AreaDTO.class);
    }

    private Area convertToEntity(AreaDTO dto){
        return mapper.map(dto, Area.class);
    }
}