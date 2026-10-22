package com.regysmendes.taskmanger.resource;

import com.regysmendes.taskmanger.dto.LoginUserDTO;
import com.regysmendes.taskmanger.dto.RegisterUserDTO;
import com.regysmendes.taskmanger.dto.TokenResponseDTO;
import com.regysmendes.taskmanger.services.AuthenticationService;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    private final AuthenticationService service;

    public AuthResource(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping(value = "/register")
    public void register(@RequestBody RegisterUserDTO userDTO) throws BadRequestException {
        service.register(userDTO);
    }

    @PostMapping(value = "/login")
    public TokenResponseDTO login(@RequestBody LoginUserDTO dto) throws BadRequestException{
       return service.login(dto);
    }

}
