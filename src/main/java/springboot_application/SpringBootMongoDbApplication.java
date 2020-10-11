package springboot_application;

import controller.MainController;
import mongodb.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.web.bind.annotation.RestController;
import user.User;

@SpringBootApplication
public class SpringBootMongoDbApplication {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        final SpringApplicationBuilder appBuilder = new SpringApplicationBuilder(SpringBootMongoDbApplication.class);
        appBuilder.profiles("common_mongo_db").application().run(args);
//        SpringApplication.run(SpringBootMongoDbApplication.class, args);



        Config mongoConfig = new Config();

    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory, MongoMappingContext mongoMappingContext) {
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDatabaseFactory), mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        System.out.println(mongoDatabaseFactory.getMongoDatabase());
        //MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactory, converter);

        return new MongoTemplate(mongoDatabaseFactory, converter);
    }
}
