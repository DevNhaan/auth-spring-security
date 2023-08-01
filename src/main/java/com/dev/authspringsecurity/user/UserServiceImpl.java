package com.dev.authspringsecurity.user;

import com.dev.authspringsecurity.role.Role;
import com.dev.authspringsecurity.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public SUser save(SUser user) {

        SUser newUser = new SUser();
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setUsername(user.getUsername());

        repository.save(newUser);

        newUser.setPassword("");
        return newUser;
    }

    @Override
    public boolean addRole(String userId, String roleId) {
        Optional<Role> roleOpt = roleRepository.findById(roleId);
        Optional<SUser> userOpt = repository.findById(userId);
        
        if(roleOpt.isEmpty() || userOpt.isEmpty())
        	return false;
        SUser user = userOpt.get();
        user.addRole(roleOpt.get());
        try {
        	repository.save(user);
        }catch(Exception ex) {
        	return false;
        }
        return true;
    }
}
