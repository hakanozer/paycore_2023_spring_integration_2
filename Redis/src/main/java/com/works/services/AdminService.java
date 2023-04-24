package com.works.services;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    final AdminRepository adminRepository;

    public Admin save( Admin admin ) {
        return adminRepository.save(admin);
    }

}
