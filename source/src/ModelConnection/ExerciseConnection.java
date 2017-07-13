package ModelConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import Models.De;
import application.FxDialogs;

public class ExerciseConnection extends AbstractConnection {

	public ExerciseConnection(String connectionString) {
		super(connectionString);
		// TODO Auto-generated constructor stub
	}

	//đọc danh sách đề
	public Vector<De> getAssignments(){
		Vector<De> info = new Vector<De>();
		try {
			CallableStatement statement = con.prepareCall("{call xemDSDe}");
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
				info.addElement(new De(Info));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}

	//xem thông tin đề
	public De getAssignment(String exID){
		De info = new De();
		try {
			CallableStatement statement = con.prepareCall("{call xemThongTinDe (?)}");
			statement.setString(1, exID);
			boolean res = statement.execute();
		
		
			int i=1;
			if (!res)
			{
				return null;
			}
			ResultSet rs = statement.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnsNumber = rsmd.getColumnCount();
			Vector<String> Info = new Vector<String>();

			if (rs.next())
			{
				while (i!= columnsNumber + 1)
				{
					String s = rs.getString(i);
					Info.addElement(s);
					if (Info.get(i-1) == null)
						Info.set(i-1, "");
					i++;
					System.out.print(s+" ");
				}

			}
			info = new De(Info);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}

	//sửa thông tin đề
	public int updateAssignment(String exID, String des, int delay)
	{
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call suaThongTinDe (?, ?, ?)}");
			statement.setString(1, exID);
			statement.setString(2, des);
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

	//thêm đề
	public String addAssignment(String des, int delay){
		String exID = new String();
		try {
			CallableStatement statement = con.prepareCall("{call ThemDe (?, ?, ?)}");
			statement.setString(1, des);
			statement.setInt(2, delay);
			statement.registerOutParameter(3, java.sql.Types.CHAR);
			statement.setString(3, exID);
			
			statement.execute();
			
			exID = statement.getString(3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return exID;
	}
	
}