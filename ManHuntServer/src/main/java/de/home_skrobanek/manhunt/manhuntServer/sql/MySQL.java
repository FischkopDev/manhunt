package de.home_skrobanek.manhunt.manhuntServer.sql;
/**
 * Project: MySQL library
 * Author: Timo Skrobanek
 * Last Changes:
 * 		date: 29.3.2020
 * 		version: 1.0
 *
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @MySQL
 *  Diese Klasse beinhaltet Methoden zur vereinfachten Benutzung einer Datenbank.
 *  Der Code bassiert auf die JDBC API.
 *  Damit der Code funktioniert wird die Library mysql-connector-java gebraucht,
 *  die über Gradlew hier im Projekt mit drin ist. Sollte die Klasse ohne Gradle
 *  verwendet werden muss die Jar extra hinzugefügt werden!
 */
public class MySQL {

    private String HOST = "";
    private String DATABASE = "";
    private String USER = "";
    private String PASSWORD = "";
    private int PORT = 3306;

    private boolean reconnect = true;
    private boolean mute = false;

    private Connection con;

    private String userFile = "database.txt";

    /**
     @param HOST
     Legt einen Server fest, zu den eine Verbindung hergestellt werden soll

     @param DATABASE
     Legt die Datenbank fest, die angesprochen werden soll.

     @param USER
     Gibt den Benutzer an die Datenbank weiter.

     @param PASSWORD
     Das Passwort soll die Datenbank schützen. Zur sicheren Übertragung muss eine SSL
     Verbindung bestehen oder Die Datenbank darf nach außen nicht erreichbar sein!
     */
    public MySQL(String HOST, String DATABASE, String USER, String PASSWORD) {
        this.HOST = HOST;
        this.DATABASE = DATABASE;
        this.USER = USER;
        this.PASSWORD = PASSWORD;

        if(!mute) {
            System.out.println("Connecting to Database");
        }

        readUserFile();
        connect();
    }

    @Deprecated
    public MySQL(String HOST, String DATABASE, String USER, String PASSWORD, boolean autoConnect, boolean thread) {
        this.HOST = HOST;
        this.DATABASE = DATABASE;
        this.USER = USER;
        this.PASSWORD = PASSWORD;

        if(!mute) {
            System.out.println("[Information]: Connecting to Database");
        }

        if(autoConnect && thread) {
            new Thread( new Runnable(){
                @Override
                public void run() {
                    readUserFile();
                    connect();

                }}).start();
        }
        else if(autoConnect && !thread) {
            readUserFile();
            connect();
        }
    }

    public MySQL(){
        if(readUserFile()) {
            connect();
        }
        else
            System.err.println("Couldn't access user data");
    }

    /**
     * Stellt eine Verbindung zur Datenbank her.
     */
    public boolean connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":"+PORT+"/" + DATABASE + "?tcpKeepAlive=true&characterEncoding=latin1&autoReconnect="+reconnect, USER, PASSWORD);

            if(!mute) {
                System.out.println("===========================");
                System.out.println("Connected to MySQL database");
                System.out.println("===========================");
            }

            return true;

        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    public boolean readUserFile(){
        try{
            FileReader fr = new FileReader(new File("src/main/resources/sql/"+userFile));
            BufferedReader br = new BufferedReader(fr);

            String lines = "";

            while((lines = br.readLine()) != null){
                String[] keyValue = lines.split("=");

                switch(keyValue[0]){
                    case "host":
                        HOST = keyValue[1];
                        break;
                    case "user":
                        USER = keyValue[1];
                        break;
                    case "password":
                        PASSWORD = keyValue[1];
                        break;
                    case "database":
                        DATABASE = keyValue[1];
                        break;
                }
            }

            br.close();
            return true;
        }catch(IOException ex){
            ex.printStackTrace();
            return false;
        }
    }


    /**
     * Beendet eine bestehende Verbindung
     */
    public boolean disconnect() {
        try {
            if(con != null) {
                con.close();
                return true;
            }
            else {
                if(!mute) {
                    System.out.println("[Information]: Can't disconnect if no connection available");
                }
                return false;
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param query
     *  Ermöglicht es Befehle an die Datenbank zu schicken. Hier können zbs
     *  Tabellen erstellt und Daten eingetragen werden. Im Parameter wird
     *  SQL-Code erwartet.
     */
    public void update(String query) {
        try {
            java.sql.Statement st = con.createStatement();
            st.execute(query);
            st.close();

        }catch(SQLException ex) {
            connect();
            ex.printStackTrace();
        }
    }

    public void executeSQLFile(File filePath) {
        try {

            StringBuilder sqlStatement = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                // Skip comments and empty lines
                if (line.trim().isEmpty() || line.trim().startsWith("--") || line.trim().startsWith("#")) {
                    continue;
                }

                sqlStatement.append(line);

                // Check if the line ends with a semicolon (indicating the end of an SQL statement)
                if (line.trim().endsWith(";")) {
                    update(sqlStatement.toString());

                    // Clear the StringBuilder for the next SQL statement
                    sqlStatement.setLength(0);
                }
            }

            reader.close();
        } catch ( IOException e) {
            // Handle or log any exceptions here
            e.printStackTrace();
        }
    }

    /**
     *
     * @param query
     *  Mit dieser Methode können Daten aus der Datenbank ausgelesen werden.
     *  Der Parameter nimmt SQL-Code entgegen.
     *
     * @return
     * Die Daten werden als @ResultSet zurückgegeben und müssen
     * dementsprechend noch in das richtige Format gecasted werden.
     */
    public ResultSet query(String query) {
        ResultSet rs = null;

        try {
            java.sql.Statement st = con.createStatement();
            rs = st.executeQuery(query);
        }catch(SQLException ex) {
            connect();
            ex.printStackTrace();
        }

        return rs;
    }

    public void setPort(int PORT){
        this.PORT = PORT;
    }

    public int getPort(){
        return PORT;
    }
}
