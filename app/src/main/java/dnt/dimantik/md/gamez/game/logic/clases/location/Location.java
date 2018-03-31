package dnt.dimantik.md.gamez.game.logic.clases.location;

import java.util.LinkedList;
import java.util.List;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.BDInterface;

/**
 * Created by dimantik on 24.03.2018.
 */

public class Location implements BDInterface {

    private String mName;
    private int mId;
    private int mDangerousLevel;
    private String mInfo;
    private String mAssertDrawableName;

    private List<Way> mWayList;
    private List<Place> mPlaceList;
    private List<Integer> mPlaceIdList;

    private Location(){}

    public static Location createNewLocation(int id, String name, String info, String assertDrawableName){
        Location location = getBaseLocation(id, name, info, assertDrawableName);

        location.setPlaceIdList(new LinkedList<Integer>());
        location.setPlaceList(new LinkedList<Place>());

        return location;
    }

    public static Location getInstance(int id, String name, String info, String assertDrawableName, List<Integer> placeIdList){
        Location location = getBaseLocation(id, name, info, assertDrawableName);

        location.setPlaceIdList(placeIdList);
        location.setPlaceList(new LinkedList<Place>());

        return location;
    }

    private static Location getBaseLocation(int id, String name, String info, String assertDrawableName){
        Location location = new Location();

        location.setId(id);
        location.setName(name);
        location.setWayList(new LinkedList<Way>());
        location.setInfo(info);
        location.setAssertDrawableName(assertDrawableName);

        return location;
    }

    public void addPlace(Place place, boolean setId){
        if (place == null){
            return;
        }
        mPlaceList.add(place);
        if (setId){
            mPlaceIdList.add(place.getId());
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getDangerousLevel() {
        return mDangerousLevel;
    }

    public void setDangerousLevel(int dangerousLevel) {
        mDangerousLevel = dangerousLevel;
    }

    public List<Place> getPlaceList() {
        return mPlaceList;
    }

    public void setPlaceList(List<Place> placeList) {
        mPlaceList = placeList;
        List<Integer> placeIdList = new LinkedList<>();
        for (Place place : placeList){
            placeIdList.add(place.getId());
        }
        setPlaceIdList(placeIdList);
    }

    public List<Integer> getPlaceIdList() {
        return mPlaceIdList;
    }

    public void setPlaceIdList(List<Integer> placeIdList) {
        mPlaceIdList = placeIdList;
    }

    public List<Way> getWayList() {
        return mWayList;
    }

    public void setWayList(List<Way> wayList) {
        mWayList = wayList;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        mInfo = info;
    }

    public int getNewIdFromNewPlace(){
        String id = mId + "" + (mPlaceList.size() + 1);
        return Integer.parseInt(id);
    }

    public boolean isOwnedPlace(int id){
        if ((id/10) == mId){
            return true;
        } else {
            return false;
        }
    }

    public String getAssertDrawableName() {
        return mAssertDrawableName;
    }

    public void setAssertDrawableName(String assertDrawableName) {
        mAssertDrawableName = assertDrawableName;
    }

    @Override
    public void update() {
        BDHelper.updateLocation(this);
    }

    @Override
    public void delete() {
        BDHelper.deleteLocation(this);
    }

    @Override
    public void add() {
        BDHelper.addLocation(this);
    }
}
