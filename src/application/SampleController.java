package application;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import bean.Airline;
import bean.Airport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class SampleController {
	
	private Model m = new Model();
	
	public void setModel(Model m){
		this.m=m;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Airline> comboCompagnia;

    @FXML
    private Button bntAereoportiServiti;

    @FXML
    private ComboBox<?> comboAereo;

    @FXML
    private Button btnRaggiunbibili;

    @FXML
    private TextArea txtResult;

    @FXML
    void doRaggiungibili(ActionEvent event) {

    }

    @FXML
    void doServiti(ActionEvent event) {
    	txtResult.clear();
    	Airline a = comboCompagnia.getValue();
    	if(comboCompagnia.getValue()==null){
    		txtResult.appendText("Seleziona una compagnia !\n");
    		return;
    	}
    	DefaultDirectedWeightedGraph<Airport, DefaultWeightedEdge>grafo = m.buildGraph(a.getAirlineId());
    	txtResult.appendText(grafo.toString());
    	
    	

    }

    @FXML
    void initialize() {
        assert comboCompagnia != null : "fx:id=\"comboCompagnia\" was not injected: check your FXML file 'Sample.fxml'.";
        assert bntAereoportiServiti != null : "fx:id=\"bntAereoportiServiti\" was not injected: check your FXML file 'Sample.fxml'.";
        assert comboAereo != null : "fx:id=\"comboAereo\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnRaggiunbibili != null : "fx:id=\"btnRaggiunbibili\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

        comboCompagnia.getItems().addAll(m.getComp());
    }
}

