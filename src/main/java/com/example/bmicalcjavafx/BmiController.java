package com.example.bmicalcjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class BmiController {
    public Button calcButton;
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField weightTextField;

    @FXML
    private TextField heightTextField;

    @FXML
    private Label resultLabel;


    @FXML
    protected void onCalcButtonClick() {
        if(checkFormIsValid()) {
            handleCalcBmi();
        }

    }

    private boolean checkFormIsValid() {

        // Sätter lite olika styles så användaren vet att den gjort fel
        if(nameTextField.getText().isBlank() || weightTextField.getText().isBlank() || heightTextField.getText().isBlank()) {

            if (nameTextField.getText().isBlank()) {
                nameTextField.setPromptText("Please Enter name");
                nameTextField.setStyle(
                        "-fx-border-color: red;" +
                                "-fx-border-width: 2");
            }
            if (weightTextField.getText().isBlank()) {
                weightTextField.setPromptText("Please Enter Weight");
                weightTextField.setStyle(
                        "-fx-border-color: red;" +
                                "-fx-border-width: 2");
            }
            if (heightTextField.getText().isBlank()) {
                heightTextField.setPromptText("Please Enter Height");
                heightTextField.setStyle(
                        "-fx-border-color: red;" +
                                "-fx-border-width: 2");
            }
            return false;
        }
        return true;
    }

    private void handleCalcBmi() {
        try {
            nameTextField.setStyle("-fx-border-color: green;");
            weightTextField.setStyle("-fx-border-color: green;");
            heightTextField.setStyle("-fx-border-color: green;");

            double weight = Double.parseDouble(weightTextField.getText());
            double height = Double.parseDouble(heightTextField.getText());
            double bmi = calcBmi(weight, height);
            setResultLabelBmi(bmi);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Only Numbers please!");
            nameTextField.setStyle("-fx-border-color: red; -fx-border-width: 2");
            weightTextField.setStyle("-fx-border-color: red; -fx-border-width: 2");
            heightTextField.setStyle("-fx-border-color: red; -fx-border-width: 2");
        }
    }

    private double calcBmi(double weight, double height) {
        return weight  / (height/100.0 * height/100.0 );
    }

    private void setResultLabelBmi(double resultBmi) {
        DecimalFormat df = new DecimalFormat("###.#"); // För avrundning av resultatet

        String bmiRounded = df.format(resultBmi);
        String name = nameTextField.getText().trim();

        // Control Flow, Bestämmer Viktklass
        if (resultBmi < 18.5) {
            resultLabel.setText(name + ",  " + bmiRounded + " Under Weight");
        } else if (resultBmi >= 18.5 && resultBmi <= 25) {
            resultLabel.setText(name + ", " + bmiRounded + " Normal Weight");
        } else if (resultBmi > 25 && resultBmi <= 30) {
            resultLabel.setText(name + ", " + bmiRounded + " Over Weight");
        } else if (resultBmi > 30 && resultBmi <= 35) {
            resultLabel.setText(name + ",  " + bmiRounded + " Obese Class 1");
        } else if (resultBmi > 35 && resultBmi <= 40) {
            resultLabel.setText(name + ",  " + bmiRounded + " Obese Class 2");
        } else {
            resultLabel.setText(name + ", " + bmiRounded + " Obese class 3");
        }
    }
}