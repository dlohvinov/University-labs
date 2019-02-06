package ua.lviv.iot.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "history", schema = "library")
public class Logger {

    private Integer id;
    private Integer reader;
    private Integer book;


    @Id
    @Column(name="rel_id", nullable = false)
    @GenericGenerator(name="auto" , strategy="increment")
    @GeneratedValue(generator = "auto")
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }


    @Column(name="reader", nullable = false, length = 50)
    public Integer getReader() {
        return reader;
    }
    public void setReader(Integer reader) {
        this.reader = reader;
    }

    @Column(name="book", nullable = false)
    public Integer getBook() {
        return book;
    }
    public void setBook(Integer book) {
        this.book = book;
    }
}
