package hei.example.com.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
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
