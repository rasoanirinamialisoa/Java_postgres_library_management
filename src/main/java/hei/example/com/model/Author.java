package hei.example.com.model;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {
    private String authorId;
    private String authorName;
    private Sex sex;
}
