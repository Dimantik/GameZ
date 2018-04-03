package dnt.dimantik.md.gamez.game.logic.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static dnt.dimantik.md.gamez.game.logic.bd.CreateTablesCommands.*;

/**
 * Created by dimantik on 3/5/18.
 */

public class AppBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "apSd1p.db";

    public AppBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_GAME_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_HEAD_CLOTHES_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_BODY_CLOTHES_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_LEGS_CLOTHES_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_FEET_CLOTHES_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_FOOD_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_LIQUID_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_DRUG_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_FIRE_ARMS_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_CARTRIDGES_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_STEEL_ARMS_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_PLACE_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_LOCATION_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TRANSPORT_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_BAG_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_PLAYER_COMMAND);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TEST);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_WAY_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
