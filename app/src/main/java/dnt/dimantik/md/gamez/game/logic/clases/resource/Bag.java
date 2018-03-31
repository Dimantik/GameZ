package dnt.dimantik.md.gamez.game.logic.clases.resource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.EquipmentOwner;
import dnt.dimantik.md.gamez.game.logic.clases.ResourceOwner;
import dnt.dimantik.md.gamez.game.logic.clases.Owner;


/**
 * Created by dimantik on 11/16/17.
 */

public class Bag extends Resource implements ResourceOwner {

    private int mCapacity;
    private List<Resource> mResourceList;
    private Set<UUID> mResourceUUIDList;

    private EquipmentOwner mPlayerOwner;

    public Bag(String name, String assetDrawableName, int capacity) {
        this(UUID.randomUUID(), name, assetDrawableName, capacity);
    }

    public Bag(UUID id, String name, String assetDrawableName, int capacity) {
        super(id, name, ResourceType.EVERYWHERE, assetDrawableName);
        mCapacity = capacity;
        mResourceList = new LinkedList<>();
        mResourceUUIDList = new HashSet<>();
    }

    private boolean addWeapons(Weapon weapon){
        if (weapon instanceof FireArms){
            for (Resource resource : mResourceList){
                if (resource instanceof FireArms){
                    if (((FireArms) weapon).getType().equals(((FireArms)resource).getType())){
                        ((FireArms) resource).upCartridges(((FireArms) weapon).getCartridges());
                        weapon.deleteOwner();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Food> getAllFoodResource(){
        List<Food> food = new ArrayList<>();
        for (Resource resource : mResourceList){
            if (resource instanceof Food){
                food.add((Food)resource);
            }
        }
        return food;
    }

    public List<Liquid> getAllLiquidResource(){
        List<Liquid> liquids = new ArrayList<>();
        for (Resource resource : mResourceList){
            if (resource instanceof Liquid){
                liquids.add((Liquid)resource);
            }
        }
        return liquids;
    }

    public List<Drug> getAllDrugResource(){
        List<Drug> drug = new ArrayList<>();
        for (Resource resource : mResourceList){
            if (resource instanceof Drug){
                drug.add((Drug)resource);
            }
        }
        return drug;
    }

    public int getCapacity() {
        return mCapacity;
    }

    public void setCapacity(int capacity) {
        mCapacity = capacity;
    }

    public List<Resource> getResourceList() {
        return mResourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        mResourceList = resourceList;
    }

    public Set<UUID> getResourceUUIDList() {
        return mResourceUUIDList;
    }

    public void setResourceUUIDList(Set<UUID> resourceUUIDList) {
        mResourceUUIDList = resourceUUIDList;
    }

    public int getFreeSpace(){
        return mCapacity - mResourceList.size();
    }

    public int getEngagedSpace(){
        return mCapacity - getFreeSpace();
    }

    public void addUUIDFromResource(UUID uuid){
        mResourceUUIDList.add(uuid);
    }


    public void addPlayerOwner(EquipmentOwner playerOwner){
        deleteOwner();
        mPlayerOwner = playerOwner;
        mPlayerOwner.addCurrentBag(this);
    }

    public Owner deletePlayerOwner(){
        if (mPlayerOwner != null){
            mPlayerOwner.deleteCurrentBag(this);
            Owner owner = mPlayerOwner;
            mPlayerOwner = null;
            return owner;
        }
        return null;
    }

    public Owner getPlayerOwner() {
        return mPlayerOwner;
    }

    @Override
    public boolean addOwner(ResourceOwner owner) {
        if (mPlayerOwner == null){
            return super.addOwner(owner);
        } else {
            boolean result = owner.putResource(this);
            if (result){
                mPlayerOwner.deleteCurrentBag(this);
                mPlayerOwner = null;
                mOwner = owner;
                return true;
            }
            return false;
        }
    }

    @Override
    public boolean putResource(Resource resource) {
        if (resource == null){
            return false;
        }
        if (mResourceList.size() >= mCapacity){
            return false;
        }
        if (resource instanceof FireArms) {
            if (addWeapons((Weapon) resource)){
                update();
                return true;
            }
        }
        mResourceUUIDList.add(resource.getId());
        mResourceList.add(resource);
        return true;
    }

    @Override
    public void deleteResource(Resource resource) {
        if(mResourceList.remove(resource)){
            mResourceUUIDList.remove(resource.getId());
        }
        update();
    }

    @Override
    public void update() {
        BDHelper.updateBag(this);
    }

    @Override
    public void delete() {
        BDHelper.deleteBag(this);
    }

    @Override
    public void add() {
        BDHelper.addBag(this);
    }

}
