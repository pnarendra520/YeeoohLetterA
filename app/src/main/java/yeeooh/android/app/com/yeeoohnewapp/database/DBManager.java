package yeeooh.android.app.com.yeeoohnewapp.database;

/**
 * Created by Narendra
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name ,String email, String address,String pincode, String city,String location, String number,String state) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.EMAIL, email);
        contentValue.put(DatabaseHelper.ADDRESS, address);
        contentValue.put(DatabaseHelper.PINCODE, pincode);
        contentValue.put(DatabaseHelper.CITY, city);
        contentValue.put(DatabaseHelper.Location, location);
        contentValue.put(DatabaseHelper.Number, number);
        contentValue.put(DatabaseHelper.State, state);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.EMAIL,DatabaseHelper.ADDRESS, DatabaseHelper.PINCODE, DatabaseHelper.CITY,DatabaseHelper.Location, DatabaseHelper.Number,DatabaseHelper.State};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String email, String address,String pincode, String city,String location, String number,String state) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.EMAIL, email);
        contentValues.put(DatabaseHelper.ADDRESS, address);
        contentValues.put(DatabaseHelper.PINCODE, pincode);
        contentValues.put(DatabaseHelper.CITY, city);
        contentValues.put(DatabaseHelper.Location, location);
        contentValues.put(DatabaseHelper.Number, number);
        contentValues.put(DatabaseHelper.State, state);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
