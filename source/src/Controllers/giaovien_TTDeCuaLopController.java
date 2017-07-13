package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import ModelConnection.SubClassConnection;
import Models.DeMon;
import Models.User;
import application.FxDialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class giaovien_TTDeCuaLopController implements Initializable{
	@FXML
	private ImageView iconUser; 
	@FXML
	private Label tenTK; 
	@FXML
	private Label dangxuat;
	@FXML
	private Label lbMaLop;
	@FXML
	private Label lbTenLop;
	@FXML
	private Button btnChiTietDe;
	@FXML
	private Button btnThemDe;
	@FXML
	private Button btnQuayVe;
	
	@FXML
	private TableView<DeMon> tableCT_De = new TableView<DeMon>();
	private String db;
	
	public void setFix(String f){
	   db = f;
	}
	private SubClassConnection subsConn = new SubClassConnection(db);
	private User user;
	

	private ObservableList<DeMon> listDe;
	
	//String maDeSel, maMonSel, loaiDASel, moTaSel, ngayBDSel, deadlineSel;
	DeMon deSelect;
	Boolean loaiDeSel;
	Integer slDKToiDaSel, slDaDKSel, slSVNhomSel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tenTK.setAlignment(Pos.TOP_RIGHT);
	}
	
	public void setGVTTDe(String user, String mon){
		listDe =  FXCollections.observableArrayList(subsConn.getAssignmentUnderControllOf(user, mon));   
		
		tableCT_De.setItems(listDe);
		
		TableColumn cMaDe = new TableColumn("Mã đề");
		cMaDe.setMinWidth(60);
		cMaDe.setMaxWidth(60);
		
		TableColumn cMaMon = new TableColumn("Mã môn");
		cMaMon.setMinWidth(80);
		cMaMon.setMaxWidth(80);
		
		TableColumn cLoaiDA = new TableColumn("Loại đồ án");
		cLoaiDA.setMinWidth(100);
		cLoaiDA.setMaxWidth(100);
		
		TableColumn cLoaiDe = new TableColumn("Loại đề");
		cLoaiDe.setMinWidth(80);
		cLoaiDe.setMaxWidth(80);
		
		TableColumn cMoTa = new TableColumn("Mô tả");
		cMoTa.setMinWidth(100);
		cMoTa.setMaxWidth(100);
		
		TableColumn cSLDKToiDa = new TableColumn("SLĐK TĐ");
		cSLDKToiDa.setMinWidth(80);
		cSLDKToiDa.setMaxWidth(80);
		
		TableColumn cSLDaDK = new TableColumn("SL đã đk");
		cSLDaDK.setMinWidth(80);
		cSLDaDK.setMaxWidth(80);
		
		TableColumn cSLSV_Nhom = new TableColumn("SLSV/Nhóm");
		cSLSV_Nhom.setMinWidth(100);
		cSLSV_Nhom.setMaxWidth(100);
		
		TableColumn cNgayBD = new TableColumn("Ngày bắt đầu đk");
		cNgayBD.setMinWidth(140);
		cNgayBD.setMaxWidth(140);
		
		TableColumn cDeadline = new TableColumn("Deadline");
		cDeadline.setMinWidth(140);
		cDeadline.setMaxWidth(140);
		
		// Dinh nghia cach de lay du lieu cho moi o
		// Lay gia tri tu cac thuoc tinh cua DeMon
		cMaDe.setCellValueFactory(new PropertyValueFactory<DeMon, String>("de"));
		cMaMon.setCellValueFactory(new PropertyValueFactory<DeMon, String>("mon"));
		cLoaiDA.setCellValueFactory(new PropertyValueFactory<DeMon, String>("loaiDA"));
		cLoaiDe.setCellValueFactory(new PropertyValueFactory<DeMon, String>("loaiDeHien"));
		cMoTa.setCellValueFactory(new PropertyValueFactory<DeMon, String>("moTa"));
		cSLDKToiDa.setCellValueFactory(new PropertyValueFactory<DeMon, Integer>("slDangKyTD"));
		cSLDaDK.setCellValueFactory(new PropertyValueFactory<DeMon, Integer>("slDangKy"));
		cSLSV_Nhom.setCellValueFactory(new PropertyValueFactory<DeMon, Integer>("slSVNhom"));
		cNgayBD.setCellValueFactory(new PropertyValueFactory<DeMon, String>("ngayBDDangKy"));
		cDeadline.setCellValueFactory(new PropertyValueFactory<DeMon, String>("deadline"));
		
		// Hien thi cac dong du lieu
		tableCT_De.getColumns().addAll(cMaDe, cMaMon, cLoaiDA, cLoaiDe, cMoTa, cSLDKToiDa, cSLDaDK, cSLSV_Nhom, cNgayBD, cDeadline);
	}
	
	public void selectDe(){
		tableCT_De.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
            	deSelect = newSelection;
            	this.btnChiTietDe.setDisable(false);
            }
        });
	}
	
	public void setTextLop(String ma, String ten){
		 this.lbMaLop.setText(ma);
		 this.lbTenLop.setText(ten);
	}
	
	public void chiTietDe_Clicked(ActionEvent event){
		Parent pane = null;
    	FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../application/giaovien_ChiTietDe.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		giaovien_ChiTietDeController display = Loader.getController();
		display.setTextLop(lbMaLop.getText(), lbTenLop.getText());
		display.setTextTenDn(user);
		display.setDe(deSelect, user);
		display.setFix(db);
		
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnChiTietDe.getScene().getWindow();
		stage.setTitle("Chi tiết đề");
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
			stage.setTitle("Đăng nhập");
			stage.setResizable(false);				        
			stage.setScene(scene);
	   }
	}
	
	public void themDe_Clicked(ActionEvent event){
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
		display.setTextTenDn(user);
		display.setTextMon(lbMaLop.getText(), lbTenLop.getText());
		display.setFix(db);
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayVe.getScene().getWindow();
		stage.setTitle("Thêm đề");
		stage.setResizable(false);					        
		stage.setScene(scene);
	}
	
	public void quayVe_Clicked(ActionEvent event){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../application/giaovienTrangChu.fxml"));
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
		Stage stage = (Stage) btnQuayVe.getScene().getWindow();
		stage.setTitle("Giáo viên");
		stage.setResizable(false);					        
		stage.setScene(scene);
	}
	
	public void setTextTenDn(User u){
		user = u;
		this.tenTK.setText(u.getUserName());
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
			display.setFix(db);
			display.setPreviousPage(stage);
			Stage stage1 = new Stage();
			Scene scene = new Scene(pane);
			stage1.setResizable(false);				        
			stage1.setScene(scene);
			stage1.setTitle("Cập nhật thông tin cá nhân");
			stage.hide();
			stage1.show();
	   };
	
}

