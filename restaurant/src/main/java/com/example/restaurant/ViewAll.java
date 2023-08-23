package com.example.restaurant;

import javax.persistence.*;
import javax.persistence.EntityManager;
import java.util.List;

public class ViewAll {
    public static void viewAll(EntityManager em) {
        Query query = em.createQuery("SELECT course FROM Course course", Course.class);
        List<Course> list = (List<Course>) query.getResultList();

        for (Course course : list) {
            System.out.println(course);
        }
    }
}
