package com.myprojects;
import com.myprojects.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args )
    {

        Logger logger = LoggerFactory.getLogger(App.class);
        logger.info("This is a simple slfg4j logging test...");

        System.out.println( "Hello World!" );
        Student student1 = new Student();

        student1.setAge(1);
        System.out.println(student1.getAge());

    }
}
