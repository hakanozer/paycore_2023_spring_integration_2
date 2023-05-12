package com.works.services;

import com.works.entites.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

    public Mono<ResponseEntity<Product>> save(Product product ) {
        try {
            System.out.println("Save Call");
            TimeUnit.MILLISECONDS.sleep(3000);
            productRepository.save(product);
            ResponseEntity responseEntity = new ResponseEntity(product, HttpStatus.OK);
            return Mono.just(responseEntity);
        }catch (Exception ex) {
            ResponseEntity responseEntity = new ResponseEntity(product, HttpStatus.BAD_REQUEST);
            return Mono.just(responseEntity);
        }
    }

    public Mono<ServerResponse> singleSave( Product product ) {
        productRepository.save(product);
        if ( product.getPid() != null && product.getPid() > 0 ) {
            System.out.println("asdasd");
            return ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("token", "asdas2e23resgdf")
                    .body(product, Product.class);
        }
        return ServerResponse.badRequest().body(product, Product.class);
    }

    public Flux<List<Product>> allProduct() {
        Flux<List<Product>> listFlux = Flux.fromStream(Stream.generate( () -> productRepository.findAll() ));
        Flux<Long> duratin = Flux.interval(Duration.ofSeconds(3));
        return Flux.zip(listFlux, duratin).map(Tuple2::getT1);
    }

    public List<Product> allListProduct() {
        return productRepository.findAll();
    }

}
