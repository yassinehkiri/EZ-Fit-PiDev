/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
//import services.AmisService;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author gaming
 */
public class AdminUserController implements Initializable {
    @FXML
    private Button btn;
    @FXML
    private TextField textfield;
    @FXML
    private TextField textfield1;
    @FXML
    private TableView<Utilisateur> table;
    @FXML
    private TableColumn<Utilisateur, String> Mailt;
    @FXML
    private TableColumn<Utilisateur, String> Mdpt;
    @FXML
    private TableColumn<Utilisateur, String> Rolet;
    ObservableList<Utilisateur> list1;
    UtilisateurService si = new UtilisateurService();
    List<Utilisateur> list = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        afficher();
        // TODO
    }    
    public void afficher(){
    System.out.println(list);
            list = si.find();

        list1 =FXCollections.observableArrayList(list);          
    Mailt.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
        Mdpt.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("mdp"));
        Rolet.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("role"));
        
    table.setItems(FXCollections.observableArrayList(list1));
        FilteredList<Utilisateur> filteredData = new FilteredList<>(list1, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		textfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(competition -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (competition.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				}
			
				     else  
				    	 return false; // Does not match.
			});
		});
                SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
                FilteredList<Utilisateur> filteredData1 = new FilteredList<>(sortedData, b -> true);
		textfield1.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData1.setPredicate(competition -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
			if (competition.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1)
				     return true;
			
				     else  
				    	 return false; // Does not match.
			});
		});
                	
		// 3. Wrap the FilteredList in a SortedList. 
	// 3. Wrap the FilteredList in a SortedList. 
		
		   SortedList<Utilisateur> sortedData1 = new SortedList<>(filteredData1);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData1.comparatorProperty().bind(table.comparatorProperty());
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData1);

		//comp.setItems(sortedData);
        
    
}
    @FXML
    public void supprimer (){
        Utilisateur e = table.getSelectionModel().getSelectedItem();
        si.supprimer(e);
        afficher();
    }
    @FXML
           public void goToLogin(ActionEvent event) throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Authentification.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }
    
}
