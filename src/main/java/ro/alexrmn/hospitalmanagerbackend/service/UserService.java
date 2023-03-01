package ro.alexrmn.hospitalmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.User;
import ro.alexrmn.hospitalmanagerbackend.model.dto.CreateUserDto;
import ro.alexrmn.hospitalmanagerbackend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User createUser(CreateUserDto createUserDto){
        User user = User.builder()
                .email(createUserDto.getEmail())
                .username((createUserDto.getUsername()))
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .build();

        user.setRoles("ROLE_USER");
        return userRepository.save(user);
    }
}
