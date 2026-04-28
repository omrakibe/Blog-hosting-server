package in.slack.service;

import in.slack.exception.ProductNotFoundException;
import in.slack.model.Blog;
import in.slack.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService
{
    private IProductRepository repo;

    @Autowired
    public ProductService(IProductRepository repo)
    {
        this.repo = repo;
    }

    @Override
    public List<Blog> getAllProducts()
    {
        return repo.findAll();
    }

    @Override
    public Blog getProductById(Long id)
    {
        Optional<Blog> opt = repo.findById(id);
        if (opt.isPresent())
        {
            return opt.get();
        }
        throw new ProductNotFoundException("Product Not Found with given ID");
    }

    @Override
    public Blog addProduct(Blog product, MultipartFile imageFile)
    {
//        Optional<Product> opt = repo.findById(product.getId());
//        if (opt.isPresent())
//        {
//            throw new ProductAlreadyExistsException("Product is already present with this product id");
//        }
        try
        {
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(imageFile.getBytes());
//            System.out.println(product.getImageData().getClass());
            return repo.save(product);
        } catch (IOException e)
        {
            throw new RuntimeException("Failed to process image");
        }

    }

    @Override
    public Blog updateProduct(Blog product, MultipartFile imageFile)
    {
        Optional<Blog> opt = repo.findById(product.getId());
        if (opt.isPresent())
        {
            try
            {
                product.setImageName(imageFile.getOriginalFilename());
                product.setImageType(imageFile.getContentType());
                product.setImageData(imageFile.getBytes());
                return repo.save(product);
            } catch (IOException e)
            {
                throw new RuntimeException("Failed to process image");
            }
        }

        throw new ProductNotFoundException("Product Not Found with given ID");
    }

    @Override
    public void deleteProduct(Long prodId)
    {
        repo.deleteById(prodId);
    }

    @Override
    public List<Blog> searchProd(String keyword)
    {
        return repo.searchProducts(keyword);
    }
}
