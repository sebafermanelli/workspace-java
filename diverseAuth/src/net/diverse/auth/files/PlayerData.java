package net.diverse.auth.files;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.diverse.auth.Core;

public class PlayerData {
	
	public static File filePlayers = new File("plugins/" + Core.getCore().getDescription().getName() + "/accounts.yml");
	public static FileConfiguration getDataPlayers = YamlConfiguration.loadConfiguration(filePlayers);
	
	public static void createTable() {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				Statement statement = Core.connection.createStatement();
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `ACCOUNTS` (`UUID` text NOT NULL,`NAME` text NOT NULL,`PASSWORD` text NOT NULL)");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean playerExists(UUID uuid) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM ACCOUNTS WHERE UUID=?");
				statement.setString(1, uuid.toString());
				
				ResultSet results = statement.executeQuery();
				if(results.next()) {
					return true;
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			return getDataPlayers.contains(uuid.toString());
		}
		return false;
	}
	
	public static void registerPlayer(UUID uuid, Player p, String password) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				if(!playerExists(uuid)) {
					PreparedStatement insert = Core.getCore().getConnection().prepareStatement("INSERT INTO ACCOUNTS (UUID,NAME,PASSWORD) VALUE (?,?,?)");
					insert.setString(1, uuid.toString());
					insert.setString(2, p.getName());
					insert.setString(3, password);
					insert.executeUpdate();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			getDataPlayers.set(uuid.toString() + ".NAME", p.getName());
			getDataPlayers.set(uuid.toString() + ".PASSWORD", password);
		    
		    try {
		    	getDataPlayers.save(filePlayers);
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
	}
	
	public static void deletePlayer(UUID uuid, Player p) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("DELETE FROM ACCOUNTS WHERE UUID=?");
				statement.setString(1, uuid.toString());
				statement.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			getDataPlayers.set(uuid.toString(), null);
			getDataPlayers.set(uuid.toString() + ".NAME", null);
			getDataPlayers.set(uuid.toString() + ".PASSWORD", null);
		    
		    try {
		    	getDataPlayers.save(filePlayers);
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
	}
	
	public static void updatePassword(UUID uuid, String password) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("UPDATE ACCOUNTS SET PASSWORD=? WHERE UUID=?");
				statement.setString(1, password);
				statement.setString(2, uuid.toString());
				statement.executeUpdate();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			getDataPlayers.set(uuid.toString() + ".PASSWORD", null);
			getDataPlayers.set(uuid.toString() + ".PASSWORD", password);
		    
		    try {
		    	getDataPlayers.save(filePlayers);
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
	}
	
	public static boolean correctPassword(UUID uuid, String trypassword) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM ACCOUNTS WHERE UUID=?");
				statement.setString(1, uuid.toString());
				
				ResultSet results = statement.executeQuery();
				results.next();
				
				if(results.getString("PASSWORD").equals(trypassword)) {
					return true;
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			String correctpass = getDataPlayers.getString(uuid.toString() + ".PASSWORD");
			if(correctpass.equals(trypassword)) {
				return true;
			}
		}
		return false;
	}
}
