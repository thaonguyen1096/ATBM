package Controllers;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ModelConnection.SubjectConnection;
import ModelConnection.UserConnection;
import Models.Mon;
import Models.Subject;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import application.FxDialogs;

public class adminPageController implements Initializable {
	   @FXML
	   private Button btnBack;
	   @FXML
	   private Button btnLock;
	   @FXML
	   private Button btnAdd;
	   @FXML
	   private Label userPic;
	   @FXML
	   private TableView<User> tblUser;
	   @FXML
	   private TableColumn<User, String> idCol;
	   @FXML
	   private TableColumn<User, String> nameCol;
	   @FXML
	   private TableColumn<User, String> dobCol;
	   @FXML
	   private TableColumn<User, String> telCol;
	   @FXML
	   private TableColumn<User, String> addCol;
	   @FXML
	   private TableColumn<User, String> emCol;
	   @FXML
	   private TableColumn<User, String> yeCol;
	   @FXML
	   private Label uName;
	   @FXML
	   private Label logOut;
	   @FXML
	   private TableView<Mon> tblSubject;
	   @FXML
	   private TableColumn<Mon, String> subIDCol;
	   @FXML
	   private TableColumn<Mon, String> subNameCol;
	   @FXML
	   private TextField textTuKhoa;
	   @FXML
	   private Button deleteSub;
	   @FXML
	   private Button openSub;
	   @FXML
	   private Button updateSub;
	   @FXML
	   private Button btnTimKiem;
	   @FXML
	   private Button btnRefesh;
	   
	   private User user;
	   private User userSelect;
	   private Mon monSelect;
	   
	   private String db;
		public void setFix(String f){
		   db = f;
		}
	   private UserConnection userConn = new UserConnection(db);
	   private SubjectConnection subjectConn = new SubjectConnection(db);
	   
	      
	   public void deactiveUser(MouseEvent e){
		   if (FxDialogs.showConfirm("Nguy hiểm", "Bạn có muốn khóa tài khoản này??", 0, "Có", "Không").equals(FxDialogs.YES)) {
		       if(userConn.lockUser(userSelect.getUserID()) == 1){
		    	   FxDialogs.showInformation("Thông báo", "Khóa tài khoản người dùng thành công!");
					tblUser.refresh();
		       }
		       else{
		    	   FxDialogs.showInformation("Thông báo", "Khóa tài khoản người dùng không thành công!");
		       }
		   }
	   };
	   
