package kz.aitu.cars.services;

import kz.aitu.cars.models.User;
import kz.aitu.cars.repositories.UserRepositoryInterface;
import kz.aitu.cars.services.interfaces.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepositoryInterface repo;

    public UserService(UserRepositoryInterface repo) {
        this.repo = repo;
    }

    @Override
    public User create(User user)  {
        return repo.save(user);
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public User getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void update(User user) {
        repo.save(user);
    }
}
