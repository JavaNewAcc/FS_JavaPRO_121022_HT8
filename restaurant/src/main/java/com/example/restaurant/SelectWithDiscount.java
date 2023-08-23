package com.example.restaurant;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class SelectWithDiscount {
    public static void selectWithDiscount(Scanner scan, EntityManager em) {

        Query query = em.createQuery("SELECT course FROM Course course WHERE course.discount=:value", Course.class);
        boolean flag = true;
        while (flag) {
            System.out.println("Please choose whether you need courses with or without discount:");
            System.out.println("1. With discount");
            System.out.println("2. Without discount");
            String s = scan.nextLine();
            switch (s) {
                case "1" -> {
                    query.setParameter("value", true);
                    flag = false;
                }
                case "2" -> {
                    query.setParameter("value", false);
                    flag = false;
                }
                default -> {
                    flag = true;
                }
            }
        }

        List<Course> list = (List<Course>) query.getResultList();

        if (list.isEmpty()) {
            System.out.println("There are no items selected according to your parameters");
            return;
        }

        for (Course course : list) {
            System.out.println(course);
        }
    }
}
