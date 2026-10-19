package com.regysmendes.taskmanger.services;

import com.regysmendes.taskmanger.repository.TaskRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

      private final TaskRepository taskRepository;

    public UserDetailsServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) taskRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
