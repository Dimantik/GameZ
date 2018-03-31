package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.LinkedList;
import java.util.List;

import dnt.dimantik.md.gamez.game.logic.clases.location.Location;

import static dnt.dimantik.md.gamez.game.logic.bd.scheme.LocationDbScheme.LocationTable.*;

/**
 * Created by dimantik on 3/5/18.
 */

public class LocationCursorWrapper extends CursorWrapper {

    public LocationCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Location getLocation(){
        int id = getInt(getColumnIndex(Cols.ID));
        String name = getString(getColumnIndex(Cols.NAME));
        String info = getString(getColumnIndex(Cols.INFO));
        String placeIdListString = getString(getColumnIndex(Cols.PLACE_ID_LIST));
        String assertDrawableName = getString(getColumnIndex(Cols.ASSERT_DRAWABLE_NAME));

        String placeId = "";
        List<Integer> placeIdList = new LinkedList<>();
        for (int i = 0; i < placeIdListString.length(); i++){
            if (placeIdListString.charAt(i) == '|'){
                placeIdList.add(Integer.parseInt(placeId));
                placeId = "";
            } else {
                placeId += placeIdListString.charAt(i);
            }
        }

        Location location = Location.getInstance(id, name, info, assertDrawableName, placeIdList);

        return location;
    }
}
