package ua.lviv.iot.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "genre", schema = "Library")
public class Genre {
    @Id
    @Column(name = "genre", nullable = false, length = 20)
    private String genre;

    @OneToMany(mappedBy = "genre")
    private Collection<Book> bookByGenre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Collection<Book> getBookByGenre() {
        return bookByGenre;
    }

    public void setBookByGenre(Collection<Book> bookByGenre) {
        this.bookByGenre = bookByGenre;
    }


}