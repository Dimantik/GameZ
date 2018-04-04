package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.LegsClothesCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.LegsClothesDbSchema.LegsClothesTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.LegsClothesDbSchema.LegsClothesTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.LegsClothes;

/**
 * Created by dimantik on 3/5/18.
 */

public class LegsClothesLab {

    private static LegsClothesLab sLegsClothesLab;

    private SQLiteDatabase mLegsClothesDatabase;

    public static LegsClothesLab get(SQLiteDatabase database){
        if (sLegsClothesLab == null){
            sLegsClothesLab = new LegsClothesLab(database);
        }
        return sLegsClothesLab;
    }

    private LegsClothesLab(SQLiteDatabase database){
        mLegsClothesDatabase = database;
    }

    public void addLegsClothes(LegsClothes legsClothes){
        ContentValues contentValues = getContentValues(legsClothes);

        mLegsClothesDatabase.insert(LegsClothesTable.TABLE_NAME, null, contentValues);
    }

    public void updateLegsClothes(LegsClothes legsClothes){
        String uuidString = legsClothes.getId().toString();
        ContentValues contentValues = getContentValues(legsClothes);

        mLegsClothesDatabase.update(LegsClothesTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteLegsClothes(LegsClothes legsClothes){
        mLegsClothesDatabase.delete(LegsClothesTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{legsClothes.getId().toString()});
    }

    public void deleteAllLegsClothes(){
        mLegsClothesDatabase.delete(LegsClothesTable.TABLE_NAME, null, null);
    }

    public void addAllLegsClothes(List<LegsClothes> allLegsClothes){
        for (LegsClothes legsClothes : allLegsClothes){
            addLegsClothes(legsClothes);
        }
    }

    public Map<UUID, LegsClothes> getAllLegsClothes(){
        Map<UUID, LegsClothes> allClothes = new HashMap<>();

        LegsClothesCursorWrapper cursor = queryLegsClothes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                LegsClothes legsClothes = cursor.getLegsClothes();
                allClothes.put(legsClothes.getId(), legsClothes);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return allClothes;
    }

    public LegsClothes getLegsCLothes(UUID id){
        LegsClothesCursorWrapper cursor = queryLegsClothes(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getLegsClothes();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(LegsClothes legsClothes){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, legsClothes.getId().toString());
        contentValues.put(Cols.NAME, legsClothes.getName());
        contentValues.put(Cols.IMAGE_NAME, legsClothes.getAssertDrawable());
        contentValues.put(Cols.PROTECTION, legsClothes.getProtection());

        return contentValues;
    }

    private LegsClothesCursorWrapper queryLegsClothes(String whereClause, String[] whereArgs){
        Cursor cursor = mLegsClothesDatabase.query(
                LegsClothesTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new LegsClothesCursorWrapper(cursor);
    }
}
