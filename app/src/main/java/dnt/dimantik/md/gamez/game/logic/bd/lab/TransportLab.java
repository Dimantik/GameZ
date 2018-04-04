package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.TransportCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.TransportDbSchema.TransportTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.TransportDbSchema.TransportTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Transport;

/**
 * Created by dimantik on 3/5/18.
 */

public class TransportLab {

    private static TransportLab sTransportLab;

    private SQLiteDatabase mTransportDatabase;

    public static TransportLab get(SQLiteDatabase database){
        if (sTransportLab == null){
            sTransportLab = new TransportLab(database);
        }
        return sTransportLab;
    }

    private TransportLab(SQLiteDatabase database){
        mTransportDatabase = database;
    }

    public void addTransport(Transport transport){
        ContentValues contentValues = getContentValues(transport);

        mTransportDatabase.insert(TransportTable.TABLE_NAME, null, contentValues);
    }

    public void updateTRansport(Transport transport){
        String uuidString = transport.getId().toString();
        ContentValues contentValues = getContentValues(transport);

        mTransportDatabase.update(TransportTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteTransport(Transport transport){
        mTransportDatabase.delete(TransportTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{transport.getId().toString()});
    }

    public void deleteAllTransports(){
        mTransportDatabase.delete(TransportTable.TABLE_NAME, null, null);
    }

    public void addAllTransport(List<Transport> allTransport){
        for (Transport transport : allTransport){
            addTransport(transport);
        }
    }

    public Map<UUID, Transport> getAllTransport(){
        Map<UUID, Transport> allTransport = new HashMap<>();

        TransportCursorWrapper cursor = queryTransport(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Transport transport = cursor.getTransport();
                allTransport.put(transport.getId(), transport);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return allTransport;
    }

    public Transport getTransport(UUID id){
        TransportCursorWrapper cursor = queryTransport(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getTransport();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Transport transport){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, transport.getId().toString());
        contentValues.put(Cols.NAME, transport.getName());
        contentValues.put(Cols.IMAGE_NAME, transport.getAssertDrawable());
        contentValues.put(Cols.POWER, transport.getPower());
        contentValues.put(Cols.PROTECTION, transport.getProtection());
        contentValues.put(Cols.BAG_UUID, transport.getBag().getId().toString());
        contentValues.put(Cols.FUEL_QUANTITY, transport.getFuelQuantity());
        contentValues.put(Cols.SPEND_FUEL, transport.getSpendFuel());

        return contentValues;
    }

    private TransportCursorWrapper queryTransport(String whereClause, String[] whereArgs){
        Cursor cursor = mTransportDatabase.query(
                TransportTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new TransportCursorWrapper(cursor);
    }
}
