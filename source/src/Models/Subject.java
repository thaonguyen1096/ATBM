package Models;

import javafx.beans.property.SimpleStringProperty;

public class Subject {
	private SimpleStringProperty subjectID;
	private SimpleStringProperty subjectName;
	
	public Subject(String id, String subName)
	{
		subjectID = new SimpleStringProperty(id);
		subjectName = new SimpleStringProperty(subName);
	}
	
	
    public String getSubjectID() {
        return subjectID.get();
    }
 
    public void setSubjectID(String id) {
        subjectID.set(id);
    }
    
    public String getSubjectName() {
        return subjectName.get();
     }
     
        
    public void setSubjectName(String subName) {
       subjectName.set(subName);
    }
}
