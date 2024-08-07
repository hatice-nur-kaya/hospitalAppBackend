package com.kodhnk.base.services.concretes;

import com.kodhnk.base.core.constant.Response;
import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.core.utilities.ErrorDataResult;
import com.kodhnk.base.core.utilities.SuccessDataResult;
import com.kodhnk.base.dataAccess.RoleRepository;
import com.kodhnk.base.dto.roles.request.CreateRoleRequest;
import com.kodhnk.base.dto.roles.request.UpdateRoleRequest;
import com.kodhnk.base.entities.Role;
import com.kodhnk.base.services.interfaces.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public DataResult<List<Role>> getAllRoles() {
        if (roleRepository.findAll().isEmpty()) {
            return new SuccessDataResult<>(Response.ROLE_NOT_FOUND.getMessage(), null, 404);
        }
        return new SuccessDataResult<>(Response.ROLE_LISTED.getMessage(), roleRepository.findAll(), 200);
    }

    @Override
    public DataResult<Role> getRoleById(Long id) {
        if (roleRepository.findById(id).isEmpty()) {
            return new SuccessDataResult<>(Response.ROLE_NOT_FOUND.getMessage(), null, 404);
        }
        return new SuccessDataResult<>(Response.ROLE_FOUND.getMessage(), roleRepository.findById(id).get(), 200);
    }

    @Override
    public DataResult<Role> createRole(CreateRoleRequest request) {
        Role role = new Role();
        if (roleRepository.findByName(request.getName()).isPresent()) {
            return new ErrorDataResult<>(Response.ROLE_ALREADY_EXISTS.getMessage(), null, 400);
        }
        role.setName(request.getName().toUpperCase());
        roleRepository.save(role);
        return new SuccessDataResult<>(Response.ROLE_CREATED.getMessage(), role, 201);
    }

    @Override
    public DataResult<Role> updateRole(UpdateRoleRequest request) {
        if (roleRepository.findById(request.getId()).isEmpty()) {
            return new SuccessDataResult<>(Response.ROLE_NOT_FOUND.getMessage(), null, 404);
        }
        Role role = roleRepository.findById(request.getId()).get();
        role.setName(request.getName());
        roleRepository.save(role);
        return new SuccessDataResult<>(Response.ROLE_UPDATED.getMessage(), role, 200);
    }

    @Override
    public DataResult<Role> deleteRole(Long id) {
        if (roleRepository.findById(id).isEmpty()) {
            return new SuccessDataResult<>(Response.ROLE_NOT_FOUND.getMessage(), null, 404);
        }
        roleRepository.deleteById(id);
        return new SuccessDataResult<>(Response.ROLE_DELETED.getMessage(), null, 200);
    }

    @Override
    public DataResult<Role> getRoleByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isEmpty()) {
            return new ErrorDataResult<>(Response.ROLE_NOT_FOUND.getMessage(), null, 404);
        }
        return new SuccessDataResult<>(Response.ROLE_FOUND.getMessage(), role.get(), 200);
    }
}