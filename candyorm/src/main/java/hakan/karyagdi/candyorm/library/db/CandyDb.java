package hakan.karyagdi.candyorm.library.db;

/**
 * Created by hakan on 11/26/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import hakan.karyagdi.candyorm.library.util.QueryBuilder;
import static hakan.karyagdi.candyorm.library.CandyContext.getContext;


public class CandyDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "traitsDB";
    private static final int DATABASE_VERSION = 1;



    private static CandyDb sInstance;


    private CandyDb() {
        super(getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized CandyDb getInstance() {

        if (sInstance == null) {
            sInstance = new CandyDb();
        }
        return sInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QueryBuilder.createTableScript(Opinion.class));
        Log.v("TABLO OLUSTU","OPINION");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Opinion.class.getSimpleName());

        onCreate(sqLiteDatabase);
    }


}
