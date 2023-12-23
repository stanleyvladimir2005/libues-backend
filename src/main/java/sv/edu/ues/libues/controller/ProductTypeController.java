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
import sv.edu.ues.libues.dto.ProductTypeDTO;
import sv.edu.ues.libues.exceptions.ModelNotFoundException;
import sv.edu.ues.libues.model.ProductType;
import sv.edu.ues.libues.service.IProductTypeService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/productTypes")
public class ProductTypeController {

    @Autowired
    private IProductTypeService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductTypeDTO>> findAll(){
        List<ProductTypeDTO> productType = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(productType, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductTypeDTO> findById(@PathVariable("id") Long id){
        ProductTypeDTO dtoResponse;
        ProductType productType = service.findById(id);
        if (productType == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            dtoResponse = convertToDto(productType);
        return new ResponseEntity<>(dtoResponse, OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@Valid @RequestBody ProductTypeDTO productTypeDTO) {
        ProductType productType = service.save(convertToEntity(productTypeDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productType.getIdProductType()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@Valid @RequestBody ProductTypeDTO productTypeDTO) {
        ProductType productType = service.findById(productTypeDTO.idProductType());
        if (productType == null)
            throw new ModelNotFoundException("ID NOT FOUND:" + productTypeDTO.idProductType());
        return new ResponseEntity<>(service.update(convertToEntity(productTypeDTO)),OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ProductType productType = service.findById(id);
        if (productType == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            service.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping(value="/pageableProductType", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProductTypeDTO>> listPageable(Pageable pageable) {
        Page<ProductTypeDTO> productTypeDTO = service.listPageable(pageable).map(this::convertToDto);
        return new ResponseEntity<>(productTypeDTO, OK);
    }

    private ProductTypeDTO convertToDto(ProductType obj){
        return mapper.map(obj, ProductTypeDTO.class);
    }

    private ProductType convertToEntity(ProductTypeDTO dto){
        return mapper.map(dto, ProductType.class);
    }
}