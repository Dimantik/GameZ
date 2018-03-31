package dnt.dimantik.md.gamez.game.logic.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.lab.BagLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.BodyClothesLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.CartridgesLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.DrugLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.FeetClothesLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.FireArmsLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.FoodLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.GameDataLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.HeadClothesLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.LegsClothesLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.LiquidLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.LocationLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.PlaceLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.PlayerLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.SteelArmsLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.TransportLab;
import dnt.dimantik.md.gamez.game.logic.bd.lab.WayLab;
import dnt.dimantik.md.gamez.game.logic.clases.GameData;
import dnt.dimantik.md.gamez.game.logic.clases.Player;
import dnt.dimantik.md.gamez.game.logic.clases.location.Location;
import dnt.dimantik.md.gamez.game.logic.clases.location.Place;
import dnt.dimantik.md.gamez.game.logic.clases.location.Way;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Bag;
import dnt.dimantik.md.gamez.game.logic.clases.resource.BodyClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Cartridges;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Drug;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FeetClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FireArms;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Food;
import dnt.dimantik.md.gamez.game.logic.clases.resource.HeadClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.LegsClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Liquid;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;
import dnt.dimantik.md.gamez.game.logic.clases.resource.SteelArms;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Transport;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Weapon;

/**
 * Created by dimantik on 3/7/18.
 */

public class BDHelper {

    private static boolean sIsSetup;

    private static SQLiteDatabase sDatabase;

    private static GameDataLab sGameDataLab;
    private static PlayerLab sPlayerLab;
    private static BagLab sBagLab;
    private static BodyClothesLab sBodyClothesLab;
    private static HeadClothesLab sHeadClothesLab;
    private static LegsClothesLab sLegsClothesLab;
    private static FeetClothesLab sFeetClothesLab;
    private static DrugLab sDrugLab;
    private static FoodLab sFoodLab;
    private static LiquidLab sLiquidLab;
    private static FireArmsLab sFireArmsLab;
    private static CartridgesLab sCartridgesLab;
    private static SteelArmsLab sSteelArmsLab;
    private static TransportLab sTransportLab;
    private static PlaceLab sPlaceLab;
    private static LocationLab sLocationLab;
    private static WayLab sWayLab;

    public static void setup(Context context){
        if (!sIsSetup){
            sDatabase = new AppBaseHelper(context).getWritableDatabase();

            sGameDataLab = GameDataLab.get(sDatabase);
            sPlayerLab = PlayerLab.get(sDatabase);
            sBagLab = BagLab.get(sDatabase);
            sBodyClothesLab = BodyClothesLab.get(sDatabase);
            sHeadClothesLab = HeadClothesLab.get(sDatabase);
            sLegsClothesLab = LegsClothesLab.get(sDatabase);
            sFeetClothesLab = FeetClothesLab.get(sDatabase);
            sDrugLab = DrugLab.get(sDatabase);
            sFoodLab = FoodLab.get(sDatabase);
            sLiquidLab = LiquidLab.get(sDatabase);
            sFireArmsLab = FireArmsLab.get(sDatabase);
            sCartridgesLab = CartridgesLab.get(sDatabase);
            sSteelArmsLab = SteelArmsLab.get(sDatabase);
            sTransportLab = TransportLab.get(sDatabase);
            sPlaceLab = PlaceLab.get(sDatabase);
            sLocationLab = LocationLab.get(sDatabase);
            sWayLab = WayLab.get(sDatabase);
        }
    }

    public static boolean checkIsExistenceGame(){
        if (sGameDataLab.getGameDataList().size() != 0){
            return true;
        }
        return false;
    }

    public static GameData getGameData(){
        return sGameDataLab.getGameDataList().get(0);
    }

    public static Player getPlayer(){
        return sPlayerLab.getAllPLayer().get(0);
    }

    public static Map<UUID, Resource> getResourceMap(){
        Map<UUID, Resource> resourceMap = new HashMap<>();

        resourceMap.putAll(getFoodList());
        resourceMap.putAll(getDrugList());
        resourceMap.putAll(getLiquidList());
        resourceMap.putAll(getBodyClothesList());
        resourceMap.putAll(getHeadClothesList());
        resourceMap.putAll(getLegsClothesList());
        resourceMap.putAll(getFeetClothesList());
        resourceMap.putAll(getTransportList());
        Map<UUID, Cartridges> map = getCartridgesList();
        Log.i("TAG", "КОЛЛИЧЕСТВО ПУЛЕЙ - " + map.size());
        resourceMap.putAll(map);
        resourceMap.putAll(getBagList());
        resourceMap.putAll(getSteelArmsList());
        resourceMap.putAll(getFireArmsList());

        return resourceMap;
    }

