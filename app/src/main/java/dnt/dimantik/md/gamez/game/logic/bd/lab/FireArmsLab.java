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

import dnt.dimantik.md.gamez.game.logic.bd.cursor.FireArmsCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Cartridges;
import dnt.dimantik.md.gamez.helper.classes.Assistant;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.FireArmsDbSchema.FireArmsTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.FireArmsDbSchema.FireArmsTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FireArms;

/**
 * Created by dimantik on 3/5/18.
 */

public class FireArmsLab {

    private static FireArmsLab sFireArmsLab;

    private SQLiteDatabase mFireArmsDatabase;

    public static FireArmsLab get(SQLiteDatabase database){
        if (sFireArmsLab == null){
            sFireArmsLab = new FireArmsLab(database);
        }
        return sFireArmsLab;
    }

    private FireArmsLab(SQLiteDatabase database){
        mFireArmsDatabase = database;
    }

    public void addFireArms(FireArms fireArms){
        ContentValues contentValues = getContentValues(fireArms);

        mFireArmsDatabase.insert(FireArmsTable.TABLE_NAME, null, contentValues);
    }

    public void updateFireArms(FireArms fireArms){
        String uuidString = fireArms.getId().toString();
        ContentValues contentValues = getContentValues(fireArms);

        mFireArmsDatabase.update(FireArmsTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteFireArms(FireArms fireArms){
        mFireArmsDatabase.delete(FireArmsTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{fireArms.getId().toString()});
    }

    public void deleteAllFireArms(){
        mFireArmsDatabase.delete(FireArmsTable.TABLE_NAME, null, null);
    }

    public void addAllFireArms(List<FireArms> allFireArms){
        for (FireArms fireArms : allFireArms){
            addFireArms(fireArms);
        }
    }

    public Map<UUID, FireArms> getAllFireArms(){
        Map<UUID, FireArms> allArms = new HashMap<>();

        FireArmsCursorWrapper cursor = queryFireArms(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                FireArms fireArms = cursor.getFireArms();
                allArms.put(fireArms.getId(), fireArms);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return allArms;
    }

    public FireArms getFireArms(UUID id){
        FireArmsCursorWrapper cursor = queryFireArms(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getFireArms();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(FireArms fireArms){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, fireArms.getId().toString());
        contentValues.put(Cols.NAME, fireArms.getName());
        contentValues.put(Cols.IMAGE_NAME, fireArms.getAssertDrawable());
        contentValues.put(Cols.POWER, fireArms.getPower());
        contentValues.put(Cols.TYPE, fireArms.getType().toString());
        contentValues.put(Cols.CARTRIDGES, Assistant.wrapUUID(fireArms.getCartridgesUUID()));

        return contentValues;
    }

    private FireArmsCursorWrapper queryFireArms(String whereClause, String[] whereArgs){
        Cursor cursor = mFireArmsDatabase.query(
                FireArmsTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new FireArmsCursorWrapper(cursor);
    }
}
