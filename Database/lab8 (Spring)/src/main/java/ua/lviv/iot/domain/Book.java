package ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="book", schema = "Library")
public class Book {
    @Id
    @Column (name = "id", nullable = false)
    private Integer bookId;

    @Basic
    @Column(name = "title", nullable = false, length = 100)
    private String bookTitle;

    @Basic
    @Column(name = "author", nullable = true, length = 100)
    private String bookAuthor;

    @ManyToOne
    @JoinColumn(name = "genre", referencedColumnName = "genre", nullable = false)
    private Genre genre;

    @Basic
    @Column(name="publisher", nullable = true, length = 50)
    private String bookPublisher;

    @Basic
    @Column(name = "availability", nullable = false)
    private Boolean bookAvailability;

    @ManyToMany
    @JoinTable(name = "history", schema = "Library",
            joinColumns = @JoinColumn(name = "book", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "reader", referencedColumnName = "id", nullable = false))
    private List<Reader> readers;


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public Boolean getBookAvailability() {
        return bookAvailability;
    }

    public void setBookAvailability(Boolean bookAvailability) {
        this.bookAvailability = bookAvailability;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }
}