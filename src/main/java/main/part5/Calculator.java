package main.part5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {

    double num1;
    double num2;

    String op;
    double ans;

    boolean onSecond;

    public Calculator(){
        num1 = 0;
        num2 = 0;

        op = "";
        ans = 0;
        onSecond = false;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        GridPane root = new GridPane();
        Scene scene = new Scene(root, 400, 600);
        scene.getStylesheets().add( getClass().getResource("application.css").toExternalForm() );
        root.getStyleClass().add("gridclass");

        TextField textField = new TextField();
        textField.setEditable(false);
        textField.getStyleClass().add("textboxclass");
        root.add(textField, 0, 0, 5, 1);

        EventHandler<ActionEvent> numButtonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Button button = (Button) actionEvent.getSource();
                textField.appendText(button.getText());
                System.out.println(textField);
            }
        };

        Button[] numButtons = new Button[10];
        for(int i = 0; i < 10; i++){
            numButtons[i] = new Button();
            numButtons[i].setText(Integer.toString((i+1)   % 10));
            numButtons[i].setOnAction(numButtonHandler);
            numButtons[i].getStyleClass().add("buttonclass");
            root.add(numButtons[i], (i % 3), (i / 3)+1);
        }


        EventHandler<ActionEvent> opButtonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Button button = (Button) actionEvent.getSource();
                num1 = Double.parseDouble(textField.getText());
                op = button.getText();
                onSecond = true;
                textField.setText("");
                System.out.println(num1);
            }
        };
        EventHandler<ActionEvent> equalsHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                num2 = Double.parseDouble(textField.getText());
                ans = solve(num2, num2, op);
                onSecond = false;
                textField.setText(Double.toString(ans));
            }
        };
        EventHandler<ActionEvent> dotHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textField.appendText(".");
            }
        };
        EventHandler<ActionEvent> negHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textField.setText("-" + textField.getText());
            }
        };
        EventHandler<ActionEvent> cHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textField.setText("");
            }
        };
        EventHandler<ActionEvent> ceHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                textField.setText("");
                onSecond = false;

            }
        };

        Button[] ops = new Button[11];

        Button plus = new Button();
        plus.setText("+");
        ops[0] = plus;

        Button minus = new Button();
        minus.setText("-");
        ops[1] = minus;

        Button times = new Button();
        times.setText("*");
        ops[2] = times;

        Button divide = new Button();
        divide.setText("/");
        ops[3] = divide;

        Button square = new Button();
        square.setText("^");
        ops[4] = square;

        Button dot = new Button();
        dot.setText(".");
        dot.setOnAction(dotHandler);
        ops[5] = dot;

        Button neg = new Button();
        neg.setText("(-)");
        neg.setOnAction(negHandler);
        ops[6] = neg;

        Button sqrt = new Button();
        sqrt.setText("sqrt");
        op = "sqrt";
        sqrt.setOnAction(equalsHandler);
        ops[7] = sqrt;

        Button equals = new Button();
        equals.setText("=");
        equals.setOnAction(equalsHandler);
        ops[8] = equals;

        Button clear = new Button();
        clear.setText("C");
        clear.setOnAction(cHandler);
        ops[9] = clear;

        Button clearEntry = new Button();
        clearEntry.setText("CE");
        clearEntry.setOnAction(ceHandler);
        ops[10] = clearEntry;



        for(int i = 0; i < 11; i++){
            if(i < 5) {
                ops[i].setOnAction(opButtonHandler);
                root.add(ops[i], 3, i+1);
            }
            if(i > 6)
                root.add(ops[i], 4, i-6);
            ops[i].getStyleClass().add("buttonclass");
        }
        root.add(ops[5],1, 4 );
        root.add(ops[6],2, 4 );


        stage.setScene(scene);
        stage.show();

    }

    private Double solve(double num1, double num2, String op){
        return switch (op) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            case "^" -> Math.pow(num1, num2);
            case "sqrt" -> Math.sqrt(num1);
            default -> 0.0;
        };
    }

}
