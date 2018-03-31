package dnt.dimantik.md.gamez.game.logic.clases.resource;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;

/**
 * Created by dimantik on 10/20/17.
 */

public class Food extends ImportantResource {

    public Food(String name, int quantity, String assetDrawableName){
        this(UUID.randomUUID(), name, quantity, assetDrawableName);
    }

    public Food(UUID id, String name, int quantity, String assetDrawableName){
        super(id, name, quantity, assetDrawableName);
    }

    @Override
    public void update() {
        BDHelper.updateFood(this);
    }

    @Override
    public void delete() {
        BDHelper.deleteFood(this);
        deleteOwner();
    }

    @Override
    public void add() {
        BDHelper.addFood(this);
    }
}
