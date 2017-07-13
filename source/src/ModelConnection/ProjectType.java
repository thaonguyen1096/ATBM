package ModelConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import Models.LoaiDA;
import application.FxDialogs;

public class ProjectType extends AbstractConnection {

	public ProjectType(String connectionString) {
		super(connectionString);
		// TODO Auto-generated constructor stub
	}
	
	public Vector<LoaiDA> getProjectTypes(){
		Vector<LoaiDA> info = new Vector<LoaiDA>();
		try {
			CallableStatement statement = con.prepareCall("{call XemDSLoaiDoAn}");
			//statement.registerOutParameter(4, java.sql.Types.VARCHAR);
			//trả về 1 nếu có resultset ngc lại trả về 0
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
				info.addElement(new LoaiDA(Info));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FxDialogs.showError("Lỗi", e.getMessage());
		}
		return info;
	}

}
