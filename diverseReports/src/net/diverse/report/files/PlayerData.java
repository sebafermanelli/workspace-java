package net.diverse.report.files;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.diverse.report.Core;

public class PlayerData {
	
	public static File fileReports = new File("plugins/" + Core.getCore().getDescription().getName() + "/reports_list.yml");
	public static FileConfiguration getDataReports = YamlConfiguration.loadConfiguration(fileReports);
	
	public static void createTable() {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				Statement statement = Core.connection.createStatement();
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `REPORTS_LIST` (`ID` INTEGER AUTO_INCREMENT PRIMARY KEY,`REPORTER` text NOT NULL,`REPORTED` text NOT NULL,`TYPE` text NOT NULL)");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void createReport(Player p, Player r, String type) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement insert = Core.getCore().getConnection().prepareStatement("INSERT INTO REPORTS_LIST (REPORTER,REPORTED,TYPE) VALUE (?,?,?)");
				insert.setString(1, p.getName());
				insert.setString(2, r.getName());
				insert.setString(3, type);
				insert.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			getDataReports.set("id" + ".REPORTER", p.getName());
			getDataReports.set("id" + ".REPORTED", r.getName());
			getDataReports.set("id" + ".TYPE", type);
			
		    try {
		    	getDataReports.save(fileReports);
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
	}
	
	public static void deleteReport(int id) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("DELETE FROM REPORTS_LIST WHERE ID=?");
				statement.setInt(1, id);
				statement.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			getDataReports.set("" + id, null);
			getDataReports.set(id + ".REPORTER", null);
			getDataReports.set(id + ".REPORTED", null);
			getDataReports.set(id + ".TYPE", null);
			
		    try {
		    	getDataReports.save(fileReports);
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
	}
	
	public static String getReporter(int id) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM REPORTS_LIST WHERE ID=?");
				statement.setInt(1, id);
				
				ResultSet results = statement.executeQuery();
				results.next();
				
				return results.getString("REPORTER");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			if(fileReports.exists()) {
				return getDataReports.getString(id + ".REPORTER");
			}
		}
		return null;
	}
	
	public static String getReported(int id) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM REPORTS_LIST WHERE ID=?");
				statement.setInt(1, id);
				
				ResultSet results = statement.executeQuery();
				results.next();
				
				return results.getString("REPORTED");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			if(fileReports.exists()) {
				return getDataReports.getString(id + ".REPORTED");
			}
		}
		return null;
	}
	
	public static String getType(int id) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM REPORTS_LIST WHERE ID=?");
				statement.setInt(1, id);
				
				ResultSet results = statement.executeQuery();
				results.next();
				
				return results.getString("TYPE");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			if(fileReports.exists()) {
				return getDataReports.getString(id + ".TYPE");
			}
		}
		return null;
	}
	
	public static List<Integer> getIDs() {
		List<Integer> ids = new ArrayList<>();
		
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM REPORTS_LIST");
				ResultSet results = statement.executeQuery();
				
				while(results.next()) {
					ids.add(results.getInt("ID"));
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			if(fileReports.exists()) {
			}
		}
		return ids;
	}
	
	private static Map<Integer, String> playerMapID() {
        try {
            Map<Integer, String> map = new TreeMap<>(Collections.reverseOrder());
            PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM REPORTS_LIST");
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                String type = resultSet.getString("TYPE");
                int id = resultSet.getInt("ID");
                
                map.put(id, type);
            }
            return map;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<Integer> playerID() {
        List<Integer> list = new ArrayList<>();
        playerMapID().forEach((integer, string) -> list.add(integer));
        
        return list;
    }
}
