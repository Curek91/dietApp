package eu.tcitsolutions.dietApp.core.diet.service.units;

import eu.tcitsolutions.dietApp.core.client.domain.dto.ClientDTO;
import eu.tcitsolutions.dietApp.core.client.domain.entity.Client;
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

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product saveProduct(Product source) {
       return productRepository.save(source);
    }

    @Override
    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Long id, Product source) {
        source.setId(id);
        productRepository.save(source);
    }

    @Override
    public void store(MultipartFile file, Long id) {
        String filename = id.toString() + "." + Utils.getExtensionByStringHandling(file.getOriginalFilename()).get();
        Path rootLocation = Paths.get("upload-dir");
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Cannot store empty file: " + filename);
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
