import java.util.Stack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Calculator extends Application {
    // the calculator dimensions
    public static int CALC_WIDTH = 220;
    public static int CALC_HEIGHT = 268;
    
    // We know that StringBuilder is mutable unlike String and will be more efficient
    private StringBuilder currNumber;
    
    private Stack<Double> operandStack;
    private Stack<Operator> operatorStack;
    
    private BorderPane gui;

    // the calculator screen
    private TextField screen; 
    
    // the calculator keypad
    private GridPane keypad;
    
    private ButtonHandler handler;
    
    // the values that appear on buttons
    private String[][] buttonTexts;
    
    @Override
    public void start(Stage primaryStage) {
    	currNumber = new StringBuilder();
    	operandStack = new Stack<>();
    	operatorStack = new Stack<>();
        
        handler = new ButtonHandler();  
        
        createScreen();
        createKeypad();
        
        
        // put screen and keypad together
        gui = new BorderPane();
        gui.setStyle("-fx-background-color: #2e2e2e;"); // dark grey
        
        gui.setTop(screen);
        gui.setCenter(keypad);
        

        primaryStage.initStyle(StageStyle.UTILITY);
        
        // set up the scene
        Scene scene = new Scene(gui); 
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        


        
        primaryStage.setResizable(false);

        primaryStage.show();
    }
    
    public void createScreen() {
        // create the calculator screen
        screen = new TextField(); 
        
        screen.setEditable(false);
        
        screen.setAlignment(Pos.BOTTOM_RIGHT);
        
        screen.setPrefHeight(80);
        screen.setPrefWidth(CALC_WIDTH);
        screen.setStyle(
                "-fx-background-color: linear-gradient(to top, #5a5a5a, #3a3a3a); " + // Lighter grey gradient background
                "-fx-text-fill: white; " + // Text color
                "-fx-font-size: 24px; ");
    }
    
    public void createKeypad() {
        keypad = new GridPane();  
        
        keypad.setMinSize(CALC_WIDTH, CALC_HEIGHT); 
        keypad.setPadding(new Insets(2, 2, 2, 2));  
        
        keypad.setVgap(3); 
        keypad.setHgap(2);       
        
        keypad.setAlignment(Pos.CENTER);
        
        
        buttonTexts = new String[][]{{"(", ")", "C", "xʸ"},
		  	 						 {"1", "2", "3", "+"},
		  	 						 {"4", "5", "6", "-"},
		  	 						 {"7", "8", "9", "×"},
		  	 						 {".", "0", "=", "÷"}};
        
        for (int r = 0; r < buttonTexts.length; r++) {
        	for (int c = 0; c < buttonTexts[0].length; c++) {
        		String currText = buttonTexts[r][c];
        		Button button = new Button(currText);
        		
        		button.setOnAction(handler);
        		
        		setButtonStyle(button);
        		
        		keypad.add(button, c, r);
        	}
        }
    }
    
    // Handler for processing the button clicks 
    private class ButtonHandler implements EventHandler<ActionEvent> { 
       @Override 
       public void handle(ActionEvent e) {
    	   
    	   Button source = (Button) e.getSource();
    	   
    	   String buttonText = source.getText();
    	   
    	   screen.appendText(buttonText == "xʸ" ? "^" : buttonText);
    	   
    	   switch(buttonText) {
    	   		case "1": case "2": case "3": case "4": case "5": case "6": case "7": case "8": case "9": case "0": case ".":
    	   			currNumber.append(buttonText);
    	   			break;
    	   			
    	   		case "+":
    	   			AddOperator addOperator = new AddOperator();
    	   			handleOperator(addOperator);
    	   			break;
    	   		case "-":
    	   			MinusOperator minusOperator = new MinusOperator();
    	   			handleOperator(minusOperator);
    	   			break;
    	   		case "×":
    	   			MulOperator mulOperator = new MulOperator();
    	   			handleOperator(mulOperator);
    	   			break;
    	   		case "÷":
    	   			DivOperator divOperator = new DivOperator();
    	   			handleOperator(divOperator);
    	   			break;
    	   		case "xʸ":
    	   			PowerOperator powerOperator = new PowerOperator();
    	   			handleOperator(powerOperator);
    	   			break;	
    	   		case "(":
    	   			LeftParenOperator leftParenOperator = new LeftParenOperator();
    	   			operatorStack.push(leftParenOperator);
    	   			break;
    	   		case ")":
    	   			RightParenOperator rightParenOperator = new RightParenOperator();
    	   			handleOperator(rightParenOperator);
    	   			operatorStack.pop();
    	   			operatorStack.pop();
    	   			break;
    	   		case "C":
    	   			currNumber.setLength(0);
    	   			
    	   			screen.clear();
    	   			operatorStack.clear();
    	   			operandStack.clear();
    	   			break;
    	   		case "=":
    	   			EqualsOperator equalsOperator = new EqualsOperator();
    	   			handleOperator(equalsOperator);
    	   			
    	   			operatorStack.pop();
    	   			
    	   			screen.clear();
    	   			
    	   			// formatting the string to remove trailing .0 from integer values
//    	   			String result = String.format("%.0f", operandStack.peek());
    	   			
    	   			screen.appendText(operandStack.peek().toString());
    	   }
       } 
    }
    
    private void handleOperator(Operator currOperator) {
    	
    	if (currNumber.length() != 0) {
    		String currNumberString = currNumber.toString();
    		operandStack.push(Double.parseDouble(currNumberString));
    		
        	currNumber.setLength(0);
    	}
    	
    	
    	while (!operatorStack.isEmpty() && currOperator.getPrecedence() <= operatorStack.peek().getPrecedence()) {
    		double num2 = operandStack.pop();
    		double num1 = operandStack.pop();
    		
    		double result = operatorStack.peek().evaluate(num1, num2);
    		
    		operandStack.push(result);
    		
    		operatorStack.pop();
    	}
   
    	operatorStack.push(currOperator);
    }

 // Method to set preferred style for buttons
    private void setButtonStyle(Button button) {
    	// An easier way of doing this could be with a map
            String baseStyle = "-fx-background-radius: 2; "
                    + "-fx-border-radius: 10; "
                    + "-fx-text-fill: white; "
                    + "-fx-font-size: 16px; "
                    + "-fx-min-width: 72px; "
                    + "-fx-min-height: 50px; "
                    + "-fx-max-width: 150px; "
                    + "-fx-max-height: 50px; "
                    + "-fx-border-color: transparent;";

            String buttonSpecificStyle;
            String hoverStyle;
            String pressedStyle;

            switch (button.getText()) {
                case "1": case "2": case "3": case "4": case "5": case "6": case "7": case "8": case "9": case "0": case ".":
                    buttonSpecificStyle = "-fx-background-color: linear-gradient(to bottom, #b0b0b0, #8a8a8a);"; // Darker grey gradient
                    hoverStyle = "-fx-background-color: linear-gradient(to bottom, #a0a0a0, #7a7a7a);"; // Lighter grey
                    pressedStyle = "-fx-background-color: linear-gradient(to bottom, #8c8c8c, #646464);"; // Darker grey
                    break;

                // Darker grey gradient for special buttons
                case "C":
                case "(":
                case ")":
                    buttonSpecificStyle = "-fx-background-color: linear-gradient(to bottom, #9e9e9e, #616161);"; // Darker grey
                    hoverStyle = "-fx-background-color: linear-gradient(to bottom, #7e7e7e, #404040);"; // Lighter grey
                    pressedStyle = "-fx-background-color: linear-gradient(to bottom, #7e7e7e, #404040);"; // Darker grey
                    break;

                // Orange gradient for operators and equals
                case "+": case "-": case "×": case "÷": case "=": case "xʸ":
                    buttonSpecificStyle = "-fx-background-color: linear-gradient(to bottom, #ff8c00, #e07b00);"; // Orange gradient
                    hoverStyle = "-fx-background-color: linear-gradient(to bottom, #ff7f00, #d06500);"; // Lighter orange
                    pressedStyle = "-fx-background-color: linear-gradient(to bottom, #e06f00, #b75c00);"; // Darker orange
                    break;

                default:
                // Default style if the button does not match any specific case
                    buttonSpecificStyle = "-fx-background-color: linear-gradient(to bottom, #3498db, #2980b9);"; // Default blue gradient
                    hoverStyle = "-fx-background-color: linear-gradient(to bottom, #5dade2, #3498db);"; // Lighter blue
                    pressedStyle = "-fx-background-color: linear-gradient(to bottom, #1c6ea4, #1a5d8a);"; // Darker blue
                    break;
            }

            // Apply the base style and button-specific style
            button.setStyle(baseStyle + buttonSpecificStyle);

            // Handle hover effect
            button.setOnMouseEntered(e -> button.setStyle(baseStyle + buttonSpecificStyle + hoverStyle));
            button.setOnMouseExited(e -> button.setStyle(baseStyle + buttonSpecificStyle)); // Revert to base style on exit

            // Handle press effect
            button.setOnMousePressed(e -> button.setStyle(baseStyle + buttonSpecificStyle + pressedStyle));
            button.setOnMouseReleased(e -> button.setStyle(baseStyle + buttonSpecificStyle + hoverStyle)); // Change to hover style when released
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

