package com.pdp.yourmeal;

import com.pdp.yourmeal.config.SessionUser;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class YourMealApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourMealApplication.class, args);
    }

    @Bean
    public AuditorAware<Long> auditorAware(SessionUser sessionUser) {
        return () -> Optional.ofNullable(sessionUser.id());
    }
}
