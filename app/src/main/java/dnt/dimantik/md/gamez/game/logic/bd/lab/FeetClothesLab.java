package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.FeetClothesCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.FeetClothesDbSchema.FeetClothesTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.FeetClothesDbSchema.FeetClothesTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.FeetClothes;

/**
 * Created by dimantik on 3/5/18.
 */

public class FeetClothesLab {

    private static FeetClothesLab sFeetClothesLab;

    private SQLiteDatabase mFeetClothesDatabase;

    public static FeetClothesLab get(SQLiteDatabase database){
        if (sFeetClothesLab == null){
            sFeetClothesLab = new FeetClothesLab(database);
        }
        return sFeetClothesLab;
    }

    private FeetClothesLab(SQLiteDatabase database){
        mFeetClothesDatabase = database;
    }

    public void addFeetClothes(FeetClothes feetClothes){
        ContentValues contentValues = getContentValues(feetClothes);

        mFeetClothesDatabase.insert(FeetClothesTable.TABLE_NAME, null, contentValues);
    }

    public void updateFeetClothes(FeetClothes feetClothes){
        String uuidString = feetClothes.getId().toString();
        ContentValues contentValues = getContentValues(feetClothes);

        mFeetClothesDatabase.update(FeetClothesTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteFeetClothes(FeetClothes feetClothes){
        mFeetClothesDatabase.delete(FeetClothesTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{feetClothes.getId().toString()});
    }

    public void deleteAllFeetClothes(){
        mFeetClothesDatabase.delete(FeetClothesTable.TABLE_NAME, null, null);
    }

    public void addAllFeetClothes(List<FeetClothes> allFeetClothes){
        for (FeetClothes feetClothes : allFeetClothes){
            addFeetClothes(feetClothes);
        }
    }

    public Map<UUID, FeetClothes> getAllFeetClothes(){
        Map<UUID, FeetClothes> allClothes = new HashMap<>();

        FeetClothesCursorWrapper cursor = queryFeetClothes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                FeetClothes feetClothes = cursor.getFeetClothes();
                allClothes.put(feetClothes.getId(), feetClothes);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return allClothes;
    }

    public FeetClothes getFeetCLothes(UUID id){
        FeetClothesCursorWrapper cursor = queryFeetClothes(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getFeetClothes();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(FeetClothes feetClothes){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, feetClothes.getId().toString());
        contentValues.put(Cols.NAME, feetClothes.getName());
        contentValues.put(Cols.IMAGE_NAME, feetClothes.getAssertDrawable());
        contentValues.put(Cols.PROTECTION, feetClothes.getProtection());

        return contentValues;
    }

    private FeetClothesCursorWrapper queryFeetClothes(String whereClause, String[] whereArgs){
        Cursor cursor = mFeetClothesDatabase.query(
                FeetClothesTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new FeetClothesCursorWrapper(cursor);
    }
}
