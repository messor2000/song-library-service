package spring_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring_app.user.User;
import spring_app.user.encryption.PasswordGenerator;
import spring_app.user.repository.UserRepository;

import java.util.concurrent.ThreadLocalRandom;

@RestController
//@Controller
@RequestMapping("/users")
public class MainController {
    private static final String[] USERS = { "Tom", "Jerry", "Donald" };
    private static int uniqueId = 0;

    int getUniqueId() {
        return uniqueId++;
    }

    PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
            .useLower(true)
            .useUpper(true)
            .useDigits(true)
            .usePunctuation(true)
            .build();

    @Autowired
    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping("/")
    public String home() {
        String html = "";
        html += "<ul>";
        html += " <li><a href='/testInsert'>Test Insert</a></li>";
        html += " <li><a href='/deleteAllEmployee'>Delete All Employee</a></li>";
        html += "</ul>";
        return html;
    }

//    @ResponseBody
    @PostMapping()
    public User testInsert(@RequestBody User user) {
        long id = ThreadLocalRandom.current().nextLong();

        user.setId(id);
//        user.setUsername(userName);
//        user.setPassword(passwordGenerator.generate(10));

//        this.userRepository.insert(user);

        return this.userRepository.insert(user);
    }

    @ResponseBody
    @RequestMapping("/deleteAllEmployee")
    public String deleteAllEmployee() {

        this.userRepository.deleteAll();
        return "Deleted!";
    }

//    @Override
//    public void run(String... args) {
//        userRepository.save(new User());
//
//        System.out.println("Customers found with findAll():");
//        System.out.println("-------------------------------");
//        for (User user : userRepository.findAll()) {
//            System.out.println(user);
//        }
//        System.out.println();
//    }
}
