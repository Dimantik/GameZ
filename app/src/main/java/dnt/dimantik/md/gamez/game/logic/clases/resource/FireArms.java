package dnt.dimantik.md.gamez.game.logic.clases.resource;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;


/**
 * Created by dimantik on 10/31/17.
 */

public class FireArms extends Weapon {

    private Type mType;
    private Cartridges mCartridges;
    private UUID mCartridgesUUID;

    public FireArms(String name, int power, String assetDrawableName, Type type) {
        this(UUID.randomUUID(), name, power, assetDrawableName, type);
    }

    public FireArms(UUID id, String name, int power, String assetDrawableName, Type type) {
        super(id, name, power, assetDrawableName);
        mType = type;
    }

    public void upCartridges(Cartridges cartridges){
        if (cartridges.getType() == mType){
            mCartridges.upQuantity(cartridges.getQuantity());
        }
        update();
    }

    public void downCartridges(int quantity){
        mCartridges.downQuantity(quantity);
        update();
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type type) {
        mType = type;
        update();
    }

    public Cartridges getCartridges() {
        return mCartridges;
    }

    public void setCartridges(Cartridges cartridges, boolean setId) {
        mCartridges = cartridges;
        if (setId) {
            mCartridgesUUID = mCartridges.getId();
        }
        update();
    }

    public int getCartridgesQuantity(){
        return mCartridges.getQuantity();
    }

    public UUID getCartridgesUUID() {
        return mCartridgesUUID;
    }

    public void setCartridgesUUID(UUID cartridgesUUID) {
        mCartridgesUUID = cartridgesUUID;
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
        PISTOL, MACHINE, MACHINE_GUN
    }

}
