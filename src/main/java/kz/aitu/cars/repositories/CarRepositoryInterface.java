package kz.aitu.cars.repositories;

import kz.aitu.cars.models.Car;
import kz.aitu.cars.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepositoryInterface extends JpaRepository<Car, Long> {
    List<Car> findByUser(User user);
}
