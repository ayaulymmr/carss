package kz.aitu.cars.controllers;

import kz.aitu.cars.exceptions.CarNotFoundException;
import kz.aitu.cars.exceptions.InsufficientFundsException;
import kz.aitu.cars.exceptions.UserNotFoundException;
import kz.aitu.cars.models.Car;
import kz.aitu.cars.services.interfaces.CarServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {
    private final CarServiceInterface service;

    public CarController(CarServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Car> getAll() {
        return service.getAll();
    }

    @GetMapping("/{car_id}")
    public ResponseEntity<Car> getById(@PathVariable("car_id") Long id) {
        Car car = service.getById(id);
        if (car == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Car> create(@RequestBody Car car) {
        Car createdCar = service.create(car);
        if (createdCar == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
    }

    @GetMapping("/user/{user_id}")
    public List<Car> getByUserId(@PathVariable("user_id") Long id) {
        return service.getCarsByUserId(id);
    }

    @PostMapping("/buy/{car_id}/user/{user_id}")
    public ResponseEntity<String> buyCar(@PathVariable("car_id") Long carId, @PathVariable("user_id") Long userId) {
        try {
            service.purchase(userId, carId);
            return new ResponseEntity<>("Car purchased successfully!", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
        } catch (InsufficientFundsException e) {
            return new ResponseEntity<>("Insufficient funds", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sell/{car_id}/user/{user_id}")
    public ResponseEntity<String> sellCar(@PathVariable("car_id") Long carId, @PathVariable("user_id") Long userId) {
        try {
            service.sell(userId, carId);
            return new ResponseEntity<>("Car sold successfully!", HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (CarNotFoundException e) {
            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
