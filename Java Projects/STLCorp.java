/******************************************************************************
 * Author       : Christopher Thompson
 * Course       : CS 2261 (Fall 2016)
 * Last Modified: 03 September 2016
 *
 * Assignment   : Project 1 StLouis Corp
 *
 * Summary      : This program will take in input from the user in the form of
 * names, pay and hours worked, store them into a vector, converts the vector
 * to an array, performs the necessary calculationin order to determine the
 * weekly pay, and will then display them on the screen.
 ******************************************************************************/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import static java.lang.System.out;

public class STLCorp {

    private Double minWage = 7.65;

    /******************************************************************************
     * Name    : main()
     * Purpose : Control the main loop for the payroll system
     * Inputs  : none
     * Outputs : none
     ******************************************************************************/

    public static void main(String [] args){

        Scanner input = new Scanner(System.in); //create scanner object
        Vector<String> employee_Names = new Vector<>(); //vector to hold employee names
        Vector<Double> base_P = new Vector<>(); //vector to hold the base pay
        Vector<Double> hours_W = new Vector<>(); //vector to hold the hours worked
        char choice = 'p'; //for answering yes or no to continue loops
        String name; //variable for the name of the employee
        Double base; //variable for the base pay
        Double hoursW; //variable for hours
        int size; //size of vector to pass to calculation function
        boolean flag = false; //flag variable for loop continuation

        System.out.println("\n\n\t\t Welcome to St. Louis Corp Payroll System\n");

       do{
            System.out.println("\n\nEnter the name of the employee: ");
            name = input.next(); //takes in the employee name
            employee_Names.addElement(name); //adds the employee's name into the vector

            System.out.println("\n\nEnter the base pay for the employee:");
            base = input.nextDouble();

            while (base < 7.65){ //while loop to error check the base pay rate
                System.out.println("\nERROR: The State of Missouri requires that all hourly employees be paid a base rate of $7.65/hr.\n");
                System.out.println("Please re-enter the employee's base pay: ");
                base = input.nextDouble();
            }

            base_P.addElement(base); //adds the base pay into the vector

            System.out.println("\n\nEnter the hours the employee worked during the pay period. Ex. 20 hrs and 30 mins = 20.50: ");
            hoursW = input.nextDouble();

            while (hoursW > 60.00){ //while loop to error check the amount of hours worked
                System.out.println("\nERROR: St. Louis Corp does not allow employees more than 20 hours of overtime per week.\n");
                System.out.println("Please re-enter the employee's total hours for the pay period: ");
                hoursW = input.nextDouble();
            }

            hours_W.addElement(hoursW); //adds the hours worked into the vector

            System.out.println("\n\nAre there anymore employees to enter? [Y]es or [N]o?\n");
            choice = input.next().charAt(0); //takes in selection to continue loop. Note: must use next().charAt(0) because == only compares data types

            if(choice == 'y' || choice == 'Y'){
                flag = true;
            }
            else { flag = false; }

        }while(flag == true);

        size = base_P.size();

        String[] nameArray = new String[size]; //Declaring string array for the employee names vector to be filled into
        Double[] payArray = new Double[size]; //Declaring double array for the base pay vector to be filled into
        Double[] hoursArray = new Double[size]; //Declaring double array for the hours vector to be filled into

        employee_Names.toArray(nameArray); //converts the names vector to an array
        base_P.toArray(payArray); //converts the pay vector to an array
        hours_W.toArray(hoursArray); //converts the hours vector to an array

        Double[] take_Home = calculation(payArray, hoursArray, size); //assigns take_Home as the array returned by calculation()
        printArray(nameArray, payArray, hoursArray, take_Home, size); //prints each array and its contents

    }


    /******************************************************************************
     * Name    : calculation
     * Purpose : calculate the week's pay
     * Inputs  : none
     * Outputs : returns a total calculation if ran successfully
     ******************************************************************************/
   public static Double[] calculation(Double[] b_P, Double[] h_W, int sizeArray){

       Double[] takeHome = new Double[sizeArray];
       Double oT; //variable to hold the total amount of overtime
       Double oT_Rate; //variable to hold the overtime rate
       Double oT_Total; //variable to hold the total overtime

       for(int i = 0; i < sizeArray; i++){
           if (h_W[i] > 40) { //if the hours worked are greater than 40
               oT = h_W[i] - 40; //computes the amount of hours that are considered overtime
               oT_Rate = b_P[i] * 1.5; //computes the rate of overtime based on
               oT_Total = oT * oT_Rate; //computes the dollar amount of overtime

               takeHome[i] = oT_Total + (b_P[i] * 40); //adds the dollar amount of overtime to the base pay of 40 hrs
           } else {
               takeHome[i] = b_P[i] * h_W[i]; //computes take home pay without overtime
           }
       }

        return takeHome; //returns the new takeHome array
    }

    /******************************************************************************
     * Name    : printArray()
     * Purpose : To print the arrays formatted
     * Inputs  : 3 Double arrays
     * Outputs : nothing
     ******************************************************************************/
    public static void printArray(String[] e_Names, Double[]bP, Double[] hW, Double[] total_, int size_Array) {
        System.out.printf("\n\n%-8s\t\t%-8s\t\t%-8s\t\t%-8s\t\t\n", "Employee Name:", "Base Pay:", "Hours Worked:", "Total Pay");

        for (int i = 0; i < size_Array; i++) { //for loop to access each array, format, and print the contents
            System.out.printf("%-8s\t\t\t", e_Names[i]); //formats and prints the employee names
            System.out.printf("%-8.2f\t\t", bP[i]); //formats and prints the base pay
            System.out.printf("%-8.2f\t\t    ", hW[i]); //formats and prints the hours worked
            System.out.printf("%-8.2f\n", total_[i]); //formats and prints the total pay for that pay period
        }
    }
}
