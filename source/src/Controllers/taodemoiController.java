package Controllers;

import java.net.URL; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.util.Optional;
import java.util.ResourceBundle;

import ModelConnection.ExerciseConnection;
import Models.De;
import Models.User;
import application.FxDialogs;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class taodemoiController implements Initializable {
	@FXML
	private Label tendn; 
	@FXML
	private Label dangxuat; 
	@FXML
	private Label maLop;
	@FXML
	private Label tenLop;
	@FXML
	private TextArea moTa;
	@FXML
	private Button btnTaoDe;
	@FXML
	private Button btnQuayLai;
	
	@FXML
	private Button btnLuuThayDoi;
	@FXML
	private Button btnQuayLai1;
	@FXML
	private Label lbMaDe;
	@FXML
	private Label maDe;
	@FXML
	private ImageView iconUser;
	@FXML
	private ComboBox<String> delay;
	@FXML
	private ComboBox<String> tranhchap;
	
	private User user;
	private String db;
	
	public void setFix(String f){
	   db = f;
	}
	private ExerciseConnection exConn = new ExerciseConnection(db);
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tendn.setAlignment(Pos.TOP_RIGHT);
	}
	
	public void suaDeInit(De de){
		btnTaoDe.setVisible(false);
		btnLuuThayDoi.setVisible(true);
		lbMaDe.setVisible(true);
		moTa.setText(de.getMoTa());
		maDe.setText(de.getMaDe());
		tranhchap.setValue("Lost Update");
		tranhchap.getItems().add("Lost Update");
	}
	
	public void taoDeInit(){
		tranhchap.setValue("Phantom");
		tranhchap.getItems().add("Phantom");
	}
	
	public void chonLuuThayDoi(ActionEvent event) {
		
	}
	
	public void chonTaoDe(ActionEvent event) {
		
		if(maDe.getText().compareTo("")!=0){ ///Cập nhật đề
			exConn.updateAssignment(maDe.getText(), moTa.getText(), 0);
		}
		else  //Tạo đề
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Tạo đề");
			alert.setHeaderText("Bạn muốn tiếp tục thêm đề này cho môn học hay chỉ thêm vào thư viện đề?");
			alert.setContentText("Lựa chọn của bạn");

			ButtonType buttonTypeOne = new ButtonType("Tiếp tục thêm đề cho môn");
			ButtonType buttonTypeTwo = new ButtonType("Chỉ thêm đề vào thư viện đề");
			ButtonType buttonTypeCancle = new ButtonType("Huy", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancle);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne){
				Parent pane = null;
				FXMLLoader Loader = new FXMLLoader();
		    	Loader.setLocation(getClass().getResource("../application/themdechomon.fxml"));
				try {
					pane = Loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				themdechomonController display = Loader.getController();
				display.setThemDeMonMoi(moTa.getText());
				display.setTextMon(maLop.getText(), tenLop.getText());
				display.setTextTenDn(user);
				display.setFix(db);
				Scene scene = new Scene(pane);
				Stage stage = (Stage) btnTaoDe.getScene().getWindow();
				stage.setTitle("Thêm đề cho môn");
				stage.setResizable(false);				        
				stage.setScene(scene);
			} 
			else if(result.get() == buttonTypeTwo){
				String kq = exConn.addAssignment(moTa.getText(), 2); //phatom;
				if(!kq.isEmpty()){
					Alert alert1 = new Alert(AlertType.INFORMATION);
					alert1.setHeaderText("Thêm đề vào thư viện thành công");
					alert1.setContentText("Mã đề: "+ kq);
					alert1.showAndWait();
					moTa.clear();
				}
				else
				{
					FxDialogs.showInformation("Thông báo", "Thêm đề thất bại");
				}
				
			}
		}
		
	}
	
	public void chonQuayLai(ActionEvent event) {
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../application/themde.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		themdeController display = Loader.getController();
		display.setTextMon(maLop.getText(), tenLop.getText());
		display.setTextTenDn(user);
		display.setFix(db);
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayLai.getScene().getWindow();
		stage.setTitle("Thêm đề");
		stage.setResizable(false);				        
		stage.setScene(scene);
	 }
	public void setTextLop(String ma, String ten){
		this.maLop.setText(ma);
		this.tenLop.setText(ten);
	}
	public void setTextTenDn(User u){
		user = u;
		this.tendn.setText(u.getUserName());
	}
	
	public void dangxuatClicked(){
		if (FxDialogs.showConfirm("Thông báo", "Bạn có muốn đăng xuất??", 1, "Có", "Không").equals(FxDialogs.YES)) {
			Parent pane = null;
	    	FXMLLoader Loader = new FXMLLoader();
	    	Loader.setLocation(getClass().getResource("../Application/signIn.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Scene scene = new Scene(pane);
			Stage stage = (Stage) dangxuat.getScene().getWindow();
			stage.setTitle("Đăng nhập");
			stage.setResizable(false);				        
			stage.setScene(scene);
	   }
	}
	
	public void updateUser(MouseEvent e){
		   Parent pane = null;
	    	FXMLLoader Loader = new FXMLLoader();
	    	Loader.setLocation(getClass().getResource("../application/suaThongTinCaNhan.fxml"));
			try {
				pane = Loader.load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Stage stage = (Stage) iconUser.getScene().getWindow();
			suaThongTinCaNhanController display = Loader.getController();
			display.setTextTenDn(user);
			display.setPreviousPage(stage);
			display.hienKhoa();
			display.setFix(db);
			Stage stage1 = new Stage();
			Scene scene = new Scene(pane);
			stage1.setResizable(false);		
			stage1.setTitle("Cập nhật thông tin cá nhân");
			stage1.setScene(scene);
			stage.hide();
			stage1.show();
	   };
}