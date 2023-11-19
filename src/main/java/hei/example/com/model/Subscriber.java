package hei.example.com.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Subscriber extends User {

    private long subscriberId;
    private String subscriptionStartDate;
    private String subscriptionEndDate;
    public Subscriber(Long id, String username, String password) {
        super(id, username, password);
    }
}

