package Models;

import java.util.Vector;

import javafx.beans.property.SimpleStringProperty;

public class User {
	private  SimpleStringProperty userID;
	private SimpleStringProperty userName;
	private SimpleStringProperty userYear;
	private SimpleStringProperty userTel;
	private  SimpleStringProperty userAdd;
	private  SimpleStringProperty userEmail;
	private  SimpleStringProperty userType;
	private  SimpleStringProperty userTypeDisplay;
	
	public User(String id, String uName)
	{
		this.userID = new SimpleStringProperty(id);
		this.userName = new SimpleStringProperty(uName);
	}
	
	public User(String id, String uName, String year, String tel, String add, String email, String type){
		
		this.userID = new SimpleStringProperty(id);
		this.userName = new SimpleStringProperty(uName);
		this.userYear = new SimpleStringProperty(year);
		this.userTel = new SimpleStringProperty(tel);
		this.userAdd = new SimpleStringProperty(add);
		this.userEmail = new SimpleStringProperty(email);
		this.userType = new SimpleStringProperty(type);
		if(type.compareTo("0")==0)
			this.userTypeDisplay = new SimpleStringProperty("Admin");
    	else if(type.compareTo("1")==0)
    		this.userTypeDisplay = new SimpleStringProperty("Giáo viên");
    	else 
    		this.userTypeDisplay = new SimpleStringProperty("Sinh viên");
	}
	
	public User(Vector<String> info)
	{
		this.userID = new SimpleStringProperty(info.get(0));
		this.userName = new SimpleStringProperty(info.get(1));
		if (info.size() != 2)
		{
			this.userYear = new SimpleStringProperty(info.get(5));
			this.userTel = new SimpleStringProperty(info.get(3));
			this.userAdd = new SimpleStringProperty(info.get(4));
			this.userEmail = new SimpleStringProperty(info.get(2));
			this.userType = new SimpleStringProperty(info.get(6));
			if(info.get(6).compareTo("0")==0)
				this.userTypeDisplay = new SimpleStringProperty("Admin");
	    	else if(info.get(6).compareTo("1")==0)
	    		this.userTypeDisplay = new SimpleStringProperty("Giáo viên");
	    	else 
	    		this.userTypeDisplay = new SimpleStringProperty("Sinh viên");
		}
		
	}
	
	public static String[] convert2StringArray(User u, boolean ex)
	{
		String[] res = new String[3];
		res[0] = u.getUserID();
		res[1] = u.getUserName();
		if (ex)
		{
			res[2] = "X";
		}
		else
			res[2] = "";
		return res;
	}
	public static String[][] convert(Vector<User> uList, String leaderID)
	{
		String[][] res = new String[uList.size()][3];
		for (int i = 0; i<uList.size(); i++)
		{
			User curUser = uList.get(i);
			
			if (curUser.getUserID().compareTo(leaderID) == 0)
				res[i] = User.convert2StringArray(curUser, true);
			else
				res[i] = User.convert2StringArray(curUser, false);
		}
		return res;
	}
	
	
    public String getUserID() {
        return userID.get();
    }
 
    public void setUserID(String id) {
        userID.set(id);
    }
    
    public String getUserName() {
        return userName.get();
     }
     
        
    public void setUserName(String uName) {
       userName.set(uName);
    }
    
    public String getUserYear() {
        return userYear.get();
    }
    
    public void setUserYear(String dob) {
    	userYear.set(dob);
    }
    
    public String getUserTel() {
        return userTel.get();
    }
    
    public void setUserAdd(String uAdd) {
        userAdd.set(uAdd);
    }

    public String getUserAdd() {
       return userAdd.get();
    }
    
    public void setUserEmail(String uEm) {
        userEmail.set(uEm);
    }

    public String getUserEmail() {
       return userEmail.get();
    }
    
    public void setUserType(String type) {
        userType.set(type);
    }

    public String getUserType() {
       return userType.get();
    }
    
    public String getUserTypeDisplay(){
       return userTypeDisplay.get();
    }
}
