package dnt.dimantik.md.gamez.game.logic.clases.resources;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;


/**
 * Created by dimantik on 10/31/17.
 */

public class FireArms extends Weapon {

    private Type mType;

    public FireArms(String name, int power, String assetDrawableName, Type type) {
        this(UUID.randomUUID(), name, power, assetDrawableName, type);
    }

    public FireArms(UUID id, String name, int power, String assetDrawableName, Type type) {
        super(id, name, power, assetDrawableName);
        mType = type;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type type) {
        mType = type;
        update();
    }

    @Override
    public void update() {
        BDHelper.updateFireArms(this);
    }

    @Override
    public void delete() {
        BDHelper.deleteFireArms(this);
    }

    @Override
    public void add() {
        BDHelper.addFireArms(this);
    }


    public enum Type {
        PISTOL, MACHINE, MACHINE_GUN, SUBMACHINE_GUN, SNIPER_RIFLE, SHOT_GUN, BOW
    }

}
