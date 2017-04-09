package House;

import java.text.DecimalFormat;

//abstract class to extend implementations and functions to subclasses
abstract class House implements Cloneable,Comparable<House>, Customize{

    String style; //style of the home
    double area; //area in ft^2
    int numBed; //number of bedrooms
    double numBath; //number of bathrooms
    double totalCost; //total cost of the house
    double basicRate; //basic rate of the house


    public House(){ //default constructor

    }

    @Override //overrides the interface to customize number of bedrooms
    public void setNumBed(int bed){
        this.numBed = bed;
    }

    @Override //overrides the interface to customize number of bathrooms
    public void setNumBath(double numBath){
        this.numBath = numBath;
    }

    @Override //overrides the interface to customize the area
    public void setArea(double area){
        this.area = area;
    }

    @Override //overrides the interface to clone the object
    public Object clone() throws CloneNotSupportedException {
        // Perform a shallow copy
        House houseClone = (House)super.clone();

        return houseClone;
    }

    @Override //overrides the compareTo() method to compare the house amenities
    public int compareTo(House template){

        //if the number of beds and baths on design house equal a template
        if((this.numBed == template.numBed) && (this.numBath == template.numBath)){
            return 1; //return 1 for true
        }
        else{ //otherwise
            return 0; //return 0 for false
        }
    }

    public String toString(){
        String description;
        DecimalFormat df = new DecimalFormat("0.00");


        description = "\nThis beautiful " + this.style + " style home features " + this.numBed + " spacious bedrooms and " +
        this.numBath + " bathrooms. This home is cozy yet " + this.style + " and\nfully equipped to accomodate all of your " +
        "family, friends, and guests. This home features a base price of $" + df.format(this.basicRate) + ".\n\n" +
        "Additional fees:\n"+
        "$800 per additional bedroom\n" +
        "$500 per additional bathroom\n" +
        "Area greater than or equal to 3000 sq/ft incur an additional fee of 1.5 * basic rate\n" +
        "Taxes are 5% of the total cost of development\n";

        return description;
    }

    //retrieves the number of bedrooms ******might get rid of
    public int getNumBed(){
        return this.numBed;
    }

    //retrieves number of bathrooms *******might get rid of
    public double getNumBath(){
        return this.numBath;
    }

    //retrieves the area *********might get rid of
    public double getArea(){
        return this.area;
    }

    public String getStyle(){
        return this.style;
    }

    public abstract double getCost(); //must override this in each of the subclasses
}

//Traditional class represents a traditional style home
class Traditional extends House {

    public Traditional(){
        this.style = "Traditional";
        this.numBed = 3; //defaults the num beds to 3
        this.numBath = 2; //defaults the num baths to 2
        this.basicRate = 3300; //defaults the basic rate to $3300
    }

    @Override //overrides the abstract getCost function and returns a totalCost
    public double getCost(){
        double totalCost = 0;
        int extraBed = 0; //for extra bedrooms
        double extraBath = 0; //for extra bathrooms

        if(this.numBed > 3){ //if the number of beds is greater than the default
            extraBed = this.numBed - 3; //calculate how many extra bedrooms there are
        }

        if(this.numBath > 2){ //if the number of bathrooms is greater than the default
            extraBath = this.numBath - 2; //calculate the number of extra bathrooms
        }


        if(this.area >= 3000){ //if the area is >= 3000
            totalCost = (this.basicRate * 1.5) + (800 * extraBed) + (500 * extraBath); //multiply 1.5 to the basic rate
            double tax = totalCost * 0.05; //calculate the tax
            totalCost = totalCost + tax; //add the tax
        }
        else{ //otherwise the basic rate is added with the standard calculations
            totalCost = this.basicRate + (800 * extraBed) + (500 * extraBed); //standard calculations
            double tax = totalCost * 0.05; //calculate tax
            totalCost = totalCost + tax; //add the tax
        }

        return totalCost; //return the total cost
    }
}

//Modern class represents a modern style home
class Modern extends House {

    public Modern(){
        this.style = "Modern";
        this.numBed = 2; //defaults the num beds to 2
        this.numBath = 1.5; //defaults the num baths to 1.5
        this.basicRate = 4200; //defaults the basic rate to $4200
    }

