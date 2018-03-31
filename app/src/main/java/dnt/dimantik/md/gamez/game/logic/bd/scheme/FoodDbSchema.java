package dnt.dimantik.md.gamez.game.logic.bd.scheme;

/**
 * Created by dimantik on 3/5/18.
 */

public class FoodDbSchema {

    public static final class FoodTable {

        public static String TABLE_NAME = "FOOD";

        public static final class Cols {

            public static final String UUID = "id";
            public static final String NAME = "name";
            public static final String IMAGE_NAME = "image_name";
            public static final String QUANTITY = "quantity";
        }
    }
}
