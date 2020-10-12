package spring_app;

import org.springframework.boot.SpringApplication;
import spring_app.mongodb.Config;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@SpringBootApplication
public class SpringBootMongoDbApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongoDbApplication.class, args);
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
