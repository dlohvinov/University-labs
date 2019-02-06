package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {
}
