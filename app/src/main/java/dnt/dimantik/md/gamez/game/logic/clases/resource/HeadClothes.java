package dnt.dimantik.md.gamez.game.logic.clases.resource;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.EquipmentOwner;
import dnt.dimantik.md.gamez.game.logic.clases.ResourceOwner;
import dnt.dimantik.md.gamez.game.logic.clases.Owner;

/**
 * Created by dimantik on 10/20/17.
 */

public class HeadClothes extends Clothes {

    private EquipmentOwner mPlayerOwner;

    public HeadClothes(String name, int protection, String assetDrawableName) {
        this(UUID.randomUUID(), name, protection, assetDrawableName);
    }

    public HeadClothes(UUID id, String name, int protection, String assetDrawableName) {
        super(id, name, protection, assetDrawableName);
    }

    public void addPlayerOwner(EquipmentOwner playerOwner){
        deleteOwner();
        mPlayerOwner = playerOwner;
        mPlayerOwner.addCurrentHeadClothes(this);
    }

    public Owner deletePlayerOwner(){
        if (mPlayerOwner != null){
            mPlayerOwner.deleteCurrentHeadClothes(this);
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
                mPlayerOwner.deleteCurrentHeadClothes(this);
                mPlayerOwner = null;
                mOwner = owner;
                return true;
            }
            return false;
        }
    }

    public Owner getPlayerOwner() {
        return mPlayerOwner;
    }

    @Override
    public void update() {
        BDHelper.updateHeadClothes(this);
    }

    @Override
    public void delete() {
        BDHelper.deleteHeadClothes(this);
    }

    @Override
    public void add() {
        BDHelper.addHeadClothes(this);
    }
}
