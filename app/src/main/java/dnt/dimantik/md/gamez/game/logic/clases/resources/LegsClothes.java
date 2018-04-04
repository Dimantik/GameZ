package dnt.dimantik.md.gamez.game.logic.clases.resources;


import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;

/**
 * Created by dimantik on 10/20/17.
 */

public class LegsClothes extends Clothes  {

    public LegsClothes(String name, int protection, String assetDrawableName) {
        this(UUID.randomUUID(), name, protection, assetDrawableName);
    }

    public LegsClothes(UUID id, String name, int protection, String assetDrawableName) {
        super(id, name, protection, assetDrawableName);
    }

    @Override
    public void update() {
        BDHelper.updateLegsClothes(this);
    }

    @Override
    public void delete() {
        BDHelper.deleteLegsClothes(this);
    }

    @Override
    public void add() {
        BDHelper.addLegsClothes(this);
    }
}
