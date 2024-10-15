package edu.sdccd.cisc191.template;

import java.io.Serializable;
import java.util.Scanner;

public class CustomerRequest implements Serializable {

    private String city;

    public CustomerRequest(){
        //creates a scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Ask user to enter a city name
        //continues to request user input until they answer from one of two choices
        while(true){
            System.out.println("Enter the name of the city (San Diego or New York): ");
            city = scanner.nextLine();

            if (city.equals("San Diego") || city.equals("New York")) {
                //ends loop
                break;
            } else {
                System.out.println("Your response is invalid. Try again.");
            }
        }

        //closes scanner to make sure there are no memory leaks
        scanner.close();
    }

    /**
     * @return the city name
     */
    public String getCity(){
        return city;
    }
}
