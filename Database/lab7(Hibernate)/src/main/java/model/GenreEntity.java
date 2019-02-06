package model;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "genre", schema = "Library")
public class GenreEntity {
    @Id
    @Column(name = "genre", nullable = false, length = 20)
    private String genre;

    @OneToMany(mappedBy = "genre")
    private Collection<BookEntity> bookByGenre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Collection<BookEntity> getBookByGenre() {
        return bookByGenre;
    }

    public void setBookByGenre(Collection<BookEntity> bookByGenre) {
        this.bookByGenre = bookByGenre;
    }


}
