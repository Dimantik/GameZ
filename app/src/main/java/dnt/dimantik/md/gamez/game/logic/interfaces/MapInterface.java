package dnt.dimantik.md.gamez.game.logic.interfaces;

import android.util.Log;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.clases.GameData;
import dnt.dimantik.md.gamez.game.logic.clases.Player;
import dnt.dimantik.md.gamez.game.logic.clases.map.Location;
import dnt.dimantik.md.gamez.game.logic.clases.map.Place;
import dnt.dimantik.md.gamez.game.logic.clases.map.Way;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Bag;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Resource;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Transport;

/**
 * Created by dimantik on 27.03.2018.
 */

public class MapInterface {

    private GameData mGameData;

    private Map<Integer, Location> mLocationMap;
    private Map<Integer, Place> mPlaceMap;
    private List<Way> mWayPlaceList;
    private List<Way> mWayLocationList;

    public MapInterface(GameData gameData, Map<Integer, Location> locationMap, Map<Integer, Place> placeMap, List<Way> wayList){
        mGameData = gameData;
        mLocationMap = locationMap;
        mPlaceMap = placeMap;
        mWayLocationList = new LinkedList<>();
        mWayPlaceList = new LinkedList<>();
        for (Way way : wayList){
            if (way.getPointTwoId() < 10){
                mWayLocationList.add(way);
            } else {
                mWayPlaceList.add(way);
            }
        }
    }

    // ИНТЕРФЕЙС РАБОТЫ С РЕСУРСАМИ НА ЛОКАЦИЯХ

    public void addResourceToCurrentPlace(Resource resource, String flag){
        if (mGameData.getCurrentPlace().isPossibleToPut(resource, flag)){
            resource.deleteOwner();
            if (resource instanceof Bag && ((Bag)resource).getResourceList().size() != 0){
                Bag bag = (Bag) resource;
                List<Resource> resourceList = bag.getResourceList();
                bag.setResourceUUIDList(new HashSet<UUID>());
                bag.setResourceList(new LinkedList<Resource>());
                bag.update();
                addResourceListToCurrentPlace(resourceList, flag);
            } else if (resource instanceof Transport && ((Transport)resource).getBag().getResourceList().size() != 0){
                Bag bag = ((Transport) resource).getBag();
                List<Resource> resourceList = bag.getResourceList();
                bag.setResourceUUIDList(new HashSet<UUID>());
                bag.setResourceList(new LinkedList<Resource>());
                bag.update();
                addResourceListToCurrentPlace(resourceList, flag);
            }
            resource.addOwner(mGameData.getCurrentPlace(), flag);
        }
    }

    public void addResourceListToCurrentPlace(List<Resource> resourceList, String flag){
        for (Resource resource : resourceList){
            addResourceToCurrentPlace(resource, flag);
        }
    }

    public List<Resource> getResourceListInCurrentPlace(){
        return mGameData.getCurrentPlace().getResourceList();
    }

    public List<Resource> getFindResourceListInCurrentPlace(){
        List<Resource> resourceList = new LinkedList<>(mGameData.getCurrentPlace().getResourceList());
//        Iterator<Resource> iterator = resourceList.iterator();
//        while (iterator.hasNext()){
//            if (iterator.next() instanceof Transport){
//                iterator.remove();
//            }
//        }
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














    // ИНТЕРФЕЙС ПЕРЕМЕЩЕНИЯ ПО КАРТЕ

    public int getRequiredTimeForTravelFromPlace(Place pointOne, Place pointTwo, Transport transport){
        int required = 0;
        for (Way way : mWayPlaceList){
            if ((way.getPointOneId() == pointOne.getId() && way.getPointTwoId() == pointTwo.getId())
                    || (way.getPointOneId() == pointTwo.getId() && way.getPointTwoId() == pointOne.getId())){
                required = way.getTravelTime();
            }
        }

        return reduceFromTransport(required, transport);
    }

    public void travelForPlace(Place pointFrom, Place pointTo, Transport transport){
        mGameData.upTime(getRequiredTimeForTravelFromPlace(pointFrom, pointTo, transport));
        mGameData.setCurrentPlace(pointTo, true);
        mGameData.update();
    }

    public int getRequiredTimeForTravelFromLocation(Location pointOne, Location pointTwo, Transport transport){
        int required = 0;
        for (Way way : mWayLocationList){
            if ((way.getPointOneId() == pointOne.getId() && way.getPointTwoId() == pointTwo.getId())
                    || (way.getPointOneId() == pointTwo.getId() && way.getPointTwoId() == pointOne.getId())){
                required = way.getTravelTime();
            }
        }

        return reduceFromTransport(required, transport);
    }

    private int reduceFromTransport(int required, Transport transport){
        if (transport != null){
            if (required*transport.getSpendFuel() <= transport.getFuelQuantity()){
                required = (int)(transport.getPower() * required);
            } else {
                int enoughFor = (int)(((double)transport.getFuelQuantity())/transport.getSpendFuel());
                required = (required - enoughFor) + (int)(enoughFor*transport.getPower());
            }
        }
        return required;
    }

    public void travelForLocation(Location pointFrom, Location pointTo, Transport transport){
        mGameData.upTime(getRequiredTimeForTravelFromLocation(pointFrom, pointTo, transport));
        mGameData.setCurrentLocation(pointTo, true);
        mGameData.setCurrentPlace(getPlaceForId(Integer.parseInt(pointTo.getId() + "1")), true);
        mGameData.update();
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

    public List<Way> getWayPlaceList() {
        return mWayPlaceList;
    }

    public void setWayPlaceList(List<Way> wayPlaceList) {
        mWayPlaceList = wayPlaceList;
    }

    public List<Way> getWayLocationList() {
        return mWayLocationList;
    }

    public void setWayLocationList(List<Way> wayLocationList) {
        mWayLocationList = wayLocationList;
    }

    public void addToBd(){
        for (Location location : mLocationMap.values()){
            location.add();
        }
        for (Place place : mPlaceMap.values()){
            place.add();
        }
        for (Way way : mWayLocationList){
            way.add();
        }
        for (Way way : mWayPlaceList){
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
