package com.example.restaurant;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class DelCourse {

    public static void deleteCourse(Scanner scan, EntityManager em) {
        System.out.println("Please enter menu item id:");
        String sId = scan.nextLine();
        Long id = Long.parseLong(sId);
        Course course;
        try {
            course = em.getReference(Course.class, id);
            if (course == null) {
                System.out.println("No such element");
                return;
            }

            em.getTransaction().begin();
            try {
                em.remove(course);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.out.println("No such item");
        }
    }
}