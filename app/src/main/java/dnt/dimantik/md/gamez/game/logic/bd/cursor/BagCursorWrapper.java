package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.BagDbSchema.BagTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Bag;

/**
 * Created by dimantik on 3/5/18.
 */

public class BagCursorWrapper extends CursorWrapper {

    public BagCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Bag getBag(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String name = getString(getColumnIndex(Cols.NAME));
        String imageName = getString(getColumnIndex(Cols.IMAGE_NAME));
        int capacity = getInt(getColumnIndex(Cols.CAPACITY));
        String resourcesId = getString(getColumnIndex(Cols.RESOURCE_LIST));

        Bag bag = new Bag(UUID.fromString(uuidString), name, imageName, capacity);

        String oneResourceId = "";
        for (int i = 0; i < resourcesId.length(); i++){
            if (resourcesId.charAt(i) == '|'){
                bag.addUUIDFromResource(UUID.fromString(oneResourceId));
                oneResourceId = "";
            } else {
                oneResourceId += resourcesId.charAt(i);
            }
        }

        return bag;
    }
}
