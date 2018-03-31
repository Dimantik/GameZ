package dnt.dimantik.md.gamez.game.logic.clases.resource;

import android.support.annotation.NonNull;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.clases.BDInterface;
import dnt.dimantik.md.gamez.game.logic.clases.ResourceOwner;
import dnt.dimantik.md.gamez.game.logic.clases.Owner;

/**
 * Created by dimantik on 10/20/17.
 */

public abstract class Resource implements BDInterface, Comparable<Resource> {

    private UUID mId;
    private String mName;
    private String mAssertDrawable;
    private ResourceType mResourceType;

    protected ResourceOwner mOwner;

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

    public ResourceOwner deleteOwner(){
        if (mOwner != null){
            mOwner.deleteResource(this);
            ResourceOwner owner = mOwner;
            mOwner = null;
            return owner;
        }
        return null;
    }

    public boolean addOwner(ResourceOwner owner){
        boolean result = owner.putResource(this);
        if (result){
            if (mOwner != null){
                mOwner.deleteResource(this);
            }
            mOwner = owner;
            return true;
        }
        return false;
    }

    public Owner getOwner() {
        return mOwner;
    }

    @Override
    public int compareTo(@NonNull Resource resource) {
        int hashCode1 = resource.getId().hashCode();
        int hashCode2 = mId.hashCode();

        if (hashCode1 == hashCode2){
            return 0;
        } else if (hashCode2 > hashCode1) {
            return 1;
        } else {
            return -1;
        }
    }

    public enum ResourceType {

        IN_ROOM, OUTSIDE, EVERYWHERE;

    }

}
