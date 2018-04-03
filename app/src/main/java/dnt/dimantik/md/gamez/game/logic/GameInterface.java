package dnt.dimantik.md.gamez.game.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.game.logic.clases.Player;
import dnt.dimantik.md.gamez.game.logic.clases.location.Location;
import dnt.dimantik.md.gamez.game.logic.clases.location.Place;
import dnt.dimantik.md.gamez.game.logic.clases.location.Way;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Bag;
import dnt.dimantik.md.gamez.game.logic.clases.resource.BodyClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Clothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Drug;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FeetClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FireArms;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Food;
import dnt.dimantik.md.gamez.game.logic.clases.resource.HeadClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.ImportantResource;
import dnt.dimantik.md.gamez.game.logic.clases.resource.LegsClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Liquid;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Transport;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Weapon;

/**
 * Created by dimantik on 12.03.2018.
 */

public class GameInterface {

    private boolean mIsInitialize;

    private PlayerInterface mPlayerInterface;
    private MapInterface mMapInterface;
    private ResourceInterface mResourceInterface;

    private GameInterface(){}

    public static GameInterface createNewGame(String playerName){
        GameInterface gameInterface = new GameInterface();
        gameInterface.initializeNewGame(playerName);

        return gameInterface;
    }

    private void initializeNewGame(String playerName){
        if (mIsInitialize){
            return;
        }
        GameCreator.createNewGame(this, playerName);
        addToBdAllGameValues();
    }

    public static GameInterface continueGame(){
        GameInterface gameInterface = new GameInterface();
        gameInterface.initializeContinueGame();
        return gameInterface;
    }

    private void initializeContinueGame(){
        if (mIsInitialize){
            return;
        }
        GameCreator.continueGame(this);
    }

    // PLAYER_INTERFACE

    public Player getPlayer(){
        return mPlayerInterface.getPlayer();
    }

    public int getPlayerHealth(){
        return mPlayerInterface.getPlayerHealth();
    }

    public int getPlayerSatiety(){
        return mPlayerInterface.getPlayerSatiety();
    }

    public int getPlayerThirst(){
        return mPlayerInterface.getPlayerThirst();
    }

    public int getPlayerEnergy(){
        return mPlayerInterface.getPlayerEnergy();
    }


    public void upImportantIndicator(ImportantResource importantResource){
        if (importantResource instanceof Food){
            upSatiety((Food) importantResource);
        } else if (importantResource instanceof Drug) {
            upHealth((Drug) importantResource);
        } else if (importantResource instanceof Liquid) {
            upThirst((Liquid) importantResource);
        }
    }

    private void upSatiety(Food food){
        mPlayerInterface.upSatiety(food);
    }

    private void upThirst(Liquid liquid){
        mPlayerInterface.upThirst(liquid);
    }

    private void upHealth(Drug drug){
        mPlayerInterface.upHealth(drug);
    }

    public boolean addResourceToPlayerBag(Resource resource, String flag){
        return mPlayerInterface.addResourceToPlayerBag(resource, flag);
    }

    public boolean addResourceListToPlayerBag(List<Resource> resourceList, String flag){
        return mPlayerInterface.addResourceListToPlayerBag(resourceList, flag);
    }

    public boolean addResourceToPlayerTransportBag(Resource resource, String flag){
        return mPlayerInterface.addResourceToPlayerTransportBag(resource, flag);
    }

    public boolean addResourceListToPlayerTransportBag(List<Resource> resourceList, String flag){
        return mPlayerInterface.addResourceListToPlayerTransportBag(resourceList, flag);
    }

    public List<Resource> getResourceListFromPlayerBag(){
        return mPlayerInterface.getResourceListFromPlayerBag();
    }

    public List<Resource> getResourceListFromPlayerTransportBag(){
        return mPlayerInterface.getResourceListFromPlayerTransportBag();
    }

    public Bag getPlayerBag(){
        return mPlayerInterface.getPlayerBag();
    }

    public HeadClothes getPlayerHeadClothes(){
        return mPlayerInterface.getPlayerHeadClothes();
    }

    public BodyClothes getPlayerBodyClothes(){
        return mPlayerInterface.getPlayerBodyClothes();
    }

    public LegsClothes getPlayerLegsClothes(){
        return mPlayerInterface.getPlayerLegsClothes();
    }

    public FeetClothes getPlayerFeetClothes(){
        return mPlayerInterface.getPlayerFeetClothes();
    }

    public Transport getPlayerTransport(){
        return mPlayerInterface.getPlayerTransport();
    }

    public Weapon getPlayerFirstWeapon(){
        return mPlayerInterface.getPlayerFirstWeapon();
    }

    public Weapon getPlayerSecondWeapon(){
        return mPlayerInterface.getPlayerSecondWeapon();
    }

    public void setPlayerEquipment(Clothes clothes){
        if (clothes instanceof HeadClothes){
            setPlayerHeadClothes((HeadClothes)clothes);
        } else if (clothes instanceof BodyClothes) {
            setPlayerBodyClothes((BodyClothes)clothes);
        } else if (clothes instanceof LegsClothes) {
            setPlayerLegsClothes((LegsClothes)clothes);
        } else if (clothes instanceof FeetClothes) {
            setPlayerFeetClothes((FeetClothes)clothes);
        }
    }

    public void setPlayerHeadClothes(HeadClothes headClothes){
        mPlayerInterface.setCurrentHeadClothes(headClothes);
    }

    public void setPlayerBodyClothes(BodyClothes bodyClothes){
        mPlayerInterface.setCurrentBodyClothes(bodyClothes);
    }

