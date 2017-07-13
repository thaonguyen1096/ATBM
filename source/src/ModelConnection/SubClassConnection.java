package ModelConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import Models.ChiTietDeMon;
import Models.De;
import Models.DeMon;
import Models.Nhom;
import application.FxDialogs;

public class SubClassConnection extends AbstractConnection{

	public SubClassConnection(String connectionString) {
		super(connectionString);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args){

				Vector<String> list = new Vector<String>();
				list.add("hô hô");
				list.addElement("hehe");
				Vector<Object> l = new Vector<Object>();
				l.add(list);
				int i = list.size();
				l.add(i);
				System.out.println(((Vector<String>)l.get(0)).get(0));
				System.out.println(l.get(1));

		    
	}
	
	//thêm đề môn cũ
	public int insertOldExercise(String exID, String subID, String projectType, boolean exType, int maxNum, int studentPerTeam, String startDate, String deadline) throws ParseException{
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call ThemDeMonCu (?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			statement.setString(1, exID);
			statement.setString(2, subID);
			statement.setString(3, projectType);
			statement.setBoolean(4, exType);
			statement.setInt(5, maxNum);
			statement.setInt(6, studentPerTeam);
			statement.setTimestamp(7, convertString2Datetime(startDate));;
			statement.setTimestamp(8, convertString2Datetime(deadline));
			statement.registerOutParameter(9, java.sql.Types.INTEGER);
			statement.setInt(9, res);
			
			statement.execute();
			
			res = statement.getInt(9);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return res;
	}
	
	//chuyển chuỗi thành ngày giờ (10/24/2014 04:40:00.000)
	public Timestamp convertString2Datetime(String date) throws ParseException
	{
		
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate;
			parsedDate = dateFormat.parse(date);
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;

		
	}
	
	//thêm đề môn mới
	public int insertNewExercise(String des, String subID, String projectType, boolean exType, int maxNum, int studentPerTeam, String startDate, String deadline) throws ParseException{
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call ThemDeMonMoi (?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			statement.setString(1, des);
			statement.setString(2, subID);
			statement.setString(3, projectType);
			statement.setBoolean(4, exType);
			statement.setInt(5, maxNum);
			statement.setInt(6, studentPerTeam);
			statement.setTimestamp(7, convertString2Datetime(startDate));;
			statement.setTimestamp(8, convertString2Datetime(deadline));
			statement.registerOutParameter(9, java.sql.Types.INTEGER);
			statement.setInt(9, res);
			
			statement.execute();
			
			res = statement.getInt(9);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return res;
	}

	//hủy đề đã đăng ký cá nhân
	public int cancelPersonalProject(String studentID, String exID, String subjectID, int delay)
	{
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call huyDangKyDeCaNhan (?, ?, ?, ?)}");
			statement.setString(1, studentID);
			statement.setString(2, exID);
			statement.setString(3, subjectID);
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
	
	//hủy đề đăng ký nhóm
	public int cancelTeamProject(String teamID, String exID, String subjectID, int delay)
	{
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call huyDangKyDeNhom (?, ?, ?, ?)}");
			statement.setString(1, teamID);
			statement.setString(2, exID);
			statement.setString(3, subjectID);
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

	//xóa đề môn
	public int eraseAssignment(String subjectID, String exID, int delay){
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call XoaDeMon (?, ?, ?)}");
			statement.setString(1, subjectID);
			statement.setString(2, exID);
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

	//sửa đề môn
	public int updateAssignment(String subjectID, String exID, int maxStudentNum, int studentPerTeam, String startDate, String deadline, int delay) throws ParseException{
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call SuaDeMon (?, ?, ?, ?, ?, ?, ?)}");
			statement.setString(1, subjectID);
			statement.setString(2, exID);
			statement.setInt(3, maxStudentNum);
			statement.setInt(4, studentPerTeam);
			System.out.println(convertString2Datetime(startDate).toString());
			statement.setTimestamp(5, convertString2Datetime(startDate));;
			statement.setTimestamp(6, convertString2Datetime(deadline));
			statement.registerOutParameter(7, java.sql.Types.INTEGER);
			statement.setInt(7, delay);
			
			statement.execute();
			
			res = statement.getInt(7);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return res;
	}

	//đăng ký đề cá nhân
	public int acceptPersonalAssignment(String studentID, String exID, String subjectID, int delay){
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call usp_DangKyDeCaNhan (?, ?, ?, ?)}");
			statement.setString(1, studentID);
			statement.setString(2, exID);
			statement.setString(3, subjectID);
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
	
	//đăng ký đề nhóm
	public int acceptTeamAssignment(String teamID, String exID, String subjectID, int delay){
		int res = 0;
		try {
			CallableStatement statement = con.prepareCall("{call usp_DangKyDeNhom (?, ?, ?, ?)}");
			statement.setString(1, teamID);
			statement.setString(2, exID);
			statement.setString(3, subjectID);
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

	//tìm danh sách đề mà giáo viên quản lý 
	public Vector<DeMon> getAssignmentUnderControllOf(String teacherID, String subjectID)
	{
		Vector<DeMon> info = new Vector<DeMon>();
		try {
			CallableStatement statement = con.prepareCall("{call DS_DeMon_GV (?, ?)}");
			statement.setString(1, teacherID);
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
				Vector<Object> Info = new Vector<Object>();
				while (i!= columnsNumber + 1)
				{
					if ((i == 6) || (i == 10))
					{
						if (rs.getObject(i) == null)
							Info.addElement("");
						else
							Info.addElement(rs.getTimestamp(i).toString());
					}
					else if (i == 4)
					{
						if (rs.getBoolean(i) == true)
							Info.addElement("Đề cá nhân");
						else
							Info.addElement("Đề nhóm");
					}			
					else
						Info.addElement(rs.getObject(i));
					if (Info.get(i-1) == null)
						Info.set(i-1, "");
					i++;
					
				}
				i= 1;
				info.addElement(new DeMon(Info));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}

	
	//xem danh sách đề còn hạn đăng ký của môn
	public Vector<DeMon> getAvailableAssignment(String subjectID){
		Vector<DeMon> info = new Vector<DeMon>();
		try {
			CallableStatement statement = con.prepareCall("{call TkiemDe_MH_SV (?)}");
			statement.setString(1, subjectID);
			
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
				Vector<Object> Info = new Vector<Object>();
				while (i!= columnsNumber + 1)
				{
					if ((i == 6) || (i == 10))
					{
						if (rs.getObject(i) == null)
							Info.addElement("");
						else
							Info.addElement(rs.getTimestamp(i).toString());
					}
					else if (i == 4)
					{
						if (rs.getBoolean(i) == true)
							Info.addElement("Đề cá nhân");
						else
							Info.addElement("Đề nhóm");
					}		
					else
						Info.addElement(rs.getObject(i));
					if (Info.get(i-1) == null)
						Info.set(i-1, "");
					i++;
					
				}
				i= 1;
				info.addElement(new DeMon(Info));
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}

	
	//xem danh sách đề sinh viên đã đăng ký
	public Vector<ChiTietDeMon> getAssignmentOf(String studentID)
		{
			Vector<ChiTietDeMon> info = new Vector<ChiTietDeMon>();
			try {
				CallableStatement statement = con.prepareCall("{call XemDSDeSV (?)}");
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
					info.addElement(new ChiTietDeMon(Info));
				}
				

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				FxDialogs.showError("Lỗi", e.getMessage());
			}
			return info;
		}	
	
	
	
}
