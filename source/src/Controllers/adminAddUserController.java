package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ModelConnection.SubjectConnection;
import ModelConnection.UserConnection;
import application.FxDialogs;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class adminAddUserController implements Initializable{
	@FXML
	private ComboBox<String> cmbType;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtID;
	@FXML
	private TextField txtTel;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtAdd;
	@FXML
	private TextField txtYear;
	@FXML
	private PasswordField txtPass;
	@FXML
	private Button btnBack;
	@FXML
	private Button btnOK;
	@FXML
	private ComboBox<String> delay;
	@FXML
	private ComboBox<String> tranhchap;
	
	private int bienDelay, bienTranhChap;
	private String db;
	public void setFix(String f){
	   db = f;
	}
	private UserConnection userConn = new UserConnection(db);
	
	public void setEnabled(boolean b)
	{
		if (b == true)
		{
			txtName.setDisable(false);
			txtTel.setDisable(false);
			txtEmail.setDisable(false);
			txtAdd.setDisable(false);
			txtPass.setDisable(false);
		}
		else
		{
			txtName.setDisable(true);
			txtTel.setDisable(true);
			txtEmail.setDisable(true);
			txtAdd.setDisable(true);
			txtPass.setDisable(true);
			txtID.setDisable(true);
			txtYear.setDisable(true);
		}
	};
	
	public void goBack(MouseEvent e)
	{
	    Node source = (Node) e.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	
	public void confirm(MouseEvent e)
	{
		char type;
		int khoa = -1;
		if(cmbType.getValue().compareTo("Admin")==0)
			type = '0';
		else if(cmbType.getValue().compareTo("Giáo viên")==0)
			type = '1';
		else{
			type = '2';
			khoa = Integer.parseInt(txtYear.getText());
		}
		int tt;
		if(tranhchap.getValue().compareTo("Phantom")==0)
			tt = 2;//phatom
		else
			tt = 4;//dirty read
		String kq = userConn.addUser(txtName.getText(), txtEmail.getText(), txtTel.getText(), txtAdd.getText(), khoa,type, txtPass.getText(), 4);
		if(kq.compareTo("")!=0)
			FxDialogs.showInformation("Thông báo", "Thêm người dùng thành công!");
	};
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setEnabled(false);
		cmbType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
			@Override 
			public void changed(ObservableValue<? extends String> selected, String oldType, String newType) {
				 if (oldType != null) {
			          switch(oldType) {
			            case "Admin":  
			            	break;
			            case "Giáo viên": 
			            	break;
			            case "Sinh viên":
			            	txtYear.setDisable(true);
			            	break;
			          }
			        }
			        if (newType != null) {
			        	setEnabled(true);
			          switch(newType) {
			            case "Admin":
			            	break;
			            case "Giáo viên": 
			            	break; 
			            case "Sinh viên": 
			            	txtYear.setDisable(false);
			            	break;
			          }
			}
		}; 
	});
	
		
	}
	
	
}
