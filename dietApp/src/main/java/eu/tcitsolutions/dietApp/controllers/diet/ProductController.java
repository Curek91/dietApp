package eu.tcitsolutions.dietApp.controllers.diet;

import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.diet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
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

@CrossOrigin(origins = "${cors.host}")
@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value = "/products", params = {"!sort", "!page", "!size"})
    public
    ResponseEntity<List<ProductDTO>> productsList(){
        List<ProductDTO> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<PagedResources<ProductDTO>> clientsList(Pageable page, PagedResourcesAssembler assembler) {
        return ResponseEntity.ok(assembler.toResource(productService.getProducts(page)));
    }

    @GetMapping(value = "/products/{id}")
    ResponseEntity<ProductDTO> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @DeleteMapping( value = "/products/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        productService.removeProduct(id);
        return ResponseEntity.ok("Product id: " + id + " Deleted with success");
    }

    @PostMapping(value="/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    ResponseEntity<Product> createProduct(@RequestBody ProductDTO source){
        Product product = productService.saveProduct(source);
        return ResponseEntity.ok(product);
    }

    @PutMapping(value = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO source){
        return ResponseEntity.ok(productService.updateProduct(id, source));
    }

    @PatchMapping(value = "/products/{id}")
    public ResponseEntity<Product> updateProductByPatch(@PathVariable("id") Long id, @RequestBody ProductDTO source) {
        return ResponseEntity.ok(productService.updateProduct(id, source));
    }

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

    @RequestMapping(method = RequestMethod.GET, value = "/getImage/{id}")
    public @ResponseBody
    ResponseEntity<Resource> getImage(@PathVariable Long id, HttpServletRequest request){
        Resource resource = productService.getImage(id);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
