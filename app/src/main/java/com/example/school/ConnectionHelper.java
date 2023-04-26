package com.example.school;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String uName,pass,ip,port,database;

    public Connection ConnectionClass() {
        ip = "192.168.29.106";
        database = "school";
        uName = "sa";
        port = "1433";
        pass = "vartul02";
        String url = "jdbc:jtds:sqlserver://SOMYA;instance=SQLEXPRESS;DatabaseName=school";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionUrl = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionUrl= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+ ";" +
                    "databasename="+ database+";user="+uName+";password="+pass+";";
            connection = DriverManager.getConnection(connectionUrl);

        }
        catch (Exception ex ) {
            Log.e("error",ex.getMessage());
        }
        return connection;
    }
}
