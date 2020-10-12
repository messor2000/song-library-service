package spring_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spring_app.user.User;
import spring_app.user.encryption.PasswordGenerator;
import spring_app.user.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class MainController implements CommandLineRunner{
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

    @ResponseBody
    @RequestMapping("/testUser")
    public String testInsert() {
        User user = new User();

        long id = getUniqueId();
        int idx = (int) (id % USERS.length);
        String userName = USERS[idx];

        user.setId(id);
        user.setUsername(userName);
        user.setPassword(passwordGenerator.generate(10));

        this.userRepository.insert(user);

        return "Inserted: " + user;
    }

    @ResponseBody
    @RequestMapping("/deleteAllEmployee")
    public String deleteAllEmployee() {

        this.userRepository.deleteAll();
        return "Deleted!";
    }

    @Override
    public void run(String... args) {
        userRepository.save(new User());

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }
        System.out.println();
    }
}
