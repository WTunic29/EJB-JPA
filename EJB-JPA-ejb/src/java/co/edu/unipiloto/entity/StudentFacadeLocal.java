/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unipiloto.entity;

import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author poeta
 */
@Local
public interface StudentFacadeLocal {

    void create(Student student);

    void edit(Student student);

    void remove(Student student);

    Student find(Object id);

    List<Student> findAll();

    List<Student> findRange(int[] range);

    int count();
    
}
