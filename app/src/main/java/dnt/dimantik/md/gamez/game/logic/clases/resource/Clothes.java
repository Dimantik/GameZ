package dnt.dimantik.md.gamez.game.logic.clases.resource;


import java.util.UUID;

/**
 * Created by dimantik on 10/20/17.
 */

public abstract class Clothes extends Resource {

    private int mProtection;

    public Clothes(String name, int protection, String assetDrawableName){
        this(UUID.randomUUID(), name, protection, assetDrawableName);
    }

    public Clothes(UUID id, String name, int protection, String assetDrawableName){
        super(id, name, ResourceType.EVERYWHERE, assetDrawableName);
        mProtection = protection;
    }

    public int getProtection() {
        return mProtection;
    }

    public void setProtection(int protection) {
        mProtection = protection;
    }

}
