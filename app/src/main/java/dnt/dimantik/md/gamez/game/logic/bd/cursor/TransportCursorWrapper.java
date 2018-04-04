package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.TransportDbSchema.TransportTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Transport;

/**
 * Created by dimantik on 3/5/18.
 */

public class TransportCursorWrapper extends CursorWrapper {

    public TransportCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Transport getTransport(){
        String uuidString = getString(getColumnIndex(Cols.UUID));
        String name = getString(getColumnIndex(Cols.NAME));
        String imageName = getString(getColumnIndex(Cols.IMAGE_NAME));
        String bagUuid = getString(getColumnIndex(Cols.BAG_UUID));
        int protection = getInt(getColumnIndex(Cols.PROTECTION));
        int power = getInt(getColumnIndex(Cols.POWER));
        int fuelQuantity = getInt(getColumnIndex(Cols.FUEL_QUANTITY));
        int spendFuel = getInt(getColumnIndex(Cols.SPEND_FUEL));

        Transport transport = new Transport(UUID.fromString(uuidString), name, power, protection, fuelQuantity, spendFuel, imageName);
        transport.setBagUUID(UUID.fromString(bagUuid));

        return transport;
    }
}
