package com.kodhnk.base.services.interfaces;

import com.kodhnk.base.core.utilities.DataResult;
import com.kodhnk.base.dto.roles.request.CreateRoleRequest;
import com.kodhnk.base.dto.roles.request.UpdateRoleRequest;
import com.kodhnk.base.entities.Role;

import java.util.List;

public interface IRoleService {
    DataResult<List<Role>> getAllRoles();

    DataResult<Role> getRoleById(Long id);

    DataResult<Role> createRole(CreateRoleRequest request);

    DataResult<Role> updateRole(UpdateRoleRequest request);

    DataResult<Role> deleteRole(Long id);

    DataResult<Role> getRoleByName(String name);
}
