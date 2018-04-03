package dnt.dimantik.md.gamez.game.logic.bd.scheme;

/**
 * Created by dimantik on 3/5/18.
 */

public class PlayerDbSchema {

    public static final class PlayerTable {

        public static String TABLE_NAME = "PLAYER";

        public static final class Cols {

            public static final String NAME = "name";
            public static final String UUID = "uuid";
            public static final String HEALTH = "health";
            public static final String SATIETY = "satiety";
            public static final String THIRST = "thirst";
            public static final String ENERGY = "energy";
            public static final String POWER = "power";
            public static final String PROTECTION = "protection";
            public static final String MURDER_SKILL = "murder_skill";
            public static final String CURRENT_HEAD_CLOTHES = "current_head_clothes";
            public static final String CURRENT_BODY_CLOTHES = "current_body_clothes";
            public static final String CURRENT_LEGS_CLOTHES = "current_legs_clothes";
            public static final String CURRENT_FEET_CLOTHES = "current_feet_clothes";
            public static final String CURRENT_WEAPON_FIRST = "current_weapon_first";
            public static final String CURRENT_WEAPON_SECOND = "current_weapon_second";
            public static final String CURRENT_BAG = "current_bag";
            public static final String WITHOUT_BAG = "without_bag";
            public static final String CURRENT_TRANSPORT = "current_transport";
        }
    }
}
