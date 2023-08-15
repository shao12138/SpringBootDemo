package com.nudt.h2db.Mapper;

import com.nudt.h2db.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
