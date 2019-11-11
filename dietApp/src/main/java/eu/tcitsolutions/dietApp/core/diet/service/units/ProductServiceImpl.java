package eu.tcitsolutions.dietApp.core.diet.service.units;

import eu.tcitsolutions.dietApp.utils.Utils;
import eu.tcitsolutions.dietApp.core.diet.domain.dto.ProductDTO;
import eu.tcitsolutions.dietApp.core.diet.domain.entity.Product;
import eu.tcitsolutions.dietApp.core.diet.domain.repository.ProductRepository;
import eu.tcitsolutions.dietApp.core.diet.service.DTOMappingService;
import eu.tcitsolutions.dietApp.core.diet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private DTOMappingService dtoMappingService;

    public ProductServiceImpl(ProductRepository productRepository, DTOMappingService dtoMappingService){
        this.productRepository = productRepository;
        this.dtoMappingService = dtoMappingService;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.getProduct(id);
    }

    @Override
    public Product saveProduct(ProductDTO source) {
       return productRepository.save(dtoMappingService.createEntity(source));
    }

    @Override
    public void removeProduct(Long id) {
        productRepository.delete(id);
    }

    @Override
    public void updateProduct(Long id, ProductDTO source) {
        productRepository.update(dtoMappingService.createEntity(id, source));
    }

    @Override
    public void store(MultipartFile file, Long id) {
        String filename = id.toString() + "." + Utils.getExtensionByStringHandling(file.getOriginalFilename()).get();
        Path rootLocation = Paths.get("upload-dir");
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Nie można zapisać pustego pliku: " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new RuntimeException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Resource getImage(Long id) {
        Path rootLocation = Paths.get("upload-dir");
        String filename = id.toString() + ".jpg";
        try {
            Path filePath = rootLocation.resolve(id + ".jpg");
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + filename, ex);
        }
    }

    @Override
    public void init() {
        Path rootLocation = Paths.get("upload-dir");
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }
}
