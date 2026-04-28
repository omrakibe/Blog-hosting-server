package in.slack.repository;

import in.slack.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Blog, Long>
{

    @Query("SELECT b FROM Blog b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%') ) OR " +
            "LOWER(b.content) LIKE LOWER(concat('%', :keyword, '%') ) OR " +
            "LOWER(b.author) LIKE LOWER(concat('%', :keyword, '%') ) OR " +
            "LOWER(b.category) LIKE LOWER(concat('%', :keyword, '%') )"
    )
    List<Blog> searchProducts(String keyword);
}
