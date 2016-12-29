package org.manuel.teambuilting.statistics;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Manuel Doncel Martos
 * @since 07/12/2016.
 */
@SpringBootApplication
@EnableRabbit
public class TeamBuiltingStatisticApplication {


    public static void main(final String[] args) {
        SpringApplication.run(TeamBuiltingStatisticApplication.class, args);
    }

}
