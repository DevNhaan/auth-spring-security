package com.dev.authspringsecurity.user;

public interface UserService {
    SUser save(SUser user);
    boolean addRole(String userId,String roleId);
}
