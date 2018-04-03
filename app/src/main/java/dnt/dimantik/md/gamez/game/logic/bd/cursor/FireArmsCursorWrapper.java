package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.FireArmsDbSchema.FireArmsTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FireArms;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

/**
 * Created by dimantik on 3/5/18.
 */

public class FireArmsCursorWrapper extends CursorWrapper {

    public FireArmsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public FireArms getFireArms(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String name = getString(getColumnIndex(Cols.NAME));
        String imageName = getString(getColumnIndex(Cols.IMAGE_NAME));
        String type = getString(getColumnIndex(Cols.TYPE));
        int power = getInt(getColumnIndex(Cols.POWER));

        FireArms fireArms = new FireArms(UUID.fromString(uuidString), name, power, imageName, FireArms.Type.valueOf(type));

        return fireArms;
    }
}
