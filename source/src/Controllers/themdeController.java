package Controllers;

import java.net.URL; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.util.ResourceBundle;

import ModelConnection.ExerciseConnection;
import Models.De;
import Models.User;
import application.FxDialogs;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class themdeController implements Initializable {
	@FXML
	private Label tendn; 
	@FXML
	private Label dangxuat; 
	@FXML
	private Label maLop;
	@FXML
	private Label tenLop;
	@FXML
	private TableView<De> tableDSDe;
	@FXML
	private TableColumn<De, String > maDe;
	@FXML
	private TableColumn<De, String> moTa;
	@FXML
	private Button btnTaoDeMoi;
	@FXML
	private Button btnThemDeChoMon;
	@FXML
	private Button btnQuayLai;
	@FXML
	private Button btnChinhSuaDe;
	
	@FXML
	private ImageView iconUser;
	
	private De deSelect;
	private String maDeSelect;
	private User user;
	private static String db;
	
	public void setFix(String f){
	   db = f;
	}
	private static ExerciseConnection exConn = new ExerciseConnection(db);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tendn.setAlignment(Pos.TOP_RIGHT);
		maDe.setCellValueFactory(new PropertyValueFactory<De, String>("maDe"));
		maDe.setMinWidth(100);
		maDe.setMaxWidth(100);
		moTa.setCellValueFactory(new PropertyValueFactory<De, String>("moTa"));
		moTa.setMinWidth(900);
		moTa.setMaxWidth(900);
		ObservableList<De> list = getDeList();
	    tableDSDe.setItems(list);
	    
	    tableDSDe.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	this.btnThemDeChoMon.setDisable(false);
            	this.btnChinhSuaDe.setDisable(false);
            	deSelect = newSelection;
            }
        });
	}
	
	public static ObservableList<De> getDeList() {
	    ObservableList<De> list = FXCollections.observableArrayList(exConn.getAssignments());
	    return list;
	}
	
	public void setTextMon(String ma, String ten){
		this.maLop.setText(ma);
		this.tenLop.setText(ten);
	}
	
	public void setTextTenDn(User u){
		user = u;
		this.tendn.setText(u.getUserName());
	}
	
	public void chonTaoDeMoi(ActionEvent event) {
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../application/taodemoi.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		taodemoiController display = Loader.getController();
		display.setTextTenDn(user);
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.taoDeInit();
		display.setFix(db);
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnTaoDeMoi.getScene().getWindow();
		stage.setTitle("Tạo đề mới");
		stage.setResizable(false);				        
		stage.setScene(scene);
	}
	
	public void chonThemDeChoMon(ActionEvent event) {
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
		display.setTextMon(maLop.getText(), tenLop.getText());
		display.setTextTenDn(user);
		display.setMaDe(deSelect.getMaDe());
		display.setFix(db);
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnThemDeChoMon.getScene().getWindow();
		stage.setTitle("Thêm đề cho môn");
		stage.setResizable(false);				        
		stage.setScene(scene);
	}
	
	public void btnChinhSuaDeClicked(){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../application/taodemoi.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		taodemoiController display = Loader.getController();
		display.setTextTenDn(user);
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.suaDeInit(deSelect);
		display.setFix(db);
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnChinhSuaDe.getScene().getWindow();
		stage.setTitle("Sửa đề");
		stage.setResizable(false);				        
		stage.setScene(scene);
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
			stage.setResizable(false);	
			stage.setTitle("Đăng nhập");
			stage.setScene(scene);
	   }
	}
	
	public void chonQuayLai(ActionEvent event) {
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../application/giaovien_TTDe.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giaovien_TTDeCuaLopController display = Loader.getController();
		display.setTextTenDn(user);
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.setFix(db);
		display.setGVTTDe(user.getUserID(), maLop.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayLai.getScene().getWindow();
		stage.setTitle("Giáo viên");
		stage.setResizable(false);					        
		stage.setScene(scene);
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