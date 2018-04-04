package dnt.dimantik.md.gamez.game.logic.clases;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.map.Location;
import dnt.dimantik.md.gamez.game.logic.clases.map.Place;

/**
 * Created by dimantik on 10/20/17.
 */

public class GameData implements BDInterface {

    private UUID mUUID;

    private int mCurrentDay;
    private int mLastDay;
    private int mAllMinutes;
    private int mCurrentHour;
    private int mCurrentMinutes;

    private Location mCurrentLocation;
    private Place mCurrentPlace;

    private int mCurrentLocationId;
    private int mCurrentPlaceId;

    public GameData(UUID uuid){
        mUUID = uuid;
    }

    public GameData(){
        this(UUID.randomUUID());
    }



    public void upTime(int minutes){
        mAllMinutes += minutes;
        mCurrentDay = mAllMinutes/(24*60) + 1;
    }

    private void setCorrectlyTime(){
        int hours = mAllMinutes/60;
        mCurrentDay = (hours/24) + 1;
        mCurrentHour = hours - (hours/24);
        mCurrentMinutes = mAllMinutes - (mAllMinutes/60);
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    public int getCurrentDay() {
        return mCurrentDay;
    }

    public void setCurrentDay(int currentDay) {
        mCurrentDay = currentDay;
    }

    public int getLastDay() {
        return mLastDay;
    }

    public void setLastDay(int lastDay) {
        mLastDay = lastDay;
    }

    public int getAllMinutes() {
        return mAllMinutes;
    }

    public void setAllMinutes(int allMinutes) {
        mAllMinutes = allMinutes;
        setCorrectlyTime();
    }

    public int getCurrentHour() {
        return mCurrentHour;
    }

    public void setCurrentHour(int currentHour) {
        mCurrentHour = currentHour;
    }

    public int getCurrentMinutes() {
        return mCurrentMinutes;
    }

    public void setCurrentMinutes(int currentMinutes) {
        mCurrentMinutes = currentMinutes;
    }


    public Location getCurrentLocation() {
        return mCurrentLocation;
    }

    public void setCurrentLocation(Location currentLocation, boolean setId) {
        mCurrentLocation = currentLocation;
        if (setId){
            setCurrentLocationId(mCurrentLocation.getId());
        }
    }

    public Place getCurrentPlace() {
        return mCurrentPlace;
    }

    public void setCurrentPlace(Place currentPlace, boolean setId) {
        mCurrentPlace = currentPlace;
        if (setId){
            setCurrentPlaceId(mCurrentPlace.getId());
        }
    }

    public int getCurrentLocationId() {
        return mCurrentLocationId;
    }

    public void setCurrentLocationId(int currentLocationId) {
        mCurrentLocationId = currentLocationId;
    }

    public int getCurrentPlaceId() {
        return mCurrentPlaceId;
    }

    public void setCurrentPlaceId(int currentPlaceId) {
        mCurrentPlaceId = currentPlaceId;
    }

    @Override
    public void update() {
        BDHelper.updateGame(this);
    }

    @Override
    public void delete() {
        BDHelper.deleteGame(this);
    }

    @Override
    public void add() {
        BDHelper.addGame(this);
    }

}
