package ua.lviv.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.domain.Logger;

public interface LoggerRepository extends JpaRepository<Logger, Integer> {
}
