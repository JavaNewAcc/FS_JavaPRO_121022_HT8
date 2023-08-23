package com.example.restaurant;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SelectByPrice {
    public static void selectByPrice(Scanner scan, EntityManager em) {
        System.out.println("Please enter lower and upper price limits using '/'");
        String[] sLimits = scan.nextLine().split("/");
        long[] limits = new long[2];
        for (int i = 0; i < sLimits.length; i++) {
            limits[i] = Long.parseLong(sLimits[i]);
        }
        Arrays.sort(limits);

        Query query = em.createQuery("SELECT course FROM Course course WHERE course.price>=:min AND course.price<=:max", Course.class);
        query.setParameter("min", limits[0]);
        query.setParameter("max", limits[1]);

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
