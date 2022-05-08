/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Salle;
import Service.Salleservice;
import Service.scontrole;
import Tools.MaConnexion;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author lamis
 */
public class SalleController implements Initializable {

    @FXML
    private AnchorPane imagesalle;
    @FXML
    private TextField noms;
    @FXML
    private TextField adresses;
    @FXML
    private TextField codep;
    @FXML
    private TextField nbr;
   
    @FXML
    private Button ajout;
    @FXML
    private Button mod;
    @FXML
    private TableView<Salle> saletable;
    @FXML
    private TableColumn<Salle, Integer> idvs;
    @FXML
    private Button supp;
    @FXML
    private TextField rechercher;
    @FXML
    private Button upload;
    @FXML
    private Button clearr;
    @FXML
    private ImageView imageview;
    
      ObservableList<Salle>  platList = FXCollections.observableArrayList();
  Salle ss=new Salle();
     Statement ste;
    private Salle r;
    String query = null;
    Connection connection = null ;
    Connection cnx=MaConnexion.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    @FXML
    private TextField vill;
  scontrole sc=new scontrole();
    @FXML
    private TableColumn<Salle, String> adressee;
    @FXML
    private TableColumn<Salle, Integer> codepp;
    @FXML
    private TableColumn<Salle, String> villev;
    @FXML
    private TableColumn<Salle, Integer> nbrplacee;
    @FXML
    private TableColumn<Salle, String> imagee;
    @FXML
    private TableColumn<Salle, String> nomsS;
  
  
    @FXML
    private TextField imgg;
    private TextField id;
    @FXML
    private Button bb;
    @FXML
    private Button excel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        load();
         refsalle();
    }    

    @FXML
    private void ajouter(ActionEvent event) {
          connection=MaConnexion.getInstance().getCnx();
        String nom_s =noms.getText();
        String adresse_s = adresses.getText();
      
        int code_postal =Integer.valueOf(codep.getText());
         String ville = vill.getText();
           int nombre_p =Integer.valueOf(nbr.getText());
       
        Salle s = new Salle(nom_s,adresse_s,code_postal,ville,nombre_p,imgg.getText());
       
     
        if( !sc.Controlechar(s)||nom_s.isEmpty()||!sc.Controlechar1(s)||ville.isEmpty()||adresse_s.isEmpty()||codep.getText().isEmpty()||nbr.getText().isEmpty()){
            Alert alert =new Alert(Alert.AlertType.WARNING);
            
            alert.setContentText("verifier les champs");
            alert.showAndWait();
        }
        
        else{

         Salleservice ps = new Salleservice();
            ps.ajouter(s);
             Notifications notificationBuild = Notifications.create()
                                      .title("Traitement salle ")
                                      .text(" salle est ajouté avec succes")
                                      .graphic(null)
                                      //.hideAfter(Duration.Hours(5))
                                      .position(Pos.CENTER)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }
                                  
                              });
                              notificationBuild.show(); 

             }
