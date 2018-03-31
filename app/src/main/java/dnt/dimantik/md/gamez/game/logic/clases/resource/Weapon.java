package dnt.dimantik.md.gamez.game.logic.clases.resource;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.clases.EquipmentOwner;
import dnt.dimantik.md.gamez.game.logic.clases.ResourceOwner;
import dnt.dimantik.md.gamez.game.logic.clases.Owner;

/**
 * Created by dimantik on 10/20/17.
 */

public abstract class Weapon extends Resource {

    private EquipmentOwner mPlayerOwner;

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

    public void addPlayerOwner(EquipmentOwner playerOwner, int slot){
        mPlayerOwner = playerOwner;
        mPlayerOwner.addCurrentWeapon(this, slot);
    }

    public void deletePlayerOwner(){
        deleteOwner();
        mPlayerOwner.deleteCurrentWeapon(this);
        mPlayerOwner = null;
    }

    public Owner getPlayerOwner() {
        return mPlayerOwner;
    }

    @Override
    public boolean addOwner(ResourceOwner owner) {
        if (mPlayerOwner == null){
            return super.addOwner(owner);
        } else {
            boolean result = owner.putResource(this);
            if (result){
                mPlayerOwner.deleteCurrentWeapon(this);
                mPlayerOwner = null;
                mOwner = owner;
                return true;
            }
            return false;
        }
    }

}
