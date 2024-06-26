package org.reservahoteles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication()
@EnableJpaRepositories
public class ReservaHotelesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReservaHotelesApplication.class, args);
    }
}