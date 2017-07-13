package ModelConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import Models.Nhom;
import Models.User;
import application.FxDialogs;

public class TeamConnection extends AbstractConnection{
	
	public TeamConnection(String connectionString) {
		super(connectionString);
		// TODO Auto-generated constructor stub
	}

	//tạo nhóm
	public String CreateTeam(String name, String subID, String leaderID, int delay){
		String res = "";
		try {
			CallableStatement statement = con.prepareCall("{call DangKyNhom (?, ?, ?, ?)}");
			statement.setString(1, name);
			statement.setString(2, subID);
			statement.setString(3, leaderID);
			statement.registerOutParameter(4, java.sql.Types.INTEGER);
			statement.setInt(4, delay);
			
			boolean t = statement.execute();
			if (t)
			{
				ResultSet rs = statement.getResultSet();
				if (rs.next())
					res = rs.getString(1);
			}	
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return res;
	}

	//thêm thành viên
	public int insertMember(String subjectID, String teamID, String userID, int delay){
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call ThemThanhVienNhom (?, ?, ?,?)}");
			statement.setString(1, subjectID);
			statement.setString(2, teamID);
			statement.setString(3, userID);
			statement.registerOutParameter(4, java.sql.Types.INTEGER);
			statement.setInt(4, delay);
			
			statement.execute();
			
			res = statement.getInt(4);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return res;
	}

	//lấy danh sách nhóm được quản lý bởi gv
	//cái này cũng vậy
	public Vector<Object> getTeamUnderControllOf(String teacherID, String subjectID, String exID, int teamNum){
		
		Vector<Object> info = new Vector<Object>();
		Vector<Nhom> teamList = new Vector<Nhom>();
		try {
			CallableStatement statement = con.prepareCall("{call DSQuanLyCuaGV(?, ?, ?, ?)}");
			statement.setString(1, teacherID);
			statement.setString(2, subjectID);
			statement.setString(3, exID);
			statement.registerOutParameter(4, java.sql.Types.INTEGER);
			statement.setInt(4, teamNum);
			
			boolean t = statement.execute();
			//BI LOI CU CHU no k lay dc
			if (t)
			{
				ResultSet rs = statement.getResultSet();
				ResultSetMetaData rsmd = rs.getMetaData();

				int columnsNumber = rsmd.getColumnCount();
				
				int  i =1;
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
					teamList.add(new Nhom(Info));
					i= 1;
				}
			
				statement.getMoreResults();
				teamNum = statement.getInt(4);
			}	
				

			info.add(teamList);
			info.add(teamNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}
	
	//lấy ds nhóm của sv
	public Vector<Nhom> getTeamListOf(String studentID, String subID){
		Vector<Nhom> info = new Vector<Nhom>();
		try {
			CallableStatement statement = con.prepareCall("{call layNhomSinhVien (?, ?)}");
			statement.setString(1, studentID);
			statement.setString(2, subID);
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
				info.addElement(new Nhom(Info));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}

	//lấy ds thành viên nhóm
	public Vector<User> getTeamMember(String teamID){
		Vector<User> info = new Vector<User>();
		try {
			CallableStatement statement = con.prepareCall("{call XemDSThanhVien_Nhom(?)}");
			statement.setString(1, teamID);
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
				info.addElement(new User(Info.get(0), Info.get(1)));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}

	//lấy ds nhóm mà sv làm nhóm trưởng
	public Vector<Nhom> getTeamLeadedBy(String studentID, String subjectID){
		Vector<Nhom> info = new Vector<Nhom>();
		try {
			CallableStatement statement = con.prepareCall("{call layNhomSVNhomTruong (?, ?)}");
			statement.setString(1, studentID);
			statement.setString(2, subjectID);
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
				info.addElement(new Nhom(Info));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}

	//rút nhóm
	public int getOutOfTeam(String subID, String teamID, String memberID, int delay){
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call RutNhom (?, ?, ?, ?)}");
			statement.setString(1, subID);
			statement.setString(2, teamID);
			statement.setString(3, memberID);
			
			statement.registerOutParameter(4, java.sql.Types.INTEGER);
			statement.setInt(4, delay);
			
			statement.execute();
			
			res = statement.getInt(4);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return res;
	}


}