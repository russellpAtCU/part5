package main.part5;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Calculator extends Application {

    double num1;
    double num2;

    String op;
    double ans;

    boolean onSecond;
    boolean isAns;

    public Calculator(){
        num1 = 0;
        num2 = 0;

        op = "";
        ans = 0;
        onSecond = false;
        isAns = false;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {



        VBox root = new VBox();
        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add( getClass().getResource("application.css").toExternalForm() );
        root.getStyleClass().add("vbox");

        HBox[] hBoxes = new HBox[5];
        for(int i = 0; i < 5; i++){
            hBoxes[i] = new HBox();
            hBoxes[i].getStyleClass().add("hbox");
            root.getChildren().add(hBoxes[i]);
        }
        HBox sliderBox = new HBox();
        sliderBox.getStyleClass().add("hbox");
        root.getChildren().add(sliderBox);

        TextField textField = new TextField();
        textField.setEditable(false);
        hBoxes[0].getChildren().add(textField);
        textField.getStyleClass().add("textbox");

        EventHandler<ActionEvent> numButtonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Button button = (Button) actionEvent.getSource();
                if(isAns){
                    isAns = false;
                    textField.setText("");
                }
                textField.appendText(button.getText());
            }
        };

        Slider slider = new Slider();
        slider.setMinWidth(300);
        slider.setMax(100);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                textField.setText(Integer.toString((int) Math.round((double) observableValue.getValue())));
            }
        });
        sliderBox.getChildren().add(slider);


        Button[] numButtons = new Button[10];
        for(int i = 0; i < 10; i++){
            numButtons[i] = new Button();
            numButtons[i].setText(Integer.toString((i+1)   % 10));
            numButtons[i].setOnAction(numButtonHandler);
            numButtons[i].getStyleClass().add("button");
            hBoxes[(i) / 3 + 1].getChildren().add(numButtons[i]);
        }


        EventHandler<ActionEvent> opButtonHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(isAns){
                    textField.setText("");
                    isAns = false;
                }
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
                ans = solve(num1, num2, op);
                onSecond = false;
                isAns = true;
                textField.setText(Double.toString(ans));
                System.out.println(num2);
            }
        };
        EventHandler<ActionEvent> sqrtHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                num1 = Double.parseDouble(textField.getText());
                ans = solve(num1, num2, "sqrt");
                onSecond = false;
                isAns = true;
                textField.setText(Double.toString(ans));
                System.out.println(num2);
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
        sqrt.setOnAction(sqrtHandler);
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
            }
            hBoxes[i / 3 + 1].getChildren().add(ops[i]);
            ops[i].getStyleClass().add("buttonclass");
        }

        EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(isAns){
                    textField.setText("");
                    isAns = false;
                }
                switch (keyEvent.getCode()){
                    case DIGIT0:
                    case NUMPAD0:
                        textField.appendText("0");
                        break;
                    case DIGIT1:
                    case NUMPAD1:
                        textField.appendText("1");
                        break;
                    case DIGIT2:
                    case NUMPAD2:
                        textField.appendText("2");
                        break;
                    case DIGIT3:
                    case NUMPAD3:
                        textField.appendText("3");
                        break;
                    case DIGIT4:
                    case NUMPAD4:
                        textField.appendText("4");
                        break;
                    case DIGIT5:
                    case NUMPAD5:
                        textField.appendText("5");
                        break;
                    case DIGIT6:
                    case NUMPAD6:
                        if(keyEvent.isShiftDown()){
                            num1 = Double.parseDouble(textField.getText());
                            op = "^";
                            onSecond = true;
                            textField.setText("");
                            System.out.println(num1);
                        }
                        else {
                            textField.appendText("6");
                        }
                        break;
                    case DIGIT7:
                    case NUMPAD7:
                        textField.appendText("7");
                        break;
                    case DIGIT8:
                    case NUMPAD8:
                        if(keyEvent.isShiftDown()){
                            num1 = Double.parseDouble(textField.getText());
                            op = "*";
                            onSecond = true;
                            textField.setText("");
                            System.out.println(num1);
                        }
                        else {
                            textField.appendText("8");
                        }
                        break;
                    case DIGIT9:
                    case NUMPAD9:
                        textField.appendText("9");
                        break;
                    case MINUS:
                        num1 = Double.parseDouble(textField.getText());
                        op = "-";
                        onSecond = true;
                        textField.setText("");
                        System.out.println(num1);
                        break;
                    case SLASH:
                        num1 = Double.parseDouble(textField.getText());
                        op = "/";
                        onSecond = true;
                        textField.setText("");
                        System.out.println(num1);
                        break;
                    case PERIOD:
                        textField.appendText(".");
                        break;
                    case EQUALS:
                        if(keyEvent.isShiftDown()){
                            num1 = Double.parseDouble(textField.getText());
                            op = "+";
                            onSecond = true;
                            isAns = true;
                            textField.setText("");
                            System.out.println(num1);
                        }
                        else {
                            num2 = Double.parseDouble(textField.getText());
                            ans = solve(num1, num2, op);
                            onSecond = false;
                            isAns = true;
                            textField.setText(Double.toString(ans));
                            System.out.println(num2);
                        }
                        break;
                    case C:
                        textField.setText("");
                    default:
                        textField.appendText("");

                }
            }
        };

        scene.setOnKeyPressed(keyListener);
        textField.setOnKeyPressed(keyListener);

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
