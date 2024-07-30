package net.skhu.likelion12thteam03be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LikeLion12thTeam03BeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LikeLion12thTeam03BeApplication.class, args);
    }

}
