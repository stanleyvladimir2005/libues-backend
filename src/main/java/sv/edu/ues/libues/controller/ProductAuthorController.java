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
import sv.edu.ues.libues.dto.ProductAuthorDTO;
import sv.edu.ues.libues.exceptions.ModelNotFoundException;
import sv.edu.ues.libues.model.ProductAuthor;
import sv.edu.ues.libues.service.IProductAuthorService;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1/productAuthors")
public class ProductAuthorController {

    @Autowired
    private IProductAuthorService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductAuthorDTO>> findAll(){
        val productAuthor = service.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(productAuthor, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductAuthorDTO> findById(@PathVariable("id") Long id){
        ProductAuthorDTO dtoResponse;
        val productAuthor = service.findById(id);
        if (productAuthor == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            dtoResponse = convertToDto(productAuthor);
        return new ResponseEntity<>(dtoResponse, OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@Valid @RequestBody ProductAuthorDTO productAuthorDTO) {
        return null;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@Valid @RequestBody ProductAuthorDTO productAuthorDTO) {
      return null;
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        val ProductAuthor = service.findById(id);
        if (ProductAuthor == null)
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        else
            service.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping(value="/pageableProductAuthor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProductAuthorDTO>> listPageable(Pageable pageable) {
        val productAuthorDTO = service.listPageable(pageable).map(this::convertToDto);
        return new ResponseEntity<>(productAuthorDTO, OK);
    }

    private ProductAuthorDTO convertToDto(ProductAuthor obj){
        return mapper.map(obj, ProductAuthorDTO.class);
    }

    private ProductAuthor convertToEntity(ProductAuthorDTO dto){
        return mapper.map(dto, ProductAuthor.class);
    }
}