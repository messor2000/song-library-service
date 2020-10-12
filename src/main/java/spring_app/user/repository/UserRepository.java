package spring_app.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring_app.user.User;

public interface UserRepository extends MongoRepository<User, Long> {
    User findUserByUsername(String username);


}
