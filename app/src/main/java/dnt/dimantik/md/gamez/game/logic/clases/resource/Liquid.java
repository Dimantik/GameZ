package dnt.dimantik.md.gamez.game.logic.clases.resource;


import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;

/**
 * Created by dimantik on 10/20/17.
 */

public class Liquid extends ImportantResource {

    public Liquid(String name, int amount, String assetDrawableName){
        this(UUID.randomUUID(), name, amount, assetDrawableName);
    }

    public Liquid(UUID id, String name, int amount, String assetDrawableName){
        super(id, name, amount, assetDrawableName);
    }

    @Override
    public int downAmount(int quantity) {
        int result =  super.downAmount(quantity);
        update();
        return result;
    }

    @Override
    public void update() {
        BDHelper.updateLiquid(this);
    }

    @Override
    public void delete() {
        BDHelper.deleteLiquid(this);
        deleteOwner();
    }

    @Override
    public void add() {
        BDHelper.addLiquid(this);
    }
}
