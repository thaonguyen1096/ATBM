package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import ModelConnection.SubClassConnection;
import Models.ChiTietDeMon;
import Models.DeMon;
import Models.Nhom;
import Models.User;
import application.FxDialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class sinhvien_DeDaDKController implements Initializable  {

	@FXML
	private TableView<ChiTietDeMon> tableDeDaDK = new TableView<ChiTietDeMon>();
	@FXML
	private Button btnHuyDKDe;
	@FXML
	private Label lblDangXuat;
	@FXML
	private Label lblTaiKhoan;
	@FXML
	private Button btnQuayLai;
	@FXML
	private Label maLop;
	@FXML
	private Label tenLop;
	@FXML
	private ImageView iconUser;
	@FXML
	private ComboBox<String> delay;
	@FXML
	private ComboBox<String> tranhchap;
	
	private User user;
	private ChiTietDeMon deSelect;
	
	private String db;
	
	public void setFix(String f){
	   db = f;
	}
	private SubClassConnection subsConn = new SubClassConnection(db);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblTaiKhoan.setAlignment(Pos.TOP_RIGHT);
	}
	
	
	public void setSVDeDaDk(String user, String mon){
		ObservableList<ChiTietDeMon> deDaDk = FXCollections.observableArrayList(subsConn.getAssignmentOf(user) ); 
			
		TableColumn cMaDe = new TableColumn("Ma de");
		cMaDe.setMinWidth(70);
		cMaDe.setMaxWidth(70);
		
		TableColumn cMoTa = new TableColumn("Mo ta");
		cMoTa.setMinWidth(370);
		cMoTa.setMaxWidth(370);
		
		TableColumn cLoaiDA = new TableColumn("Loai do an");
		cLoaiDA.setMinWidth(100);
		cLoaiDA.setMaxWidth(100);
		
		TableColumn cLoaiDe = new TableColumn("Loai de");
		cLoaiDe.setMinWidth(85);
		cLoaiDe.setMaxWidth(85);
		
		TableColumn cNhom = new TableColumn("Nhom");
		cNhom.setMinWidth(50);
		cNhom.setMaxWidth(50);
		
		TableColumn cGVPT = new TableColumn("GV phu trach");
		cGVPT.setMinWidth(120);
		cGVPT.setMaxWidth(120);
		
		TableColumn cDeadline = new TableColumn("Deadline");
		cDeadline.setMinWidth(150);
		cDeadline.setMaxWidth(150);
		
		cMaDe.setCellValueFactory(new PropertyValueFactory<DeMon, String>("de"));
		cMoTa.setCellValueFactory(new PropertyValueFactory<DeMon, String>("moTa"));
		
		cLoaiDA.setCellValueFactory(new PropertyValueFactory<DeMon, String >("loaiDA"));
		cLoaiDe.setCellValueFactory(new PropertyValueFactory<DeMon, String >("loaiDeHien"));
		cNhom.setCellValueFactory(new PropertyValueFactory<DeMon, Nhom>("maNhom"));
		cDeadline.setCellValueFactory(new PropertyValueFactory<DeMon, Date>("deadline"));
		cGVPT.setCellValueFactory(new PropertyValueFactory<DeMon, User>("giaoVienPhuTrach"));
		
	    //ObservableList<DeMon> list = getDeDaDKList();
	    tableDeDaDK.setItems(deDaDk);	 
	    
	    tableDeDaDK.getColumns().addAll(cMaDe, cMoTa, cLoaiDA, cLoaiDe, cDeadline, cNhom, cGVPT);
	    
	    	cMoTa.setCellFactory
		    (
		      column ->
		       {
		         return new TableCell<DeMon, String>()
		          {
		            @Override
		            protected void updateItem(String item, boolean empty)
		             {
		            	
		                super.updateItem(item, empty);
		                setText( item );
		                setTooltip(new Tooltip(item));
		             }
		          };
		       });
	}

	
	public void setSelectDe(){
		 tableDeDaDK.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	            if (newSelection != null) {
	            	deSelect = newSelection;
	            	this.btnHuyDKDe.setDisable(false);
	            }
	        });
	}
	
	public void huyDKDe_Clicked(){
		int kq = 0;
		int tt = 0;
		if(tranhchap.getValue().compareTo("Unrepeatable Read")==0)
			tt = 2;
		else if(tranhchap.getValue().compareTo("Lost Update")==0)
			tt = 3;
			
		if(deSelect.getLoaiDeHien().compareTo("Đề nhóm")==0){
			kq = subsConn.cancelTeamProject(deSelect.getMaNhom(), deSelect.getDe(), deSelect.getMon(), tt);
		}
		else{
			kq = subsConn.cancelPersonalProject(user.getUserID(),  deSelect.getDe(), deSelect.getMon(), tt);
		}
		if(kq==1)
			FxDialogs.showInformation("Thông báo", "Hủy đăng ký thành công!");
		else
			FxDialogs.showInformation("Thông báo", "Hủy đăng ký thất bại!");
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
			Stage stage = (Stage) lblDangXuat.getScene().getWindow();
			stage.setTitle("Đăng nhâp");
			stage.setResizable(false);				        
			stage.setScene(scene);
	   }
	}
	
	public void quayLai_Clicked(){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../Application/sinhvienNhomDe.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sinhvienNhomDeController display = Loader.getController();
		display.setTextTenDn(user);
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.setFix(db);
		display.setSinhVienNhomDe(user.getUserID(), maLop.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayLai.getScene().getWindow();
		stage.setTitle("Sinh viên");
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void setTextTenDn(User u){
		user = u;
		this.lblTaiKhoan.setText(u.getUserName());
	}
	
	public void setTextLop(String ma, String ten){
		 this.maLop.setText(ma);
		 this.tenLop.setText(ten);
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
			stage.setTitle("Cập nhật thông tin cá nhân");
			stage1.setScene(scene);
			stage.hide();
			stage1.show();
	   };
}
