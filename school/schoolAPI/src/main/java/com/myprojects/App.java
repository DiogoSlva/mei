package com.myprojects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {

        Logger logger = LoggerFactory.getLogger(App.class);
        logger.info("This is a simple slfg4j logging test...");

        SpringApplication.run(App.class, args);

    }
}
