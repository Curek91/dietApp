package eu.tcitsolutions.dietApp.controllers.diet;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.diet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

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
        Product product = productService.saveProduct(source);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
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

    @CrossOrigin(origins = "${cors.host}")
    @PostMapping("/addImage/{id}")
    HashMap<String, String> handleFileUpload(@PathVariable("id") Long id, @RequestParam("file0") MultipartFile file){
        System.out.println("Podaj ID: " + id);
        productService.store(file, id);

        HashMap<String, String> result;
        result = new HashMap<String, String>();
        result.put("id", id.toString());
        result.put("status", "success");
        result.put("file", file.getOriginalFilename());

        return result;
    }

    @CrossOrigin(origins = "${cors.host}")
    @RequestMapping(method = RequestMethod.GET, value = "/getImage/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    ResponseEntity<Resource> getImage(@PathVariable Long id, HttpServletRequest request){
        Resource resource = productService.getImage(id);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }
        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
