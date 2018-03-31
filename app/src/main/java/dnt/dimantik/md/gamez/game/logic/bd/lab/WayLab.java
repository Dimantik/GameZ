package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.WayCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.WayDbSchema.WayTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.WayDbSchema.WayTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.location.Way;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;

/**
 * Created by dimantik on 3/5/18.
 */

public class WayLab {

    private static WayLab sWayLab;

    private SQLiteDatabase mWayDatabase;

    public static WayLab get(SQLiteDatabase database){
        if (sWayLab == null){
            sWayLab = new WayLab(database);
        }
        return sWayLab;
    }

    private WayLab(SQLiteDatabase database){
        mWayDatabase = database;
    }

    public void addWay(Way way){
        ContentValues contentValues = getContentValues(way);

        mWayDatabase.insert(WayTable.TABLE_NAME, null, contentValues);
    }



    public void deleteAllWay(){
        mWayDatabase.delete(WayTable.TABLE_NAME, null, null);
    }

    public void addAllWay(List<Way> wayList){
        for (Way way : wayList){
            addWay(way);
        }
    }

    public List<Way> getAllWay(){
        List<Way> wayList = new LinkedList<>();

        WayCursorWrapper cursor = queryWay(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                wayList.add(cursor.getWay());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return wayList;
    }

    private static ContentValues getContentValues(Way way){
        ContentValues contentValues = new ContentValues();

        contentValues.put(Cols.TRAVEL_TIME, way.getTravelTime());
        contentValues.put(Cols.TO_ID, way.getToId());
        contentValues.put(Cols.FROM_ID, way.getFromId());

        return contentValues;
    }

    private WayCursorWrapper queryWay(String whereClause, String[] whereArgs){
        Cursor cursor = mWayDatabase.query(
                WayTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new WayCursorWrapper(cursor);
    }
}
