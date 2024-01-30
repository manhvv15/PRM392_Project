package com.example.prm392_project.dal;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static Connection connection = null;

    public static Connection createConnection() {
        String url = null;
        String ip = "10.0.2.2";
        String port = "1433";
        String db = "sellphonecard";
        String un = "sa";
        String pass = "123";
        //lay tat ca cac quyen
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //thiet lap chinh sach ket noi bao gom tat ca cac quyen
        StrictMode.setThreadPolicy(policy);
        // StrictMode.ThreadPolicy(po)
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db + ";user=" + un + ";password=" + pass + ";";
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
        return connection;
    }
}
