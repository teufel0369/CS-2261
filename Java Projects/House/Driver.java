/******************************************************************************
 * Author       : Christopher Thompson
 * Course       : CS 2261 (Fall 2016)
 * Last Modified: 19 November 2016
 *
 * Assignment   : Project 4
 * Summary      : This program will allow the user to create, clone,
 * compare, and customize a house
 ******************************************************************************/

package House;

import static java.lang.System.out;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        DecimalFormat dec = new DecimalFormat("0.00");
        Scanner input = new Scanner(System.in);

        out.println("\t\tWelcome to the Midwest Home Design Tool!\n");
        out.println("This tool will allow you to customize the home of your dreams while\n"+
                    "\t\tallowing you to stay within your budget.\n\n");

        out.println("Please choose from the following home styles templates:\n");
        out.println("Traditional, Modern, European, Southwest, Mountain, Victorian, and Country\n");
        out.print("Selection: ");

        String style = input.nextLine(); //input the style selection

        style = style.toLowerCase(); //converts the input to lower case

        while(checkStyle(style) == false){ //loop to ensure the user enters the correct style
            out.print("That selection is not available in our home design templates\n"+
                        "Please enter another selection: ");
            style = input.nextLine(); //re-entry for input
            style = style.toLowerCase(); //converts the string to lowercase
            checkStyle(style); //checks the style
        }

        House newHouse = newHouse(style); //House object based on customer selection

        //declare an array of objects of House type to utilize as templates
        House[] houseArr = {new Country(), new European(), new Modern(), new Mountain(), new Southwest(), new Traditional(), new Victorian()};

        out.println("\nHow many bedrooms are in the home of your dreams?");
        int beds = input.nextInt(); //takes in user input for the number of beds;

        while(beds < 1){
            out.println("\nThat was an incorrect selection. Please try again: ");
            beds = input.nextInt();
        }
        newHouse.setNumBed(beds); // sets the number of bedrooms

        out.println("\nHow many baths would you like?");
        double baths = input.nextDouble();

        while(baths < 1){
            out.println("\nThat was an incorrect selection. Please try again: ");
            baths = input.nextInt();
        }
        newHouse.setNumBath(baths); //sets the number of bathrooms

        out.println("Finally, what would be your ideal area for your house?");
        double area = input.nextDouble(); //takes in the user input for the ideal area

        while(area < 1){
            out.println("\nThat was an incorrect selection. Please try again: ");
            area = input.nextInt();
        }
        newHouse.setArea(area); //takes in the user input for the ideal area

        //out.println("Your projected cost for this house is: " + newHouse.getCost());

        out.print("\nComparing your search to other houses in our database");
        for(int i = 0; i < 5; i++){ //stuff to make it look cool
            try{ //try-catch block of InterruptedException
                out.print(". ");
                Thread.sleep(350);
            }
            catch(InterruptedException e){ //catch the exception
                Thread.currentThread().interrupt();
            }
        }
        out.println("\n");

        try{ //try-catch block for the compare house function
            newHouse = compareHouse(newHouse, houseArr);
        }
        catch (CloneNotSupportedException e){ //catches the exception
            e.printStackTrace(); //prints the stack trace
        }

        out.println(newHouse.toString()); //print the description of the house
        out.println("Total cost to build: $" + dec.format(newHouse.getCost())); //get the cost to build

        try{ //try-catch block for the
            newHouse = customizeFunction(newHouse);
        }
        catch (CloneNotSupportedException e){ //catches the exception
            e.printStackTrace(); //prints the stack trace
        }

    }

    //checks to see if the strings match the available styles
    public static boolean checkStyle(String style){
        String str1, str2, str3, str4, str5, str6, str7;

        str1 = "traditional";
        str2 = "modern";
        str3 = "european";
        str4 = "southwest";
        str5 = "mountain";
        str6 = "victorian";
        str7 = "country";

        if(style.equals(str1) || style.equals(str2) || style.equals(str3) || style.equals(str4) || style.equals(str5) ||
                style.equals(str6) || style.equals(str7)){ //compares the strings
            return true; //returns true if the strings are equal
        }
        else{ //returns false otherwise
            return false;
        }
    }

    //instantiates an object of House type based on the style the user entered and returns it
    public static House newHouse(String style){
        House userHouse = null;

        if(style.equals("traditional")){ //if the style is equal to traditional
           userHouse = new Traditional(); //then instantiate a new House of Traditional type
        }
        else if(style.equals("modern")){ //if the style is equal to modern
            userHouse = new Modern(); //then instantiate a new House of Modern type
        }
        else if(style.equals("european")){
            userHouse = new European();
        }
        else if(style.equals("southwest")){
            userHouse = new Southwest();
        }
        else if(style.equals("mountain")){
            userHouse = new Mountain();
        }
        else if(style.equals("victorian")){
            userHouse = new Victorian();
        }
        else if(style.equals("country")){
            userHouse = new Country();
        }

        return userHouse; //returns the userHouse object
    }

    //function to customize the house
    public static House customizeFunction(House userHouse) throws CloneNotSupportedException{
        DecimalFormat df = new DecimalFormat("0.00");
        Scanner in = new Scanner(System.in);
        char choice; //for selection
        int choice2;
        char choice3 = 'x';
        double baths = 0;
        int beds = 0;
        double area = 0;
        House tempHouse = (House)userHouse.clone(); //cloned for reseting values
        House trad = new Traditional(); //templates to clone
        House mod = new Modern(); //*
        House euro = new European(); //*
        House south = new Southwest(); //*
        House mountain = new Mountain(); //*
        House vic = new Victorian(); //*
        House country = new Country(); //end of templates to clone
        String style; //string for the style

        out.println("\nWould you like to customize the house anymore? [Y]es or [N]o?");
        choice = in.next().charAt(0);

        if((choice == 'y') || (choice == 'Y')){

            do{ //do more customizations while the answer is yes
                out.println("\nWhat would you like to customize?\n" +
                        "1) Style\n" +
                        "2) Number of bedrooms\n" +
                        "3) Number of bathrooms\n" +
                        "4) Area\n\n" +
                        "Selection: ");

                do{
                    choice2 = in.nextInt();
                }while(choice2 < 1); //takes in a choice while the choice is less than 1

                switch(choice2){
                    case 1: //case is 1
                        out.println("\nWhich style would you prefer? Traditional, Modern, European, Southwest, Mountain, Victorian, or Country?");
                        out.println("\nSelection: ");
                        do{
                            style = in.nextLine(); //takes in a style
                            style = style.toLowerCase(); //converts the style to lowercase
                            checkStyle(style); //checks the style
                        }while(checkStyle(style) == false);


                        switch(style){ //switch statement for style choice

                            case "traditional": //if the style is traditional
                                userHouse = (House) trad.clone(); //clone the traditional template
                                userHouse.setNumBed(tempHouse.getNumBed()); //reset the number of beds to original selection
                                userHouse.setNumBath(tempHouse.getNumBath()); //reset the number of baths to original selection
                                userHouse.setArea(tempHouse.getArea()); //reset the area to original selection
                                break;

                            case "modern": //if the style is modern
                                userHouse = (House) mod.clone(); //clone the modern house
                                userHouse.setNumBed(tempHouse.getNumBed()); //reset the number of beds to original selection
                                userHouse.setNumBath(tempHouse.getNumBath()); //reset the number of baths to original selection
                                userHouse.setArea(tempHouse.getArea()); //reset the area to original selection
                                break;

                            case "european": //if the style is european
                                userHouse = (House) euro.clone(); //clone the european house
                                userHouse.setNumBed(tempHouse.getNumBed()); //reset the number of beds to original selection
                                userHouse.setNumBath(tempHouse.getNumBath()); //reset the number of baths to original selection
                                userHouse.setArea(tempHouse.getArea()); //reset the area to original selection
                                break;

                            case "southwest": //if the style is southwest
                                userHouse = (House) south.clone();  //clone the southwest house
                                userHouse.setNumBed(tempHouse.getNumBed()); //reset the number of beds to original selection
                                userHouse.setNumBath(tempHouse.getNumBath()); //reset the number of baths to original selection
                                userHouse.setArea(tempHouse.getArea()); //reset the area to original selection
                                break;

                            case "mountain": //if the style is southwest
                                userHouse = (House) mountain.clone(); //clone the mountain house
                                userHouse.setNumBed(tempHouse.getNumBed()); //reset the number of beds to original selection
                                userHouse.setNumBath(tempHouse.getNumBath()); //reset the number of baths to original selection
                                userHouse.setArea(tempHouse.getArea()); //reset the area to original selection
                                break;

                            case "victorian": //if the style is victorian
                                userHouse = (House) vic.clone(); //clone the victorian house
                                userHouse.setNumBed(tempHouse.getNumBed()); //reset the number of beds to original selection
                                userHouse.setNumBath(tempHouse.getNumBath()); //reset the number of baths to original selection
                                userHouse.setArea(tempHouse.getArea()); //reset the area to original selection
                                break;

                            case "country": //if the style is country
                                userHouse = (House) country.clone(); //clone the country house
                                userHouse.setNumBed(tempHouse.getNumBed()); //reset the number of beds to original selection
                                userHouse.setNumBath(tempHouse.getNumBath()); //reset the number of baths to original selection
                                userHouse.setArea(tempHouse.getArea()); //reset the area to original selection
                                break;
                        }
                        break;

                    case 2: //choice 2
                        out.println("\nHow many bedrooms would you like?");
                        beds = in.nextInt();
                        userHouse.setNumBed(beds); //sets the number of beds
                        break;

                    case 3: //choice 3
                        out.println("\nHow many bathrooms would you like?");
                        baths = in.nextDouble();
                        userHouse.setNumBath(baths); //sets the number of baths
                        break;

                    case 4: //choice 4
                        out.println("\nWhat area would you like?");
                        area = in.nextDouble();
                        userHouse.setArea(area); //sets the area
                        break;
                }

                out.println("\nAre there anymore customizations? [Y]es or [N]o?");
                choice3 = in.next().charAt(0);
            }while((choice3 == 'y') || (choice3 == 'Y'));

            out.println("\n------------ FINAL QUOTE ------------"+
                    "\nYour final design is a spacious " + userHouse.getStyle() + " home with " + userHouse.getArea() + " sq/ft, " +
                     userHouse.getNumBed() + " bedrooms, and " + userHouse.getNumBath() + " bathrooms.\n" +
                    "The total cost to build is $" + df.format(userHouse.getCost()));

        }
        else{
            out.println("\n------------ FINAL QUOTE ------------"+
                    "\nYour final design is a spacious " + userHouse.getStyle() + " stylehome with " + df.format(userHouse.getArea()) + " sq/ft, " +
                     userHouse.getNumBed() + " bedrooms, and " + userHouse.getNumBath() + " bathrooms.\n" +
                    "The total cost to build is $" + df.format(userHouse.getCost()));
        }

        return userHouse;
    }

    //compares the house and returns a house base on the comparison
    public static House compareHouse(House userHouse, House[] houseArray) throws CloneNotSupportedException{
        Scanner in = new Scanner(System.in);
        char compareChoice;
        char choice2;
        House tempHouse = (House)userHouse.clone();

        for(int i = 0; i < houseArray.length; i++){ //for loop to access House array

            if(userHouse.compareTo(houseArray[i]) == 1) { //if compareTo returns true/false

                out.println("We've found a house that may match your selection.\n");
                out.println("Would you like to see a description? [Y]es or [N]o?");
                compareChoice = in.next().charAt(0); //takes in a choice

                if ((compareChoice == 'y') || (compareChoice == 'Y')) { //if the answer is yes
                    out.println(houseArray[i].toString()); //prints the description

                    out.println("Would you like to select this house? [Y]es or [N]o?");
                    choice2 = in.next().charAt(0); //takes in a choice

                    if((choice2 == 'y') || (choice2 == 'Y')){ //if the choice is yes

                        userHouse = (House) houseArray[i].clone(); //clones the house use
                        userHouse.setArea(tempHouse.getArea()); //reset the area to the original selection
                        break; //breaks from the for loop

                    }
                    else { //if the choice is no
                        break; //break from the for loop
                    }
                }
                else{ //if compareChoice is no
                    break; //break from the for loop
                }
            }
        }

        return userHouse; //returns userHouse
    }

}
