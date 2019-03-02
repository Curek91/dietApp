package eu.tcitsolutions.dietApp.controllers;

import eu.tcitsolutions.dietApp.core.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.GET, value = "/products")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<List<Product>> productsList(){
        List<Product> productList = productService.getProducts();
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.GET, value = "/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = productService.getProduct(id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.POST, value="/product/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Product> createProduct(@RequestBody ProductDTO source){
        productService.saveProduct(source);
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.PUT, value = "/product/modify/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO source){
        productService.updateProduct(id, source);
        return new ResponseEntity<Product>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.DELETE, value = "/product/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        productService.removeProduct(id);
        return new ResponseEntity<Product>(HttpStatus.OK);
    }
}
