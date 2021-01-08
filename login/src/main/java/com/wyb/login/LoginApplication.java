package com.wyb.login;

import com.wyb.message.api.mail.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LoginApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LoginApplication.class, args);
        MailService mailService = context.getBean(MailService.class);
        System.out.println(mailService.send());
    }

}
