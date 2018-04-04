package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.WayCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.WayDbSchema.WayTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.WayDbSchema.WayTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.map.Way;

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
        contentValues.put(Cols.POINT_ONE_ID, way.getPointOneId());
        contentValues.put(Cols.POINT_TWO_ID, way.getPointTwoId());

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
