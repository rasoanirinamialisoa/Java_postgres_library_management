package hei.example.model;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Author {
    private long authorId;
    private String authorName;
    private Sex sex;
}
