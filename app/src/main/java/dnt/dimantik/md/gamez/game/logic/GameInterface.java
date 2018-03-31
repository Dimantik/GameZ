package dnt.dimantik.md.gamez.game.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.clases.ResourceOwner;
import dnt.dimantik.md.gamez.game.logic.clases.Player;
import dnt.dimantik.md.gamez.game.logic.clases.location.Location;
import dnt.dimantik.md.gamez.game.logic.clases.location.Place;
import dnt.dimantik.md.gamez.game.logic.clases.location.Way;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Bag;
import dnt.dimantik.md.gamez.game.logic.clases.resource.BodyClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Clothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Drug;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FeetClothes;
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

    public PlayerInterface getPlayerInterface() {
        return mPlayerInterface;
    }

    public void setPlayerInterface(PlayerInterface playerInterface) {
        mPlayerInterface = playerInterface;
    }

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

    public boolean addResourceToPlayerBag(Resource resource){
        return mPlayerInterface.addResourceToPlayerBag(resource);
    }

    public boolean addResourceListToPlayerBag(List<Resource> resourceList){
        return mPlayerInterface.addResourceListToPlayerBag(resourceList);
    }

    public boolean addResourceToPlayerTransportBag(Resource resource){
        return mPlayerInterface.addResourceToPlayerTransportBag(resource);
    }

    public boolean addResourceListToPlayerTransportBag(List<Resource> resourceList){
        return mPlayerInterface.addResourceListToPlayerTransportBag(resourceList);
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
        return mPlayerInterface.getCurrentHeadClothes();
    }

    public BodyClothes getPlayerBodyClothes(){
        return mPlayerInterface.getCurrentBodyClothes();
    }

    public LegsClothes getPlayerLegsClothes(){
        return mPlayerInterface.getCurrentLegsClothes();
    }

    public FeetClothes getPlayerFeetClothes(){
        return mPlayerInterface.getCurrentFeetClothes();
    }

    public Transport getPlayerTransport(){
        return mPlayerInterface.getCurrentTransport();
    }

    public Weapon getPlayerFirstWeapon(){
        return mPlayerInterface.getCurrentFirstWeapon();
    }

    public Weapon getPlayerSecondWeapon(){
        return mPlayerInterface.getCurrentSecondWeapon();
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
        mPlayerInterface.setCurrentTransport(transport);
    }

    public void setPlayerBag(Bag bag){
        mPlayerInterface.setCurrentBag(bag);
    }









    // MAP_INTERFACE

    public void addResourceToCurrentPlace(Resource resource){
        mMapInterface.addResourceToCurrentPlace(resource);
    }

    public void addResourceListToCurrentPlace(List<Resource> resourceList){
        mMapInterface.addResourceListToCurrentPlace(resourceList);
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


    public List<Resource> findResourceInTheCurrentPlace(){
        List<Resource> resourceList = mMapInterface.getCurrentPlace().getResourceList();
        return resourceList;
    }

    public List<Resource> getPlayerResourceList(){
        return mPlayerInterface.getPlayerResourceListInBag();
    }

    public void addResourceListInCurrentPlace(List<Resource> resourceList){
        for (Resource resource : resourceList){
            resource.deleteOwner();
            resource.addOwner(mMapInterface.getCurrentPlace());
        }
    }

    public boolean putResourceListInPlayerBag(List<Resource> resourceList){
        if (mPlayerInterface.getPlayer().getCurrentBag().getFreeSpace() < resourceList.size()){
            return false;
        } else {
            for (Resource resource : resourceList){
                resource.deleteOwner();
                resource.addOwner(mPlayerInterface.getPlayer().getCurrentBag());
            }
            return true;
        }
    }

    public boolean putResourceInPlayerBag(Resource resource){
        if (mPlayerInterface.getPlayer().getCurrentBag().getFreeSpace() < 1){
            return false;
        } else {
            resource.deleteOwner();
            resource.addOwner(mPlayerInterface.getPlayer().getCurrentBag());
            return true;
        }
    }


    public void addResourceInCurrentPlace(Resource resource){
        resource.deleteOwner();
        resource.addOwner(mMapInterface.getCurrentPlace());
    }

    public void changeCurrentHeadClothes(HeadClothes headClothes){
       ResourceOwner owner = headClothes.deleteOwner();
       HeadClothes oldHeadClothes = mPlayerInterface.getPlayer().getCurrentHeadClothes();
       oldHeadClothes.deletePlayerOwner();
       oldHeadClothes.addOwner(owner);
       mPlayerInterface.setCurrentHeadClothes(headClothes);
    }

    public void changeCurrentBodyClothes(BodyClothes bodyClothes){
        ResourceOwner owner = bodyClothes.deleteOwner();
        BodyClothes oldBodyClothes = mPlayerInterface.getPlayer().getCurrentBodyClothes();
        oldBodyClothes.deletePlayerOwner();
        oldBodyClothes.addOwner(owner);
        mPlayerInterface.setCurrentBodyClothes(bodyClothes);
    }

    public void changeCurrentLegsClothes(LegsClothes legsClothes){
        ResourceOwner owner = legsClothes.deleteOwner();
        LegsClothes oldLegsClothes = mPlayerInterface.getPlayer().getCurrentLegsClothes();
        oldLegsClothes.deletePlayerOwner();
        oldLegsClothes.addOwner(owner);
        mPlayerInterface.setCurrentLegsClothes(legsClothes);
    }

    public void changeCurrentFeetClothes(FeetClothes feetClothes){
        ResourceOwner owner = feetClothes.deleteOwner();
        FeetClothes oldFeetClothes = mPlayerInterface.getPlayer().getCurrentFeetClothes();
        oldFeetClothes.deletePlayerOwner();
        oldFeetClothes.addOwner(owner);
        mPlayerInterface.setCurrentFeetClothes(feetClothes);
    }

    public void changeCurrentFirstWeapon(Weapon weapon){
        ResourceOwner owner = weapon.deleteOwner();
        Weapon oldWeapon = mPlayerInterface.getPlayer().getCurrentFirstWeapon();
        oldWeapon.deletePlayerOwner();
        oldWeapon.addOwner(owner);
        mPlayerInterface.setCurrentFirstWeapon(weapon);
    }

    public void changeCurrentSecondWeapon(Weapon weapon, boolean itIsInBag){
        ResourceOwner owner = weapon.deleteOwner();
        Weapon oldWeapon = mPlayerInterface.getPlayer().getCurrentSecondWeapon();
        oldWeapon.deletePlayerOwner();
        oldWeapon.addOwner(owner);
        mPlayerInterface.setCurrentSecondWeapon(weapon);
    }

    public void changeCurrentTransport(Transport transport){
        ResourceOwner owner = transport.deleteOwner();
        Transport oldTransport = mPlayerInterface.getPlayer().getCurrentTransport();
        oldTransport.deletePlayerOwner();
        oldTransport.addOwner(owner);
        mPlayerInterface.setCurrentTransport(transport);
    }

    public void swapWeaponFromSlot(){
        Weapon weapon = mPlayerInterface.getPlayer().getCurrentFirstWeapon();
        mPlayerInterface.setCurrentFirstWeapon(mPlayerInterface.getPlayer().getCurrentSecondWeapon());
        mPlayerInterface.setCurrentSecondWeapon(weapon);
    }



    public boolean changePlayerCurrentBag(Bag bag){
        if (bag == null){
            return false;
        }

        if (bag.getCapacity() < mPlayerInterface.getPlayer().getCurrentBag().getEngagedSpace()){
            List<Resource> excess = new LinkedList<>();
            for (Resource resource : mPlayerInterface.getPlayer().getCurrentBag().getResourceList()){
                if (bag.getFreeSpace() != 0){
                    bag.putResource(resource);
                } else {
                    excess.add(resource);
                }
            }
            addResourceListInCurrentPlace(excess);
            mPlayerInterface.getPlayer().getCurrentBag().setResourceList(new ArrayList<Resource>());
            mMapInterface.getCurrentPlace().putResource(mPlayerInterface.getPlayer().getCurrentBag());
            mMapInterface.getCurrentPlace().deleteResource(bag);
            bag.update();
            mPlayerInterface.getPlayer().getCurrentBag().update();
            mMapInterface.getCurrentPlace().update();
            mPlayerInterface.getPlayer().setCurrentBag(bag, true);
            mPlayerInterface.getPlayer().update();

            return false;
        } else {
            bag.setResourceList(mPlayerInterface.getPlayer().getCurrentBag().getResourceList());
            mPlayerInterface.getPlayer().getCurrentBag().setResourceList(new LinkedList<Resource>());
            mMapInterface.getCurrentPlace().putResource(mPlayerInterface.getPlayer().getCurrentBag());
            mMapInterface.getCurrentPlace().deleteResource(bag);
            mMapInterface.getCurrentPlace().update();
            bag.update();
            mPlayerInterface.getPlayer().getCurrentBag().update();
            mPlayerInterface.getPlayer().setCurrentBag(bag, true);
            mPlayerInterface.getPlayer().update();

            return true;
        }
    }

//    public boolean addResourceListInBagFromTransportBag(Transport transport, List<Resource> resourceList){
//        if (mGame.getPlayer().getCurrentBag().getFreeSpace() < resourceList.size()){
//            return false;
//        } else {
//            transport.getBag().deleteResourceList(resourceList, true);
//            mGame.getPlayer().getCurrentBag().putInBagList(resourceList, true);
//            transport.getBag().update();
//            mGame.getPlayer().getCurrentBag().update();
//            return true;
//        }
//    }
//
//    public void addResourceListInTransportBagFromBag(Transport transport, List<Resource> resourceList){
//        mGame.getPlayer().getCurrentBag().deleteResourceList(resourceList, true);
//        transport.getBag().putInBagList(resourceList, true);
//        transport.getBag().update();
//        mGame.getPlayer().getCurrentBag().update();
//    }

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

    private void addToBdAllGameValues(){
        mPlayerInterface.addToBd();
        mMapInterface.addToBd();
        mResourceInterface.addToBd();
    }

    public void update(){
        mPlayerInterface.update();
        mMapInterface.update();
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

    public Resource getResource(UUID uuid){
        return mResourceInterface.getResource(uuid);
    }

    public static final String BECAUSE_OF_FOOD = "Вы умерли от голода";
    public static final String BECAUSE_OF_LIQUID = "Вы умерли от жажды";
    public static final String BECAUSE_OF_DRUG = "Вы умерли из-за очень плохого здоровья";
    public static final String BECAUSE_KILLED_ZOMBIES = "Вас убили зомби";
    public static final String BECAUSE_OTHER_SURVIVOR = "Вас убили другие выжившие";

}
