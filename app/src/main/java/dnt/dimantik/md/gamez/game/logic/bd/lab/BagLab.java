package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.BagCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.BagDbSchema.BagTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.BagDbSchema.BagTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Bag;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Resource;

/**
 * Created by dimantik on 3/5/18.
 */

public class BagLab {

    private static BagLab sBagLab;

    private SQLiteDatabase mBagDatabase;

    public static BagLab get(SQLiteDatabase database){
        if (sBagLab == null){
            sBagLab = new BagLab(database);
        }
        return sBagLab;
    }

    private BagLab(SQLiteDatabase database){
        mBagDatabase = database;
    }

    public void addBag(Bag bag){
        ContentValues contentValues = getContentValues(bag);

        mBagDatabase.insert(BagTable.TABLE_NAME, null, contentValues);
    }

    public void updateBag(Bag bag){
        String uuidString = bag.getId().toString();
        ContentValues contentValues = getContentValues(bag);

        mBagDatabase.update(BagTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteBag(Bag bag){
        mBagDatabase.delete(BagTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{bag.getId().toString()});
    }

    public void deleteAllBags(){
        mBagDatabase.delete(BagTable.TABLE_NAME, null, null);
    }

    public void addAllBag(List<Bag> allBodyClothes){
        for (Bag bag : allBodyClothes){
            addBag(bag);
        }
    }

    public Map<UUID, Bag> getAllBag(){
        Map<UUID, Bag> allBag = new HashMap<>();

        BagCursorWrapper cursor = queryBag(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Bag bag = cursor.getBag();
                allBag.put(bag.getId(), bag);
                Log.i("TAG", "Bag - " + cursor.getBag().getId().toString());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return allBag;
    }

    public Bag getBag(UUID id){
        BagCursorWrapper cursor = queryBag(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getBag();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Bag bag){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, bag.getId().toString());
        contentValues.put(Cols.NAME, bag.getName());
        contentValues.put(Cols.IMAGE_NAME, bag.getAssertDrawable());
        contentValues.put(Cols.CAPACITY, bag.getCapacity());

        String resourcesId = "";
        for (Resource resource : bag.getResourceList()){
            resourcesId += resource.getId() + "|";
        }

        contentValues.put(Cols.RESOURCE_LIST, resourcesId);

        return contentValues;
    }

    private BagCursorWrapper queryBag(String whereClause, String[] whereArgs){
        Cursor cursor = mBagDatabase.query(
                BagTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new BagCursorWrapper(cursor);
    }
}
