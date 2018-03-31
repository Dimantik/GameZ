package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.PlayerCursorWrapper;
import dnt.dimantik.md.gamez.helper.classes.Assistant;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.PlayerDbSchema.PlayerTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.PlayerDbSchema.PlayerTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.Player;

/**
 * Created by dimantik on 3/5/18.
 */

public class PlayerLab {

    private static PlayerLab sPlayerLab;

    private SQLiteDatabase mPlayerDatabase;

    public static PlayerLab get(SQLiteDatabase database){
        if (sPlayerLab == null){
            sPlayerLab = new PlayerLab(database);
        }
        return sPlayerLab;
    }

    private PlayerLab(SQLiteDatabase database){
        mPlayerDatabase = database;
    }

    public void addPLayer(Player player){
        ContentValues contentValues = getContentValues(player);

        mPlayerDatabase.insert(PlayerTable.TABLE_NAME, null, contentValues);
    }

    public void updatePlayer(Player player){
        String uuidString = player.getUUID().toString();
        ContentValues contentValues = getContentValues(player);

        mPlayerDatabase.update(PlayerTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deletePlayer(Player player){
        mPlayerDatabase.delete(PlayerTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{player.getUUID().toString()});
    }

    public void deleteAllPlayers(){
        mPlayerDatabase.delete(PlayerTable.TABLE_NAME, null, null);
    }

    public void addAllPlayer(List<Player> allPlayer){
        for (Player player : allPlayer){
            addPLayer(player);
        }
    }

    public List<Player> getAllPLayer(){
        List<Player> allPlayer = new ArrayList<>();

        PlayerCursorWrapper cursor = queryPlayer(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                allPlayer.add(cursor.getPlayer());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return allPlayer;
    }

    public Player getPlayer(UUID id){
        PlayerCursorWrapper cursor = queryPlayer(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getPlayer();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Player player){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, player.getUUID().toString());
        contentValues.put(Cols.NAME, player.getName());
        contentValues.put(Cols.HEALTH, player.getHealth());
        contentValues.put(Cols.ENERGY, player.getEnergy());
        contentValues.put(Cols.SATIETY, player.getSatiety());
        contentValues.put(Cols.THIRST, player.getThirst());
        contentValues.put(Cols.MURDER_SKILL, player.getMurderSkill());
        contentValues.put(Cols.POWER, player.getPower());
        contentValues.put(Cols.PROTECTION, player.getProtection());

        contentValues.put(Cols.CURRENT_HEAD_CLOTHES, Assistant.wrapUUID(player.getCurrentHeadClothesUUID()));
        contentValues.put(Cols.CURRENT_BODY_CLOTHES, Assistant.wrapUUID(player.getCurrentBodyClothesUUID()));
        contentValues.put(Cols.CURRENT_LEGS_CLOTHES, Assistant.wrapUUID(player.getCurrentLegsClothesUUID()));
        contentValues.put(Cols.CURRENT_FEET_CLOTHES, Assistant.wrapUUID(player.getCurrentFeetClothesUUID()));
        contentValues.put(Cols.CURRENT_WEAPON_FIRST, Assistant.wrapUUID(player.getCurrentFirstWeaponUUID()));
        contentValues.put(Cols.CURRENT_WEAPON_SECOND, Assistant.wrapUUID(player.getCurrentSecondWeaponUUID()));
        contentValues.put(Cols.CURRENT_TRANSPORT, Assistant.wrapUUID(player.getCurrentTransportUUID()));
        contentValues.put(Cols.CURRENT_BAG, Assistant.wrapUUID(player.getCurrentBagUUID()));


        return contentValues;
    }

    private PlayerCursorWrapper queryPlayer(String whereClause, String[] whereArgs){
        Cursor cursor = mPlayerDatabase.query(
                PlayerTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new PlayerCursorWrapper(cursor);
    }
}
