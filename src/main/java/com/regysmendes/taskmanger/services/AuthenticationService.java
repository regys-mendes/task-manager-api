package com.regysmendes.taskmanger.services;

import com.regysmendes.taskmanger.config.TokenProvider;
import com.regysmendes.taskmanger.dto.LoginUserDTO;
import com.regysmendes.taskmanger.dto.RegisterUserDTO;
import com.regysmendes.taskmanger.dto.TokenResponseDTO;
import com.regysmendes.taskmanger.entities.RolesEntity;
import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.entities.User;
import com.regysmendes.taskmanger.enums.RoleTypeEnum;
import com.regysmendes.taskmanger.repository.RolesRepository;
import com.regysmendes.taskmanger.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    private  final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    @Value("${jwt.expiration}")
    private Long expirationTime;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository userRepository, RolesRepository rolesRepository,  AuthenticationManager authenticationManager, TokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterUserDTO dto) throws BadRequestException {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if (user != null){
            throw new BadRequestException("User already registred with this email!");
        }

        RolesEntity role = rolesRepository.findByName(RoleTypeEnum.ROLE_USER.name())
                .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                        .name(RoleTypeEnum.ROLE_USER.name())
                        .build()));

        userRepository.save(User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Set.of(role))
                .build());
    }

    public TokenResponseDTO login(LoginUserDTO dto) throws BadRequestException{
         try {
         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
         String token = tokenProvider.generateToken(authentication);
         return new  TokenResponseDTO(token, expirationTime);
         }
         catch (BadCredentialsException e ){
           throw new BadRequestException("Invalid Credentials!");
         }
         catch (Exception e) {
             throw e;
         }
    }

}
