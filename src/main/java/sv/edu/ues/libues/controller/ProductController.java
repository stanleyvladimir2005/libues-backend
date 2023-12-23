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
import sv.edu.ues.libues.dto.ProductDTO;
import sv.edu.ues.libues.exceptions.ModelNotFoundException;
import sv.edu.ues.libues.model.Product;
import sv.edu.ues.libues.service.IProductService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<ProductDTO> product = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Long id){
        ProductDTO dtoResponse;
        Product product = service.findById(id);
        if (product == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            dtoResponse = convertToDto(product);
        return new ResponseEntity<>(dtoResponse, OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@Valid @RequestBody ProductDTO productDTO) {
        Product product = service.save(convertToEntity(productDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getIdProduct()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@Valid @RequestBody ProductDTO productDTO) {
        Product product = service.findById(productDTO.idProduct());
        if (product == null)
            throw new ModelNotFoundException("ID NOT FOUND:" + productDTO.idProduct());
        return new ResponseEntity<>(service.update(convertToEntity(productDTO)),OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Product product = service.findById(id);
        if (product == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            service.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping(value="/pageableProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProductDTO>> listPageable(Pageable pageable) {
        Page<ProductDTO> productDTO = service.listPageable(pageable).map(this::convertToDto);
        return new ResponseEntity<>(productDTO, OK);
    }

    private ProductDTO convertToDto(Product obj){
        return mapper.map(obj, ProductDTO.class);
    }

    private Product convertToEntity(ProductDTO dto){
        return mapper.map(dto, Product.class);
    }
}