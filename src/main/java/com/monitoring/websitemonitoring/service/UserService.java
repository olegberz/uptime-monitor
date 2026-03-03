package com.monitoring.websitemonitoring.service;

import com.monitoring.websitemonitoring.ExceptionHandler.AlreadyExistsException;
import com.monitoring.websitemonitoring.entity.User;
import com.monitoring.websitemonitoring.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("This user with ID: " + id + " does not exist"));
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    @Transactional
    public User createUser(User user) {
        if (userRepo.existsByEmail(user.getEmail()))
            throw new AlreadyExistsException("User with that email already exists");

        if (userRepo.existsByTelegramId(user.getTelegramId()))
            throw new AlreadyExistsException("User with that Telegram ID already exists");
        return userRepo.save(user);
    }

    @Transactional
    public User updateUser(Long id, User user) {
        User existingUser = findUserById(id);

        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
            if (userRepo.existsByEmail(user.getEmail())) {
                throw new AlreadyExistsException("User with this email already exists");
            }
            existingUser.setEmail(user.getEmail());
        }

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }

        if (user.getTelegramId() != null && !user.getTelegramId().equals(existingUser.getTelegramId())){
            if (userRepo.existsByTelegramId(user.getTelegramId())) {
                throw new AlreadyExistsException("User with this Telegram ID already exists");
            }
            existingUser.setTelegramId(user.getTelegramId());
        }
        return existingUser;
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new EntityNotFoundException("User with id: " + id + " does not exist");
        }

        userRepo.deleteById(id);
    }
}