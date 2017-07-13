package Controllers;

import Models.LoaiDA;
import Models.LoaiDe;
import Models.User;
import application.FxDialogs;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import ModelConnection.ProjectType;
import ModelConnection.SubClassConnection;

public class themdechomonController implements Initializable {
	@FXML
	private Label tendn; 
	@FXML
	private Label dangxuat; 
	@FXML
	private Label maLop;
	@FXML
	private Label tenLop;
	@FXML
	private Label lbmd;
	@FXML
	private Label maDe;
	@FXML
	private Label maMon;
	@FXML
	private ComboBox<LoaiDA> loaiDA;
	@FXML
	private ComboBox<LoaiDe> loaiDe;
	@FXML
	private TextField SLDKToiDa;
	@FXML
	private TextField SLSVNhom;
	@FXML
	private JFXDatePicker ngayBD;
	@FXML
	private JFXDatePicker ngayDeadline;
	@FXML
	private JFXTimePicker gioBD;
	@FXML
	private JFXTimePicker gioDeadline;
	@FXML
	private Button btnThemDe;
	@FXML
	private Button btnQuayLai;
	@FXML
	private ImageView iconUser;
//	@FXML
//	private ComboBox<String> delay;
	
	private String moTaDeThemMoi;
	private User user;
	private static String db;	
	public void setFix(String f){
	   db = f;
	}
	private static ProjectType ptype = new ProjectType(db);
	private static SubClassConnection subsConn = new SubClassConnection(db); 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<LoaiDA> list1 = getLoaiDAList();
		tendn.setAlignment(Pos.TOP_RIGHT);
		
		loaiDA.setItems(list1);
		loaiDA.getSelectionModel().select(0);
		ObservableList<LoaiDe> list2 = getLoaiDeList();
		loaiDe.setItems(list2);
		loaiDe.getSelectionModel().select(0);
		
		ngayBD.setPromptText("Ngày");
		ngayDeadline.setPromptText("Ngày");
		gioBD.setPromptText("Giờ");
		gioDeadline.setPromptText("Giờ");
	}
	
	public static ObservableList<LoaiDA> getLoaiDAList() {
        ObservableList<LoaiDA> list = FXCollections.observableArrayList(ptype.getProjectTypes());
        return list;
    }
	
	public static ObservableList<LoaiDe> getLoaiDeList() {
    	LoaiDe canhan = new LoaiDe(true, "Đè cá nhân");
    	LoaiDe nhom = new LoaiDe(false, "Đề nhóm");
        ObservableList<LoaiDe> list = FXCollections.observableArrayList(canhan, nhom);
        return list;
    }
	
	public void chonThemDe(ActionEvent event) {
		System.out.println("do them de");
		int kq = 0;
		String bd, dl;
		bd = ngayBD.getValue().toString() + " " + gioBD.getValue().toString()+":00.000";
		dl = ngayDeadline.getValue().toString() + " " + gioDeadline.getValue().toString() + ":00.000";
		
		try {
			if(maDe.getText().compareTo("")==0){
				
					kq = subsConn.insertNewExercise(moTaDeThemMoi, maLop.getText(),loaiDA.getValue().getMaLoai(),
												loaiDe.getValue().getMaLoai() , 
												Integer.parseInt(SLDKToiDa.getText()),
												Integer.parseInt(SLSVNhom.getText()), bd, dl);
			}
			else{
					kq = subsConn.insertOldExercise(maDe.getText(), maLop.getText(),loaiDA.getValue().getMaLoai(),
												loaiDe.getValue().getMaLoai() , 
												Integer.parseInt(SLDKToiDa.getText()),
												Integer.parseInt(SLSVNhom.getText()), bd, dl);
				}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(kq==1){
			System.out.println(moTaDeThemMoi);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Them de thanh cong");
			alert.showAndWait();
		}
		SLDKToiDa.clear();
		SLSVNhom.clear();
		ngayBD.setValue(null);
		ngayDeadline.setValue(null);
		gioBD.setValue(null);
		gioDeadline.setValue(null);
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
	
	public void setTextMon(String ma, String ten){
		this.maLop.setText(ma);
		this.tenLop.setText(ten);
		this.maMon.setText(ma);
	}
	
	public void setMaDe(String maD ){
		maDe.setText(maD);
		maDe.setVisible(true);
		lbmd.setVisible(true);
	}
	
	public void setThemDeMonMoi(String mt){
		moTaDeThemMoi = mt;
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