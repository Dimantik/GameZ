package dnt.dimantik.md.gamez.game.logic.clases.resource;


import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.EquipmentOwner;
import dnt.dimantik.md.gamez.game.logic.clases.ResourceOwner;
import dnt.dimantik.md.gamez.game.logic.clases.Owner;

/**
 * Created by dimantik on 10/20/17.
 */

public class BodyClothes extends Clothes {

    private EquipmentOwner mPlayerOwner;

    public BodyClothes(String name, int protection, String assetDrawableName) {
        this(UUID.randomUUID(), name, protection, assetDrawableName);
    }

    public BodyClothes(UUID id, String name, int protection, String assetDrawableName) {
        super(id, name, protection, assetDrawableName);
    }

    public void addPlayerOwner(EquipmentOwner playerOwner){
        deleteOwner();
        mPlayerOwner = playerOwner;
        mPlayerOwner.addCurrentBodyClothes(this);
    }

    public Owner deletePlayerOwner(){
        if (mPlayerOwner != null){
            mPlayerOwner.deleteCurrentBodyClothes(this);
            Owner owner = mPlayerOwner;
            mPlayerOwner = null;
            return owner;
        }
        return null;
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
                mPlayerOwner.deleteCurrentBodyClothes(this);
                mPlayerOwner = null;
                mOwner = owner;
                return true;
            }
            return false;
        }
    }

    @Override
    public void delete() {
        BDHelper.deleteBodyClothes(this);
    }

    @Override
    public void add() {
        BDHelper.addBodyClothes(this);
    }

    @Override
    public void update() {
        BDHelper.updateBodyClothes(this);
    }

}