refsalle();
load();
       
     
    }
    private void load() {
    Salleservice pp=new Salleservice();
    connection= MaConnexion.getInstance().getCnx();
    refsalle();
    idvs.setCellValueFactory(new PropertyValueFactory<>("id"));
    nomsS.setCellValueFactory(new PropertyValueFactory<>("nom_s"));
    adressee.setCellValueFactory(new PropertyValueFactory<>("adresse_s"));
    codepp.setCellValueFactory(new PropertyValueFactory<>("code_postal"));
    villev.setCellValueFactory(new PropertyValueFactory<>("ville"));
    nbrplacee.setCellValueFactory(new PropertyValueFactory<>("nombre_p"));
    imagee.setCellValueFactory(new PropertyValueFactory<>("image"));
 

    }   
    
   private void refsalle() {     
        try {
            platList.clear();
            
            query = "select * from Salle";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                platList.add(new  Salle(
                        resultSet.getInt("id"),
                        resultSet.getString("nom_s"),
                        resultSet.getString("adresse_s"),
                        resultSet.getInt("code_postal"),
                        resultSet.getString("ville"),
                        resultSet.getInt("nombre_p"),
                        resultSet.getString("image")
                        
                        
                        )); 
               saletable.setItems(platList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   

    @FXML
    private void modifier(ActionEvent event) {
         Salle cat=new Salle();
   Salleservice pl = new Salleservice();
   cat=saletable.getSelectionModel().getSelectedItem();
   cat.setNom_s(noms.getText());
   cat.setAdresse_s(adresses.getText());
   
  
   int code_postal=Integer.valueOf(codep.getText());
   cat.setCode_postal(code_postal);
 cat.setVille(vill.getText());
  int nombre_p=Integer.valueOf(nbr.getText());
   cat.setNombre_p(nombre_p);
 cat.setImage(imgg.getText());
   pl.modifier(cat);
   load(); 
refsalle(); 
    }

    @FXML
    private void onclicked(MouseEvent event) {
         try {
     Salle cattt = saletable.getSelectionModel().getSelectedItem();
   
    
    noms.setText(String.valueOf(cattt.getNom_s()));
    adresses.setText(String.valueOf(cattt.getAdresse_s()));
   // imageeep.setText(String.valueOf(cattt.getImage()));
    codep.setText(String.valueOf(cattt.getCode_postal()));
   vill.setText(String.valueOf(cattt.getVille()));
    nbr.setText(String.valueOf(cattt.getNombre_p()));
     imgg.setText(String.valueOf(cattt.getImage()));
        String path = cattt.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageview.setImage(img);
    
        } catch (Exception e) {
               System.out.println(e.getMessage());
           }
    }

    @FXML
    private void supprimerr(ActionEvent event) {
                 if (!saletable.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer plat " + saletable.getSelectionModel().getSelectedItem().getNom_s()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    Salleservice r=new Salleservice();
    r.supprimer(saletable.getSelectionModel().getSelectedItem());
     Notifications notificationBuild = Notifications.create()
                                      .title("Traitement salle ")
                                      .text(" salle a été supprimé avec succes")
                                      .graphic(null)
                                      .position(Pos.CENTER)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }
                                  
                              });
                              notificationBuild.show(); 
    refsalle();
}
 
        }
         else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut séléctionner une ligne");
            alert.showAndWait();}
    }

 
    

    @FXML
    private void uploadimg(ActionEvent event) throws FileNotFoundException, IOException {
                 Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\wamp64\\\\www\\\\esayfit\\\\salle\\\\"+ "salle"  + x + ".jpg";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            imageview.setImage(img);
           /* File File1 = new File(DBPath);
            Image img = new Image(File1.getAbsolutePath());
            image_event.setImage(img);*/
            imgg.setText(DBPath);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();
            
        } else {
            System.out.println("error");

        }
    }
    

    @FXML
    private void clear(ActionEvent event) {
         noms.setText(null);
        adresses.setText(null);
        codep.setText(null);
        vill.setText(null);
        nbr.setText(null);
        imgg.setText(null);
    }   
    
    private void populateTable(ObservableList<Salle> branlist){
       saletable.setItems(branlist);
   
       } 

    @FXML
    private void search(KeyEvent event) {
         Salleservice bs=new Salleservice(); 
        Salle b= new Salle();
        ObservableList<Salle>filter= bs.chercherTitreplat(rechercher.getText());
        populateTable(filter);
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }

    @FXML
    private void excel(MouseEvent event) {
    
        connection= MaConnexion.getInstance().getCnx();
       
       
try{
String filename="C:\\Users\\lamis\\OneDrive\\Documents\\NetBeansProjects\\EasyFit1\\data.xls" ;
    HSSFWorkbook hwb=new HSSFWorkbook();
    HSSFSheet sheet =  hwb.createSheet("new sheet");

    HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("ID");
rowhead.createCell((short) 1).setCellValue("nom salle");
rowhead.createCell((short) 2).setCellValue("adresse");
rowhead.createCell((short) 3).setCellValue("code postal");
rowhead.createCell((short) 4).setCellValue("ville");
rowhead.createCell((short) 5).setCellValue("nombre_p");
rowhead.createCell((short) 6).setCellValue("url de l'image");
rowhead.createCell((short) 7).setCellValue("coordonnées longitude");
rowhead.createCell((short) 8).setCellValue("coordonnées lattitude");


Statement st=cnx.createStatement();
ResultSet rs=st.executeQuery("select id,nom_s,adresse_s,code_postal,ville,nombre_p,image,longitude,lattitude from salle");
int i=1;
while(rs.next()){
HSSFRow row=   sheet.createRow((short)i);

row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id")));
row.createCell((short) 1).setCellValue(rs.getString("nom_s"));
row.createCell((short) 2).setCellValue(rs.getString("adresse_s"));
row.createCell((short) 3).setCellValue(Integer.toString(rs.getInt("code_postal")));
row.createCell((short) 4).setCellValue(rs.getString("ville"));
row.createCell((short) 5).setCellValue(Integer.toString(rs.getInt("nombre_p")));
row.createCell((short) 6).setCellValue(rs.getString("image"));
row.createCell((short) 7).setCellValue(rs.getString("longitude"));
row.createCell((short) 8).setCellValue(rs.getString("lattitude"));

i++;
}
    FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("Your excel file has been generated!");
 File file = new File(filename);
        if (file.exists()){
        if(Desktop.isDesktopSupported()){
            Desktop.getDesktop().open(file);
        }}
       
} catch ( Exception ex ) {
    System.out.println(ex);

}


    }

    
}
