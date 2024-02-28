package kz.aitu.cars.services.interfaces;

import kz.aitu.cars.models.Car;

import java.util.List;

public interface CarServiceInterface {
    Car create(Car car);
    List<Car> getAll();
    Car getById(Long id);
    List<Car> getCarsByUserId(Long userId);

    void purchase(Long userId, Long carId) throws Exception;
    void sell(Long userId, Long carId) throws Exception;
}
