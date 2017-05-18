package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Fermata> boxPartenza;

    @FXML
    private ComboBox<Fermata> boxArrivo;

    @FXML
    private Button btnPercorso;

    @FXML
    private TextArea txtResult;

    @FXML
    void calcolaPercorso(ActionEvent event) {
    	if (boxPartenza.getValue()!=null && boxArrivo.getValue()!=null){
    		List<Fermata> percorso = model.percorsoMinimo(boxPartenza.getValue(), boxArrivo.getValue());
    		txtResult.appendText(""+percorso);
    		double tempo = model.getPeso()+((percorso.size()-2)*(0.5));
    		txtResult.appendText("\nTempo totale: "+ tempo);
    	}
    }
    
    @FXML
    void calcolaArrivo(ActionEvent event) {
    	boxArrivo.getItems().addAll(model.getRaggiungibili(boxPartenza.getValue()));
    }

    @FXML
    void initialize() {
        assert boxPartenza != null : "fx:id=\"boxPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert boxArrivo != null : "fx:id=\"boxArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MetroDeParis.fxml'.";

    }
    
    void setModel(Model m){
    	this.model=m;
    	boxPartenza.getItems().addAll(m.getFermate());
    }
}
