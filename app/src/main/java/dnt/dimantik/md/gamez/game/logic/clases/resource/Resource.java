package dnt.dimantik.md.gamez.game.logic.clases.resource;

import android.support.annotation.NonNull;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.clases.BDInterface;
import dnt.dimantik.md.gamez.game.logic.clases.Owner;

/**
 * Created by dimantik on 10/20/17.
 */

public abstract class Resource implements BDInterface {

    private UUID mId;
    private String mName;
    private String mAssertDrawable;
    private ResourceType mResourceType;

    protected Owner mOwner;

    public Resource(UUID id, String name, ResourceType resourceType, String assetDrawableName){
        mId = id;
        mName = name;
        mAssertDrawable = assetDrawableName;
        mResourceType = resourceType;
    }

    public Resource(String name, ResourceType resourceType, String assetDrawableName){
        this(UUID.randomUUID(), name, resourceType, assetDrawableName);
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAssertDrawable() {
        return mAssertDrawable;
    }

    public void setAssertDrawable(String assertDrawable) {
        mAssertDrawable = assertDrawable;
    }

    public ResourceType getResourceType() {
        return mResourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        mResourceType = resourceType;
    }

    public Owner deleteOwner(){
        if (mOwner != null){
            mOwner.deleteResource(this);
            Owner owner = mOwner;
            mOwner = null;
            return owner;
        }
        return null;
    }

    public boolean addOwner(Owner owner, String flag){
        if (!owner.isPossibleToPut(this, flag)){
            return false;
        }
        owner.putResource(this, flag);
        mOwner = owner;
        return true;
    }

    public Owner getOwner() {
        return mOwner;
    }

    public enum ResourceType {

        IN_ROOM, OUTSIDE, EVERYWHERE;

    }

}
