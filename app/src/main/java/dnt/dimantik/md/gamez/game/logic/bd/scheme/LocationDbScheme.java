package dnt.dimantik.md.gamez.game.logic.bd.scheme;

/**
 * Created by dimantik on 3/5/18.
 */

public class LocationDbScheme {

    public static final class LocationTable {

        public static String TABLE_NAME = "LOCATION";

        public static final class Cols {

            public static final String NAME = "name";
            public static final String ID = "id";
            public static final String INFO = "info";
            public static final String PLACE_ID_LIST = "place_id_list";
            public static final String ASSERT_DRAWABLE_NAME = "assert_drawable_name";

        }
    }
}
