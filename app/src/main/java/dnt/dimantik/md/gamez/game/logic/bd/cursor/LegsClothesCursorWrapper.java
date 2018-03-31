package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.LegsClothesDbSchema.LegsClothesTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resource.LegsClothes;

/**
 * Created by dimantik on 3/5/18.
 */

public class LegsClothesCursorWrapper extends CursorWrapper {

    public LegsClothesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public LegsClothes getLegsClothes(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String name = getString(getColumnIndex(Cols.NAME));
        String imageName = getString(getColumnIndex(Cols.IMAGE_NAME));
        int protection = getInt(getColumnIndex(Cols.PROTECTION));

        LegsClothes legsClothes = new LegsClothes(UUID.fromString(uuidString), name, protection, imageName);

        return legsClothes;
    }
}
