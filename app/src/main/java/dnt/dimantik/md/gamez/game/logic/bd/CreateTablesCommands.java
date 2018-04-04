package dnt.dimantik.md.gamez.game.logic.bd;

import dnt.dimantik.md.gamez.game.logic.bd.scheme.BagDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.BodyClothesDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.CartridgesDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.DrugDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.FeetClothesDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.FireArmsDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.FoodDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.GameDataDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.HeadClothesDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.LegsClothesDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.LiquidDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.LocationDbScheme;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.PlaceDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.PlayerDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.SteelArmsDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.TransportDbSchema;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.WayDbSchema;

/**
 * Created by dimantik on 18.03.2018.
 */

public class CreateTablesCommands {

    public static final String SQL_CREATE_TABLE_WAY_COMMAND =
            "CREATE TABLE " + WayDbSchema.WayTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    WayDbSchema.WayTable.Cols.POINT_ONE_ID + " INTEGER, " +
                    WayDbSchema.WayTable.Cols.POINT_TWO_ID + " INTEGER, " +
                    WayDbSchema.WayTable.Cols.TRAVEL_TIME + " INTEGER)";

    public static final String SQL_CREATE_TABLE_GAME_DATA_COMMAND =
            "CREATE TABLE " + GameDataDbSchema.GameDataTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GameDataDbSchema.GameDataTable.Cols.UUID + " TEXT, " +
                    GameDataDbSchema.GameDataTable.Cols.LAST_DAY + " INTEGER, " +
                    GameDataDbSchema.GameDataTable.Cols.ALL_MINUTES + " INTEGER, " +
                    GameDataDbSchema.GameDataTable.Cols.CURRENT_LOCATION + " INTEGER, " +
                    GameDataDbSchema.GameDataTable.Cols.CURRENT_PLACE + " TEXT)";

    public static final String SQL_CREATE_TABLE_BAG_COMMAND =
            "CREATE TABLE " + BagDbSchema.BagTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BagDbSchema.BagTable.Cols.UUID + " TEXT, " +
                    BagDbSchema.BagTable.Cols.NAME + " TEXT, " +
                    BagDbSchema.BagTable.Cols.IMAGE_NAME + " TEXT, " +
                    BagDbSchema.BagTable.Cols.RESOURCE_LIST + " TEXT, " +
                    BagDbSchema.BagTable.Cols.CAPACITY + " INTEGER)";

    public static final String SQL_CREATE_TABLE_BODY_CLOTHES_COMMAND =
            "CREATE TABLE " + BodyClothesDbSchema.BodyClothesTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BodyClothesDbSchema.BodyClothesTable.Cols.UUID + " TEXT, " +
                    BodyClothesDbSchema.BodyClothesTable.Cols.NAME + " TEXT, " +
                    BodyClothesDbSchema.BodyClothesTable.Cols.IMAGE_NAME + " TEXT, " +
                    BodyClothesDbSchema.BodyClothesTable.Cols.PROTECTION + " INTEGER)";

    public static final String SQL_CREATE_TABLE_DRUG_COMMAND =
            "CREATE TABLE " + DrugDbSchema.DrugTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DrugDbSchema.DrugTable.Cols.UUID + " TEXT, " +
                    DrugDbSchema.DrugTable.Cols.NAME + " TEXT, " +
                    DrugDbSchema.DrugTable.Cols.IMAGE_NAME + " TEXT, " +
                    DrugDbSchema.DrugTable.Cols.QUANTITY + " INTEGER)";

    public static final String SQL_CREATE_TABLE_FEET_CLOTHES_COMMAND =
            "CREATE TABLE " + FeetClothesDbSchema.FeetClothesTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FeetClothesDbSchema.FeetClothesTable.Cols.UUID + " TEXT, " +
                    FeetClothesDbSchema.FeetClothesTable.Cols.NAME + " TEXT, " +
                    FeetClothesDbSchema.FeetClothesTable.Cols.IMAGE_NAME + " TEXT, " +
                    FeetClothesDbSchema.FeetClothesTable.Cols.PROTECTION + " INTEGER)";

    public static final String SQL_CREATE_TABLE_FOOD_COMMAND =
            "CREATE TABLE " + FoodDbSchema.FoodTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FoodDbSchema.FoodTable.Cols.UUID + " TEXT, " +
                    FoodDbSchema.FoodTable.Cols.NAME + " TEXT, " +
                    FoodDbSchema.FoodTable.Cols.IMAGE_NAME + " TEXT, " +
                    FoodDbSchema.FoodTable.Cols.QUANTITY + " INTEGER)";

