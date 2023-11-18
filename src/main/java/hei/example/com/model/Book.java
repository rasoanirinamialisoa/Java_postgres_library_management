package hei.example.com.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private String idBook;
    private String bookName;
    private int pageNumbers;
    private String releaseDate;
    private Author author;
    private Topic topic;
    private BookStatus bookStatus;
}



