package yeeooh.android.app.com.yeeoohnewapp.database;

/**
 * Created by Narendra
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "YEOOHADDRESS";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String PINCODE = "pincode";
    public static final String CITY = "city";
    public static final String Location = "location";
    public static final String Number = "number";
    public static final String State = "state";

    // Database Information
    static final String DB_NAME = "YEEOOH LABS.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + EMAIL + " TEXT NOT NULL, " + ADDRESS + " TEXT NOT NULL, " + PINCODE + " TEXT NOT NULL, " + CITY + " TEXT NOT NULL, " + Location + " TEXT NOT NULL, "  + Number + " TEXT NOT NULL, "  + State + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
