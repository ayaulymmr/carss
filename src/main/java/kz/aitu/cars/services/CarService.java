package kz.aitu.cars.services;

import kz.aitu.cars.exceptions.CarNotFoundException;
import kz.aitu.cars.exceptions.InsufficientFundsException;
import kz.aitu.cars.exceptions.UserNotFoundException;
import kz.aitu.cars.models.Car;
import kz.aitu.cars.models.User;
import kz.aitu.cars.repositories.CarRepositoryInterface;
import kz.aitu.cars.services.interfaces.CarServiceInterface;
import kz.aitu.cars.services.interfaces.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CarService implements CarServiceInterface {
    private final CarRepositoryInterface repo;
    private final UserServiceInterface userService;

    public CarService(CarRepositoryInterface repo, UserServiceInterface userService) {
        this.repo = repo;
        this.userService = userService;
    }

    @Override
    public Car create(Car car) {
        return repo.save(car);
    }

    @Override
    public List<Car> getAll() {
        return repo.findAll();
    }

    @Override
    public Car getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Car> getCarsByUserId(Long userId) {
        User user = userService.getById(userId);
        if (user == null)
            return Collections.emptyList();
        return repo.findByUser(user);
    }

    private User checkUserById(Long userId) throws UserNotFoundException {
        User user = userService.getById(userId);
        if (user == null)
            throw new UserNotFoundException(userId);
        return user;
    }

    private Car checkCarById(Long carId) throws CarNotFoundException {
        Car car = repo.findById(carId).orElse(null);
        if (car == null)
            throw new CarNotFoundException(carId);
        return car;
    }

    @Override
    public void purchase(Long userId, Long carId) throws InsufficientFundsException {
        User user = this.checkUserById(userId);
        Car car = this.checkCarById(carId);
        if (user.getWealth() < car.getPrice())
            throw new InsufficientFundsException();
        user.addCar(car);
        user.setWealth(user.getWealth() - car.getPrice());
        userService.update(user);

    }

    @Override
    public void sell(Long userId, Long carId) {
        User user = this.checkUserById(userId);
        Car car = this.checkCarById(carId);
        user.removeCar(car);
        user.setWealth(user.getWealth() + car.getPrice());
        userService.update(user);
    }
}