	   public void addUser(MouseEvent e){
		   try {
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("../application/adminAddUser.fxml"));
		        
		        Parent root1 = (Parent) fxmlLoader.load();
		        adminAddUserController con = fxmlLoader.getController();
		        con.setFix(db);
		        Stage stage1 = new Stage();
		        stage1.setScene(new Scene(root1));  
		        stage1.initModality(Modality.APPLICATION_MODAL);
		        stage1.show();
		        } catch(Exception e1) {
		           e1.printStackTrace();
		          }
		   
	   };
	   
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
			Stage stage = (Stage) uName.getScene().getWindow();
			suaThongTinCaNhanController display = Loader.getController();
			display.setTextTenDn(user);
			display.setPreviousPage(stage);
			display.setFix(db);
			Stage stage1 = new Stage();
			Scene scene = new Scene(pane);
			stage1.setResizable(false);				        
			stage1.setScene(scene);
			stage.hide();
			stage1.show();
	   };

	   
	   //môn học
	   public void deleteSubject(MouseEvent e)
	   {
		   if (FxDialogs.showConfirm("Nguy hiểm", "Bạn có muốn khóa môn học này??", 0, "Có", "Không").equals(FxDialogs.YES)) {
			   JFrame frame = new JFrame("dangnhap");
				frame.setAlwaysOnTop(true);
//		       if(userConn.(monSelect.getMaLop()) == 1){
//					JOptionPane.showMessageDialog(frame, "Khóa môn học thành công!!");
//					tblSubject.refresh();
//		       }
//		       else{
//		    	   JOptionPane.showMessageDialog(frame, "Khóa môn học không thành công");
//		       }
		   }
	   }
	   
	   public void updateSubject(MouseEvent e)
	   {
		   Mon item = monSelect;
		   System.out.println(item.getMaLop());
		   try {
		        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../application/adminAddSubject.fxml"));

		        Parent root3 = (Parent) fxmlLoader.load();
		        adminAddSubjectController con = fxmlLoader.getController();
		        con.setID(item.getMaLop());
		        con.setTitle("Chỉnh sửa môn học");
		        con.setFix(db);
		        con.loaiTranhChap("Lost Update");
		        Stage stage3 = new Stage();
		        stage3.setTitle("Chỉnh sửa môn học");
		        stage3.setScene(new Scene(root3));  
		        stage3.initModality(Modality.APPLICATION_MODAL);
		        stage3.show();
		        } catch(Exception e1) {
		           e1.printStackTrace();
		          }
		   
	   }
	   
	   public void openSubject(MouseEvent e)
	   {
		   try {
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("../application/adminAddSubject.fxml"));
		        
		        Parent root2 = (Parent) fxmlLoader.load();
		        adminAddSubjectController con = fxmlLoader.getController();
		        con.loaiTranhChap("Phantom");
		        con.setFix(db);
		        Stage stage2 = new Stage();
		        stage2.setScene(new Scene(root2));  
		        stage2.setTitle("Thêm môn học");
		        stage2.initModality(Modality.APPLICATION_MODAL);
		        stage2.show();
		        } catch(Exception e1) {
		           e1.printStackTrace();
		          }
			   
	   }
	   
	   public void btnTimKiemClicked(){		  
		   List<User> t = userConn.findUser(textTuKhoa.getText());
		   ObservableList<User> list = FXCollections.observableArrayList(t);
		   tblUser.setItems(list);
		   tblUser.refresh();
	   }

	   
	   public ObservableList<User> getUserList(){
		   Vector<User> u = new Vector<User>();
//		   u = userConn.getUserUnderControllOf();
	       ObservableList<User> list = FXCollections.observableArrayList(u);
	       return list;
	   };
	   
	   public ObservableList<Mon> getSubjectList(){
		   	  List<Mon> t = subjectConn.getAllSubject();
		      ObservableList<Mon> list = FXCollections.observableArrayList(t);
		      return list;
	   };
//	   
	   public void lgOut(MouseEvent e){
		   if (FxDialogs.showConfirm("Thông báo", "Bạn có muốn đăng xuất??", 1, "Có", "Không").equals(FxDialogs.YES)) {
			   Parent pane = null;
		    	FXMLLoader Loader = new FXMLLoader();
		    	Loader.setLocation(getClass().getResource("../Application/signIn.fxml"));
				try {
					pane = Loader.load();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Scene scene = new Scene(pane); 
				Node source = (Node) e.getSource();
			    Stage stage = (Stage) source.getScene().getWindow();
			    stage.setResizable(false);				        
				stage.setScene(scene);
		   }
	   };
	   

	   public void setUser(User u)
	   {
		   user = u;
		   uName.setText(user.getUserName());
	   };
	   
	   public void btnRefeshClicked(){
		   ObservableList<Mon> list1 = getSubjectList();
		   System.out.println(list1.get(list1.size()-1).getTenLop());
		   tblSubject.setItems(list1);
		   tblSubject.refresh();
	   }
	   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
//		ObservableList<User> list = getUserList();
//		tblUser.setItems(list);
		
		ObservableList<Mon> list1 = getSubjectList();
		tblSubject.setItems(list1);
		
		btnLock.setDisable(true);
		tblUser.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			btnLock.setDisable(false);  
			userSelect = newSelection;
		});
		
			deleteSub.setDisable(true);
			updateSub.setDisable(true);
			
		tblSubject.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			updateSub.setDisable(false);
			deleteSub.setDisable(false);
			monSelect = newSelection;
		});
		
	}
}