    public static final String SQL_CREATE_TABLE_FIRE_ARMS_COMMAND =
            "CREATE TABLE " + FireArmsDbSchema.FireArmsTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FireArmsDbSchema.FireArmsTable.Cols.UUID + " TEXT, " +
                    FireArmsDbSchema.FireArmsTable.Cols.NAME + " TEXT, " +
                    FireArmsDbSchema.FireArmsTable.Cols.IMAGE_NAME + " TEXT, " +
                    FireArmsDbSchema.FireArmsTable.Cols.TYPE + " TEXT, " +
                    FireArmsDbSchema.FireArmsTable.Cols.POWER + " INTEGER)";

    public static final String SQL_CREATE_TABLE_CARTRIDGES_COMMAND =
            "CREATE TABLE " + CartridgesDbSchema.CartridgesTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CartridgesDbSchema.CartridgesTable.Cols.UUID + " TEXT, " +
                    CartridgesDbSchema.CartridgesTable.Cols.NAME + " TEXT, " +
                    CartridgesDbSchema.CartridgesTable.Cols.IMAGE_NAME + " TEXT, " +
                    CartridgesDbSchema.CartridgesTable.Cols.TYPE + " TEXT, " +
                    CartridgesDbSchema.CartridgesTable.Cols.QUANTITY + " INTEGER)";

    public static final String SQL_CREATE_TABLE_HEAD_CLOTHES_COMMAND =
            "CREATE TABLE " + HeadClothesDbSchema.HeadClothesTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    HeadClothesDbSchema.HeadClothesTable.Cols.UUID + " TEXT, " +
                    HeadClothesDbSchema.HeadClothesTable.Cols.NAME + " TEXT, " +
                    HeadClothesDbSchema.HeadClothesTable.Cols.IMAGE_NAME + " TEXT, " +
                    HeadClothesDbSchema.HeadClothesTable.Cols.PROTECTION + " INTEGER)";

    public static final String SQL_CREATE_TABLE_LEGS_CLOTHES_COMMAND =
            "CREATE TABLE " + LegsClothesDbSchema.LegsClothesTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LegsClothesDbSchema.LegsClothesTable.Cols.UUID + " TEXT, " +
                    LegsClothesDbSchema.LegsClothesTable.Cols.NAME + " TEXT, " +
                    LegsClothesDbSchema.LegsClothesTable.Cols.IMAGE_NAME + " TEXT, " +
                    LegsClothesDbSchema.LegsClothesTable.Cols.PROTECTION + " INTEGER)";

    public static final String SQL_CREATE_TABLE_LIQUID_COMMAND =
            "CREATE TABLE " + LiquidDbSchema.LiquidTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LiquidDbSchema.LiquidTable.Cols.UUID + " TEXT, " +
                    LiquidDbSchema.LiquidTable.Cols.NAME + " TEXT, " +
                    LiquidDbSchema.LiquidTable.Cols.IMAGE_NAME + " TEXT, " +
                    LiquidDbSchema.LiquidTable.Cols.QUANTITY + " INTEGER)";

    public static final String SQL_CREATE_TABLE_PLACE_COMMAND =
            "CREATE TABLE " + PlaceDbSchema.PlaceTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PlaceDbSchema.PlaceTable.Cols.ID + " TEXT, " +
                    PlaceDbSchema.PlaceTable.Cols.NAME + " TEXT, " +
                    PlaceDbSchema.PlaceTable.Cols.DANGEROUS_LEVEL + " INTEGER, " +
                    PlaceDbSchema.PlaceTable.Cols.PROTECTION + " INTEGER, " +
                    PlaceDbSchema.PlaceTable.Cols.INFO + " TEXT, " +
                    PlaceDbSchema.PlaceTable.Cols.RESOURCE_ID_LIST + " TEXT, " +
                    PlaceDbSchema.PlaceTable.Cols.ASSERT_DRAWABLE_NAME + " TEXT, " +
                    PlaceDbSchema.PlaceTable.Cols.RESOURCE_TYPE_LIST + " TEXT)";

