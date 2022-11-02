package com.tonyhc.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.tonyhc.notification",
                "com.tonyhc.ampq",
                "com.tonyhc.kafka"
        }
)
@EnableEurekaClient
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties"),
        @PropertySource("classpath:kafka-${spring.profiles.active}.properties")
})
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