package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.HeadClothesDbSchema.HeadClothesTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resource.HeadClothes;

/**
 * Created by dimantik on 3/5/18.
 */

public class HeadClothesCursorWrapper extends CursorWrapper {

    public HeadClothesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public HeadClothes getHeadClothes(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String name = getString(getColumnIndex(Cols.NAME));
        String imageName = getString(getColumnIndex(Cols.IMAGE_NAME));
        int protection = getInt(getColumnIndex(Cols.PROTECTION));

        HeadClothes headClothes = new HeadClothes(UUID.fromString(uuidString), name, protection, imageName);

        return headClothes;
    }
}
