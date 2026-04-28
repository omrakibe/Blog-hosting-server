package in.slack.service;


import in.slack.model.Blog;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService
{
    public List<Blog> getAllProducts();

    public Blog getProductById(Long id);

    Blog addProduct(Blog product, MultipartFile imageFile);

    Blog updateProduct(Blog prod, MultipartFile imageFile);

    void deleteProduct(Long prod);

    List<Blog> searchProd(String keyword);
}
