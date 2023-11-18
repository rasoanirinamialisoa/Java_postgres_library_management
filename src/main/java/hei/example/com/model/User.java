package hei.example.com.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {
    private long id;
    private String name;
    private String password;
}
