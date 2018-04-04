package dnt.dimantik.md.gamez.game.logic.clases.resources;


import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;

/**
 * Created by dimantik on 10/31/17.
 */

public class SteelArms extends Weapon {

    public SteelArms(String name, int power, String assetDrawableName) {
        this(UUID.randomUUID(), name, power, assetDrawableName);
    }

    public SteelArms(UUID id, String name, int power, String assetDrawableName) {
        super(id, name, power, assetDrawableName);
    }


    @Override
    public void update() {
        BDHelper.updateSteelArms(this);
    }

    @Override
    public void delete() {
        BDHelper.deleteSteelArms(this);
    }

    @Override
    public void add() {
        BDHelper.addSteelArms(this);
    }
}
