package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.FeetClothesDbSchema.FeetClothesTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.FeetClothes;

/**
 * Created by dimantik on 3/5/18.
 */

public class FeetClothesCursorWrapper extends CursorWrapper {

    public FeetClothesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public FeetClothes getFeetClothes(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String name = getString(getColumnIndex(Cols.NAME));
        String imageName = getString(getColumnIndex(Cols.IMAGE_NAME));
        int protection = getInt(getColumnIndex(Cols.PROTECTION));

        FeetClothes feetClothes = new FeetClothes(UUID.fromString(uuidString), name, protection, imageName);

        return feetClothes;
    }
}
