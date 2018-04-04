package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.LiquidCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.LiquidDbSchema.LiquidTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.LiquidDbSchema.LiquidTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Liquid;

/**
 * Created by dimantik on 3/5/18.
 */

public class LiquidLab {

    private static LiquidLab sLiquidLab;

    private SQLiteDatabase mLiquidDatabase;

    public static LiquidLab get(SQLiteDatabase database){
        if (sLiquidLab == null){
            sLiquidLab = new LiquidLab(database);
        }
        return sLiquidLab;
    }

    private LiquidLab(SQLiteDatabase database){
        mLiquidDatabase = database;
    }

    public void addLiquid(Liquid liquid){
        ContentValues contentValues = getContentValues(liquid);

        mLiquidDatabase.insert(LiquidTable.TABLE_NAME, null, contentValues);
    }

    public void updateLiquid(Liquid liquid){
        String uuidString = liquid.getId().toString();
        ContentValues contentValues = getContentValues(liquid);

        mLiquidDatabase.update(LiquidTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteLiquid(Liquid liquid){
        mLiquidDatabase.delete(LiquidTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{liquid.getId().toString()});
    }

    public void deleteAllLiquids(){
        mLiquidDatabase.delete(LiquidTable.TABLE_NAME, null, null);
    }

    public void addLiquids(List<Liquid> liquids){
        for (Liquid liquid : liquids){
            addLiquid(liquid);
        }
    }

    public Map<UUID, Liquid> getLiquids(){
        Map<UUID, Liquid> foods = new HashMap<>();

        LiquidCursorWrapper cursor = queryLiquids(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Liquid liquid = cursor.getLiquid();
                foods.put(liquid.getId(), liquid);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return foods;
    }

    public Liquid getLiquid(UUID id){
        LiquidCursorWrapper cursor = queryLiquids(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getLiquid();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Liquid liquid){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, liquid.getId().toString());
        contentValues.put(Cols.NAME, liquid.getName());
        contentValues.put(Cols.IMAGE_NAME, liquid.getAssertDrawable());
        contentValues.put(Cols.QUANTITY, liquid.getQuantity());

        return contentValues;
    }

    private LiquidCursorWrapper queryLiquids(String whereClause, String[] whereArgs){
        Cursor cursor = mLiquidDatabase.query(
                LiquidTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new LiquidCursorWrapper(cursor);
    }
}
