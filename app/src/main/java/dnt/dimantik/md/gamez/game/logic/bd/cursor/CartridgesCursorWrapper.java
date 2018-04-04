package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.CartridgesDbSchema.CartridgesTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Cartridges;
import dnt.dimantik.md.gamez.game.logic.clases.resources.FireArms;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

/**
 * Created by dimantik on 3/5/18.
 */

public class CartridgesCursorWrapper extends CursorWrapper {

    public CartridgesCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Cartridges getCartridges(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String name = getString(getColumnIndex(Cols.NAME));
        String imageName = getString(getColumnIndex(Cols.IMAGE_NAME));
        String type = getString(getColumnIndex(Cols.TYPE));
        int quantity = getInt(getColumnIndex(Cols.QUANTITY));

        Cartridges cartridges = new Cartridges(Assistant.convertToUUID(uuidString), name, imageName, quantity,
                FireArms.Type.valueOf(type));

        return cartridges;
    }
}
