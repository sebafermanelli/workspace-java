package net.diverse.ffa.files;

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
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.diverse.ffa.Core;

public class PlayerData {
	
	public static File filePlayers = new File("plugins/" + Core.getCore().getDescription().getName() + "/ffa_stats.yml");
	public static FileConfiguration getDataPlayers = YamlConfiguration.loadConfiguration(filePlayers);
	
	public static void createTable() {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				Statement statement = Core.connection.createStatement();
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS `FFA_STATS` (`UUID` text NOT NULL,`NAME` text NOT NULL,`COINS` int,`KILLS` int,`DEATHS` int,`STREAK` int,`BEST_STREAK` int)");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean playerExists(UUID uuid) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM FFA_STATS WHERE UUID=?");
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
	
	public static void createPlayer(UUID uuid, Player p) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				if(!playerExists(uuid)) {
					PreparedStatement insert = Core.getCore().getConnection().prepareStatement("INSERT INTO FFA_STATS (UUID,NAME,COINS,KILLS,DEATHS,STREAK,BEST_STREAK) VALUE (?,?,?,?,?,?,?)");
					insert.setString(1, uuid.toString());
					insert.setString(2, p.getName());
					insert.setInt(3, 0);
					insert.setInt(4, 0);
					insert.setInt(5, 0);
					insert.setInt(6, 0);
					insert.setInt(7, 0);
					insert.executeUpdate();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			getDataPlayers.set(uuid.toString() + ".NAME", p.getName());
			getDataPlayers.set(uuid.toString() + ".COINS", 0);
			getDataPlayers.set(uuid.toString() + ".KILLS", 0);
			getDataPlayers.set(uuid.toString() + ".DEATHS", 0);
			getDataPlayers.set(uuid.toString() + ".STREAK", 0);
			getDataPlayers.set(uuid.toString() + ".BEST_STREAK", 0);
		    
		    try {
		    	getDataPlayers.save(filePlayers);
		    }
		    catch (Exception e) {
		    	e.printStackTrace();
		    }
		}
	}
	
	public static void updateCoins(UUID uuid, int coins) {
		if(playerExists(uuid)) {
			if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
				try {
					PreparedStatement statement = Core.getCore().getConnection().prepareStatement("UPDATE FFA_STATS SET COINS=? WHERE UUID=?");
					statement.setInt(1, coins);
					statement.setString(2, uuid.toString());
					statement.executeUpdate();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				getDataPlayers.set(uuid.toString() + ".COINS", coins);
			    
			    try {
			    	getDataPlayers.save(filePlayers);
			    }
			    catch (Exception e) {
			    	e.printStackTrace();
			    }
			}
		}
	}
	
	public static int getCoins(UUID uuid) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM FFA_STATS WHERE UUID=?");
				statement.setString(1, uuid.toString());
				
				ResultSet results = statement.executeQuery();
				results.next();
				
				return results.getInt("COINS");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			if(filePlayers.exists()) {
				return getDataPlayers.getInt(uuid.toString() + ".COINS");
			}
		}
		return 0;
	}
	
	public static void updateKills(UUID uuid, int kills) {
		if(playerExists(uuid)) {
			if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
				try {
					PreparedStatement statement = Core.getCore().getConnection().prepareStatement("UPDATE FFA_STATS SET KILLS=? WHERE UUID=?");
					statement.setInt(1, kills);
					statement.setString(2, uuid.toString());
					statement.executeUpdate();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				getDataPlayers.set(uuid.toString() + ".KILLS", kills);
			    
			    try {
			    	getDataPlayers.save(filePlayers);
			    }
			    catch (Exception e) {
			    	e.printStackTrace();
			    }
			}
		}
	}
	
	public static int getKills(UUID uuid) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM FFA_STATS WHERE UUID=?");
				statement.setString(1, uuid.toString());
				
				ResultSet results = statement.executeQuery();
				results.next();
				
				return results.getInt("KILLS");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			if(filePlayers.exists()) {
				return getDataPlayers.getInt(uuid.toString() + ".KILLS");
			}
		}
		return 0;
	}
	
	public static void updateDeaths(UUID uuid, int deaths) {
		if(playerExists(uuid)) {
			if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
				try {
					PreparedStatement statement = Core.getCore().getConnection().prepareStatement("UPDATE FFA_STATS SET DEATHS=? WHERE UUID=?");
					statement.setInt(1, deaths);
					statement.setString(2, uuid.toString());
					statement.executeUpdate();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				getDataPlayers.set(uuid.toString() + ".DEATHS", deaths);
			    
			    try {
			    	getDataPlayers.save(filePlayers);
			    }
			    catch (Exception e) {
			    	e.printStackTrace();
			    }
			}
		}
	}
	
	public static int getDeaths(UUID uuid) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM FFA_STATS WHERE UUID=?");
				statement.setString(1, uuid.toString());
				
				ResultSet results = statement.executeQuery();
				results.next();
				
				return results.getInt("DEATHS");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			if(filePlayers.exists()) {
				return getDataPlayers.getInt(uuid.toString() + ".DEATHS");
			}
		}
		return 0;
	}
	
	public static void updateStreak(UUID uuid, int streak) {
		if(playerExists(uuid)) {
			if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
				try {
					PreparedStatement statement = Core.getCore().getConnection().prepareStatement("UPDATE FFA_STATS SET STREAK=? WHERE UUID=?");
					statement.setInt(1, streak);
					statement.setString(2, uuid.toString());
					statement.executeUpdate();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				getDataPlayers.set(uuid.toString() + ".STREAK", streak);
			    
			    try {
			    	getDataPlayers.save(filePlayers);
			    }
			    catch (Exception e) {
			    	e.printStackTrace();
			    }
			}
		}
	}
	
	public static int getStreak(UUID uuid) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM FFA_STATS WHERE UUID=?");
				statement.setString(1, uuid.toString());
				
				ResultSet results = statement.executeQuery();
				results.next();
				
				return results.getInt("STREAK");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			if(filePlayers.exists()) {
				return getDataPlayers.getInt(uuid.toString() + ".STREAK");
			}
		}
		return 0;
	}
	
	public static void updateBestStreak(UUID uuid) {
		if(playerExists(uuid)) {
			if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
				try {
					PreparedStatement statement = Core.getCore().getConnection().prepareStatement("UPDATE FFA_STATS SET BEST_STREAK=? WHERE UUID=?");
					statement.setInt(1, getStreak(uuid));
					statement.setString(2, uuid.toString());
					statement.executeUpdate();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				getDataPlayers.set(uuid.toString() + ".BEST_STREAK", getStreak(uuid));
			    
			    try {
			    	getDataPlayers.save(filePlayers);
			    }
			    catch (Exception e) {
			    	e.printStackTrace();
			    }
			}
		}
	}
	
	public static int getBestStreak(UUID uuid) {
		if(Core.getCore().getConfig().getBoolean("MySQL.Enable", true)) {
			try {
				PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM FFA_STATS WHERE UUID=?");
				statement.setString(1, uuid.toString());
				
				ResultSet results = statement.executeQuery();
				results.next();
				
				return results.getInt("BEST_STREAK");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			if(filePlayers.exists()) {
				return getDataPlayers.getInt(uuid.toString() + ".BEST_STREAK");
			}
		}
		return 0;
	}
	
	public static Double getKDR(UUID uuid) {
		int kills = getKills(uuid);
		int deaths = getDeaths(uuid);
		
		if(deaths == 0) {
			deaths = 1;
		}
		if(kills == 0) {
			kills = 1;
		}
		
		Double kdr = ((double)kills)/((double)deaths);
		String kdrStr = String.valueOf(kdr);
		String finalKdr = kdrStr.substring(0, 3);
		Double finalKdrDouble = Double.valueOf(finalKdr);
		
		return finalKdrDouble;
	}
	
	private static Map<Integer, String> playerStatsMapKills() {
        try {
            Map<Integer, String> map = new TreeMap<>(Collections.reverseOrder());
            PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM FFA_STATS");
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                String name = resultSet.getString("NAME");
                int kills = resultSet.getInt("KILLS");
                
                map.put(kills, name);
            }
            return map;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<String> playerTopKills() {
        List<String> list = new ArrayList<>();
        playerStatsMapKills().forEach((integer, string) -> list.add(string));
        
        return list;
    }
    
    public static List<Integer> killsTopKills() {
        List<Integer> list = new ArrayList<>();
        playerStatsMapKills().forEach((integer, string) -> list.add(integer));
        
        return list;
    }
    
	private static Map<Integer, String> playerStatsMapStreaks() {
        try {
            Map<Integer, String> map = new TreeMap<>(Collections.reverseOrder());
            PreparedStatement statement = Core.getCore().getConnection().prepareStatement("SELECT * FROM FFA_STATS");
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                String name = resultSet.getString("NAME");
                int kills = resultSet.getInt("BEST_STREAK");
                
                map.put(kills, name);
            }
            return map;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static List<String> playerTopStreaks() {
        List<String> list = new ArrayList<>();
        playerStatsMapStreaks().forEach((integer, string) -> list.add(string));
        
        return list;
    }
    
    public static List<Integer> streakTopStreaks() {
        List<Integer> list = new ArrayList<>();
        playerStatsMapStreaks().forEach((integer, string) -> list.add(integer));
        
        return list;
    }
}
