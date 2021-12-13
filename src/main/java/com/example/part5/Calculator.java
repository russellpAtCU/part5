package com.example.part5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {

    int num1;
    int num2;
    String op;
    Double ans;

    public Calculator(){
        num1 = 0;
        num2 = 0;
        op = "";
        ans = 0.0;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        Scene scene = new Scene(root, 200, 400);

        HBox[] hBoxes = new HBox[6];
        for(int i = 0; i < 6; i++){
            hBoxes[i] = new HBox();
            root.getChildren().add(hBoxes[i]);
        }

        TextField textField = new TextField();
        textField.setEditable(false);
        hBoxes[0].getChildren().add(textField);

        EventHandler<ActionEvent> numButtonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Button button = (Button) actionEvent.getSource();
                textField.appendText(button.getText());
                System.out.println(textField);
            }
        };

        EventHandler<ActionEvent> opButtonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Button button = (Button) actionEvent.getSource();
                num1 = Integer.getInteger(textField.getText());
                op = button.getText();
                textField.setText("");
                System.out.println(num1);
            }
        };

        Button[] numButtons = new Button[10];
        for(int i = 0; i < 10; i++){
            numButtons[i] = new Button();
            numButtons[i].setText(Integer.toString((i+1)   % 10));
            numButtons[i].setOnAction(numButtonHandler);
            hBoxes[(i+3) / 3].getChildren().add(numButtons[i]);
        }

        Button[] ops = new Button[9];

        Button dot = new Button();
        dot.setText(".");
        ops[0] = dot;
        Button plus = new Button();
        plus.setText("+");
        ops[1] = plus;
        Button minus = new Button();
        minus.setText("-");
        ops[2] = minus;
        Button times = new Button();
        times.setText("*");
        ops[3] = times;
        Button divide = new Button();
        divide.setText("/");
        ops[4] = divide;
        Button neg = new Button();
        neg.setText("(-)");
        ops[5] = neg;
        Button square = new Button();
        square.setText("^");
        ops[6] = square;
        Button sqrt = new Button();
        sqrt.setText("sqrt");
        ops[7] = sqrt;

        EventHandler<ActionEvent> equalsHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                num2 = Integer.getInteger(textField.getText());
                ans = solve();
            }
        };

        Button equals = new Button();
        equals.setText("=");
        ops[8] = equals;

        for(int i = 0; i < 8; i++){
            if(ops[i] != equals && ops[i] != dot)
                ops[i].setOnAction(opButtonHandler);
            hBoxes[(i+2) / 2].getChildren().add(ops[i]);
        }

        stage.setScene(scene);
        stage.show();

    }

    private Double solve(){
        return 0.0;
    }

}
