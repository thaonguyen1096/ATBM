package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ModelConnection.UserConnection;
import Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class signInController implements Initializable{
	@FXML
	private TextField username;
	@FXML
	private PasswordField pass;
	@FXML
	private Button btnSignin;
	@FXML
	private Button btnExit;
	@FXML
	private ComboBox<String> cbFix;
	
	private User user;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	String db = "QL_DangKyDoAn";
	
	public void btnSigninClicked (){ 
		
		UserConnection conn;
    	if(cbFix.getValue().compareTo("Fixed")==0){
    		db = "QL_DangKyDoAn_fix";
    	}
    	conn  = new UserConnection(db);
		user = conn.checkEmInfo(username.getText(), pass.getText());
		
		Parent pane = null;
    	FXMLLoader Loader = new FXMLLoader();	
    		
		if(user.getUserType().compareTo("0")==0){
	    	Loader.setLocation(getClass().getResource("../Application/adminPage.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			adminPageController display = Loader.getController();
			display.setUser(user);
			display.setFix(db);
			display.getSubjectList();
			Scene scene = new Scene(pane);
			Stage stage = (Stage) btnSignin.getScene().getWindow();
			stage.setTitle("Admin");
			stage.setResizable(false);			        
			stage.setScene(scene);
		}
		else if (user.getUserType().compareTo("1")==0){
	    	Loader.setLocation(getClass().getResource("../Application/giaovienTrangChu.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			giaovienTrangChuController display = Loader.getController();
			display.setTextTenDn(user);
			display.setFix(db);
			display.setGiaoVienTT(user);
			Scene scene = new Scene(pane);
			Stage stage = (Stage) btnSignin.getScene().getWindow();
			stage.setTitle("Giáo viên");
			stage.setResizable(false);			        
			stage.setScene(scene);
		}
		else if(user.getUserType().compareTo("2")==0){
			Loader.setLocation(getClass().getResource("../Application/sinhvien-trangchu.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sinhvienTrangchuController display = Loader.getController();
			display.setTextTenDn(user);
			display.setFix(db);
			display.layDsMonSV(user.getUserID());
			Scene scene = new Scene(pane);
			Stage stage = (Stage) btnSignin.getScene().getWindow();
			stage.setTitle("Sinh viên");
			stage.setResizable(false);			        
			stage.setScene(scene);
		}
		else{
			JFrame frame = new JFrame("dangnhap");
			frame.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(frame, "Tên đăng nhập hoặc mật khẩu không chính xác!!");
		}
	}
	
	public void btnEixitClicked(){
		Stage stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
	}
}
