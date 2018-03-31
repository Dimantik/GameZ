package dnt.dimantik.md.gamez.game.logic.clases.resource;

import java.util.UUID;

/**
 * Created by dimantik on 10/20/17.
 */

public abstract class ImportantResource extends Resource {

    private int mQuantity;

    ImportantResource(String name, int quantity, String assetDrawableName) {
        this(UUID.randomUUID(), name, quantity, assetDrawableName);
    }

    ImportantResource(UUID id, String name, int quantity, String assetDrawableName) {
        super(id, name, ResourceType.IN_ROOM, assetDrawableName);
        mQuantity = quantity;
    }

    public int downAmount(int quantity){
        mQuantity -= quantity;
        return quantity;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }


}
