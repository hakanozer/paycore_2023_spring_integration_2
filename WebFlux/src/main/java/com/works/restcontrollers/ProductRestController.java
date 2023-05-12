package com.works.restcontrollers;

import com.works.entites.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    final ProductService productService;

    @PostMapping("/save")
    public Mono<ResponseEntity<Product>> save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<Product>> allProduct() {
        return productService.allProduct();
    }

    @GetMapping(value = "/allList", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public List<Product> allList() {
        return productService.allListProduct();
    }
}
