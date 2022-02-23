package aforo255.com.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import aforo255.com.entity.User;

public interface UserDao extends CrudRepository<User, Long> {

    public User findByUsername(@Param("nombre") String Username);
}
