package dnt.dimantik.md.gamez.game.logic.clases.resources;


import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.Owner;

/**
 * Created by dimantik on 10/20/17.
 */

public class Transport extends Resource implements Owner{

    private double mPower;
    private int mProtection;
    private Bag mBag;
    private UUID mBagUUID;
    private int mFuelQuantity;
    private double mSpendFuel;

    public Transport(String name, double power, int protection, int fuelQuantity, double spendFuel, String assetDrawableName){
        this(UUID.randomUUID(), name, power, protection, fuelQuantity, spendFuel, assetDrawableName);
    }

    public Transport(UUID id, String name, double power, int protection, int fuelQuantity, double spendFuel, String assetDrawableName){
        super(id, name, ResourceType.OUTSIDE, assetDrawableName);
        mPower = power;
        mProtection = protection;
        mFuelQuantity = fuelQuantity;
        mSpendFuel = spendFuel;
    }

    public double getSpendFuel() {
        return mSpendFuel;
    }

    public void setSpendFuel(double spendFuel) {
        mSpendFuel = spendFuel;
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

    @Override
    public boolean putResource(Resource resource, String flag) {
        if (!isPossibleToPut(resource, flag)){
            return false;
        }

        setBag((Bag) resource, true);
        update();
        return true;
    }

    @Override
    public boolean isPossibleToPut(Resource resource, String flag) {
        if (resource instanceof Bag){
            return true;
        }
        return false;
    }

    @Override
    public void deleteResource(Resource resource) {
        setBag(null, true);
        update();
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
