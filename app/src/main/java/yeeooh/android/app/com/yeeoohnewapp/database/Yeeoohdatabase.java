package yeeooh.android.app.com.yeeoohnewapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by Narendra on 4/10/2017.
 */

public class Yeeoohdatabase extends SQLiteOpenHelper {

    static String DATABASE_NAME="yeeooh";

    static int DB_VERSION=1;

    String CREAT_ORDER_TABLE="create table order(id text,cin text,uid text,mid text,sTime text,eTime text,status text,price text)";

    String CREAT_USER_TABLE="create table user(id text,name text,email text,phone text,password text,dop text,gender text,image text,notification text,pincode text,address text,landmark text,city text,state text)";

    String TABLE_ORDER="order";

    SQLiteDatabase db;

    public Yeeoohdatabase(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       // db.execSQL(CREAT_ORDER_TABLE);
        db.execSQL(CREAT_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase()
    {
        db=getWritableDatabase();
    }
    public void closeDB()
    {
        db.close();
    }



    public void insertUser(String id,String name,String email,String number)
    {
        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("name",name);
        values.put("email",email);
        values.put("phone",number);

        db.insert("user",null,values);
    }
    public void insertUser1(String name,String pincode,String address,String landmark,String city,String state,String number)
    {
        ContentValues values=new ContentValues();;
        values.put("name",name);
        values.put("pincode",pincode);
        values.put("address",address);
        values.put("landmark",landmark);
        values.put("city",city);
        values.put("state",state);
        values.put("phone",number);

        db.insert("user",null,values);
    }

    public Cursor getUser()
    {
        return db.rawQuery("select * from user",null);
    }



    public void updateProfile(String id,String name,String email,String number,String password,String date,String gender,String image,String notification)
    {
        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("name",name);
        values.put("email",email);
        values.put("phone",number);
        values.put("password",password);
        values.put("dop",date);
        values.put("image",image);
        values.put("notification",notification);
        db.update("user",values,id,null);

    }
}
