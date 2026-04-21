package com.abdullaevaziz.controllers;

import com.abdullaevaziz.main.App;
import com.abdullaevaziz.model.Document;
import com.abdullaevaziz.service.PrintDispatcherService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MainController {

    @FXML
    public TextField tPrintingOrder;
    @FXML
    public TextField tFieldPrintDuration;
    @FXML
    public TextField tFieldNameDocument;
    @FXML
    public TextField tFieldSizeDocument;
    @FXML
    public TextField tСalculateAverage;
    @FXML
    public ListView<Document> listViewDocument;

    @FXML
    private ComboBox<String> comboBoxSort = new ComboBox<>();

    private PrintDispatcherService printDispatcherService = new PrintDispatcherService();
    private Document document;


    @FXML
    public void initialize() {
        ArrayList<String> documentArrayList = new ArrayList<>();
        documentArrayList.add("Printing order");
        documentArrayList.add("Name");
        documentArrayList.add("Duration");
        documentArrayList.add("Size");
        this.comboBoxSort.setItems(FXCollections.observableList(documentArrayList));

    }

    @FXML
    public void addDocumentButton(ActionEvent actionEvent) {

        int printingOrder = Integer.parseInt(tPrintingOrder.getText());
        String nameDocument = tFieldNameDocument.getText();
        double printDuration = Double.parseDouble(tFieldPrintDuration.getText());
        String sizeDocument = tFieldSizeDocument.getText();
        document = new Document(printingOrder, nameDocument, printDuration, sizeDocument);
        printDispatcherService.addQueue(document);
        printDispatcherService.addPrintList(document);
        this.listViewDocument.getItems().add(document);

    }

    @FXML
    public void sortDocumentButton(ActionEvent actionEvent) {

        String selectedItem = this.comboBoxSort.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            App.showAlert("Error!", "Select Document", Alert.AlertType.ERROR);
            return;
        }
        ArrayList<Document> sortedDocuments = printDispatcherService.sortDocumentList(selectedItem);
        this.listViewDocument.getItems().clear();
        this.listViewDocument.getItems().addAll(sortedDocuments);
    }


    /**
     * Остановка диспетчера. Печать документов в очереди отменяется.
     * На выходе должен быть список ненапечатанных документов.
     */
    @FXML
    public void closeButton(ActionEvent actionEvent) {
        ArrayList<Document> arrayList = this.printDispatcherService.stopPrintDocuments();
        this.listViewDocument.getItems().addAll(document.getArrayList());
        App.showAlert("Info!", "Печать остановлена", Alert.AlertType.INFORMATION);
        this.listViewDocument.getItems().clear();
        this.listViewDocument.getItems().addAll(arrayList);
    }


    public void calculateAverageButton(ActionEvent actionEvent) {
        double getRes = Double.parseDouble(tFieldPrintDuration.getText());
        double res = printDispatcherService.calculateAveragePrintTime(getRes);
        tСalculateAverage.setText(String.valueOf(res));
    }
}
