package aforo255.com.service;

import aforo255.com.entity.User;

public interface IUserService {

    public User findByUsername(String username);

    public User update(User user);

    public void loginSucceeded(User user);

    public void loginFailed(User user);

    public boolean isBlocked(User user);
}
