package springboot_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

        //MongoTemplate mongoTemplate = new MongoTemplate(mongoDatabaseFactory, converter);

        return new MongoTemplate(mongoDatabaseFactory, converter);
    }
}
