package com.myprojects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * StudentsRepository interface to interact with the database.
 * It extends JpaRepository to have access to the CRUD operations.
 * It also has custom queries to get students by age, approved status, and age range.
 */
public interface StudentsRepository extends JpaRepository<Student, Integer> {

    List<Student> getStudentByAge(@Param("age") Integer age);

    @Query("FROM Student s WHERE s.approved = :approved")
    List<Student> getStudentByApproved(@Param("approved") Boolean approved);

    @Query(
            value = "SELECT * FROM Student WHERE age BETWEEN :from AND :to",
            nativeQuery = true
    )
    List<Student> getStudentByAgeBetween(@Param("from") Integer from, @Param("to") Integer to);
}
