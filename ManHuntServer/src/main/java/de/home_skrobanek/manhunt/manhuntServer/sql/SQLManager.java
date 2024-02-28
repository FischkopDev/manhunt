package de.home_skrobanek.manhunt.manhuntServer.sql;

import de.home_skrobanek.manhunt.manhuntServer.ManhuntServerApplication;
import de.home_skrobanek.manhunt.manhuntServer.dao.Player;
import de.home_skrobanek.manhunt.manhuntServer.dao.Position;
import de.home_skrobanek.manhunt.manhuntServer.encryption.SHAHashing;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLManager {


    private MySQL mysql;
    public SQLManager(){
        mysql = new MySQL();

        //Create table statements.
        mysql.executeSQLFile(new File("src/main/resources/sql/createStatements.sql"));

    }

    public boolean existsPlayerWithDeviceID(String id){
        try{
            String hashedID = SHAHashing.hashString(id );
            ResultSet rs = mysql.query("SELECT * FROM player WHERE device_id='"+hashedID+"';");

            rs.next();
            return rs.getString(1) != null;
        }catch (SQLException ex){
            return false;
        }
    }

    public void registerPlayer(Player player){
        try {
            String encryptedName = ManhuntServerApplication.serverRSA.encrypt(player.getUsername());
            String hashedID = SHAHashing.hashString(player.getDevice_id());

            mysql.update("INSERT INTO player (username, role, device_id, accepted) VALUES ('"+encryptedName+"','"+player.getRole()+"','"+hashedID+"','false');");
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Position> getAllPositions(String timestamp){
        ArrayList<Position> list = new ArrayList<>();

        try{
            //Return values for all players where their timestamp is greater than all others of their timestamps
            ResultSet rs = mysql.query("SELECT * FROM player_position WHERE time_stamp >= '"+timestamp+"';");

            while(rs.next()){
                String player_time_stamp = rs.getString("time_stamp");
                double lat = rs.getDouble("lat");
                double lon = rs.getDouble("lon");

                Position tmp = new Position(getPlayerInfo(rs.getInt("player_id")),lat, lon);
                list.add(tmp);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return list;
    }

    public void addPosition(Position pos){
        mysql.update("INSERT INTO player_position(lat, lon, player_id) VALUES ("+pos.getLatitude() +","+
                pos.getLongitude()+","+getPlayerID(pos.getPlayer()) + ");");
    }

    public Player getPlayerInfo(int id){
        try{
            ResultSet rs = mysql.query("SELECT username, role FROM player WHERE id="+id+";");
            rs.next();

            return new Player(rs.getString("username"), rs.getString("role"), null);
        }catch (SQLException ex){
            return null;
        }
    }

    public int getPlayerID(Player player){
        try{
            ResultSet rs = mysql.query("SELECT player_id FROM player WHERE device_id='"+SHAHashing.hashString(player.getDevice_id())+"';");
            rs.next();

            return rs.getInt("player_id");
        }catch (SQLException ex){
            return -1;
        }
    }

}
