package com.example.demo.rest;



import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserResource {

    @Autowired
    UserService userService;

    @PostMapping("/AddUser") @ResponseBody
    @PostAuthorize("hasAuthority('Admin')")
    public User save(@RequestBody @Valid User user) {
        return userService.save(user);
    }

    @GetMapping("/AllUsers")
    @PostAuthorize("hasAuthority('Admin')")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }



}
