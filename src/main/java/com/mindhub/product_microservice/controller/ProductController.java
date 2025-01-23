package com.mindhub.product_microservice.controller;

import com.mindhub.product_microservice.dtos.get.GetProductDTO;
import com.mindhub.product_microservice.dtos.post.PostProductDTO;
import com.mindhub.product_microservice.dtos.update.UpdateProductDTO;
import com.mindhub.product_microservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<GetProductDTO>> getAll() {
        List<GetProductDTO> productDTO = productService.getAll()
                .stream()
                .map(GetProductDTO::new)
                .toList();
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductDTO> getOneDto(@PathVariable Long id) {
        GetProductDTO productDTO = productService.getDTOById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @PutMapping("/stock/{id}")
    public ResponseEntity<?> patchStock(@PathVariable Long id, @RequestParam(required = true) Integer quantity)  {
        productService.patchStock(id, quantity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/")
    public ResponseEntity<GetProductDTO> create(@RequestBody PostProductDTO product){
        if (product.name() == null || product.description() == null || product.price() == null || product.stock() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetProductDTO> update(@RequestBody UpdateProductDTO product, @PathVariable long id){
        if (product.name() == null || product.description() == null || product.price() == null || product.stock() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productService.update(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
