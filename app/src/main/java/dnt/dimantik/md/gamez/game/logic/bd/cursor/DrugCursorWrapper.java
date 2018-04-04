package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.DrugDbSchema.DrugTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Drug;

/**
 * Created by dimantik on 3/5/18.
 */

public class DrugCursorWrapper extends CursorWrapper {

    public DrugCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Drug getDrug(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String name = getString(getColumnIndex(Cols.NAME));
        String imageName = getString(getColumnIndex(Cols.IMAGE_NAME));
        int quantity = getInt(getColumnIndex(Cols.QUANTITY));

        Drug drug = new Drug(UUID.fromString(uuidString), name, quantity, imageName);

        return drug;
    }
}
