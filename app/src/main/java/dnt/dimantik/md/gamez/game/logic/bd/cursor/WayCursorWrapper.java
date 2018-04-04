package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.WayDbSchema.WayTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.map.Way;

/**
 * Created by dimantik on 3/5/18.
 */

public class WayCursorWrapper extends CursorWrapper {

    public WayCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Way getWay(){
        int pointOneId = getInt(getColumnIndex(Cols.POINT_ONE_ID));
        int pointTwoId = getInt(getColumnIndex(Cols.POINT_TWO_ID));
        int travelTime = getInt(getColumnIndex(Cols.TRAVEL_TIME));

        Way way = new Way(pointOneId, pointTwoId, travelTime);

        return way;
    }
}
