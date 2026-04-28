package in.slack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String author;
    private String category;
    private LocalDate releaseDate;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;

    public Blog(Long id)
    {
        this.id = id;
    }
}
