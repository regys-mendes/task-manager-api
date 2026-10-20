package com.regysmendes.taskmanger.services;

import com.regysmendes.taskmanger.dto.TaskUserDTO;
import com.regysmendes.taskmanger.entities.RolesEntity;
import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.entities.User;
import com.regysmendes.taskmanger.enums.RoleTypeEnum;
import com.regysmendes.taskmanger.repository.RolesRepository;
import com.regysmendes.taskmanger.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final RoleTypeEnum roleTypeEnum;

    private PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, RolesEntity roles, RolesRepository rolesRepository, RoleTypeEnum roleTypeEnum) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.roleTypeEnum = roleTypeEnum;
    }

    public void register(TaskUserDTO dto) throws BadRequestException {
        Task task = userRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if (task != null){
            throw new BadRequestException("User already registred with this email!");
        }

        RolesEntity role = rolesRepository.findByName(roleTypeEnum.USER.name())
                .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                        .name(RoleTypeEnum.USER.name())
                        .build()));

        userRepository.save(User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Set.of(role))
                .build());
    }
}
