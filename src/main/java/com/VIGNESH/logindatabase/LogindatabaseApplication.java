package com.VIGNESH.logindatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.bson.types.ObjectId;
import com.VIGNESH.logindatabase.model.User;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class LogindatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogindatabaseApplication.class, args);
    }

    @Bean
    public BeforeConvertCallback<User> beforeSaveCallback() {
        return (User user, String collection) -> {
            if (user.getId() == null) {
                user.setId(new ObjectId().toString());
            }
            return user;
        };
    }
}