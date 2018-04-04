package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.BodyClothesDbSchema.BodyClothesTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.BodyClothesDbSchema.BodyClothesTable.Cols;
import dnt.dimantik.md.gamez.game.logic.bd.cursor.BodyClothesCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.clases.resources.BodyClothes;

/**
 * Created by dimantik on 3/5/18.
 */

public class BodyClothesLab {

    private static BodyClothesLab sBodyClothesLab;

    private SQLiteDatabase mBodyClothesDatabase;

    public static BodyClothesLab get(SQLiteDatabase database){
        if (sBodyClothesLab == null){
            sBodyClothesLab = new BodyClothesLab(database);
        }
        return sBodyClothesLab;
    }

    private BodyClothesLab(SQLiteDatabase database){
        mBodyClothesDatabase = database;
    }

    public void addBodyClothes(BodyClothes bodyClothes){
        ContentValues contentValues = getContentValues(bodyClothes);

        mBodyClothesDatabase.insert(BodyClothesTable.TABLE_NAME, null, contentValues);
    }

    public void updateBodyClothes(BodyClothes bodyClothes){
        String uuidString = bodyClothes.getId().toString();
        ContentValues contentValues = getContentValues(bodyClothes);

        mBodyClothesDatabase.update(BodyClothesTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteBodyClothes(BodyClothes bodyClothes){
        mBodyClothesDatabase.delete(BodyClothesTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{bodyClothes.getId().toString()});
    }

    public void deleteAllBodyClothes(){
        mBodyClothesDatabase.delete(BodyClothesTable.TABLE_NAME, null, null);
    }

    public void addAllBodyClothes(List<BodyClothes> allBodyClothes){
        for (BodyClothes bodyClothes : allBodyClothes){
            addBodyClothes(bodyClothes);
        }
    }

    public Map<UUID, BodyClothes> getAllBodyClothes(){
        Map<UUID, BodyClothes> allClothes = new HashMap<>();

        BodyClothesCursorWrapper cursor = queryBodyClothes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                BodyClothes bodyClothes = cursor.getBodyClothes();
                allClothes.put(bodyClothes.getId(), bodyClothes);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return allClothes;
    }

    public BodyClothes getBodyClothes(UUID id){
        BodyClothesCursorWrapper cursor = queryBodyClothes(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getBodyClothes();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(BodyClothes bodyClothes){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, bodyClothes.getId().toString());
        contentValues.put(Cols.NAME, bodyClothes.getName());
        contentValues.put(Cols.IMAGE_NAME, bodyClothes.getAssertDrawable());
        contentValues.put(Cols.PROTECTION, bodyClothes.getProtection());

        return contentValues;
    }

    private BodyClothesCursorWrapper queryBodyClothes(String whereClause, String[] whereArgs){
        Cursor cursor = mBodyClothesDatabase.query(
                BodyClothesTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new BodyClothesCursorWrapper(cursor);
    }
}
