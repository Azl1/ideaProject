package com.abdullaevaziz.brothelfx.controllers.prostitutecontrollers;

import com.abdullaevaziz.brothelfx.App;
import com.abdullaevaziz.brothelfx.controllers.ControllerData;
import com.abdullaevaziz.brothelfx.model.ProstituteIndividual;
import com.abdullaevaziz.brothelfx.repository.ProstituteRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdateProstitute implements ControllerData<ProstituteIndividual> {

    @FXML
    public TextField fioPrstTextField;
    @FXML
    public TextField agePrstTextField;
    @FXML
    public TextField weightPrstTextField;
    @FXML
    public TextField specializationPrstTextField;
    @FXML
    public TextField pricePrstTextField;
    @FXML
    public TextField idClientTextField;


    private ProstituteIndividual prostituteIndividual;
    private ProstituteRepository prostituteRepository = new ProstituteRepository();


    @Override
    public void initData(ProstituteIndividual valueProstitute) throws IOException {
        prostituteIndividual = valueProstitute;
        fioPrstTextField.setText(valueProstitute.getFio());
        agePrstTextField.setText(String.valueOf(valueProstitute.getAge()));
        weightPrstTextField.setText(String.valueOf(valueProstitute.getWeight()));
        specializationPrstTextField.setText(valueProstitute.getSpecialization());
        pricePrstTextField.setText(String.valueOf(valueProstitute.getPricePerHour()));
        idClientTextField.setText(String.valueOf(valueProstitute.getId_cl()));
    }

    @FXML
    public void addProstitute(ActionEvent actionEvent) {
        prostituteIndividual.setFio(String.valueOf(fioPrstTextField.getText()));
        prostituteIndividual.setAge(Integer.parseInt(agePrstTextField.getText()));
        prostituteIndividual.setWeight(Integer.parseInt(weightPrstTextField.getText()));
        prostituteIndividual.setPricePerHour(Double.parseDouble(pricePrstTextField.getText()));
        prostituteIndividual.setSpecialization(String.valueOf(specializationPrstTextField.getText()));
        prostituteIndividual.setId_cl(Integer.parseInt(idClientTextField.getText()));
        try {
            if (prostituteIndividual != null) {
              prostituteRepository.update(prostituteIndividual);
                App.closeWindow(actionEvent);
            } else {
                System.out.println("Репозиторий не инициализирован");
            }
        } catch (IOException e) {
            App.showAlert("Error!", "Error contacting server!", Alert.AlertType.ERROR);
        }
    }
}