    public void setPlayerLegsClothes(LegsClothes legsClothes){
        mPlayerInterface.setCurrentLegsClothes(legsClothes);
    }

    public void setPlayerFeetClothes(FeetClothes feetClothes){
        mPlayerInterface.setCurrentFeetClothes(feetClothes);
    }

    public void setPlayerFirstWeapon(Weapon weapon){
        mPlayerInterface.setCurrentFirstWeapon(weapon);
    }

    public void setPlayerSecondWeapon(Weapon weapon){
        mPlayerInterface.setCurrentSecondWeapon(weapon);
    }

    public void setPlayerTransport(Transport transport){
        if (getPlayerTransport() != null){
            List<Resource> resourceList = exchangeResourceFromTwoBags(getPlayerTransport().getBag(), transport.getBag());
            addResourceListToCurrentPlace(resourceList, null);
        }
        mPlayerInterface.setCurrentTransport(transport);
    }

    public void setPlayerBag(Bag bag){
        List<Resource> resourceList = exchangeResourceFromTwoBags(getPlayerBag(), bag);
        mPlayerInterface.setCurrentBag(bag);
        addResourceListToCurrentPlace(resourceList, null);
    }

    public int getCartridgesQuantityInPlayerBagForType(FireArms.Type type){
        return mPlayerInterface.getCartridgesQuantityForSingleType(type);
    }




    // MAP_INTERFACE

    public void addResourceToCurrentPlace(Resource resource, String flag){
        mMapInterface.addResourceToCurrentPlace(resource, flag);
    }

    public void addResourceListToCurrentPlace(List<Resource> resourceList, String flag){
        mMapInterface.addResourceListToCurrentPlace(resourceList, flag);
    }

    public List<Resource> getResourceListInCurrentPlace(){
        return mMapInterface.getResourceListInCurrentPlace();
    }

    public List<Resource> getFindResourceListInCurrentPlace(){
        return mMapInterface.getFindResourceListInCurrentPlace();
    }

    public List<Resource> getFindTransportListInCurrentPlace(){
        return mMapInterface.getFindTransportListInCurrentPlace();
    }

    public Place getCurrentPlace(){
        return mMapInterface.getCurrentPlace();
    }

    public Location getCurrentLocation(){
        return mMapInterface.getCurrentLocation();
    }

    public Place getPlaceForIndex(int index){
        return mMapInterface.getPlaceForId(index);
    }

    public Location getLocationForIndex(int index){
        return mMapInterface.getLocationForIndex(index);
    }

    public Place getPlaceForId(int id){
        return mMapInterface.getPlaceForId(id);
    }

    public Location getLocationForId(int id){
        return mMapInterface.getLocationForId(id);
    }

    public int getCurrentDay(){
        return mMapInterface.getCurrentDay();
    }

    public int getLastDay(){
        return mMapInterface.getLastDay();
    }

    public int getAllMinutes(){
        return mMapInterface.getAllMinutes();
    }

    public Collection<Location> getLocationList(){
        return mMapInterface.getAllLocation();
    }

    public Collection<Place> getPlaceList(){
        return mMapInterface.getAllPlace();
    }

    public List<Way> getWayList(){
        return mMapInterface.getWayList();
    }




    // RESOURCE INTERFACE

    public Resource getResource(UUID uuid){
        return mResourceInterface.getResource(uuid);
    }

    public Map<UUID, Resource> getResourceMap(){
        return mResourceInterface.getResourceMap();
    }

    public void addResource(Resource resource){
        mResourceInterface.addResource(resource);
    }

    public void addAllResources(Map<UUID, Resource> resourceMap){
        mResourceInterface.addAllResources(resourceMap);
    }


    // OTHER INTERFACE

    private List<Resource> exchangeResourceFromTwoBags(Bag bagOne, Bag bagTwo){
        Iterator<Resource> iterator = bagOne.getResourceList().iterator();
        List<Resource> excessResourceList = new LinkedList<>();

        while (iterator.hasNext()){
            Resource resource = iterator.next();
            if (bagTwo.getFreeSpace() != 0) {
                resource.addOwner(bagTwo, null);
            } else {
                excessResourceList.add(resource);
            }
        }

        bagOne.setResourceList(new LinkedList<Resource>());
        bagOne.setResourceUUIDList(new HashSet<UUID>());

        return excessResourceList;
    }




    // GET-SET METHODS

    public void setResourceInterface(ResourceInterface resourceInterface){
        mResourceInterface = resourceInterface;
    }

    public ResourceInterface getResourceInterface(){
        return mResourceInterface;
    }

    public MapInterface getMapInterface() {
        return mMapInterface;
    }

    public void setMapInterface(MapInterface mapInterface) {
        mMapInterface = mapInterface;
    }

    public PlayerInterface getPlayerInterface() {
        return mPlayerInterface;
    }

    public void setPlayerInterface(PlayerInterface playerInterface) {
        mPlayerInterface = playerInterface;
    }




    // CRUD METHODS


    private void addToBdAllGameValues(){
        mPlayerInterface.addToBd();
        mMapInterface.addToBd();
        mResourceInterface.addToBd();
    }

    public void update(){
        mPlayerInterface.update();
        mMapInterface.update();
    }

    public static final String BECAUSE_OF_FOOD = "Вы умерли от голода";
    public static final String BECAUSE_OF_LIQUID = "Вы умерли от жажды";
    public static final String BECAUSE_OF_DRUG = "Вы умерли из-за очень плохого здоровья";
    public static final String BECAUSE_KILLED_ZOMBIES = "Вас убили зомби";
    public static final String BECAUSE_OTHER_SURVIVOR = "Вас убили другие выжившие";

}
