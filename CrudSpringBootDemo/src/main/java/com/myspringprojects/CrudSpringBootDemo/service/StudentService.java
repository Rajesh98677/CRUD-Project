package com.myspringprojects.CrudSpringBootDemo.service;

import com.myspringprojects.CrudSpringBootDemo.entity.Student;
import com.myspringprojects.CrudSpringBootDemo.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository sr;
    public StudentService(StudentRepository sr)
    {
        this.sr  =sr;
    }
    public Student createStudent(Student student)
    {
        Student studentResp = sr.save(student);
        return studentResp;
    }
    public  Student getStudent(Long id)
    {

      Optional<Student> student =  sr.findByIdAndDeletedIsFalse(id);
      if(student.isPresent())
      {
          return student.get();
      }
      return null;
    }

    public List<Student> getAll()
    {
         List<Student> studentList =    sr.findByDeletedFalse();


        return studentList;
    }

    public Student updateStudent(Long id , Student studentReq)
    {
       Optional<Student> existingStudent= sr.findByIdAndDeletedIsFalse(id);
       if(existingStudent.isEmpty())
       {
           return null;
       }

       Student studentToSave = existingStudent.get();


       studentToSave.setName(studentReq.getName());
       studentToSave.setSubject((studentReq.getSubject()));
       studentToSave.setRoll(studentReq.getRoll());
       studentToSave.setEmail(studentReq.getEmail());
       studentToSave.setAge(studentReq.getAge());
       studentToSave.setDeleted(false);
sr.save(studentToSave);

return studentToSave;
    }

    public Boolean delStudent(Long id){
        Boolean isExist =  sr.existsById(id);

        if(!isExist) return false;
        sr.deleteById(id);
        return true;



    }
    public Boolean deleteSoft(Long id)
    {
      Optional<Student> student  =     sr.findByIdAndDeletedIsFalse(id);
      if(student.isEmpty()) return false;
      Student deletedStudent = student.get();
      deletedStudent.setDeleted(true);
      sr.save(deletedStudent);
      return true;
    }


}
