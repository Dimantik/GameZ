package dnt.dimantik.md.gamez.game.logic.bd.scheme;

/**
 * Created by dimantik on 3/5/18.
 */

public class BodyClothesDbSchema {

    public static final class BodyClothesTable {

        public static String TABLE_NAME = "BODY_CLOTHES";

        public static final class Cols {

            public static final String UUID = "id";
            public static final String NAME = "name";
            public static final String IMAGE_NAME = "image_name";
            public static final String PROTECTION = "protection";
        }
    }
}
