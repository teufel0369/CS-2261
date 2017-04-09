/******************************************************************************
 * Author       : Christopher Thompson
 * Course       : CS 2261 (Fall 2016)
 * Last Modified: 15 December 2016
 *
 * Assignment   : Project 5 JavaFx Pizza Ordering System
 * Summary      : This program will start a JavaFx program, which will allow the
 * user to select the number and type of pizza slices they want, calculate a
 * total, and then display that total for the user.
 ******************************************************************************/

package Pizza;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import java.text.DecimalFormat;

public class Main extends Application {

    private Button placeOrder = new Button("Place Order");
    private Button reset = new Button("Reset");
    private ComboBox<Integer> cheese1 = new ComboBox<>();
    private ComboBox<Integer> pepperoni1 = new ComboBox<>();
    private ComboBox<Integer> veggie1 = new ComboBox<>();
    private Label pickSlice = new Label("Pick your slice(s):");
    private Label cheese = new Label("Cheese Pizza\n" + "($1.50 / slice)");
    private Label pepperoni = new Label("Pepperoni Pizza\n" + "($2.25 / slice)");
    private Label veggie = new Label("Veggie Pizza\n" + "(2.00 / slice)");
    private Text total = new Text("");
    private DecimalFormat df = new DecimalFormat("#0.00");
    private Arc cheeseArc = new Arc(300, 100, 110, 110, 0, 0);
    private Arc pepperoniArc = new Arc(150, 268, 110, 110, 0, 0);
    private Arc veggieArc = new Arc(452, 268, 110, 110, 0, 0);
    private Text thanks = new Text("");

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane pane = new BorderPane(); //flowpane object to lay out the nodes on ***
        pane.setStyle("-fx-background-color: darkgrey;");
        pane.setPadding(new Insets(25, 25, 25, 25));

        //initialize the combo boxes to prevent null-pointer exception from being thrown
        cheese1.setValue(0);
        pepperoni1.setValue(0);
        veggie1.setValue(0);

        //add the nodes into the pane
        pane.setLeft(getCombos()); //sets the pane on the left side of the BorderPane
        pane.setCenter(flowControl()); //sets the pane in the center of the BorderPane


