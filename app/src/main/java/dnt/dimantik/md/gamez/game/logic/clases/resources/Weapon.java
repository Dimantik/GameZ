package dnt.dimantik.md.gamez.game.logic.clases.resources;

import java.util.UUID;

/**
 * Created by dimantik on 10/20/17.
 */

public abstract class Weapon extends Resource {

    private int mPower;

    public Weapon(String name, int power, String assetDrawableName) {
        this(UUID.randomUUID(), name, power, assetDrawableName);
    }

    public Weapon(UUID id, String name, int power, String assetDrawableName) {
        super(id, name, ResourceType.IN_ROOM, assetDrawableName);
        mPower = power;
    }

    public int getPower() {
        return mPower;
    }

    public void setPower(int power) {
        mPower = power;
    }

}
