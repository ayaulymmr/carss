package kz.aitu.cars.exceptions;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {
        super("User does not have enough money to purchase the car");
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
