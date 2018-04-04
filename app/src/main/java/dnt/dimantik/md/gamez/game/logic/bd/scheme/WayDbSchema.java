package dnt.dimantik.md.gamez.game.logic.bd.scheme;

/**
 * Created by dimantik on 3/5/18.
 */

public class WayDbSchema {

    public static final class WayTable {

        public static String TABLE_NAME = "Way";

        public static final class Cols {

            public static final String POINT_ONE_ID = "point_one_id";
            public static final String POINT_TWO_ID = "point_two_id";
            public static final String TRAVEL_TIME = "travel_time";

        }
    }
}
