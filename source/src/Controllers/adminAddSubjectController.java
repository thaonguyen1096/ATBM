package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ModelConnection.SubjectConnection;
import ModelConnection.UserConnection;
import Models.Mon;
import application.FxDialogs;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class adminAddSubjectController implements Initializable{
	
	@FXML
	private Label lblTitle;
	@FXML
	private TextField txtID;
	@FXML
	private TextField txtName;
	@FXML
	private Button btnOK;
	@FXML
	private ComboBox<String> delay;
	@FXML
	private ComboBox<String> tranhchap;
	
	private String db;
	public void setFix(String f){
	   db = f;
	}
	
	private SubjectConnection subConn = new SubjectConnection(db);
	
	public void createSubject(MouseEvent e){
		int kq = 0;
		System.out.println(txtID.getText());
		System.out.println(txtName.getText());
		int tt = 0;
		if(delay.getValue().compareTo("Delay")==0)
			tt = 1;
		if(txtID.getText().compareTo("")==0 && txtName.getText().compareTo("")!=0 )
			kq = subConn.createSubject(txtName.getText(), tt);//phantom
		if(txtID.getText().compareTo("")!=0 && txtName.getText().compareTo("")!=0 )
			kq = subConn.updateSubject(new Mon(txtID.getText(), txtName.getText()), 3);//lost update
		if(kq!=0)
			FxDialogs.showInformation("Thông báo", "Lưu thành công!");
		else
			FxDialogs.showInformation("Thông báo", "Lưu thât bại!!!");
	}
	
	public void setTitle(String tit)
	{
		lblTitle.setText(tit);
	}
	
	public void setID(String id)
	{
		txtID.setText(id);
	}
	
	public void loaiTranhChap(String loai){
		tranhchap.setValue(loai);
		tranhchap.getItems().add(loai);
	}
	
	
	public void goBack(MouseEvent e){
	    Node source = (Node) e.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		txtID.setDisable(true);
		
	}

}
