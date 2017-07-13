package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

import ModelConnection.TeamConnection;
import ModelConnection.UserConnection;
import Models.Nhom;
import Models.SvNhom;
import Models.User;
import application.FxDialogs;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class nhomChitietController implements Initializable {
	@FXML
	private Label tendn;
	@FXML
	private Label dangxuat;
	@FXML
	private ImageView iconUser; 
	@FXML
	private Label maLop;
	@FXML
	private Label lableMaNhom;
	@FXML
	private TextField textMaNhom ;
	@FXML
	private Label tenLop;
	@FXML
	private TextField tenNhom;
	@FXML
	private Button btnQuayve;
	@FXML
	private Button btnHoantat;
	@FXML
	private Button btnThemthanhvien;
	@FXML
	private TableView<String[]> tableNhom;
	@FXML
	private ComboBox<String> delay;
	@FXML
	private ComboBox<String> tranhchap;
	
	private User user;
	private String db;
	
	public void setFix(String f){
	   db = f;
	}
	private TeamConnection teConn = new TeamConnection(db);
	private UserConnection uConn = new UserConnection(db);
	private String[] thanhVienThem = new String[3];
	
	private int type;
	
	ObservableList<SvNhom> data;  
	
	public void setType(int t)
	{
		type = t;
	}
	
	public void setChiTietNhom(Nhom mNhom){
		TableColumn cMasv = new TableColumn("MSSV");
		cMasv.setMinWidth(200);
		cMasv.setMaxWidth(200);
		
		TableColumn cTenSv = new TableColumn("Tên sinh vien");
		cTenSv.setMinWidth(300);
		cTenSv.setMaxWidth(300);
		
		TableColumn cNhomTruong = new TableColumn("Nhóm trưởng");
		cNhomTruong.setMinWidth(200);
		cNhomTruong.setMaxWidth(200);
		
		tableNhom.getColumns().addAll(cMasv, cTenSv, cNhomTruong);
		
		
		cMasv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
            	String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length>1 ? x[0] : "");
            }
        });
		
		cTenSv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
            	String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length>1 ? x[1] : "");
            }
        });
		
		cNhomTruong.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
            	String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length>1 ? x[2] : "");
            }
        });
		
		if (type == 1)
		{
			Vector<User> uList = teConn.getTeamMember(mNhom.getMaNhom());
			String[][] data = User.convert(uList, mNhom.getNhomTruong());
			tableNhom.getItems().addAll(Arrays.asList(data));
			btnHoantat.setVisible(false);
		}
		else
		{
			User u = new User(user.getUserID(), user.getUserName());
			String[] nhomTruong = User.convert2StringArray(u, true);
			tableNhom.getItems().add(nhomTruong);
		}
	}
	
	public void hoanTatClicked(){
		int i = 1;
		int tt = 0;
		if(tranhchap.getValue().compareTo("Phamtom")==0)
			tt = 2;
		else if(tranhchap.getValue().compareTo("Lost Update")==0)
			tt = 3;
		String[] teamMem = tableNhom.getItems().get(0);
		String teamID = teConn.CreateTeam(tenNhom.getText(), maLop.getText(), user.getUserID(), tt);
		do
		{
			teamMem = tableNhom.getItems().get(i);
			teConn.insertMember(maLop.getText(), teamID, teamMem[0], tt);
			i++;
			System.out.println(i);
		} while (teamMem != null);
		if(!teamID.isEmpty())
			FxDialogs.showInformation("Thông báo", "Đăng ký nhóm thành công");
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tendn.setAlignment(Pos.TOP_RIGHT);
		tenNhom.setFocusTraversable(false);
		
	}
	
	public void setTextLop(String ma, String ten){
		 this.maLop.setText(ma);
		 this.tenLop.setText(ten);
	}
	
	public void setTextTenDn(User u){
		user = u;
		this.tendn.setText(u.getUserName());
	}
	
	public void disableEditTenNhom(){
		this.tenNhom.setEditable(false);
	}
	
	public void setTenNhom(String ten){
		this.tenNhom.setText(ten);
	}
	
	public void loaiTranhChap(String loai){
		tranhchap.setValue(loai);
		tranhchap.getItems().add(loai);
	}
	
	public void setVisibleMaNhom(String ma){
		this.lableMaNhom.setVisible(true);
		this.textMaNhom.setText(ma);
		this.textMaNhom.setVisible(true);
	}
	
	public void layThongTinNhom(String maNhom){
		
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
			stage.setScene(scene);
	   }
	}
	
	public void quaylaiClicked(){
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
		display.setNhomDefault();
		display.setSinhVienNhomDe(user.getUserID(), maLop.getText());
		display.setFix(db);
		display.setSinhVienNhomDe(user.getUserID(), maLop.getText());
		Scene scene = new Scene(pane);
		Stage stage = (Stage) btnQuayve.getScene().getWindow();
		stage.setTitle("Sinh viên");
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("../Application/sinhvien.css").toExternalForm());					        
		stage.setScene(scene);
	}
	
	public void themthanhvienClicked(){
		TextInputDialog dialog = new TextInputDialog("");		
		dialog.getDialogPane().getStylesheets().add(
				   getClass().getResource("../Application/sinhvien.css").toExternalForm());
		dialog.getDialogPane().getStyleClass().add("dialogThemthanhvien");
		dialog.setTitle("Thêm thành viên");
		dialog.setHeaderText("Nhập MSSV");
		dialog.getDialogPane().setPrefSize(300, 180);
		
		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			if(type==1){
				int k = teConn.insertMember(maLop.getText(), textMaNhom.getText(), result.get(), 4);//dirty read
				if(k==1){
					FxDialogs.showInformation("Thông báo", "Thêm thành viên thành công");
					thanhVienThem = uConn.getStudentInfo(result.get());
					tableNhom.getItems().add(thanhVienThem);
					tableNhom.refresh();
				}
				else{
					FxDialogs.showInformation("Thông báo", "Thêm thành viên thất bại");
				}
			}
			else{
				thanhVienThem = uConn.getStudentInfo(result.get());
				tableNhom.getItems().add(thanhVienThem);
				tableNhom.refresh();
			}
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
