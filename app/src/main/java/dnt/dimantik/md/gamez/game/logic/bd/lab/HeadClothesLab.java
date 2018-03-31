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

import dnt.dimantik.md.gamez.game.logic.bd.cursor.HeadClothesCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.HeadClothesDbSchema.HeadClothesTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.HeadClothesDbSchema.HeadClothesTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resource.HeadClothes;

/**
 * Created by dimantik on 3/5/18.
 */

public class HeadClothesLab {

    private static HeadClothesLab sHeadClothesLab;

    private SQLiteDatabase mHeadClothesDatabase;

    public static HeadClothesLab get(SQLiteDatabase database){
        if (sHeadClothesLab == null){
            sHeadClothesLab = new HeadClothesLab(database);
        }
        return sHeadClothesLab;
    }

    private HeadClothesLab(SQLiteDatabase database){
        mHeadClothesDatabase = database;
    }

    public void addHeadClothes(HeadClothes headClothes){
        ContentValues contentValues = getContentValues(headClothes);

        mHeadClothesDatabase.insert(HeadClothesTable.TABLE_NAME, null, contentValues);
    }

    public void updateHeadClothes(HeadClothes headClothes){
        String uuidString = headClothes.getId().toString();
        ContentValues contentValues = getContentValues(headClothes);

        mHeadClothesDatabase.update(HeadClothesTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteHeadClothes(HeadClothes headClothes){
        mHeadClothesDatabase.delete(HeadClothesTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{headClothes.getId().toString()});
    }

    public void deleteAllHeadClothes(){
        mHeadClothesDatabase.delete(HeadClothesTable.TABLE_NAME, null, null);
    }

    public void addAllHeadClothes(List<HeadClothes> allHeadClothes){
        for (HeadClothes headClothes : allHeadClothes){
            addHeadClothes(headClothes);
        }
    }

    public Map<UUID, HeadClothes> getAllHeadClothes(){
        Map<UUID, HeadClothes> allClothes = new HashMap<>();

        HeadClothesCursorWrapper cursor = queryHeadClothes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                HeadClothes headClothes = cursor.getHeadClothes();
                allClothes.put(headClothes.getId(), headClothes);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return allClothes;
    }

    public HeadClothes getHeadClothes(UUID id){
        HeadClothesCursorWrapper cursor = queryHeadClothes(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getHeadClothes();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(HeadClothes headClothes){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, headClothes.getId().toString());
        contentValues.put(Cols.NAME, headClothes.getName());
        contentValues.put(Cols.IMAGE_NAME, headClothes.getAssertDrawable());
        contentValues.put(Cols.PROTECTION, headClothes.getProtection());

        return contentValues;
    }

    private HeadClothesCursorWrapper queryHeadClothes(String whereClause, String[] whereArgs){
        Cursor cursor = mHeadClothesDatabase.query(
                HeadClothesTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new HeadClothesCursorWrapper(cursor);
    }
}
