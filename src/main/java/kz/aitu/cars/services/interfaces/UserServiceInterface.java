package kz.aitu.cars.services.interfaces;

import kz.aitu.cars.models.User;

import java.util.List;

public interface UserServiceInterface {
    User create(User user);
    List<User> getAll();
    User getById(Long id);

    void update(User user);
}
