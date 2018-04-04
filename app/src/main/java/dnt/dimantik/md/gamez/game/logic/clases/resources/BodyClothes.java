package dnt.dimantik.md.gamez.game.logic.clases.resources;


import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;

/**
 * Created by dimantik on 10/20/17.
 */

public class BodyClothes extends Clothes {

    public BodyClothes(String name, int protection, String assetDrawableName) {
        this(UUID.randomUUID(), name, protection, assetDrawableName);
    }

    public BodyClothes(UUID id, String name, int protection, String assetDrawableName) {
        super(id, name, protection, assetDrawableName);
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
