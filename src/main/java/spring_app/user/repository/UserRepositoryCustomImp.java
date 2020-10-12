package spring_app.user.repository;

// Кастомизированый интерфейс со своими методами

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import spring_app.user.User;


@Repository
public class UserRepositoryCustomImp implements UserRepositoryCustom{
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public long updateUser(String userName, String password) {
        Query query = new Query(Criteria.where("userName").is(userName));
        Update update = new Update();
        update.set("username", userName);
        update.set("password", password);

        UpdateResult updateResult = this.mongoTemplate.updateFirst(query, update, User.class);

        return updateResult.getModifiedCount();

    }
}
