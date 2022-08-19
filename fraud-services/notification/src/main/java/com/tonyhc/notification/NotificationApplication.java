package com.tonyhc.notification;

import com.tonyhc.ampq.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.tonyhc.notification",
                "com.tonyhc.ampq"
        }
)
@EnableEurekaClient
public class NotificationApplication {
    public static void main( String[] args )
    {
        SpringApplication.run(NotificationApplication.class, args);
    }

   /*
    @Bean
    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,
                                        NotificationConfig notificationConfig) {
        return args -> {
            producer.publish(new Person("Rory", 14),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey()
            );
        };
    }

    record Person(String name, int age) {}
   */
}