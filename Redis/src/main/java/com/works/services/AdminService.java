package com.works.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    final AdminRepository adminRepository;
    final HttpServletRequest httpServletRequest;
    final ObjectMapper objectMapper;

    public Admin save( Admin admin ) {
        return adminRepository.save(admin);
    }

    public boolean login( Admin admin ) {
        Optional<Admin> optionalAdmin = adminRepository.findByEmailEqualsAndPasswordEquals(admin.getEmail(), admin.getPassword());
        boolean status = optionalAdmin.isPresent();
        if ( status ) {
            String adminString = "";
            try {
                adminString = objectMapper.writeValueAsString(optionalAdmin.get());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            httpServletRequest.getSession().setAttribute("email", adminString);
        }
        return status;
    }

    public boolean logout() {
        httpServletRequest.getSession().removeAttribute("email");
        return true;
    }



}