    @Override //overrides the abstract getCost function and returns a totalCost
    public double getCost(){
        double totalCost = 0;
        int extraBed = 0; //for extra bedrooms
        double extraBath = 0; //for extra bathrooms

        if(this.numBed > 2){ //if the number of beds is greater than the default
            extraBed = this.numBed - 2; //calculate how many extra bedrooms there are
        }

        if(this.numBath > 1.5){ //if the number of bathrooms is greater than the default
            extraBath = this.numBath - 1.5; //calculate the number of extra bathrooms
        }


        if(this.area >= 3000){ //if the area is >= 3000
            totalCost = (this.basicRate * 1.5) + (800 * extraBed) + (500 * extraBath); //multiply 1.5 to the basic rate
            double tax = totalCost * 0.05; //calculate the tax
            totalCost = totalCost + tax; //add the tax
        }
        else{ //otherwise the basic rate is added with the standard calculations
            totalCost = this.basicRate + (800 * extraBed) + (500 * extraBed); //standard calculations
            double tax = totalCost * 0.05; //calculate tax
            totalCost = totalCost + tax; //add the tax
        }

        return totalCost; //return the total cost
    }
}

//European class represents a european style home
class European extends House {

    public European(){
        this.style = "European";
        this.numBed = 2; //defaults the num beds to 2
        this.numBath = 2; //defaults the num baths to 2
        this.basicRate = 4700; //defaults the basic rate to $4700
    }

    @Override //overrides the abstract getCost function and returns a totalCost
    public double getCost(){
        double totalCost = 0;
        int extraBed = 0; //for extra bedrooms
        double extraBath = 0; //for extra bathrooms

        if(this.numBed > 2){ //if the number of beds is greater than the default
            extraBed = this.numBed - 2; //calculate how many extra bedrooms there are
        }

        if(this.numBath > 2){ //if the number of bathrooms is greater than the default
            extraBath = this.numBath - 2; //calculate the number of extra bathrooms
        }


        if(this.area >= 3000){ //if the area is >= 3000
            totalCost = (this.basicRate * 1.5) + (800 * extraBed) + (500 * extraBath); //multiply 1.5 to the basic rate
            double tax = totalCost * 0.05; //calculate the tax
            totalCost = totalCost + tax; //add the tax
        }
        else{ //otherwise the basic rate is added with the standard calculations
            totalCost = this.basicRate + (800 * extraBed) + (500 * extraBed); //standard calculations
            double tax = totalCost * 0.05; //calculate tax
            totalCost = totalCost + tax; //add the tax
        }

        return totalCost; //return the total cost
    }
}

//Southwest class represents a southwestern style home
class Southwest extends House {

    public Southwest(){
        this.style = "Southwest";
        this.numBed = 3; //defaults the num beds to 3
        this.numBath = 2; //defaults the num baths to 2
        this.basicRate = 3600; //defaults the basic rate to $3600
    }

    @Override //overrides the abstract getCost function and returns a totalCost
    public double getCost(){
        double totalCost = 0;
        int extraBed = 0; //for extra bedrooms
        double extraBath = 0; //for extra bathrooms

        if(this.numBed > 3){ //if the number of beds is greater than the default
            extraBed = this.numBed - 3; //calculate how many extra bedrooms there are
        }

        if(this.numBath > 2){ //if the number of bathrooms is greater than the default
            extraBath = this.numBath - 2; //calculate the number of extra bathrooms
        }


        if(this.area >= 3000){ //if the area is >= 3000
            totalCost = (this.basicRate * 1.5) + (800 * extraBed) + (500 * extraBath); //multiply 1.5 to the basic rate
            double tax = totalCost * 0.05; //calculate the tax
            totalCost = totalCost + tax; //add the tax
        }
        else{ //otherwise the basic rate is added with the standard calculations
            totalCost = this.basicRate + (800 * extraBed) + (500 * extraBed); //standard calculations
            double tax = totalCost * 0.05; //calculate tax
            totalCost = totalCost + tax; //add the tax
        }

        return totalCost; //return the total cost
    }
}

//Mountain class represents a mountain style home
class Mountain extends House {

