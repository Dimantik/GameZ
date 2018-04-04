package dnt.dimantik.md.gamez.game.logic.clases.map;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.BDInterface;

/**
 * Created by dimantik on 24.03.2018.
 */

public class Way implements BDInterface{

    private int mTravelTime;
    private int mPointOneId;
    private int mPointTwoId;

    public Way(int pointOneId, int pointTwoId, int travelTime){
        mPointOneId = pointOneId;
        mTravelTime = travelTime;
        mPointTwoId = pointTwoId;
    }

    public int getTravelTime() {
        return mTravelTime;
    }

    public void setTravelTime(int travelTime) {
        mTravelTime = travelTime;
    }

    public int getPointOneId() {
        return mPointOneId;
    }

    public void setPointOneId(int pointOneId) {
        mPointOneId = pointOneId;
    }

    public int getPointTwoId() {
        return mPointTwoId;
    }

    public void setPointTwoId(int pointTwoId) {
        mPointTwoId = pointTwoId;
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
