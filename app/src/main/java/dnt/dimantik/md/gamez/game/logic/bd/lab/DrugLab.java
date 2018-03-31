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

import dnt.dimantik.md.gamez.game.logic.bd.cursor.DrugCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.DrugDbSchema.DrugTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.DrugDbSchema.DrugTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Drug;


/**
 * Created by dimantik on 3/5/18.
 */

public class DrugLab {

    private static DrugLab sDrugLab;

    private SQLiteDatabase mDrugDatabase;

    public static DrugLab get(SQLiteDatabase database){
        if (sDrugLab == null){
            sDrugLab = new DrugLab(database);
        }
        return sDrugLab;
    }

    private DrugLab(SQLiteDatabase database){
        mDrugDatabase = database;
    }

    public void addDrug(Drug drug){
        ContentValues contentValues = getContentValues(drug);

        mDrugDatabase.insert(DrugTable.TABLE_NAME, null, contentValues);
    }

    public void updateDrug(Drug drug){
        String uuidString = drug.getId().toString();
        ContentValues contentValues = getContentValues(drug);

        mDrugDatabase.update(DrugTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteDrug(Drug drug){
        mDrugDatabase.delete(DrugTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{drug.getId().toString()});
    }

    public void deleteAllDrugs(){
        mDrugDatabase.delete(DrugTable.TABLE_NAME, null, null);
    }

    public void addDrugs(List<Drug> drugs){
        for (Drug drug : drugs){
            addDrug(drug);
        }
    }

    public Map<UUID, Drug> getDrugs(){
        Map<UUID, Drug> drugs = new HashMap<>();

        DrugCursorWrapper cursor = queryDrugs(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Drug drug = cursor.getDrug();
                drugs.put(drug.getId(), drug);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return drugs;
    }

    public Drug getDrug(UUID id){
        DrugCursorWrapper cursor = queryDrugs(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getDrug();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Drug food){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, food.getId().toString());
        contentValues.put(Cols.NAME, food.getName());
        contentValues.put(Cols.IMAGE_NAME, food.getAssertDrawable());
        contentValues.put(Cols.QUANTITY, food.getQuantity());

        return contentValues;
    }

    private DrugCursorWrapper queryDrugs(String whereClause, String[] whereArgs){
        Cursor cursor = mDrugDatabase.query(
                DrugTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new DrugCursorWrapper(cursor);
    }
}
