package com.myspringprojects.CrudSpringBootDemo.controller;


import com.myspringprojects.CrudSpringBootDemo.entity.Student;
import com.myspringprojects.CrudSpringBootDemo.repository.StudentRepository;
import com.myspringprojects.CrudSpringBootDemo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository studentRepository;
    StudentService studentService;

    public StudentController(StudentService ss, StudentRepository studentRepository) {
        this.studentService = ss;
        this.studentRepository = studentRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        student.setDeleted(false);
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @GetMapping("/get")
    public ResponseEntity<Student> getStudent(/*@PathVariable*/ @RequestParam Long id)
    {
        Student st = studentService.getStudent(id);
        if (st == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        return ResponseEntity.ok(st);


    }
    @GetMapping("/getAll")
  public ResponseEntity<List<Student>> getAllStudent()
    {
       List<Student>  all_students =   studentService.getAll();
       if(all_students.isEmpty()) return ResponseEntity.notFound().build();

       return ResponseEntity.ok(all_students);
    }

    @PutMapping("/update")
   public ResponseEntity<Student>  update(@RequestParam Long id ,
                                          @RequestBody Student student)
   {
     Student updatedStudent =  studentService.updateStudent(id , student);

       if(updatedStudent==null) return ResponseEntity.notFound().build();

       return ResponseEntity.ok(updatedStudent);

   }
  @DeleteMapping("/delete/{id}")
   public ResponseEntity<String> deleteStudent(@RequestParam Long id)
  {
      Boolean isDelete =  studentService.delStudent(id);
      if(!isDelete) return ResponseEntity.notFound().build();

      return ResponseEntity.ok("Deleted succesfully");
  }


  @PatchMapping("/delete")
    public ResponseEntity<String> softDelete(@RequestParam Long id)
  {
      Boolean isDeleted =  studentService.deleteSoft(id);
      if(!isDeleted) return  ResponseEntity.notFound().build();

    return ResponseEntity.ok("deleted Entry");
  }



}