    public static Map<UUID, Food> getFoodList(){
        return sFoodLab.getFoods();
    }

    public static Map<UUID, Liquid> getLiquidList(){
        return sLiquidLab.getLiquids();
    }

    public static Map<UUID, Drug> getDrugList(){
        return sDrugLab.getDrugs();
    }

    public static Map<UUID, HeadClothes> getHeadClothesList(){
        return sHeadClothesLab.getAllHeadClothes();
    }

    public static Map<UUID, BodyClothes> getBodyClothesList(){
        return sBodyClothesLab.getAllBodyClothes();
    }

    public static Map<UUID, LegsClothes> getLegsClothesList(){
        return sLegsClothesLab.getAllLegsClothes();
    }

    public static Map<UUID, FeetClothes> getFeetClothesList(){
        return sFeetClothesLab.getAllFeetClothes();
    }

    public static Map<UUID, SteelArms> getSteelArmsList(){
        return sSteelArmsLab.getAllSteelArms();
    }

    public static Map<UUID, FireArms> getFireArmsList(){
        return sFireArmsLab.getAllFireArms();
    }

    public static Map<UUID, Bag> getBagList(){
        return sBagLab.getAllBag();
    }

    public static Map<UUID, Transport> getTransportList(){
        return sTransportLab.getAllTransport();
    }

    public static Map<UUID, Cartridges> getCartridgesList(){
        return sCartridgesLab.getAllCartridges();
    }

    public static List<Way> getWayList(){
        return sWayLab.getAllWay();
    }

    public static Map<Integer, Location> getLocationList(){
        return sLocationLab.getAllLocation();
    }

    public static Map<Integer, Place> getPlaceList(){
        return sPlaceLab.getAllPlace();
    }

    public static void deleteGameData(GameData gameData){
        sGameDataLab.deleteGameData(gameData);
    }


    public static void addGame(GameData gameData){
        sGameDataLab.addGameData(gameData);
    }

    public static void updateGameData(GameData gameData){
        sGameDataLab.updateGameData(gameData);
    }

    public static void addPlayer(Player player){
        sPlayerLab.addPLayer(player);
    }

    public static void addBag(Bag bag){
        sBagLab.addBag(bag);
    }

    public static void addBodyClothes(BodyClothes bodyClothes){
        sBodyClothesLab.addBodyClothes(bodyClothes);
    }

    public static void addHeadClothes(HeadClothes headClothes){
        sHeadClothesLab.addHeadClothes(headClothes);
    }

    public static void addLegsClothes(LegsClothes legsClothes){
        sLegsClothesLab.addLegsClothes(legsClothes);
    }

    public static void addFeetClothes(FeetClothes feetClothes){
        sFeetClothesLab.addFeetClothes(feetClothes);
    }

    public static void addFood(Food food){
        sFoodLab.addFood(food);
    }

    public static void addLiquid(Liquid liquid){
        sLiquidLab.addLiquid(liquid);
    }

    public static void addDrug(Drug drug){
        sDrugLab.addDrug(drug);
    }

    public static void addFireArms(FireArms fireArms){
        sFireArmsLab.addFireArms(fireArms);
    }

    public static void addSteelArms(SteelArms steelArms){
        sSteelArmsLab.addSteelArms(steelArms);
    }


    public static void addTransport(Transport transport){
        sTransportLab.addTransport(transport);
    }

    public static void addCartridges(Cartridges cartridges){
        sCartridgesLab.addCartridges(cartridges);
    }

    public static void addPlace(Place place){
        sPlaceLab.addPlace(place);
    }

    public static void addLocation(Location location){
        sLocationLab.addLocation(location);
    }

    public static void addWay(Way way){
        sWayLab.addWay(way);
    }

    public static void updatePlayer(Player player){
        sPlayerLab.updatePlayer(player);
    }

    public static void updateGame(GameData gameData){
        sGameDataLab.updateGameData(gameData);
    }

