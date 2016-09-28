package pharmasoft.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import pharmasoft.dbConnection.DataBaseConnection;
import pharmasoft.util.StringFormatter;

public class IdGeneratorDAO {
	
    Statement statement = null;
    ResultSet rs = null;
    DataBaseConnection dbConnection;

	public IdGeneratorDAO() {
		
		dbConnection = new DataBaseConnection();
	}
	
    public String getNextSerial (String idType) {
    	Date todayDate = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
    	String strDate = sdf.format(todayDate);
    	String nxtSeqNo = String.valueOf(getNextSequence(idType));
    	String id = strDate + nxtSeqNo;
    	String genId = StringFormatter.padLeft(id, 12, "0");   	
    	
    	return genId;
    }
    
    public int getNextSequence (String idType) {
    	int nextSeq = 0;
    	Timestamp timestamp;
    	Date todayDate = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
    	String strSystemDate = sdf.format(todayDate);
    	String tblDate = null;
    	
        String query = "SELECT next_seq_no, timestamp FROM sys_params WHERE id_type = '" + idType + "' ";
        try {
            statement = dbConnection.getConnection().createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
            	nextSeq = rs.getInt("next_seq_no");
            	timestamp = rs.getTimestamp("timestamp");
            	tblDate = sdf.format(timestamp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (strSystemDate.equals(tblDate))
        	return ++nextSeq;
        else
        	return 1;
    }
    
	public boolean updateNextSequence(String idType) {
		int flag = 0;
		boolean ret = false;
		String query = "UPDATE sys_params SET next_seq_no = ? WHERE id_type = '" + idType + "' ";
		int nextSeq = getNextSequence(idType);
		try {
			
			PreparedStatement stat = dbConnection.getConnection().prepareStatement(query);
			stat.setInt(1, nextSeq);

			flag = stat.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		if (flag == 1) {
			ret = true;
		} else {
			ret = false;
		}

		return ret;
	}

}