        //Time to start the show!
        Scene scene = new Scene(pane, 900, 500); //size of the scene
        primaryStage.setTitle("Big Papa's Pizza Ordering System"); //set the title
        primaryStage.setScene(scene); //place the scene in the stage
        primaryStage.show(); //SHOWTIME!!!!
    }


    public static void main(String[] args) {
        launch(args);
    }

    //Returns a BorderPane with combo boxes and labels
    private BorderPane getCombos(){
        BorderPane paneForCombos = new BorderPane(); //create a BorderPane object
        VBox vb = new VBox(); //VBox for the labels
        VBox vb2 = new VBox(); //VBox for the comboboxes

        //set the padding for both VBoxes
        vb.setPadding(new Insets(15, 5, 15, 5)); //Set the padding
        vb2.setPadding(new Insets(95, 15, 15, 5)); //set the padding

        //set the spacing for in between the boxes
        vb.setSpacing(55);
        vb2.setSpacing(50);

        //set all fonts of the labels and text fields
        pickSlice.setFont(Font.font("Comic Sans MS", 20));
        cheese.setFont(Font.font("Comic Sans MS", 15));
        pepperoni.setFont(Font.font("Comic Sans MS", 15));
        veggie.setFont(Font.font("Comic Sans MS", 15));
        total.setFont(Font.font("Comic Sans MS", 20));
        thanks.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 16));

        //create the cheese, pepperoni, and veggie comboboxes
        cheese1.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8); //adds the selectors 0 - 8 for each combo box
        pepperoni1.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8);
        veggie1.getItems().addAll(0, 1, 2, 3, 4, 5, 6, 7, 8);

        //add all the labels and text fields to the VBox vb
        vb.getChildren().addAll(pickSlice, cheese, pepperoni, veggie, total, thanks);

        //add all the combo boxes to the VBox vb2
        vb2.getChildren().addAll(cheese1, pepperoni1, veggie1);

        //align each of the VBoxes within the BorderPane object
        paneForCombos.setLeft(vb);
        paneForCombos.setRight(vb2);

        return paneForCombos; //return the BorderPane object
    }

    //Returns a Pane with images and buttons
    private Pane getImage(){
        Pane pane = new Pane();

        /*getResourceAsStream converts the file path to a url and tells the
          the stream to look in the current directory*/
        Image cheese = new Image(Main.class.getResourceAsStream("./cheese1.png"));
        Image pepperoni = new Image(Main.class.getResourceAsStream("./pepperoni.png"));
        Image veggie = new Image(Main.class.getResourceAsStream("./veggie.png"));

        //create ImageView objects to display the images
        ImageView cheese1 = new ImageView(); //cheese pizza image node
        ImageView pep1 = new ImageView(); //pepperoni image node
        ImageView veg1 = new ImageView(); //veggie image node

        //set the images into the pane
        cheese1.setImage(cheese);
        pep1.setImage(pepperoni);
        veg1.setImage(veggie);

        //size, filter, and cache the image to improve image quality of cheese1
        cheese1.setFitWidth(200); //fits the width to 200 pixels
        cheese1.setFitHeight(200); //fits the height to 200 pixels
        cheese1.setPreserveRatio(true); //preserves the ratio of the picture to make it look nice
        cheese1.setSmooth(true); //a little editing to smooth the picture and increase quality
        cheese1.setCache(true); //let the system know that rendering node should be cached as an internal image

        //size, filter, and cache the image to improve image quality of pepperoni image node ****
        pep1.setFitWidth(200); //same as cheese1 comments
        pep1.setFitHeight(200);
        pep1.setPreserveRatio(true);
        pep1.setSmooth(true);
        pep1.setCache(true);

        //size, filter, and cache the image to improve image quality of veg1
        veg1.setFitWidth(200); //same as cheese 1 comments
        veg1.setFitHeight(200);
        veg1.setPreserveRatio(true);
        veg1.setSmooth(true);
        veg1.setCache(true);

        //set the location of cheese1
        cheese1.setX(200);
        cheese1.setY(0);

        //set the location of pep1
        pep1.setX(50);
        pep1.setY(160);

        //set the location of veg1
        veg1.setX(350);
        veg1.setY(160);

        //style the buttons
        placeOrder.setStyle("-fx-font-size: 15px; -fx-font-family: Comic Sans MS;");

        //set the location of the buttons
        placeOrder.setLayoutX(20);
        placeOrder.setLayoutY(400);


        //add the image to the pane
        pane.getChildren().addAll(cheese1, pep1, veg1, placeOrder, cheeseArc, pepperoniArc, veggieArc);

        return pane;
    }

    //contains all the handlers and controls the flow of the program
    private Pane flowControl(){
        Pane pane = getImage(); //make the pane the same

        //event handler for cheese pizza
        cheese1.setOnAction(e -> { //if a value is selected from the combo box
            cheeseArc.setType(ArcType.ROUND); //set the arc as round for a slice
            cheeseArc.setFill(Color.DARKGREY); //fill it with dark grey to indicate slice missing
            cheeseArc.setLength((360/8) * cheese1.getValue()); //create the length of the arc based on the value from the combo box
            total.setText(String.format("Total: $ %.2f", calculateTotal())); //calculate the total

        });

        //event handler for pepperoni pizza
        pepperoni1.setOnAction(e -> { //same as cheese1's comments
            pepperoniArc.setType(ArcType.ROUND);
            pepperoniArc.setFill(Color.DARKGREY);
            pepperoniArc.setLength((360/8) * pepperoni1.getValue());
            total.setText(String.format("Total: $ %.2f", calculateTotal()));
        });

        //event handler for veggie pizza
        veggie1.setOnAction(e -> { //same as cheese 1's comments
            veggieArc.setType(ArcType.ROUND);
            veggieArc.setFill(Color.DARKGREY);
            veggieArc.setLength((360/8) * veggie1.getValue());
            total.setText(String.format("Total: $ %.2f", calculateTotal()));
        });

        //event handler for the place order button
        placeOrder.setOnAction(e -> { //if the place order button is pressed
            thanks.setText("Thanks for your order.\n It will be up shortly."); //Tell the customer thank you
            reset.setStyle("-fx-font-size: 15px; -fx-font-family: Comic Sans MS;"); //set the style for the reset button
            reset.setLayoutX(175); //set the x location for the reset button
            reset.setLayoutY(400); //set the y location for the reset button
            reset.setPrefWidth(100); //set the width of the reset button

            //prevents a RunTimeException from being thrown
            if(pane.getChildren().contains(reset)){ //if the reset button already exists
                thanks.setText(" Order Placed.\n Please press reset."); //give the user a warning
            }
            else{ //otherwise place the button on the pane
                pane.getChildren().add(reset); //add the reset button to the pane
            }
        });

        //event handler for the reset button
        reset.setOnAction(e -> { //if the reset button is pressed
            cheese1.setValue(0); //set the cheese combo box value to 0
            pepperoni1.setValue(0); //set the pepperoni combo box value to 0
            veggie1.setValue(0); //set the veggie combo box value to 0
            total.setText(String.format("Total: $ %.2f", calculateTotal())); //calculate the total
            thanks.setText(""); //set the text back to nothing
            pane.getChildren().remove(reset); //remove the reset button from the pane to place another order
        });

        return pane; //return the pane
    }

    //used to calculate the total
    private double calculateTotal(){
        double total = 0;

        //grab the values from the combo boxes and compute the total cost
        total = (cheese1.getValue()*1.50) + (pepperoni1.getValue()*2.25) + (veggie1.getValue()*2.00);

        return total; //return the total cost
    }

}

