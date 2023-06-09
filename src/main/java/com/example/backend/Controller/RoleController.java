package com.example.backend.Controller;


import com.example.backend.Entity.Role;
import com.example.backend.Services.IRoleService;
import com.example.backend.generic.GenericController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController extends GenericController<Role, Integer> {

    private IRoleService roleService;


}
