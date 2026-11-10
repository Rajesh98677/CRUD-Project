package com.myspringprojects.CrudSpringBootDemo.repository;

import com.myspringprojects.CrudSpringBootDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Component
//@Repository
public interface StudentRepository extends JpaRepository<Student , Long>
{
    Optional<Student> findByIdAndDeletedIsFalse(Long id);

    List<Student> findByDeletedFalse();
//    public Student savedStudent(Student student)
//    {
//        Student s1 = new Student();
//        s1.setName("Rajesh");
//        s1.setAge(23);
//        s1.setEmail("thegretrajesh40@gmail.com");
//        s1.setRoll(33);
//        s1.setSubject("Spring boot");
//        return s1;
//    }

}
