package dnt.dimantik.md.gamez.game.logic.clases.location;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.BDInterface;

/**
 * Created by dimantik on 24.03.2018.
 */

public class Way implements BDInterface{

    private int mTravelTime;
    private int mToId;
    private int mFromId;

    public Way(int travelTime, int toId, int fromId){
        mToId = toId;
        mTravelTime = travelTime;
        mFromId = fromId;
    }

    public int getTravelTime() {
        return mTravelTime;
    }

    public void setTravelTime(int travelTime) {
        mTravelTime = travelTime;
    }

    public int getToId() {
        return mToId;
    }

    public void setToId(int toId) {
        mToId = toId;
    }

    public int getFromId() {
        return mFromId;
    }

    public void setFromId(int fromId) {
        mFromId = fromId;
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {
    }

    @Override
    public void add() {
        BDHelper.addWay(this);
    }
}
