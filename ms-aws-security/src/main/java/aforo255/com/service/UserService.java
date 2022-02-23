package aforo255.com.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import aforo255.com.dao.UserDao;


@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserDao client;
    private Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);
    private final int MAX_ATTEMPT = 3;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        try {

            aforo255.com.entity.User user = client.findByUsername(username);
            List<GrantedAuthority> authorities = user.getRoles().stream()

                    .map(role -> new SimpleGrantedAuthority(role.getNameRol()))
                    .peek(authority -> log.info("Role: " + authority.getAuthority()))
                    .collect(Collectors.toList());

            return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true,
                    authorities);
        } catch (Exception ex) {

            throw new UsernameNotFoundException("Error login");
        }
    }


    @Override
    public aforo255.com.entity.User findByUsername(String username) {
        // TODO Auto-generated method stub
        return client.findByUsername(username);
    }

    @Override
    public aforo255.com.entity.User update(aforo255.com.entity.User user) {
        // TODO Auto-generated method stub
        return client.save(user);
    }

    public void loginSucceeded(aforo255.com.entity.User usuario) {
        usuario.setAttemp(0L);
        update(usuario);
    }

    public void loginFailed(aforo255.com.entity.User usuario) {
        Long attempts = 0L;
        try {
            attempts = usuario.getAttemp() == null ? 0 : usuario.getAttemp();
        } catch (Exception e) {
            attempts = 0L;
        }
        attempts++;
        usuario.setAttemp(attempts);
        update(usuario);
    }

    public boolean isBlocked(aforo255.com.entity.User usuario) {
        try {
            return (usuario.getAttemp() == null ? 0 : usuario.getAttemp()) >= MAX_ATTEMPT;
        } catch (Exception e) {
            return false;
        }
    }

}
