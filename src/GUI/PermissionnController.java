/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import ClickSend.ApiClient;
import ClickSend.Api.SmsApi;
import java.util.Arrays;
import java.util.List;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import Entities.Permission;
import Entities.Salle;
import Entities.personnel;
import Service.PermissionService;
import Service.Salleservice;
import Service.personnelservice;
import Service.scontrole;
import Tools.MaConnexion;
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
import java.util.List;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author lamis
 */
public class PermissionnController implements Initializable {

    @FXML
    private TextField imgg;
    @FXML
    private Button ajout;
    @FXML
    private Button mod;
    
    @FXML
    private TableColumn<Permission, Integer> idvs;
    @FXML
    private TableColumn<Permission, String> typev;
    @FXML
    private TableColumn<Permission,String > reclamationv;
    @FXML
    private TableColumn<Permission, Date> datedv;
    @FXML
    private TableColumn<Permission, Date> datefv;
    @FXML
    private TableColumn<Permission, String> imagee;
    @FXML
    private TableColumn<Permission, String> nompersov;
    @FXML
    private Button supp;
    @FXML
    private Button upload;
    @FXML
    private Button clearr;
    @FXML
    private ImageView imageview;
    @FXML
    private ComboBox<String> comboxnp;
    @FXML
    private ComboBox<String> combotype;
    @FXML
    private DatePicker dated;
    @FXML
    private DatePicker datef;
 personnel ss=new personnel();
     Statement ste;
    private personnel r;
    String query = null;
    ObservableList<Permission>  platList = FXCollections.observableArrayList();
    Connection connection = null ;
    Connection cnx=MaConnexion.getInstance().getCnx();
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    @FXML
    private TextField reclamatio;
    @FXML
    private TableView<Permission> permisiontab;
    @FXML
    private Button jj;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("maladie","congée ","vacances");
        combotype.setItems(list);
        load();
        refpermission();
        
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        
          connection= MaConnexion.getInstance().getCnx();
          String nom_p = comboxnp.getValue();
           PermissionService rec = new PermissionService();
          int id= rec.chercherIds(nom_p);
           String type = (String)combotype.getValue();
        String reclamation =reclamatio.getText();
           
              Date date_d = Date.valueOf(dated.getValue());
                 Date date_f = Date.valueOf(datef.getValue());
        
          
              
          scontrole sc= new scontrole();

  
        Permission ppp=new Permission(type,reclamation,date_d,date_f,imgg.getText(),id);
    
           
if(!reclamation.isEmpty()){
         PermissionService ps = new PermissionService();
            ps.ajouter(ppp);
             Notifications notificationBuild = Notifications.create()
                                      .title("Traitement permission ")
                                      .text("permission a été ajouté avec succes")
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
                              refpermission();
load();
       
sms(combotype.getValue());
        
}else{ Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("il faut remplir les champs");
            alert.showAndWait();}
    }
 private void load() {
    PermissionService pp=new PermissionService();
    connection= MaConnexion.getInstance().getCnx();
    refpermission();
    idvs.setCellValueFactory(new PropertyValueFactory<>("id"));
    typev.setCellValueFactory(new PropertyValueFactory<>("type"));
    reclamationv.setCellValueFactory(new PropertyValueFactory<>("reclamation"));
    datedv.setCellValueFactory(new PropertyValueFactory<>("date_d"));
    datefv.setCellValueFactory(new PropertyValueFactory<>("date_f"));
  //  nbrplacee.setCellValueFactory(new PropertyValueFactory<>("nombre_p"));
    imagee.setCellValueFactory(new PropertyValueFactory<>("image"));
     nompersov.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
     comboxnp.setItems(FXCollections.observableArrayList(pp.getAll()));

    }   
    
   private void refpermission() {     
        try {
            platList.clear();
            
            query = "select Permission.id,Permission.type,Permission.reclamation,Permission.date_d,Permission.date_f,Permission.image,personnel.nom_p from Permission inner join personnel on Permission.personnel_id=personnel.id ";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                platList.add(new  Permission(
                        resultSet.getInt("id"),
                        resultSet.getString("type"),
                        resultSet.getString("reclamation"),
                        resultSet.getDate("date_d"),
                        resultSet.getDate("date_f"),
                        resultSet.getString("image"),
                        resultSet.getString("nom_p")
                        
                        
                        )); 
               permisiontab.setItems(platList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void modifier(ActionEvent event) {
                     Permission cat=new Permission();
   PermissionService pl = new PermissionService();
   cat=permisiontab.getSelectionModel().getSelectedItem();
    String type = (String)combotype.getValue();
   cat.setType(type);
  
 cat.setImage(imgg.getText());
   pl.modifier(cat);
   load(); 
refpermission();
    }

    @FXML
    private void onclicked(MouseEvent event) {
           {
           try {
               Permission per = permisiontab.getSelectionModel().getSelectedItem();
   
          String c =  per.getType();
    combotype.setValue(c);     
            
    reclamatio.setText(String.valueOf(per.getReclamation()));
  
    //imagee.setText(String.valueOf(per.getImage()));
    imgg.setText(String.valueOf(per.getImage()));
        String path = per.getImage();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                imageview.setImage(img);
    
//  Date d =  per.getDate_embauche();
//    txt_date_embauche.setValue(d);
           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
           }}

    @FXML
    private void supprimerr(ActionEvent event) {
                        if (!permisiontab.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer plat " + permisiontab.getSelectionModel().getSelectedItem().getReclamation()+ " ?", ButtonType.YES, ButtonType.NO);
alert.showAndWait();

if (alert.getResult() == ButtonType.YES) {
    PermissionService r=new PermissionService();
    r.supprimer(permisiontab.getSelectionModel().getSelectedItem());
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
    refpermission();
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
         combotype .setValue(null);
        reclamatio.setText(null);
        dated.setValue(null);
        datef.setValue(null);
        imgg.setText(null);
         comboxnp.setValue(null);
    }

    @FXML
    private void select(ActionEvent event) {
          String ss = combotype.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/Gui/Home.fxml"));
              Scene scene = new Scene(root);
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
    }
    
    
    void sms(String nom)
    {
       
        int num=92327067;
       
       
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("lamis.hammami@esprit.tn");
        defaultClient.setPassword("3EC629EE-CECA-82D7-F014-6C2D3E329955");
        SmsApi apiInstance = new SmsApi(defaultClient);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("une reclamation de type '"+nom+"' a été ajouté avec succes" );
        smsMessage.to("+216"+num);
        smsMessage.source("permission");
       

        List<SmsMessage> smsMessageList = Arrays.asList(smsMessage);
        // SmsMessageCollection | SmsMessageCollection model
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            String result = apiInstance.smsSendPost(smsMessages);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SmsApi#smsSendPost");
            e.printStackTrace();
        }
    }
    
    
}
