package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.PlaceCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.PlaceDbSchema.PlaceTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.PlaceDbSchema.PlaceTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.map.Place;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Resource;

/**
 * Created by dimantik on 3/5/18.
 */

public class PlaceLab {

    private static PlaceLab sPlaceLab;

    private SQLiteDatabase mPlaceDatabase;

    public static PlaceLab get(SQLiteDatabase database){
        if (sPlaceLab == null){
            sPlaceLab = new PlaceLab(database);
        }
        return sPlaceLab;
    }

    private PlaceLab(SQLiteDatabase database){
        mPlaceDatabase = database;
    }

    public void addPlace(Place place){
        ContentValues contentValues = getContentValues(place);

        mPlaceDatabase.insert(PlaceTable.TABLE_NAME, null, contentValues);
    }

    public void updatePlace(Place place){
        String idString = Integer.toString(place.getId());
        ContentValues contentValues = getContentValues(place);

        mPlaceDatabase.update(PlaceTable.TABLE_NAME, contentValues, Cols.ID + " = ?", new String[]{idString});
    }

    public void deletePlace(Place place){
        String idString = Integer.toString(place.getId());
        mPlaceDatabase.delete(PlaceTable.TABLE_NAME, Cols.ID + " = ?", new String[]{idString});
    }

    public void deleteAllPlaces(){
        mPlaceDatabase.delete(PlaceTable.TABLE_NAME, null, null);
    }

    public void addAllPlace(List<Place> placeList){
        for (Place place : placeList){
            addPlace(place);
        }
    }

    public void updateAllPlace(List<Place> placeList){
        for (Place place : placeList){
            updatePlace(place);
        }
    }

    public Map<Integer, Place> getAllPlace(){
        Map<Integer, Place> allPlace = new HashMap<>();

        PlaceCursorWrapper cursor = queryPlace(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Place place = cursor.getPlace();
                allPlace.put(place.getId(), place);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }


        return allPlace;
    }

    public Place getPlace(int id){
        PlaceCursorWrapper cursor = queryPlace(Cols.ID + " = ?", new String[]{Integer.toString(id)});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getPlace();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Place place){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.NAME, place.getName());
        contentValues.put(Cols.ID, place.getId());
        contentValues.put(Cols.DANGEROUS_LEVEL, place.getDangerousLevel());
        contentValues.put(Cols.INFO, place.getInfo());
        contentValues.put(Cols.ASSERT_DRAWABLE_NAME, place.getAssertDrawableName());
        contentValues.put(Cols.PROTECTION, place.getProtection());

        String resourceUUIDList = "";
        for (Resource resource : place.getResourceList()){
            resourceUUIDList += resource.getId() + "|";
        }
        contentValues.put(Cols.RESOURCE_ID_LIST, resourceUUIDList);

        String resourceTypeList = "";
        for (Resource.ResourceType type : place.getResourceTypeList()){
            resourceTypeList += type.toString() + "|";
        }
        contentValues.put(Cols.RESOURCE_TYPE_LIST, resourceTypeList);

        return contentValues;
    }

    private PlaceCursorWrapper queryPlace(String whereClause, String[] whereArgs){
        Cursor cursor = mPlaceDatabase.query(
                PlaceTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new PlaceCursorWrapper(cursor);
    }
}
