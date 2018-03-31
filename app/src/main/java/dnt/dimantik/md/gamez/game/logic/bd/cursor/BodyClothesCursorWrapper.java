package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.BodyClothesDbSchema.BodyClothesTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resource.BodyClothes;

/**
 * Created by dimantik on 3/5/18.
 */

public class BodyClothesCursorWrapper extends CursorWrapper {

    public BodyClothesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public BodyClothes getBodyClothes(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String name = getString(getColumnIndex(Cols.NAME));
        String imageName = getString(getColumnIndex(Cols.IMAGE_NAME));
        int protection = getInt(getColumnIndex(Cols.PROTECTION));

        BodyClothes bodyClothes = new BodyClothes(UUID.fromString(uuidString), name, protection, imageName);

        return bodyClothes;
    }
}
