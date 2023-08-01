package com.dev.authspringsecurity.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository repository;

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public boolean delete(Role role) {
        repository.delete(role);
        return true;
    }

	@Override
	public List<Role> findAll() {
		return repository.findAll();
	}
}
