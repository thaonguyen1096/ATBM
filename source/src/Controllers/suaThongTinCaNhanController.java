package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import ModelConnection.UserConnection;
import Models.SvNhom;
import Models.User;
import application.FxDialogs;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class suaThongTinCaNhanController implements Initializable {
	@FXML
	private ImageView iconUser; 
	@FXML
	private Label tendn; 
	@FXML
	private Label dangxuat;
	@FXML
	private Label lbKhoa;
	
	@FXML
	private TextField textMaNguoiDung;
	@FXML
	private TextField textKhoa;
	@FXML
	private TextField textHoTen;
	@FXML
	private TextField textMatKhau;
	@FXML
	private TextField textSDT;
	@FXML
	private TextField textEmail;
	@FXML
	private TextArea textDiaChi;
	
	@FXML
	private Button btnLuuChinhSua;
	@FXML
	private Button btnQuayVe;
	@FXML
	private Button btnDoiMatKhau;
	@FXML
	private ComboBox<String> delay;
	@FXML
	private ComboBox<String> tranhchap;
	
	private Stage previousPage;
	
	private String db;
	
	public void setFix(String f){
	   db = f;
	}
	private UserConnection userConn = new UserConnection(db);
	private User user;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tendn.setAlignment(Pos.TOP_RIGHT);	}
	
	public void hienKhoa(){
		lbKhoa.setVisible(true);
		textKhoa.setVisible(true);
	}
	
	public void setTextTenDn(User u){
		user = u;
		this.tendn.setText(u.getUserName());
		textKhoa.setText(u.getUserYear());
		textMaNguoiDung.setText(u.getUserID());
		textKhoa.setText(u.getUserYear());
		textHoTen.setText(u.getUserName());
		textMatKhau.setText("**************");
		textSDT.setText(u.getUserTel());;
		textEmail.setText(u.getUserEmail());;
		textDiaChi.setText(u.getUserAdd());;
	}
	
	public void setPreviousPage(Stage page){
		previousPage = page;
	}
	
	public void btnDoiMatKhauClick(){
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Doi mat khau");
		dialog.setHeaderText("Doi mat khau");

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Hoan tat", ButtonData.OK_DONE);
		ButtonType loginButtonCancel = new ButtonType("Huy", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, loginButtonCancel);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		PasswordField passCu = new PasswordField();
		passCu.setPromptText("Mat khau cu");
		PasswordField passMoi = new PasswordField();
		passMoi.setPromptText("Mat khau moi");
		
		ComboBox<String> del = new ComboBox<String>();
		del.setValue("No delay");
		del.getItems().addAll("No delay", "Delay");
		
		ComboBox<String> trchap = new ComboBox<String>();
		trchap.setValue("Unrepeatable Read");
		trchap.getItems().addAll("Unrepeatable Read");
		
		grid.add(new Label("Nhap mat khau cu:"), 0, 0);
		grid.add(passCu, 1, 0);
		grid.add(new Label("Nhap mat khau moi:"), 0, 1);
		grid.add(passMoi, 1, 1);
		grid.add(del, 1, 2);
		grid.add(trchap, 1, 3);

		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		passCu.textProperty().addListener((observable, oldValue, newValue) -> {
			passMoi.textProperty().addListener((observable1, oldValue1, newValue1) -> {
				if(!newValue.trim().isEmpty() && !newValue1.trim().isEmpty())
					loginButton.setDisable(false);
				else
					loginButton.setDisable(true);
			});
		});
		
		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> passCu.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(passCu.getText(), passMoi.getText());
		    }
		    return null;
		});

		
		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(pass -> {
			int kq = userConn.changPasswordDelay(user.getUserID(), pass.getKey(), pass.getValue(), 1);
			if(kq==1)
				FxDialogs.showInformation("Thông báo", "Đổi thành công!");
			else
				FxDialogs.showInformation("Thông báo", "Đổi thất bại!");
		});
	}
	
	public void btnQuayVeClicked(){
		Stage stage = (Stage) btnQuayVe.getScene().getWindow();
		stage.setResizable(false);
		stage.close();
		previousPage.show();
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
	
	public void btnLuuChinhSuaClicked(){
				int tt = 0;
				if(tranhchap.getValue().compareTo("Unrepeatbale Read")==0)
					tt=1;
				else if(tranhchap.getValue().compareTo("Dirty Read")==0)
					tt = 4;
				// Create the custom dialog.
				Dialog<String> dialog = new Dialog<>();
				dialog.setTitle("Mật khẩu");
				dialog.setHeaderText("Nhập mật khẩu");

				// Set the button types.
				ButtonType loginButtonType = new ButtonType("Hoàn tất", ButtonData.OK_DONE);
				ButtonType loginButtonCancel = new ButtonType("Hủy", ButtonData.CANCEL_CLOSE);
				dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, loginButtonCancel);

				// Create the username and password labels and fields.
				GridPane grid = new GridPane();
				grid.setHgap(10);
				grid.setVgap(10);
				grid.setPadding(new Insets(20, 150, 10, 10));

				PasswordField pass = new PasswordField();
				pass.setPromptText("Mật khẩu");
				grid.add(pass, 0, 0);
				
				dialog.getDialogPane().setContent(grid);


				Optional<String> result = dialog.showAndWait();
				
			    int kq = 0; //
				kq = userConn.updateUserDelay(user.getUserID(), pass.getText(), user.getUserName(), textEmail.getText(), textSDT.getText(), textDiaChi.getText(), 1);
				if(kq!=0)
					FxDialogs.showInformation("Thông báo", "Lưu thành công!");
				else
					FxDialogs.showInformation("Thông báo", "Lưu thât bại!!!");
			
				
		
		
		
	}
	
}