    public static final String SQL_CREATE_TABLE_LOCATION_COMMAND =
            "CREATE TABLE " + LocationDbScheme.LocationTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LocationDbScheme.LocationTable.Cols.ID + " TEXT, " +
                    LocationDbScheme.LocationTable.Cols.NAME + " TEXT, " +
                    LocationDbScheme.LocationTable.Cols.INFO + " TEXT, " +
                    LocationDbScheme.LocationTable.Cols.ASSERT_DRAWABLE_NAME + " TEXT, " +
                    LocationDbScheme.LocationTable.Cols.PLACE_ID_LIST + " TEXT)";

    public static final String SQL_CREATE_TABLE_PLAYER_COMMAND =
            "CREATE TABLE " + PlayerDbSchema.PlayerTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PlayerDbSchema.PlayerTable.Cols.UUID + " TEXT, " +
                    PlayerDbSchema.PlayerTable.Cols.NAME + " TEXT, " +
                    PlayerDbSchema.PlayerTable.Cols.HEALTH + " INTEGER, " +
                    PlayerDbSchema.PlayerTable.Cols.SATIETY + " INTEGER, " +
                    PlayerDbSchema.PlayerTable.Cols.THIRST + " INTEGER, " +
                    PlayerDbSchema.PlayerTable.Cols.ENERGY + " INTEGER, " +
                    PlayerDbSchema.PlayerTable.Cols.POWER + " INTEGER, " +
                    PlayerDbSchema.PlayerTable.Cols.PROTECTION + " INTEGER, " +
                    PlayerDbSchema.PlayerTable.Cols.MURDER_SKILL + " REAL, " +
                    PlayerDbSchema.PlayerTable.Cols.CURRENT_HEAD_CLOTHES + " TEXT, " +
                    PlayerDbSchema.PlayerTable.Cols.CURRENT_BODY_CLOTHES + " TEXT, " +
                    PlayerDbSchema.PlayerTable.Cols.CURRENT_LEGS_CLOTHES + " TEXT, " +
                    PlayerDbSchema.PlayerTable.Cols.CURRENT_FEET_CLOTHES + " TEXT, " +
                    PlayerDbSchema.PlayerTable.Cols.CURRENT_WEAPON_FIRST + " TEXT, " +
                    PlayerDbSchema.PlayerTable.Cols.CURRENT_WEAPON_SECOND + " TEXT, " +
                    PlayerDbSchema.PlayerTable.Cols.CURRENT_TRANSPORT + " TEXT, " +
                    PlayerDbSchema.PlayerTable.Cols.WITHOUT_BAG + " TEXT, " +
                    PlayerDbSchema.PlayerTable.Cols.CURRENT_BAG + " TEXT)";

    public static final String SQL_CREATE_TABLE_STEEL_ARMS_COMMAND =
            "CREATE TABLE " + SteelArmsDbSchema.SteelArmsTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SteelArmsDbSchema.SteelArmsTable.Cols.UUID + " TEXT, " +
                    SteelArmsDbSchema.SteelArmsTable.Cols.NAME + " TEXT, " +
                    SteelArmsDbSchema.SteelArmsTable.Cols.IMAGE_NAME + " TEXT, " +
                    SteelArmsDbSchema.SteelArmsTable.Cols.POWER + " INTEGER)";

    public static final String SQL_CREATE_TABLE_TRANSPORT_COMMAND =
            "CREATE TABLE " + TransportDbSchema.TransportTable.TABLE_NAME + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TransportDbSchema.TransportTable.Cols.UUID + " TEXT, " +
                    TransportDbSchema.TransportTable.Cols.NAME + " TEXT, " +
                    TransportDbSchema.TransportTable.Cols.IMAGE_NAME + " TEXT, " +
                    TransportDbSchema.TransportTable.Cols.PROTECTION + " INTEGER, " +
                    TransportDbSchema.TransportTable.Cols.BAG_UUID + " TEXT, " +
                    TransportDbSchema.TransportTable.Cols.FUEL_QUANTITY + " INTEGER, " +
                    TransportDbSchema.TransportTable.Cols.SPEND_FUEL + " INTEGER, " +
                    TransportDbSchema.TransportTable.Cols.POWER + " INTEGER)";

    public static final String SQL_CREATE_TABLE_TEST =
            "CREATE TABLE " + "test" + "(" +
                    " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "ID" + " INTEGER)";


}
