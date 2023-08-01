package com.dev.authspringsecurity.role;

import java.util.List;

public interface RoleService {
    Role save(Role Role);
    boolean delete(Role role);
	List<Role> findAll();
}
