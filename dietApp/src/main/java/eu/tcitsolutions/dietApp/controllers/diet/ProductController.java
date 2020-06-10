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

import javax.annotation.security.RolesAllowed;
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

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "/products", params = {"!sort", "!page", "!size"})
    public
    ResponseEntity<List<ProductDTO>> productsList(){
        List<ProductDTO> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "/products")
    public ResponseEntity<PagedResources<ProductDTO>> clientsList(Pageable page, PagedResourcesAssembler assembler) {
        return ResponseEntity.ok(assembler.toResource(productService.getProducts(page)));
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "/products/{id}")
    ResponseEntity<ProductDTO> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @GetMapping(value = "products/byName/{name}")
    ResponseEntity<ProductDTO> getProductByName(@PathVariable String name){
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @DeleteMapping( value = "/products/{id}")
    public @ResponseBody
    ResponseEntity<String> deleteProduct(@PathVariable("id") Long id){
        productService.removeProduct(id);
        return ResponseEntity.ok("Product id: " + id + " Deleted with success");
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @PostMapping(value="/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public
    ResponseEntity<Product> createProduct(@RequestBody ProductDTO source){
        Product product = productService.saveProduct(source);
        return ResponseEntity.ok(product);
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @PutMapping(value = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO source){
        return ResponseEntity.ok(productService.updateProduct(id, source));
    }

    @RolesAllowed({"ROLE_TRAINER"})
    @PatchMapping(value = "/products/{id}")
    public ResponseEntity<Product> updateProductByPatch(@PathVariable("id") Long id, @RequestBody ProductDTO source) {
        return ResponseEntity.ok(productService.updateProduct(id, source));
    }

    @RolesAllowed({"ROLE_TRAINER"})
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

    @RolesAllowed({"ROLE_TRAINER"})
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
