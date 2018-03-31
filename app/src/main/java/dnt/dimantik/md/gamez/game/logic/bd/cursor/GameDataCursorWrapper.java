package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.GameDataDbSchema.GameDataTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.GameData;

public class GameDataCursorWrapper extends CursorWrapper {

    public GameDataCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public GameData getGameData(){
        String uuid = getString(getColumnIndex(Cols.UUID));
        int allMinutes = getInt(getColumnIndex(Cols.ALL_MINUTES));
        int lastDay = getInt(getColumnIndex(Cols.LAST_DAY));
        int currentLocationId = getInt(getColumnIndex(Cols.CURRENT_LOCATION));
        int currentPlaceId = getInt(getColumnIndex(Cols.CURRENT_PLACE));

        GameData gameData = new GameData(UUID.fromString(uuid));
        gameData.setAllMinutes(allMinutes);
        gameData.setLastDay(lastDay);
        gameData.setCurrentPlaceId(currentPlaceId);
        gameData.setCurrentLocationId(currentLocationId);

        return gameData;
    }

    private List<Integer> parseId(String values){
        List<Integer> list = new LinkedList<>();
        String id = "";
        for (int i = 0; i < values.length(); i++){
            if (values.charAt(i) == '|'){
                list.add(Integer.parseInt(id));
                id = "";
            } else {
                id += values.charAt(i);
            }
        }
        return list;
    }

}
