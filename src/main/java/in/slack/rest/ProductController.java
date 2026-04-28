package in.slack.rest;

import in.slack.model.Blog;
import in.slack.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController
{
    private IProductService service;

    @Autowired
    public ProductController(IProductService service)
    {
        this.service = service;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Blog>> getAllProducts()
    {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Blog> getProduct(@PathVariable Long id)
    {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Blog>> search(@RequestParam String keyword)
    {
        List<Blog> blog = service.searchProd(keyword);
        return ResponseEntity.ok(blog);
    }

    @PostMapping("/product")
    public ResponseEntity<Blog> addProduct(@RequestPart Blog product, @RequestPart MultipartFile imageFile)
    {
//        System.out.println(imageFile);
        System.out.println(product);
        Blog prod = service.addProduct(product, imageFile);
        return ResponseEntity.status(201).body(prod);
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long productId)
    {
        Blog prod = service.getProductById(productId);
        return ResponseEntity.ok(prod.getImageData());
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Blog> updateProduct(@PathVariable Long productId, @RequestPart Blog product, @RequestPart MultipartFile imageFile)
    {
        return ResponseEntity.ok(service.updateProduct(product, imageFile));
    }

    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long prodId)
    {
        Blog prod = service.getProductById(prodId);
        if (prod != null)
        {
            service.deleteProduct(prodId);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.status(404).body("Product not Found!!");
    }
}
