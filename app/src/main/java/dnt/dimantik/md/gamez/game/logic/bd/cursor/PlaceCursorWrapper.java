package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.PlaceDbSchema.PlaceTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.map.Place;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Resource;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

/**
 * Created by dimantik on 3/5/18.
 */

public class PlaceCursorWrapper extends CursorWrapper {

    public PlaceCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Place getPlace(){
        int id = getInt(getColumnIndex(Cols.ID));
        String name = getString(getColumnIndex(Cols.NAME));
        int dangerousLevel = getInt(getColumnIndex(Cols.DANGEROUS_LEVEL));
        int protetion = getInt(getColumnIndex(Cols.PROTECTION));
        String info = getString(getColumnIndex(Cols.INFO));
        String resourceIdList = getString(getColumnIndex(Cols.RESOURCE_ID_LIST));
        String resourceTypeList = getString(getColumnIndex(Cols.RESOURCE_TYPE_LIST));
        String assertDrawableName = getString(getColumnIndex(Cols.ASSERT_DRAWABLE_NAME));

        String resourceUUID = "";
        Set<UUID> uuidSet = new HashSet<>();
        for (int i = 0; i < resourceIdList.length(); i++){
            if (resourceIdList.charAt(i) == '|'){
                uuidSet.add(Assistant.convertToUUID(resourceUUID));
                resourceUUID = "";
            } else {
                resourceUUID += resourceIdList.charAt(i);
            }
        }

        String resourceType = "";
        List<Resource.ResourceType> typeList = new LinkedList<>();
        for (int i = 0; i < resourceTypeList.length(); i++){
            if (resourceTypeList.charAt(i) == '|'){
                typeList.add(Resource.ResourceType.valueOf(resourceType));
                resourceType = "";
            } else {
                resourceType += resourceTypeList.charAt(i);
            }
        }

        Place place = Place.getInstance(id, name, dangerousLevel, protetion, info, assertDrawableName, typeList, uuidSet);

        return place;
    }
}
