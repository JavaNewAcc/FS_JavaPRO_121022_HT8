package com.example.restaurant;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LessThanOneKG {
    public static void lessThanOneKG(Scanner scan, EntityManager em) {
        System.out.println("Please enter max weight for random menu items selection:");
        String sMaxWeight = scan.nextLine();
        double maxWeight = Double.parseDouble(sMaxWeight);

        Query query = em.createQuery("SELECT course FROM Course course", Course.class);
        List<Course> list = (ArrayList<Course>) query.getResultList();

        List<Course> selectedList = new ArrayList<Course>();
        double totalWeight = 0;
        Random rnd = new Random();
        while (totalWeight <= maxWeight && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Course tempCourse = list.get(rnd.nextInt(list.size()));
                if (tempCourse.getWeight() > maxWeight || totalWeight + tempCourse.getWeight() > maxWeight) {
                    list.remove(tempCourse);
                } else {
                    if (!selectedList.contains(tempCourse)) {
                        selectedList.add(tempCourse);
                        totalWeight += tempCourse.getWeight();
                        list.remove(tempCourse);
                    }
                }
            }
        }
        if (totalWeight == 0 && list.isEmpty()) {
            System.out.println("No items could be selected with wight limit of " + maxWeight + " kg");
        }
        for (Course course : selectedList) {
            System.out.println(course);
        }
    }
}
