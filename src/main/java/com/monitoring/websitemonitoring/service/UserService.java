package com.monitoring.websitemonitoring.service;

import com.monitoring.websitemonitoring.DTO.UserDTO;
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
    public User createUser(UserDTO userDTO) {
        if (userRepo.existsByEmail(userDTO.getEmail()))
            throw new AlreadyExistsException("User with that email already exists");

        if (userRepo.existsByTelegramId(userDTO.getTelegramId()))
            throw new AlreadyExistsException("User with that Telegram ID already exists");

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(user.getEmail());
        user.setTelegramId(userDTO.getTelegramId());

        return userRepo.save(user);
    }

    @Transactional
    public User updateUser(Long id, UserDTO userDTO) {
        User existingUser = findUserById(id);

        if (userDTO.getEmail() != null && !userDTO.getEmail().equals(existingUser.getEmail())) {
            if (userRepo.existsByEmail(userDTO.getEmail())) {
                throw new AlreadyExistsException("User with this email already exists");
            }
            existingUser.setEmail(userDTO.getEmail());
        }

        if (userDTO.getName() != null) {
            existingUser.setName(userDTO.getName());
        }

        if (userDTO.getTelegramId() != null && !userDTO.getTelegramId().equals(existingUser.getTelegramId())){
            if (userRepo.existsByTelegramId(userDTO.getTelegramId())) {
                throw new AlreadyExistsException("User with this Telegram ID already exists");
            }
            existingUser.setTelegramId(userDTO.getTelegramId());
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