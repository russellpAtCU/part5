module com.example.part5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.part5 to javafx.fxml;
    exports main.part5;
}