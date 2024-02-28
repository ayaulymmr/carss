package kz.aitu.cars.exceptions;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException() {
        super("Car not found");
    }

    public CarNotFoundException(Long carId) {
        super("Car with ID " + carId + " not found");
    }
}


