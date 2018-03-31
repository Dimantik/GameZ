package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.LocationCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.LocationDbScheme.LocationTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.LocationDbScheme.LocationTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.location.Location;
import dnt.dimantik.md.gamez.game.logic.clases.location.Place;

/**
 * Created by dimantik on 3/5/18.
 */

public class LocationLab {

    private static LocationLab sLocationLab;

    private SQLiteDatabase mLocationDatabase;

    public static LocationLab get(SQLiteDatabase database){
        if (sLocationLab == null){
            sLocationLab = new LocationLab(database);
        }
        return sLocationLab;
    }

    private LocationLab(SQLiteDatabase database){
        mLocationDatabase = database;
    }

    public void addLocation(Location location){
        ContentValues contentValues = getContentValues(location);

        mLocationDatabase.insert(LocationTable.TABLE_NAME, null, contentValues);
    }

    public void updateLocation(Location location){
        String idString = Integer.toString(location.getId());
        ContentValues contentValues = getContentValues(location);

        mLocationDatabase.update(LocationTable.TABLE_NAME, contentValues, Cols.ID + " = ?", new String[]{idString});
    }

    public void deleteLocation(Location location){
        String idString = Integer.toString(location.getId());
        mLocationDatabase.delete(LocationTable.TABLE_NAME, Cols.ID + " = ?", new String[]{idString});
    }

    public void deleteAllLocation(){
        mLocationDatabase.delete(LocationTable.TABLE_NAME, null, null);
    }

    public void addAllLocation(List<Location> locationList){
        for (Location location : locationList){
            addLocation(location);
        }
    }

    public Map<Integer, Location> getAllLocation(){
        Map<Integer, Location> locationMap = new HashMap<>();

        LocationCursorWrapper cursor = queryLocation(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Location location = cursor.getLocation();
                locationMap.put(location.getId(), location);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return locationMap;
    }

    public void updateAllLocation(List<Location> locationList){
        for (Location location : locationList){
            updateLocation(location);
        }
    }

    public Location getLocation(int id){
        LocationCursorWrapper cursor = queryLocation(Cols.ID + " = ?", new String[]{Integer.toString(id)});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getLocation();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Location location){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.NAME, location.getName());
        contentValues.put(Cols.ID, location.getId());
        contentValues.put(Cols.INFO, location.getInfo());
        contentValues.put(Cols.ASSERT_DRAWABLE_NAME, location.getAssertDrawableName());

        String placeIdList = "";
        for (Place place : location.getPlaceList()){
            placeIdList += place.getId() + "|";
        }

        contentValues.put(Cols.PLACE_ID_LIST, placeIdList);

        return contentValues;
    }

    private LocationCursorWrapper queryLocation(String whereClause, String[] whereArgs){
        Cursor cursor = mLocationDatabase.query(
                LocationTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new LocationCursorWrapper(cursor);
    }
}
