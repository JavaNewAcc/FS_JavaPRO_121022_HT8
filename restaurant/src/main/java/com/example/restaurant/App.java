package com.example.restaurant;

import javax.persistence.*;
import java.util.Scanner;

public class App {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            emf = Persistence.createEntityManagerFactory("RestaurantMenu");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: Add menu item");
                    System.out.println("2: Delete menu item");
                    System.out.println("3: Select menu item by price");
                    System.out.println("4: Find menu item with or without discount");
                    System.out.println("5: Find menu item with combined weight less that stated limit");
                    System.out.println("6: View full menu");
                    System.out.print("-> ");

                    String option = scan.nextLine();

                    switch (option) {
                        case "1" -> AddCourse.addCourse(scan, em);
                        case "2" -> DelCourse.deleteCourse(scan, em);
                        case "3" -> SelectByPrice.selectByPrice(scan, em);
                        case "4" -> SelectWithDiscount.selectWithDiscount(scan, em);
                        case "5" -> LessThanOneKG.lessThanOneKG(scan, em);
                        case "6" -> ViewAll.viewAll(em);
                    }
                }
            } finally {
                em.close();
                emf.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}