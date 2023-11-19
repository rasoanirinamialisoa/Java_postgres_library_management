package hei.example.model;

import lombok.*;

import java.sql.Date;


@AllArgsConstructor
@Getter
@Setter
@ToString
public class Subscribers extends User {
    private Date subscriptionStartDate;
    private Date subscriptionEndDate;
    public Subscribers(Long id, String username, String password,Date subscriptionStartDate, Date subscriptionEndDate) {
        super(id, username, password);
        this.subscriptionStartDate=subscriptionStartDate;
        this.subscriptionEndDate=subscriptionEndDate;
    }

    public Subscribers() {

    }

}

