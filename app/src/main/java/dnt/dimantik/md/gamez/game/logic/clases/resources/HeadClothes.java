package dnt.dimantik.md.gamez.game.logic.clases.resources;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;

/**
 * Created by dimantik on 10/20/17.
 */

public class HeadClothes extends Clothes {

    public HeadClothes(String name, int protection, String assetDrawableName) {
        this(UUID.randomUUID(), name, protection, assetDrawableName);
    }

    public HeadClothes(UUID id, String name, int protection, String assetDrawableName) {
        super(id, name, protection, assetDrawableName);
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
