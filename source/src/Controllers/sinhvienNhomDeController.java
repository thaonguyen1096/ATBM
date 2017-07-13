package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import ModelConnection.SubClassConnection;
import ModelConnection.TeamConnection;
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
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class sinhvienNhomDeController  implements Initializable {
	@FXML
	private Label tendn;
	@FXML
	private Label dangxuat;
	@FXML
	private ImageView iconUser; 
	@FXML
	private Label maLop;
	@FXML
	private Label tenLop;
	@FXML
	private Button btnQuayve;
	@FXML
	private Button btnDangkynhom;
	@FXML
	private Button btnRutnhom;
	@FXML
	private Button btnChitietnhom;
	@FXML
	private Tab tabNhom;
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Label lblDSDeConHanDK;
	@FXML
	private TableView<DeMon> tableDeConHanDK = new TableView<DeMon>();
	@FXML
	private Button btnDangKyDe;
	@FXML
	private Button btnQuayVeTC;
	@FXML 
	private Button btnXemDSDeDaDK;
	@FXML
	private ComboBox<String> delay;
	@FXML
	private ComboBox<String> tranhchapDKDe;
	@FXML
	private ComboBox<String> tranhchapNhom;
	@FXML
	private TableView<Nhom> tableNhom = new TableView<Nhom>();
	
	private User user;
	private Nhom nhomSelect;
	private DeMon deSelect;
	
	private String db;
	
	public void setFix(String f){
	   db = f;
	}
	
	private TeamConnection teConn = new TeamConnection(db);	
	private SubClassConnection subsConn = new SubClassConnection(db);
	private TeamConnection tConn = new TeamConnection(db);
	
	private List<Nhom> nhomTruongCua;

	public void setSinhVienNhomDe(String user, String mon){
		
		//Nhom ma thang dang nhap lam nhom truong
		nhomTruongCua = 
			    FXCollections.observableArrayList(
			    		tConn.getTeamLeadedBy(user, mon)
			    );

		//Nhom
		ObservableList<Nhom> data =
			        FXCollections.observableArrayList(
			        		tConn.getTeamListOf(user, mon)
			        );   
		//De
		ObservableList<DeMon> data_de =
		        FXCollections.observableArrayList(
		        		subsConn.getAvailableAssignment(mon)
		        );   
		TableColumn cManhom = new TableColumn("Ma nhom");
		cManhom.setMinWidth(110);
		cManhom.setMaxWidth(110);
		cManhom.setCellValueFactory(
            new PropertyValueFactory<Nhom,String>("maNhom")
        );

		TableColumn cTennhom = new TableColumn("Ten nhom");
        cTennhom.setMinWidth(335);
        cTennhom.setMaxWidth(335);
        cTennhom.setCellValueFactory(
            new PropertyValueFactory<Nhom,String>("tenNhom")
        );

        TableColumn cNhomtruong = new TableColumn("Nhom truong");
        cNhomtruong.setMinWidth(150);
        cNhomtruong.setMaxWidth(150);
        cNhomtruong.setCellValueFactory(
            new PropertyValueFactory<Nhom,String>("nhomTruong")
        );
        TableColumn cSoluong = new TableColumn("So luong thanh vien");
        cSoluong.setMinWidth(200);
        cSoluong.setMaxWidth(200);
        cSoluong.setCellValueFactory(
            new PropertyValueFactory<Nhom,String>("soLuong")
        );
		
        tableNhom.getColumns().addAll(cManhom, cTennhom, cNhomtruong, cSoluong);
        
        tableNhom.setItems(data);
          
        
        /////////////////////////////////////////////////////////////////////////////////////////////////
        TableColumn cMaDe = new TableColumn("Ma de");
		cMaDe.setMinWidth(60);
		cMaDe.setMaxWidth(60);
		
		TableColumn cMoTa = new TableColumn("Mo ta");
		cMoTa.setMinWidth(460);
		cMoTa.setMaxWidth(460);
		
		TableColumn cLoaiDA = new TableColumn("Loai do an");
		cLoaiDA.setMinWidth(90);
		cLoaiDA.setMaxWidth(90);
		
		TableColumn cLoaiDe = new TableColumn("Loai de");
		cLoaiDe.setMinWidth(70);
		cLoaiDe.setMaxWidth(70);
		
		TableColumn cNgaybd = new TableColumn("Ngay bd");
		cNgaybd.setMinWidth(130);
		cNgaybd.setMaxWidth(130);
		
		TableColumn cDeadline = new TableColumn("Deadline");
		cDeadline.setMinWidth(130);
		cDeadline.setMaxWidth(130);
		
		cMaDe.setCellValueFactory(new PropertyValueFactory<DeMon, String>("de"));
		cMoTa.setCellValueFactory(new PropertyValueFactory<DeMon, String>("moTa"));
		cLoaiDA.setCellValueFactory(new PropertyValueFactory<DeMon, Byte >("loaiDA"));
		cLoaiDe.setCellValueFactory(new PropertyValueFactory<DeMon, String >("loaiDeHien"));
		cNgaybd.setCellValueFactory(new PropertyValueFactory<DeMon, String>("ngayBDDangKy"));
		cDeadline.setCellValueFactory(new PropertyValueFactory<DeMon, Date>("deadline"));
		
	    tableDeConHanDK.setItems(data_de);
	 
	    tableDeConHanDK.getColumns().addAll(cMaDe, cMoTa, cLoaiDA, cLoaiDe, cNgaybd, cDeadline);
	    
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tendn.setAlignment(Pos.TOP_RIGHT);
	}
	
	public void selectNhomDe(){
	      tableNhom.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		      if (newSelection != null) {
		      	this.btnChitietnhom.setDisable(false);
		      	this.btnRutnhom.setDisable(false);
		      	nhomSelect = newSelection;
		      	
		      	//System.out.println(maNhom + "\n" + tenNhom + "\n" + nhomTruong + "\n" + soLuong);
		      }
	      });
	      
	    tableDeConHanDK.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	        	this.btnDangKyDe.setDisable(false);
	        	deSelect = newSelection;
	        }
	    });
	}
	
	public void setTextLop(String ma, String ten){
		 this.maLop.setText(ma);
		 this.tenLop.setText(ten);
	}
	
	public void setTextTenDn(User u){
		user = u;
		this.tendn.setText(u.getUserName());
	}
	
	public void disableButton(){
		this.btnChitietnhom.setDisable(true);
		this.btnRutnhom.setDisable(true);
	}
	
	public void setNhomDefault(){
		this.tabPane.getSelectionModel().select(this.tabNhom);
	}
	
	public void quaylaiClicked(){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
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
		Stage stage = (Stage) btnQuayve.getScene().getWindow();
		stage.setTitle("Sinh viên");
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void dangkynhomClicked(){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../Application/nhomChitiet.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nhomChitietController display = Loader.getController();
		display.setTextTenDn(user);
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.loaiTranhChap("Phantom");
		display.setType(2);
		display.setChiTietNhom(null);
		display.setFix(db);
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnDangkynhom.getScene().getWindow();
		stage.setTitle("Đăng ký nhóm");
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void chitietnhomClicked(){
		Parent pane = null;
		FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../Application/nhomChitiet.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nhomChitietController display = Loader.getController();
		display.setTextTenDn(user);
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.disableEditTenNhom();
		display.setTenNhom(nhomSelect.getTenNhom());
		display.setType(1);
		display.setChiTietNhom(nhomSelect);
		display.setVisibleMaNhom(nhomSelect.getMaNhom());
		
		display.setFix(db);
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnDangkynhom.getScene().getWindow();
		stage.setTitle("Chi tiết nhóm");
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void rutnhomClicked(){
		int dl;
		if(delay.getValue().compareTo("Delay")==0)
			dl = 1;
		else
			dl = 0;
		int kq = teConn.getOutOfTeam(maLop.getText(), nhomSelect.getMaNhom(), user.getUserID(), dl);
		if(kq==1)
			FxDialogs.showInformation("Thông báo", "Rút nhóm thành công!");
		else
			FxDialogs.showInformation("Thông báo", "Rút nhóm thất bại!");
		
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
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.setTextTenDn(user);
		display.setNhomDefault();
		display.setFix(db);
		display.setSinhVienNhomDe(user.getUserID(), maLop.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnRutnhom.getScene().getWindow();
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void dangKyDe_CLicked(){
		nhomTruongCua = teConn.getTeamLeadedBy(user.getUserID(), maLop.getText());
		System.out.println("đăng ký đề  "+ nhomTruongCua.get(0).getMaNhom());
		int kq = 0;
		int tt;
		if(tranhchapDKDe.getValue().compareTo("Unrepeatable Read")==0)
			tt = 2;
		else if(tranhchapDKDe.getValue().compareTo("Lost Update")==0)
			tt = 3;
		else 
			tt = 4;
		if(deSelect.getLoaiDeHien().compareTo("Đề nhóm")==0){
			List<String> choices = new ArrayList<>();
			for(int i=0;i<nhomTruongCua.size(); i++)
				choices.add(nhomTruongCua.get(i).getMaNhom());

			ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);
			dialog.setTitle("Chon nhom");
			dialog.setHeaderText("Chon nhom muon dang ky de nay");
						
			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				kq = subsConn.acceptTeamAssignment(result.get(), deSelect.getDe(), maLop.getText(), tt);
				
			}
		}
		//Tien hanh dang ky de cho nhom
    	else{	    		
    		//Tien hanh dang ky de cho ca nhan
    		kq = subsConn.acceptPersonalAssignment(user.getUserID(), deSelect.getDe(), maLop.getText(), tt);
    	}
		
		if(kq==1)
			FxDialogs.showInformation("Thông báo", "Đăng ký đề thành công!");
		else
			FxDialogs.showInformation("Thông báo", "Đăng ký đề thất bại!");

	}
	
	public void xemDSDeDaDK_Clicked(){
		Parent pane = null;
    	FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../Application/sinhvien_DeDaDK.fxml"));
		try {
			pane = Loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sinhvien_DeDaDKController display = Loader.getController();
		display.setTextTenDn(user);
		display.setTextLop(maLop.getText(), tenLop.getText());
		display.setFix(db);
		display.setSVDeDaDk(user.getUserID(), maLop.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnXemDSDeDaDK.getScene().getWindow();
		stage.setTitle("Danh sách đề đã đăng ký");
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
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
			stage1.setScene(scene);
			stage.setTitle("Cập nhật thông tin cá nhân");
			stage.hide();
			stage1.show();
	   };
}
