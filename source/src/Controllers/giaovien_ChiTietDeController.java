package Controllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import ModelConnection.SubClassConnection;
import ModelConnection.TeamConnection;
import ModelConnection.UserConnection;
import Models.DeMon;
import Models.Nhom;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class giaovien_ChiTietDeController implements Initializable {
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
	private TextField txtMaDe = new TextField();
	@FXML
	private TextField txtLoaiDe = new TextField();
	@FXML
	private TextArea txtMoTa = new TextArea();
	@FXML
	private JFXDatePicker ngayBD;
	@FXML
	private JFXDatePicker ngayDeadline;
	@FXML
	private JFXTimePicker gioBD;
	@FXML
	private JFXTimePicker gioDeadline;
	@FXML
	private TextField txtSLSV_Nhom = new TextField();
	@FXML
	private TextField txtSLDKToiDa = new TextField();
	@FXML
	private TextField txtSLDaDK = new TextField();
	@FXML
	private TextField txtSLDangQuanLy = new TextField();
	@FXML
	private Button btnQuayVe;
	@FXML
	private Button luuThayDoi;
	@FXML
	private ComboBox<String> delay;
	@FXML
	private ComboBox<String> tranhchap;
	
	@FXML
	private TableView<Nhom> tableDS_Nhom = new TableView<Nhom>();
	@FXML
	private TableView<User> tableDS_SV = new TableView<User>();
	
	
	private String db;
	
	public void setFix(String f){
	   db = f;
	}
	private TeamConnection teConn = new TeamConnection(db);
	private UserConnection userConn = new UserConnection(db);
	private SubClassConnection subsConn =  new SubClassConnection(db);
	
	private User user;
	private DeMon deLuu;
	
	
	//Nhom dang ky de ma giao vien quan ly
	private ObservableList<Nhom> data_Nhom ;
	
	
	 
	
	//SV dang ky de ma giao vien quan ly
	private ObservableList<User> data_SV;
	
	//So luong nhom/sinh vien dang ky de nay ma giao vien dang quan ly -- cung la bien delay
	private int slSV_QL;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tenTK.setAlignment(Pos.TOP_RIGHT);
	}
	
	public void quayVe_Clicked(ActionEvent event){
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
		display.setTextLop(lbMaLop.getText(), lbTenLop.getText());
		display.setTextTenDn(user);
		display.setFix(db);
		display.setGVTTDe(user.getUserID(), lbMaLop.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayVe.getScene().getWindow();
		stage.setTitle("Giáo viên");
		stage.setResizable(false);					        
		stage.setScene(scene);
	}
	
	public void setTextLop(String ma, String ten){
		 this.lbMaLop.setText(ma);
		 this.lbTenLop.setText(ten);
	}
	
	public void setTextTenDn(User u){
		user = u;
		this.tenTK.setText(u.getUserName());
	}
	
	public void setDe(DeMon de, User u){
		this.txtMaDe.setText(de.getDe());
		this.txtLoaiDe.setText(de.getLoaiDeHien());
		this.txtMoTa.setText(de.getMoTa());
		this.txtSLSV_Nhom.setText(Integer.toString(de.getSlSVNhom()));
		this.txtSLDKToiDa.setText(Integer.toString(de.getSlDangKyTD()));
		this.txtSLDaDK.setText(Integer.toString(de.getSlDangKy()));
		
		
		 if(de.getLoaiDeHien().compareTo("Đề nhóm")==0){
			 System.out.println(u.getUserID().concat(" ").concat(lbMaLop.getText()).concat(" ").concat(de.getDe()));
			 Vector<Object> res = teConn.getTeamUnderControllOf(u.getUserID(), lbMaLop.getText(), de.getDe(), slSV_QL);
			 data_Nhom = FXCollections.observableArrayList((Vector<Nhom>)res.get(0)) ;
			 this.txtSLDangQuanLy.setText("" + (int)res.get(1));
		 }
		 else{
			 Vector<Object> res = userConn.getUserUnderControllOf(u.getUserID(), lbMaLop.getText(), de.getMaDe(), slSV_QL);   
			 data_SV = FXCollections.observableArrayList((Vector<User>)res.get(0)) ;
			 this.txtSLDangQuanLy.setText("" + (int)res.get(1));
		 }
		 
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate lngayBD = LocalDate.parse(de.getNgayBDDangKy().substring(0, 10), formatter);
		LocalDate lngayDl = LocalDate.parse(de.getDeadline().substring(0, 10), formatter);
		ngayBD.setValue(lngayBD);
		gioBD.setValue(LocalTime.parse(de.getNgayBDDangKy().substring(11, 19)));
		ngayDeadline.setValue(lngayDl);
		gioDeadline.setValue(LocalTime.parse(de.getDeadline().substring(11, 19)));
		deLuu = de;
		
		if(de.getLoaiDeHien().compareTo("Đề cá nhân")==0){
			TableColumn cMasv = new TableColumn("MSSV");
	
			cMasv.setMinWidth(150);
			cMasv.setMaxWidth(150);
			cMasv.setCellValueFactory(
	            new PropertyValueFactory<User,String>("userID")
	        );

			TableColumn cTensv = new TableColumn("Tên sinh viên");
			cTensv.setMinWidth(390);
			cTensv.setMaxWidth(390);
			cTensv.setCellValueFactory(
	            new PropertyValueFactory<User,String>("userName")
	        );
			
	        tableDS_SV.setVisible(true);
	        tableDS_SV.getColumns().addAll(cMasv, cTensv);
	        tableDS_SV.setItems(data_SV);
		}
		else{
			TableColumn cManhom = new TableColumn("Mã nhóm");
			cManhom.setMinWidth(90);
			cManhom.setMaxWidth(90);
			cManhom.setCellValueFactory(
	            new PropertyValueFactory<Nhom,String>("maNhom")
	        );

			TableColumn cTennhom = new TableColumn("Tên nhóm");
	        cTennhom.setMinWidth(200);
	        cTennhom.setMaxWidth(200);
	        cTennhom.setCellValueFactory(
	            new PropertyValueFactory<Nhom,String>("tenNhom")
	        );

	        TableColumn cNhomtruong = new TableColumn("Nhóm trưởng");
	        cNhomtruong.setMinWidth(100);
	        cNhomtruong.setMaxWidth(100);
	        cNhomtruong.setCellValueFactory(
	            new PropertyValueFactory<Nhom,String>("nhomTruong")
	        );
	        TableColumn cSoluong = new TableColumn("Số lượng thành viên");
	        cSoluong.setMinWidth(150);
	        cSoluong.setMaxWidth(150);
	        cSoluong.setCellValueFactory(
	            new PropertyValueFactory<Nhom,String>("soLuong")
	        );
			
	        tableDS_Nhom.setVisible(true);
	        tableDS_Nhom.getColumns().addAll(cManhom, cTennhom, cNhomtruong, cSoluong);
	        tableDS_Nhom.setItems(data_Nhom);
		}
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
			display.setFix(db);
			Stage stage1 = new Stage();
			Scene scene = new Scene(pane);
			stage1.setResizable(false);				        
			stage1.setScene(scene);
			stage1.setTitle("Cập nhật thông tin cá nhân");
			stage.hide();
			stage1.show();
	   };
	   
	   public void luuThayDoiClicked(){
		   int tt=0;
		   if(tranhchap.getValue().compareTo("Dirty Read")==0)
			   tt = 4;
		   else if(tranhchap.getValue().compareTo("Unrepeatable Read")==0)
			   tt=2;
			   
		   String dl = ngayDeadline.getValue().toString() + " " + gioDeadline.getValue().toString() + ":00.000";
		   String bd = ngayBD.getValue().toString() + " " + gioBD.getValue().toString() + ":00.000";
		   int kq = 0;
		   try {
			  kq = subsConn.updateAssignment(lbMaLop.getText(), txtMaDe.getText(), Integer.parseInt(txtSLDKToiDa.getText()), Integer.parseInt(txtSLSV_Nhom.getText()), bd, dl, tt);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   if(kq==1)
				FxDialogs.showInformation("Thông báo", "Lưu thành công!");
			else
				FxDialogs.showInformation("Thông báo", "Lưu thất bại!");
		   
	   }
}