    public Mountain(){
        this.style = "Mountain";
        this.numBed = 3; //defaults the num beds to 3
        this.numBath = 2; //defaults the num baths to 2
        this.basicRate = 3500; //defaults the basic rate to $3500
    }

    @Override //overrides the abstract getCost function and returns a totalCost
    public double getCost(){
        double totalCost = 0;
        int extraBed = 0; //for extra bedrooms
        double extraBath = 0; //for extra bathrooms

        if(this.numBed > 3){ //if the number of beds is greater than the default
            extraBed = this.numBed - 3; //calculate how many extra bedrooms there are
        }

        if(this.numBath > 2){ //if the number of bathrooms is greater than the default
            extraBath = this.numBath - 2; //calculate the number of extra bathrooms
        }


        if(this.area >= 3000){ //if the area is >= 3000
            totalCost = (this.basicRate * 1.5) + (800 * extraBed) + (500 * extraBath); //multiply 1.5 to the basic rate
            double tax = totalCost * 0.05; //calculate the tax
            totalCost = totalCost + tax; //add the tax
        }
        else{ //otherwise the basic rate is added with the standard calculations
            totalCost = this.basicRate + (800 * extraBed) + (500 * extraBed); //standard calculations
            double tax = totalCost * 0.05; //calculate tax
            totalCost = totalCost + tax; //add the tax
        }

        return totalCost; //return the total cost
    }

}

//Victorian class represents a victorian style home
class Victorian extends House {

    public Victorian(){
        this.style = "Victorian";
        this.numBed = 3; //defaults the num beds to 3
        this.numBath = 2.5; //defaults the num baths to 2.5
        this.basicRate = 3200; //defaults the basic rate to $3200
    }

    @Override //overrides the abstract getCost function and returns a totalCost
    public double getCost(){
        double totalCost = 0;
        int extraBed = 0; //for extra bedrooms
        double extraBath = 0; //for extra bathrooms

        if(this.numBed > 3){ //if the number of beds is greater than the default
            extraBed = this.numBed - 3; //calculate how many extra bedrooms there are
        }

        if(this.numBath > 2.5){ //if the number of bathrooms is greater than the default
            extraBath = this.numBath - 2.5; //calculate the number of extra bathrooms
        }


        if(this.area >= 3000){ //if the area is >= 3000
            totalCost = (this.basicRate * 1.5) + (800 * extraBed) + (500 * extraBath); //multiply 1.5 to the basic rate
            double tax = totalCost * 0.05; //calculate the tax
            totalCost = totalCost + tax; //add the tax
        }
        else{ //otherwise the basic rate is added with the standard calculations
            totalCost = this.basicRate + (800 * extraBed) + (500 * extraBed); //standard calculations
            double tax = totalCost * 0.05; //calculate tax
            totalCost = totalCost + tax; //add the tax
        }

        return totalCost; //return the total cost
    }
}

//Country class represents a country style home
class Country extends House {


    public Country(){
        this.style = "Country";
        this.numBed = 3; //defaults the num beds to 3
        this.numBath = 3; //defaults the num baths to 3
        this.basicRate = 3000; //defaults the basic rate to $3000
    }

    @Override //overrides the abstract getCost function and returns a totalCost
    public double getCost(){
        double totalCost = 0;
        int extraBed = 0; //for extra bedrooms
        double extraBath = 0; //for extra bathrooms

        if(this.numBed > 3){ //if the number of beds is greater than the default
            extraBed = this.numBed - 3; //calculate how many extra bedrooms there are
        }

        if(this.numBath > 3){ //if the number of bathrooms is greater than the default
            extraBath = this.numBath - 3; //calculate the number of extra bathrooms
        }


        if(this.area >= 3000){ //if the area is >= 3000
            totalCost = (this.basicRate * 1.5) + (800 * extraBed) + (500 * extraBath); //multiply 1.5 to the basic rate
            double tax = totalCost * 0.05; //calculate the tax
            totalCost = totalCost + tax; //add the tax
        }
        else{ //otherwise the basic rate is added with the standard calculations
            totalCost = this.basicRate + (800 * extraBed) + (500 * extraBed); //standard calculations
            double tax = totalCost * 0.05; //calculate tax
            totalCost = totalCost + tax; //add the tax
        }

        return totalCost; //return the total cost
    }

}