    public static void updateBag(Bag bag){
        sBagLab.updateBag(bag);
    }

    public static void updateBodyClothes(BodyClothes bodyClothes){
        sBodyClothesLab.updateBodyClothes(bodyClothes);
    }

    public static void updateHeadClothes(HeadClothes headClothes){
        sHeadClothesLab.updateHeadClothes(headClothes);
    }

    public static void updateLegsClothes(LegsClothes legsClothes){
        sLegsClothesLab.updateLegsClothes(legsClothes);
    }

    public static void updateFeetClothes(FeetClothes feetClothes){
        sFeetClothesLab.updateFeetClothes(feetClothes);
    }

    public static void updateFood(Food food){
        sFoodLab.updateFood(food);
    }

    public static void updateDrug(Drug drug){
        sDrugLab.updateDrug(drug);
    }

    public static void updateLiquid(Liquid liquid){
        sLiquidLab.updateLiquid(liquid);
    }

    public static void updateFireArms(FireArms fireArms){
        sFireArmsLab.updateFireArms(fireArms);
    }

    public static void updateSteelArms(SteelArms steelArms){
        sSteelArmsLab.updateSteelArms(steelArms);
    }

    public static void updatePlace(Place place){
        sPlaceLab.updatePlace(place);
    }

    public static void updateLocation(Location location){
        sLocationLab.updateLocation(location);
    }

    public static void updateTransport(Transport transport){
        sTransportLab.updateTRansport(transport);
    }

    public static void deleteBag(Bag bag){
        sBagLab.deleteBag(bag);
    }

    public static void deleteHeadClothes(HeadClothes headClothes){
        sHeadClothesLab.deleteHeadClothes(headClothes);
    }

    public static void deleteBodyClothes(BodyClothes bodyClothes){
        sBodyClothesLab.deleteBodyClothes(bodyClothes);
    }

    public static void deleteLegsClothes(LegsClothes legsClothes){
        sLegsClothesLab.deleteLegsClothes(legsClothes);
    }

    public static void deleteFeetClothes(FeetClothes feetClothes){
        sFeetClothesLab.deleteFeetClothes(feetClothes);
    }

    public static void deleteFood(Food food){
        sFoodLab.deleteFood(food);
    }

    public static void deleteDrug(Drug drug){
        sDrugLab.deleteDrug(drug);
    }

    public static void deleteLiquid(Liquid liquid){
        sLiquidLab.deleteLiquid(liquid);
    }

    public static void deleteFireArms(FireArms fireArms){
        sFireArmsLab.deleteFireArms(fireArms);
    }

    public static void deleteSteelArms(SteelArms steelArms){
        sSteelArmsLab.deleteSteelArms(steelArms);
    }

    public static void deleteTransport(Transport transport){
        sTransportLab.deleteTransport(transport);
    }

    public static void deletePlayer(Player player){
        sPlayerLab.deletePlayer(player);
    }

    public static void deleteGame(GameData gameData){
        sGameDataLab.deleteGameData(gameData);
    }

    public static void deletePlace(Place place){
        sPlaceLab.deletePlace(place);
    }

    public static void deleteLocation(Location location){
        sLocationLab.deleteLocation(location);
    }

    public static void updateCartridges(Cartridges cartridges){
        sCartridgesLab.updateCartridges(cartridges);
    }

    public static void deleteCartridges(Cartridges cartridges){
        sCartridgesLab.deleteCartridges(cartridges);
    }

    public static void deleteGame(){
        sPlaceLab.deleteAllPlaces();
        sLocationLab.deleteAllLocation();
        sPlayerLab.deleteAllPlayers();
        sGameDataLab.deleteGameDataList();
        sFoodLab.deleteAllFoods();
        sDrugLab.deleteAllDrugs();
        sLiquidLab.deleteAllLiquids();
        sHeadClothesLab.deleteAllHeadClothes();
        sBodyClothesLab.deleteAllBodyClothes();
        sLegsClothesLab.deleteAllLegsClothes();
        sFeetClothesLab.deleteAllFeetClothes();
        sBagLab.deleteAllBags();
        sTransportLab.deleteAllTransports();
        sWayLab.deleteAllWay();
        sFireArmsLab.deleteAllFireArms();
        sSteelArmsLab.deleteAllSteelArms();
        sCartridgesLab.deleteAllCartridges();
    }
}
