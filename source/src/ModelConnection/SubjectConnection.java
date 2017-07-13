package ModelConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import Models.Mon;
import application.FxDialogs;

public class SubjectConnection extends AbstractConnection{
	
	public SubjectConnection(String connectionString) {
		super(connectionString);
		// TODO Auto-generated constructor stub
	}

	//lấy ds môn của admin
	public Vector<Mon> getAllSubject(){
		Vector<Mon> info = new Vector<Mon>();
		try {
			CallableStatement statement = con.prepareCall("{call XemDSMonAdmin}");
			boolean res = statement.execute();
		
		
			int i=1;
			if (!res)
			{
				return null;
			}
			ResultSet rs = statement.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnsNumber = rsmd.getColumnCount();
			

			while (rs.next())
			{
				Vector<String> Info = new Vector<String>();
				while (i!= (columnsNumber + 1))
				{
					String s = rs.getString(i);
					Info.addElement(s);
					if (Info.get(i-1) == null)
						Info.set(i-1, "");
					i++;
//					System.out.println(s);
				}
				i= 1;
				info.addElement(new Mon(Info));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}

	//lấy ds môn của giáo viên
	public Vector<Mon> getSubjectsUnderControllOf(String teacherID){
		Vector<Mon> info = new Vector<Mon>();
		try {
			CallableStatement statement = con.prepareCall("{call XemDSMonGiaoVien (?)}");
			statement.setString(1, teacherID);
			boolean res = statement.execute();
		
		
			int i=1;
			if (!res)
			{
				return null;
			}
			ResultSet rs = statement.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnsNumber = rsmd.getColumnCount();
			

			while (rs.next())
			{
				Vector<String> Info = new Vector<String>();
				while (i!= columnsNumber + 1)
				{
					String s = rs.getString(i);
					Info.addElement(s);
					if (Info.get(i-1) == null)
						Info.set(i-1, "");
					i++;
					System.out.print(s+" ");
				}
				i= 1;
				info.addElement(new Mon(Info));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}
	
	//lấy ds môn của sinh viên
	public Vector<Mon> getSubjectsAttendedBy(String studentID){
		Vector<Mon> info = new Vector<Mon>();
		try {
			CallableStatement statement = con.prepareCall("{call XemDSMonSinhVien (?)}");
			statement.setString(1, studentID);
			boolean res = statement.execute();
		
		
			int i=1;
			if (!res)
			{
				return null;
			}
			ResultSet rs = statement.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnsNumber = rsmd.getColumnCount();
			

			while (rs.next())
			{
				Vector<String> Info = new Vector<String>();
				while (i!= columnsNumber + 1)
				{
					String s = rs.getString(i);
					Info.addElement(s);
					if (Info.get(i-1) == null)
						Info.set(i-1, "");
					i++;
					System.out.print(s+" ");
				}
				i= 1;
				info.addElement(new Mon(Info));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}

	//thêm môn học mới
	public int createSubject(String subjectName, int delay){
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call themMon (?, ?)}");
			statement.setString(1, subjectName);
			statement.registerOutParameter(2, java.sql.Types.INTEGER);
			statement.setInt(2, delay);
			
			statement.execute();
			
			res = statement.getInt(2);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return res;
	}

	//chỉnh sửa thông tin môn
	public int updateSubject(Mon subject, int delay){
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call SuaTT_Mon (?, ?, ?)}");
			statement.setString(1, subject.getMaLop());
			statement.setString(2, subject.getTenLop());
			statement.registerOutParameter(3, java.sql.Types.INTEGER);
			statement.setInt(3, delay);
			
			statement.execute();
			
			res = statement.getInt(3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return res;
	}

	
}
