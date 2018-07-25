package it.polito.tdp.porto;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	
	Model model = new Model();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;

    public Model getModel() {
		return model;
	}
     
	public void setModel(Model model) {
		this.model = model;
		model.createGraph();
		boxPrimo.getItems().addAll(model.autori);
	}

    
    @FXML
    void handleCoautori(ActionEvent event) {
    		model.createGraph();
    		Author autore = boxPrimo.getValue();
    		List<Author> coautori = model.getCoautori(autore);
    		this.txtResult.appendText("co-autori = " + coautori.toString());
    		this.boxSecondo.getItems().addAll(model.getAltriAutori(coautori));

    }

    @FXML
    void handleSequenza(ActionEvent event) {
    		this.txtResult.clear();
    		Author start = boxPrimo.getValue();
    		Author arr = boxSecondo.getValue();
    		this.txtResult.setText(model.getSequenzaArticoli(start, arr).toString());
    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	
    
    
}
