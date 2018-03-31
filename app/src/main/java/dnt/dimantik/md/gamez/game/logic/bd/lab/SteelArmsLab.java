package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.SteelArmsCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.SteelArmsDbSchema.SteelArmsTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.SteelArmsDbSchema.SteelArmsTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resource.SteelArms;

/**
 * Created by dimantik on 3/5/18.
 */

public class SteelArmsLab {

    private static SteelArmsLab sSteelArmsLab;

    private SQLiteDatabase mSteelArmsDatabase;

    public static SteelArmsLab get(SQLiteDatabase database){
        if (sSteelArmsLab == null){
            sSteelArmsLab = new SteelArmsLab(database);
        }
        return sSteelArmsLab;
    }

    private SteelArmsLab(SQLiteDatabase database){
        mSteelArmsDatabase = database;
    }

    public void addSteelArms(SteelArms steelArms){
        ContentValues contentValues = getContentValues(steelArms);

        mSteelArmsDatabase.insert(SteelArmsTable.TABLE_NAME, null, contentValues);
    }

    public void updateSteelArms(SteelArms steelArms){
        String uuidString = steelArms.getId().toString();
        ContentValues contentValues = getContentValues(steelArms);

        mSteelArmsDatabase.update(SteelArmsTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteSteelArms(SteelArms steelArms){
        mSteelArmsDatabase.delete(SteelArmsTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{steelArms.getId().toString()});
    }

    public void deleteAllSteelArms(){
        mSteelArmsDatabase.delete(SteelArmsTable.TABLE_NAME, null, null);
    }

    public void addAllLegsClothes(List<SteelArms> allSteelArms){
        for (SteelArms steelArms : allSteelArms){
            addSteelArms(steelArms);
        }
    }

    public Map<UUID, SteelArms> getAllSteelArms(){
        Map<UUID, SteelArms> allArms = new HashMap<>();

        SteelArmsCursorWrapper cursor = querySteelArms(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                SteelArms steelArms = cursor.getSteelArms();
                allArms.put(steelArms.getId(), steelArms);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return allArms;
    }

    public SteelArms getSteelArms(UUID id){
        SteelArmsCursorWrapper cursor = querySteelArms(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getSteelArms();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(SteelArms steelArms){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, steelArms.getId().toString());
        contentValues.put(Cols.NAME, steelArms.getName());
        contentValues.put(Cols.IMAGE_NAME, steelArms.getAssertDrawable());
        contentValues.put(Cols.POWER, steelArms.getPower());

        return contentValues;
    }

    private SteelArmsCursorWrapper querySteelArms(String whereClause, String[] whereArgs){
        Cursor cursor = mSteelArmsDatabase.query(
                SteelArmsTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new SteelArmsCursorWrapper(cursor);
    }
}
