package com.example.restaurant;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class AddCourse {

    public static void addCourse(Scanner scan, EntityManager em) {
        System.out.println("Please enter course name:");
        String name = scan.nextLine();
        System.out.println("Please enter price:");
        long price = scan.nextLong();
        System.out.println("Please enter weight:");
        double weight = scan.nextDouble();
        System.out.println("Should discount be applied (Y/N):");
        boolean discount = false;
        scan.nextLine();
        String sDiscount = scan.nextLine();
        if (sDiscount.equals("y")) {
            discount = true;
        } else if (!sDiscount.equals("n")) {
            System.out.println("You have entered wrong value. No discount will be applied for this course.");
        }

        em.getTransaction().begin();
        try {
            Course course = new Course(name, price, weight, discount);
            em.persist(course);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
}
