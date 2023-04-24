package com.works.restcontrollers;

import com.works.entities.Admin;
import com.works.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminRestController {

    final AdminService adminService;

    @PostMapping("/register")
    public Admin register( @RequestBody Admin admin ) {
        return adminService.save(admin);
    }

    @PostMapping("/login")
    public boolean login( @RequestBody Admin admin ) {
       return adminService.login(admin);
    }

    @GetMapping("/logout")
    public boolean logout() {
        return adminService.logout();
    }


}
