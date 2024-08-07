package com.kodhnk.base.controllers;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.dto.roles.request.CreateRoleRequest;
import com.kodhnk.base.dto.roles.request.UpdateRoleRequest;
import com.kodhnk.base.entities.Role;
import com.kodhnk.base.services.interfaces.IRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/getAllRoles")
    ResponseEntity<DataResult<List<Role>>> getAllRoles() {
        DataResult<List<Role>> result = roleService.getAllRoles();
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/getRoleById")
    ResponseEntity<DataResult<Role>> getRoleById(@RequestParam Long id) {
        DataResult<Role> result = roleService.getRoleById(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @GetMapping("/getRoleByName")
    ResponseEntity<DataResult<Role>> getRoleByName(@RequestParam String name) {
        DataResult<Role> result = roleService.getRoleByName(name);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PostMapping("/createRole")
    ResponseEntity<DataResult<Role>> createRole(@RequestBody CreateRoleRequest request) {
        DataResult<Role> result = roleService.createRole(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("/updateRole")
    ResponseEntity<DataResult<Role>> updateRole(@RequestBody UpdateRoleRequest request) {
        DataResult<Role> result = roleService.updateRole(request);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @DeleteMapping("/deleteRole")
    ResponseEntity<DataResult<Role>> deleteRole(@RequestParam Long id) {
        DataResult<Role> result = roleService.deleteRole(id);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}