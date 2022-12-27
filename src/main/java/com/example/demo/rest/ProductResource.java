package com.example.demo.rest;



import com.example.demo.domain.Order;
import com.example.demo.domain.Product;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductResource {

    @Autowired
    ProductService productService;
    //@PreAuthorize("hasRole('Admin')")
    @PostMapping("/") @ResponseBody
    public Product save(@RequestBody @Valid Product product) {
        return productService.save(product);
    }

    @PostMapping("/update_Prod") @ResponseBody
    public  Product update(@RequestBody @Valid Product product){
        return productService.update(product);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable UUID uuid){
        return productService.delete(uuid);
    }

    @GetMapping("/AllProducts")
    @PostAuthorize("hasAnyAuthority('Admin','Fournisseur')")
    public List<Product> getAllProducts(){
        return productService.findAllProducts();
    }

}
