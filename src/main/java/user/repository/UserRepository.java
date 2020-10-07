package user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import user.User;

public interface UserRepository extends MongoRepository<User, Long> {
    User findUserByUsername(String username);


}
