package dnt.dimantik.md.gamez.game.logic.bd.scheme;

/**
 * Created by dimantik on 3/5/18.
 */

public class PlaceDbSchema {

    public static final class PlaceTable {

        public static String TABLE_NAME = "PLACE";

        public static final class Cols {

            public static final String NAME = "name";
            public static final String ID = "id";
            public static final String DANGEROUS_LEVEL = "dangerous_level";
            public static final String INFO = "info";
            public static final String RESOURCE_ID_LIST = "resource_id_list";
            public static final String RESOURCE_TYPE_LIST = "resource_type_list";
            public static final String ASSERT_DRAWABLE_NAME = "assert_drawable_name";
            public static final String PROTECTION = "protection";

        }
    }
}
