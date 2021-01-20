package com.wyb.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.wyb.message.api.mail.MailService;

/**
 * https://blog.csdn.net/uotail/article/details/90302130?utm_medium=distribute.pc_relevant_t0.none-task-blog-
 * BlogCommendFromBaidu-1.control&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromBaidu-1.
 * control
 *
 */
@SpringBootApplication
public class LoginApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LoginApplication.class, args);
        MailService mailService = context.getBean(MailService.class);
        System.out.println(mailService.send());
    }

}
