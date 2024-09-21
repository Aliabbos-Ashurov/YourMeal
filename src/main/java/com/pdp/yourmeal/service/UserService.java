package com.pdp.yourmeal.service;

import com.pdp.yourmeal.dto.UserDTO;
import com.pdp.yourmeal.entity.User;
import com.pdp.yourmeal.handler.exception.UserNotFoundException;
import com.pdp.yourmeal.mapper.UserMapper;
import com.pdp.yourmeal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * @since 20/September/2024  08:45
 **/
@Service
@RequiredArgsConstructor
public class UserService implements BaseDtoService<User, Long, UserDTO> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with username: {0}", username));
        return userMapper.toUserDTO(user);
    }

    public UserDTO findByPhone(String phone) {
        User user = userRepository.findByPhone(phone).orElseThrow(() -> new UserNotFoundException("User not found with phone: {0}", phone));
        return userMapper.toUserDTO(user);
    }

    public UserDTO findByFirstAndLastName(String first, String lastName) {
        User user = userRepository.findByFirstAndLastName(first, lastName).orElseThrow(() -> new UserNotFoundException("User not found with first: {0} and last: {1}", first, lastName));
        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO save(UserDTO dto) {
        User user = userMapper.toUser(dto);
        User savingUser = userRepository.save(user);
        return userMapper.toUserDTO(savingUser);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found By Id: {0}", id));
        return userMapper.toUserDTO(user);
    }

    public User findByIdUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found By Id: {0}", id));
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserDTO)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
