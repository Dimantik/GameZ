package dnt.dimantik.md.gamez.game.logic.bd.scheme;

/**
 * Created by dimantik on 3/5/18.
 */

public class TransportDbSchema {

    public static final class TransportTable {

        public static String TABLE_NAME = "TRANSPORT";

        public static final class Cols {

            public static final String UUID = "id";
            public static final String NAME = "name";
            public static final String IMAGE_NAME = "image_name";
            public static final String PROTECTION = "protection";
            public static final String POWER = "power";
            public static final String BAG_UUID = "bag_uuid";
            public static final String FUEL_QUANTITY = "fuel_quantity";
            public static final String SPEND_FUEL = "spend_fuel";
        }
    }
}
