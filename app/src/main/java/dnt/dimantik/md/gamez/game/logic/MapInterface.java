package dnt.dimantik.md.gamez.game.logic;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.controllers.place.fragment.MapFragment;
import dnt.dimantik.md.gamez.game.logic.clases.GameData;
import dnt.dimantik.md.gamez.game.logic.clases.Player;
import dnt.dimantik.md.gamez.game.logic.clases.location.Location;
import dnt.dimantik.md.gamez.game.logic.clases.location.Place;
import dnt.dimantik.md.gamez.game.logic.clases.location.Way;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Liquid;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Transport;

/**
 * Created by dimantik on 27.03.2018.
 */

public class MapInterface {

    private GameData mGameData;

    private Map<Integer, Location> mLocationMap;
    private Map<Integer, Place> mPlaceMap;
    private List<Way> mWayList;

    MapInterface(GameData gameData, Map<Integer, Location> locationMap, Map<Integer, Place> placeMap, List<Way> wayList){
        mGameData = gameData;
        mLocationMap = locationMap;
        mPlaceMap = placeMap;
        mWayList = wayList;
    }

    // ИНТЕРФЕЙС РАБОТЫ С РЕСУРСАМИ НА ЛОКАЦИЯХ

    public void addResourceToCurrentPlace(Resource resource){
        resource.addOwner(mGameData.getCurrentPlace());
    }

    public void addResourceListToCurrentPlace(List<Resource> resourceList){
        for (Resource resource : resourceList){
            addResourceToCurrentPlace(resource);
        }
    }

    public List<Resource> getResourceListInCurrentPlace(){
        return mGameData.getCurrentPlace().getResourceList();
    }

    public List<Resource> getFindResourceListInCurrentPlace(){
        List<Resource> resourceList = new LinkedList<>(mGameData.getCurrentPlace().getResourceList());
        Iterator<Resource> iterator = resourceList.iterator();
        while (iterator.hasNext()){
            if (iterator.next() instanceof Transport){
                iterator.remove();
            }
        }
        return resourceList;
    }

    public List<Resource> getFindTransportListInCurrentPlace(){
        List<Resource> transportLit = new LinkedList<>(mGameData.getCurrentPlace().getResourceList());
        Iterator<Resource> iterator = transportLit.iterator();
        while (iterator.hasNext()){
            if (!(iterator.next() instanceof Transport)){
                iterator.remove();
            }
        }
        return transportLit;
    }

















    public Collection<Location> getAllLocation(){
        return mLocationMap.values();
    }

    public Collection<Place> getAllPlace(){
        return mPlaceMap.values();
    }

    public Place getPlaceForId(int id){
        return mPlaceMap.get(id);
    }

    public Location getLocationForId(int id){
        return mLocationMap.get(id);
    }

    public int getCurrentDay(){
        return mGameData.getCurrentDay();
    }

    public int getLastDay(){
        return mGameData.getLastDay();
    }

    public int getAllMinutes(){
        return mGameData.getAllMinutes();
    }

    public void deleteResourceFromCurrentPlace(Resource resource){
        mGameData.getCurrentPlace().deleteResource(resource);
    }

    public void addResourceInCurrentPlace(Resource resource){
        mGameData.getCurrentPlace().putResource(resource);
    }

    public GameData getGameData() {
        return mGameData;
    }

    public void setGameData(GameData gameData) {
        mGameData = gameData;
    }

    public Place getCurrentPlace(){
        return mGameData.getCurrentPlace();
    }

    public Location getCurrentLocation(){
        return mGameData.getCurrentLocation();
    }

    public void updateCurrentPlace(){
        mGameData.getCurrentPlace().update();
    }

    public Place getPlaceForIndex(int index){
        //return getPlaceList().get(index);
        return null;
    }

    public Location getLocationForIndex(int index){
        //return getLocationList().get(index);
        return null;
    }

    public Map<Integer, Location> getLocationMap() {
        return mLocationMap;
    }

    public void setLocationMap(Map<Integer, Location> locationMap) {
        mLocationMap = locationMap;
    }

    public Map<Integer, Place> getPlaceMap() {
        return mPlaceMap;
    }

    public void setPlaceMap(Map<Integer, Place> placeMap) {
        mPlaceMap = placeMap;
    }

    public List<Way> getWayList() {
        return mWayList;
    }

    public void setWayList(List<Way> wayList) {
        mWayList = wayList;
    }

    public void addToBd(){
        for (Location location : mLocationMap.values()){
            location.add();
        }
        for (Place place : mPlaceMap.values()){
            place.add();
        }
        for (Way way : mWayList){
            way.add();
        }
        mGameData.add();
    }

    public void update(){
        for (Location location : mLocationMap.values()){
            location.update();
        }
        for (Place place : mPlaceMap.values()){
            place.update();
        }
        mGameData.update();
    }


    public void updateGameData(){
        mGameData.update();
    }

}
