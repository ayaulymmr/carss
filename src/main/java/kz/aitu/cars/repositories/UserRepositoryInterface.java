package kz.aitu.cars.repositories;

import kz.aitu.cars.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryInterface extends JpaRepository<User, Long> {
}
