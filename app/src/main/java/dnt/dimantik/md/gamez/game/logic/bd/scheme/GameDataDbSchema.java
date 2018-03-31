package dnt.dimantik.md.gamez.game.logic.bd.scheme;

/**
 * Created by dimantik on 3/5/18.
 */

public class GameDataDbSchema {

    public static final class GameDataTable {

        public static String TABLE_NAME = "GAME_DATA";

        public static final class Cols {

            public static final String UUID = "uuid";
            public static final String LAST_DAY = "last_day";
            public static final String ALL_MINUTES = "all_minutes";

            public static final String CURRENT_LOCATION = "current_location";
            public static final String CURRENT_PLACE = "current_place";

        }
    }
}
