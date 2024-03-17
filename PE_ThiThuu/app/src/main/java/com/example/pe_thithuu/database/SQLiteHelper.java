package com.example.pe_thithuu.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.pe_thithuu.model.Booking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "Booking.db";

    private static final int DATABASE_VERSION= 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE Booking(" +
                "customreName TEXT PRIMARY KEY, " +
                "serviceName TEXT , quantity INTEGER , price REAL, value REAL,time DATETIME)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public List<Booking> listAllBooking(){
        List<Booking> list= new ArrayList<>();
        SQLiteDatabase st= getReadableDatabase();
        Cursor rs = st.query("Booking", // Tên bảng
                null,
                null,
                null,
                null,
                null,
                "time DESC");

        while (rs!= null && rs.moveToNext()){
            String customreName= rs.getString(0);
            String serviceName= rs.getString(1);
            int quantity= rs.getInt(2);
            double price= rs.getDouble(3);
            double value= rs.getDouble(4);
//            String timeString = rs.getString(5);
//            LocalDateTime time = LocalDateTime.parse(timeString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDateTime time = LocalDateTime.now();
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            String time = currentTime.format(formatter);
            list.add(new Booking(customreName, serviceName, quantity, price, value,time));
        }
        return list;
    }
    public long addBooking(Booking booking) {
        try {
            if (booking == null) {
                return -1;
            }
            ContentValues values = new ContentValues();
            values.put("customreName", booking.getCustomerName());
            values.put("serviceName", booking.getServiceName());
            values.put("quantity", booking.getQuantity());
            values.put("price", booking.getPrice());
            values.put("value", booking.getPrice());
            values.put("time", booking.getTime()+"");

            SQLiteDatabase db = getWritableDatabase();
            if (db == null) {
                // Xử lý khi không thể mở được cơ sở dữ liệu
                return -1;
            }

            long result = db.insert("Booking", null, values);
            db.close(); // Đóng cơ sở dữ liệu sau khi sử dụng
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
