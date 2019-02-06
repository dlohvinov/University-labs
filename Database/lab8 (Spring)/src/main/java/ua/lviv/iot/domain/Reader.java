package ua.lviv.iot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="reader", schema = "Library")
public class Reader {
    @Id
    @Column(name="id", nullable = false)
    private Integer readerId;

    @Column(name = "surname", nullable = false, length = 20)
    private String readerSurname;

    @Column(name = "name", length = 20)
    private String readerName;

    @Column(name = "address", nullable = false, length = 50)
    private String readerAddress;

    @ManyToMany(mappedBy = "readers")
    private List<Book> books;


    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getReaderSurname() {
        return readerSurname;
    }

    public void setReaderSurname(String readerSurname) {
        this.readerSurname = readerSurname;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderAddress() {
        return readerAddress;
    }

    public void setReaderAddress(String readerAddress) {
        this.readerAddress = readerAddress;
    }

    @JsonIgnore
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}