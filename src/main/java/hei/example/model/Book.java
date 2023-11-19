package hei.example.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private String idBook;
    private String bookName;
    private int pageNumbers;
    private Date releaseDate;
    private int authorId;
    private int topicId;
    private BookStatus bookStatus;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public Integer getBookId() {
        if (idBook == null) {
            return null;
        }
        return Integer.parseInt(idBook);
    }

    public void setBookId(int bookId) {
    }

    public void setAuthorId(String authorId) {
    }

    public void setTopicId(String topicId) {
    }


}



