package mongodb;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import user.User;

import java.util.List;

public class MongoDBPOperations {

    public void saveUser(MongoOperations mongoOperation, User user) {

        mongoOperation.save(user);
        System.out.println("user saved successfully");
        // student object got created with id.
        System.out.println("user : " + user);
    }

    public void searchStudent(MongoOperations mongoOperation, String critera, String value) {
        // query to search student
        Query searchUser = new Query(Criteria.where(critera).is(value));

        // find student based on the query
        User resultUser = mongoOperation.findOne(searchUser, User.class);
        System.out.println("User found!!");
        System.out.println("User details: " + resultUser);
    }

    public void updateUser(MongoOperations mongoOperation, String critera, String value, String updateCriteria, String updateValue) {
        // query to search student
        Query searchStudent = new Query(Criteria.where(critera).is(value));
        mongoOperation.updateFirst(searchStudent, Update.update(updateCriteria, updateValue), User.class);
        System.out.println("Student got updated successfully");
    }
    public void getAllStudent(MongoOperations mongoOperation) {
        List<User> listUser = mongoOperation.findAll(User.class);
        for(User user: listUser) {
            System.out.println("User = " + user);
        }
    }
    public void removeUser(MongoOperations mongoOperation, String critera, String value) {
        Query searchUser = new Query(Criteria.where(critera).is(value));
        mongoOperation.remove(searchUser, User.class);
        System.out.println("User removed successfully!! ");
    }
}
