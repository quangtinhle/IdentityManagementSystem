package com.example.egovernmentportal;

import com.example.egovernmentportal.model.User;
import com.example.egovernmentportal.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class EGovernmentPortalApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext =
        SpringApplication.run(EGovernmentPortalApplication.class, args);

        UserRepository userRepository = configurableApplicationContext.getBean(UserRepository.class);


        //User user1 = new User("Le","Tinh","tinh","tinh@gmail.com","07cce711-8cf0-4bbb-8fbe-203c5e46c70",LocalDate.now(),"Schleseiger","Dortmund","12345","Germany");
        //User user2 = new User("Ho","Nguyen","nguyen","nguyen@gmail.com","62053299-f930-4be0-8dd0-70c0935c304e",LocalDate.now(),"Osningstraße 12","Bochum","12345","Germany";

        //userRepository.save(user1);
        //userRepository.save(user2);

    }

}
