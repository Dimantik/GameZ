package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.FoodDbSchema.FoodTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Food;

/**
 * Created by dimantik on 3/5/18.
 */

public class FoodCursorWrapper extends CursorWrapper {

    public FoodCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Food getFood(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String name = getString(getColumnIndex(Cols.NAME));
        String imageName = getString(getColumnIndex(Cols.IMAGE_NAME));
        int quantity = getInt(getColumnIndex(Cols.QUANTITY));

        Food food = new Food(UUID.fromString(uuidString), name, quantity, imageName);

        return food;
    }
}
