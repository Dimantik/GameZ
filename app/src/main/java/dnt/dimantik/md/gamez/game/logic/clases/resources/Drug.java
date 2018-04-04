package dnt.dimantik.md.gamez.game.logic.clases.resources;


import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;

/**
 * Created by dimantik on 10/20/17.
 */

public class Drug extends ImportantResource {

    public Drug(String name, int quantity, String assetDrawableName){
        this(UUID.randomUUID(), name, quantity, assetDrawableName);
    }

    public Drug(UUID id, String name, int quantity, String assetDrawableName){
        super(id, name, quantity, assetDrawableName);
    }

    @Override
    public void add() {
        BDHelper.addDrug(this);
    }

    @Override
    public void delete() {
        BDHelper.deleteDrug(this);
        deleteOwner();
    }

    @Override
    public void update() {
        BDHelper.updateDrug(this);
    }
}
