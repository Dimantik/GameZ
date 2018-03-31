package dnt.dimantik.md.gamez.game.logic.clases.resource;


import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.EquipmentOwner;
import dnt.dimantik.md.gamez.game.logic.clases.ResourceOwner;
import dnt.dimantik.md.gamez.game.logic.clases.Owner;

/**
 * Created by dimantik on 10/20/17.
 */

public class Transport extends Resource {

    private EquipmentOwner mPlayerOwner;

    private double mPower;
    private int mProtection;
    private Bag mBag;
    private UUID mBagUUID;
    private int mFuelQuantity;

    public Transport(String name, double power, int protection, int fuelQuantity, String assetDrawableName){
        this(UUID.randomUUID(), name, power, protection, fuelQuantity, assetDrawableName);
    }

    public Transport(UUID id, String name, double power, int protection, int fuelQuantity, String assetDrawableName){
        super(id, name, ResourceType.OUTSIDE, assetDrawableName);
        mPower = power;
        mProtection = protection;
        mFuelQuantity = fuelQuantity;
    }

    public double getPower() {
        return mPower;
    }

    public void setPower(double power) {
        mPower = power;
    }

    public int getProtection() {
        return mProtection;
    }

    public void setProtection(int protection) {
        mProtection = protection;
    }

    public Bag getBag() {
        return mBag;
    }

    public void setBag(Bag bag, boolean setId) {
        mBag = bag;
        if (setId){
            mBagUUID = mBag.getId();
        }
    }

    public UUID getBagUUID() {
        return mBagUUID;
    }

    public void setBagUUID(UUID bagUUID) {
        mBagUUID = bagUUID;
    }

    public int getFuelQuantity() {
        return mFuelQuantity;
    }

    public void setFuelQuantity(int fuelQuantity) {
        mFuelQuantity = fuelQuantity;
    }

    public void downFuelQuantity(int quantity){
        mFuelQuantity -= quantity;
    }

    public void upFuelQuantity(int quantity){
        mFuelQuantity += quantity;
    }

    public void addPlayerOwner(EquipmentOwner playerOwner){
        deleteOwner();
        mPlayerOwner = playerOwner;
        mPlayerOwner.addCurrentTransport(this);
    }

    public Owner deletePlayerOwner(){
        if (mPlayerOwner != null){
            mPlayerOwner.deleteCurrentTransport(this);
            Owner owner = mPlayerOwner;
            mPlayerOwner = null;
            return owner;
        }
        return null;
    }

    @Override
    public boolean addOwner(ResourceOwner owner) {
        if (mPlayerOwner == null){
            return super.addOwner(owner);
        } else {
            boolean result = owner.putResource(this);
            if (result){
                mPlayerOwner.deleteCurrentTransport(this);
                mPlayerOwner = null;
                mOwner = owner;
                return true;
            }
            return false;
        }
    }

    @Override
    public void update() {
        BDHelper.updateTransport(this);
    }

    @Override
    public void delete() {
        BDHelper.deleteTransport(this);
    }

    @Override
    public void add() {
        BDHelper.addTransport(this);
    }
}
