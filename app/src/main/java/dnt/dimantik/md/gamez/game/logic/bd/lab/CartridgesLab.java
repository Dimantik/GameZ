package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.CartridgesCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.CartridgesDbSchema.CartridgesTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.CartridgesDbSchema.CartridgesTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Cartridges;

/**
 * Created by dimantik on 3/5/18.
 */

public class CartridgesLab {

    private static CartridgesLab sCartridgesLab;

    private SQLiteDatabase mCartridgesDatabase;

    public static CartridgesLab get(SQLiteDatabase database){
        if (sCartridgesLab == null){
            sCartridgesLab = new CartridgesLab(database);
        }
        return sCartridgesLab;
    }

    private CartridgesLab(SQLiteDatabase database){
        mCartridgesDatabase = database;
    }

    public void addCartridges(Cartridges cartridges){
        ContentValues contentValues = getContentValues(cartridges);

        mCartridgesDatabase.insert(CartridgesTable.TABLE_NAME, null, contentValues);
    }

    public void updateCartridges(Cartridges cartridges){
        String uuidString = cartridges.getId().toString();
        ContentValues contentValues = getContentValues(cartridges);

        mCartridgesDatabase.update(CartridgesTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteCartridges(Cartridges cartridges){
        mCartridgesDatabase.delete(CartridgesTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{cartridges.getId().toString()});
    }

    public void deleteAllCartridges(){
        mCartridgesDatabase.delete(CartridgesTable.TABLE_NAME, null, null);
    }

    public void addAllCartridges(List<Cartridges> allCartridges){
        for (Cartridges cartridges : allCartridges){
            addCartridges(cartridges);
        }
    }

    public Map<UUID, Cartridges> getAllCartridges(){
        Map<UUID, Cartridges> allCartridges = new HashMap<>();

        CartridgesCursorWrapper cursor = queryCartridges(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                Cartridges cartridges = cursor.getCartridges();
                allCartridges.put(cartridges.getId(), cartridges);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return allCartridges;
    }

    public Cartridges getCartridges(UUID id){
        CartridgesCursorWrapper cursor = queryCartridges(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCartridges();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Cartridges cartridges){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, cartridges.getId().toString());
        contentValues.put(Cols.NAME, cartridges.getName());
        contentValues.put(Cols.IMAGE_NAME, cartridges.getAssertDrawable());
        contentValues.put(Cols.QUANTITY, cartridges.getQuantity());
        contentValues.put(Cols.TYPE, cartridges.getType().toString());

        return contentValues;
    }

    private CartridgesCursorWrapper queryCartridges(String whereClause, String[] whereArgs){
        Cursor cursor = mCartridgesDatabase.query(
                CartridgesTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new CartridgesCursorWrapper(cursor);
    }
}
