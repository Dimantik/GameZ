package dnt.dimantik.md.gamez.game.logic.bd.lab;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.cursor.GameDataCursorWrapper;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.GameDataDbSchema.GameDataTable;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.GameDataDbSchema.GameDataTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.GameData;
import dnt.dimantik.md.gamez.game.logic.clases.GameData;

/**
 * Created by dimantik on 3/5/18.
 */

public class GameDataLab {

    private static GameDataLab sGameDataLab;

    private SQLiteDatabase mGameDataDatabase;

    public static GameDataLab get(SQLiteDatabase database){
        if (sGameDataLab == null){
            sGameDataLab = new GameDataLab(database);
        }
        return sGameDataLab;
    }

    private GameDataLab(SQLiteDatabase database){
        mGameDataDatabase = database;
    }

    public void addGameData(GameData gameData){
        ContentValues contentValues = getContentValues(gameData);

        mGameDataDatabase.insert(GameDataTable.TABLE_NAME, null, contentValues);
    }

    public void updateGameData(GameData gameData){
        String uuidString = gameData.getUUID().toString();
        ContentValues contentValues = getContentValues(gameData);

        mGameDataDatabase.update(GameDataTable.TABLE_NAME, contentValues, Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void deleteGameData(GameData gameData){
        mGameDataDatabase.delete(GameDataTable.TABLE_NAME, Cols.UUID + " = ?", new String[]{gameData.getUUID().toString()});
    }

    public void deleteGameDataList(){
        mGameDataDatabase.delete(GameDataTable.TABLE_NAME, null, null);
    }

    public void addGameDataList(List<GameData> gameDataList){
        for (GameData gameData : gameDataList){
            addGameData(gameData);
        }
    }

    public List<GameData> getGameDataList(){
        List<GameData> gameDataList = new LinkedList<>();

        GameDataCursorWrapper cursor = queryGame(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                gameDataList.add(cursor.getGameData());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return gameDataList;
    }

    public GameData getGameData(UUID id){
        GameDataCursorWrapper cursor = queryGame(Cols.UUID + " = ?", new String[]{id.toString()});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getGameData();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(GameData gameData){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.UUID, gameData.getUUID().toString());
        contentValues.put(Cols.ALL_MINUTES, gameData.getAllMinutes());
        contentValues.put(Cols.LAST_DAY, gameData.getLastDay());
        contentValues.put(Cols.CURRENT_LOCATION, gameData.getCurrentLocationId());
        contentValues.put(Cols.CURRENT_PLACE, gameData.getCurrentPlaceId());

        return contentValues;
    }

    private static String getUUIDValues(List<UUID> list){
        String result = "";
        for (UUID uuid : list){
            result += uuid.toString() + "|";
        }
        return result;
    }

    private static String getStringValues(List<String> list){
        String result = "";
        for (String id : list){
            result += id + "|";
        }
        return result;
    }

    private GameDataCursorWrapper queryGame(String whereClause, String[] whereArgs){
        Cursor cursor = mGameDataDatabase.query(
                GameDataTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new GameDataCursorWrapper(cursor);
    }
}
