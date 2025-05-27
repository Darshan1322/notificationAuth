package service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.User;
import repo.UserRepository;

@Service
public class UserService {

    @Autowired private UserRepository repo;
    public Optional<User> authenticate(String username, String password) {
        return repo.findByUsername(username)
                   .filter(u -> u.getPassword().equals(password));
    }
}
