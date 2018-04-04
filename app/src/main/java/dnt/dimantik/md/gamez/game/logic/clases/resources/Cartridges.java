package dnt.dimantik.md.gamez.game.logic.clases.resources;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;

/**
 * Created by dimantik on 10/20/17.
 */

public class Cartridges extends Resource  {

    private int mQuantity;
    private FireArms.Type mType;

    public Cartridges(String name, String assetDrawableName, int quantity, FireArms.Type type) {
        this(UUID.randomUUID(), name, assetDrawableName, quantity, type);
    }

    public Cartridges(UUID id, String name, String assetDrawableName, int quantity, FireArms.Type type) {
        super(id, name, ResourceType.EVERYWHERE, assetDrawableName);
        mQuantity = quantity;
        mType = type;
    }

    public void upQuantity(int quantity){
        mQuantity += quantity;
    }

    public void downQuantity(int quantity){
        mQuantity -= quantity;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public FireArms.Type getType() {
        return mType;
    }

    public void setType(FireArms.Type type) {
        mType = type;
    }


    @Override
    public void delete() {
        BDHelper.deleteCartridges(this);
    }

    @Override
    public void add() {
        BDHelper.addCartridges(this);
    }

    @Override
    public void update() {
        BDHelper.updateCartridges(this);
    }
}
