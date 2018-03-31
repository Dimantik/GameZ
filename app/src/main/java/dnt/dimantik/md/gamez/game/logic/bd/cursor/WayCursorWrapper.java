package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.WayDbSchema.WayTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.location.Way;

/**
 * Created by dimantik on 3/5/18.
 */

public class WayCursorWrapper extends CursorWrapper {

    public WayCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Way getWay(){
        int toId = getInt(getColumnIndex(Cols.TO_ID));
        int fromId = getInt(getColumnIndex(Cols.FROM_ID));
        int travelTime = getInt(getColumnIndex(Cols.TRAVEL_TIME));

        Way way = new Way(travelTime, toId, fromId);

        return way;
    }
}
