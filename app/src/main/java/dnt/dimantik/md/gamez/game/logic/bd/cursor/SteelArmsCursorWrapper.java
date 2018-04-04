package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.SteelArmsDbSchema.SteelArmsTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.SteelArms;

/**
 * Created by dimantik on 3/5/18.
 */

public class SteelArmsCursorWrapper extends CursorWrapper {

    public SteelArmsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public SteelArms getSteelArms(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String name = getString(getColumnIndex(Cols.NAME));
        String imageName = getString(getColumnIndex(Cols.IMAGE_NAME));
        int power = getInt(getColumnIndex(Cols.POWER));

        SteelArms steelArms = new SteelArms(UUID.fromString(uuidString), name, power, imageName);

        return steelArms;
    }
}
