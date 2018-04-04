package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.FoodDbSchema.FoodTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Liquid;

/**
 * Created by dimantik on 3/5/18.
 */

public class LiquidCursorWrapper extends CursorWrapper {

    public LiquidCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Liquid getLiquid(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String name = getString(getColumnIndex(Cols.NAME));
        String imageName = getString(getColumnIndex(Cols.IMAGE_NAME));
        int quantity = getInt(getColumnIndex(Cols.QUANTITY));

        Liquid liquid = new Liquid(UUID.fromString(uuidString), name, quantity, imageName);

        return liquid;
    }
}
