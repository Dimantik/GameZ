package dnt.dimantik.md.gamez.game.logic.clases.resource;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.Owner;

/**
 * Created by dimantik on 10/20/17.
 */

public class FeetClothes extends Clothes {

    public FeetClothes(String name, int protection, String assetDrawableName) {
        this(UUID.randomUUID(), name, protection, assetDrawableName);
    }

    public FeetClothes(UUID id, String name, int protection, String assetDrawableName) {
        super(id, name, protection, assetDrawableName);
    }

    @Override
    public void update() {
        BDHelper.updateFeetClothes(this);
    }

    @Override
    public void delete() {
        BDHelper.deleteFeetClothes(this);
    }

    @Override
    public void add() {
        BDHelper.addFeetClothes(this);
    }
}
