package dnt.dimantik.md.gamez.game.logic.clases.location;

import android.util.Log;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.Owner;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;
import dnt.dimantik.md.gamez.game.logic.clases.BDInterface;

/**
 * Created by dimantik on 24.03.2018.
 */

public class Place implements BDInterface, Owner {

    private String mName;
    private int mId;
    private int mDangerousLevel;
    private String mInfo;
    private String mAssertDrawableName;

    private List<Way> mWayList;
    private List<Resource> mResourceList;
    private Set<UUID> mResourceUUIDList;
    private List<Resource.ResourceType> mResourceTypeList;

    private Place(){}

    public static Place createNewPlace(int id, String name, int dangerousLevel, String info, String assertDrawableName, List<Resource.ResourceType> resourceTypeList){
        Place place = getBasePlace(id, name, dangerousLevel, info, assertDrawableName, resourceTypeList);

        place.setResourceUUIDList(new HashSet<UUID>());

        return place;
    }

    public static Place getInstance(int id, String name, int dangerousLevel, String info, String assertDrawableName, List<Resource.ResourceType> resourceTypeList, Set<UUID> resourceUUIDList){
        Place place = getBasePlace(id, name, dangerousLevel, info, assertDrawableName, resourceTypeList);

        place.setResourceUUIDList(resourceUUIDList);

        return place;
    }

    private static Place getBasePlace(int id, String name, int dangerousLevel, String info, String assertDrawableName, List<Resource.ResourceType> resourceTypeList){
        Place place = new Place();

        place.setId(id);
        place.setName(name);
        place.setDangerousLevel(dangerousLevel);
        place.setInfo(info);
        place.setResourceTypeList(resourceTypeList);
        place.setWayList(new LinkedList<Way>());
        place.setAssertDrawableName(assertDrawableName);
        place.setResourceList(new LinkedList<Resource>());

        return place;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public List<Resource> getResourceList() {
        return mResourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        mResourceList = resourceList;
        Set<UUID> resourceUUIDList = new HashSet<>();
        for (Resource resource : resourceList){
            resourceUUIDList.add(resource.getId());
        }
        setResourceUUIDList(resourceUUIDList);
    }

    public Set<UUID> getResourceUUIDList() {
        return mResourceUUIDList;
    }

    public void setResourceUUIDList(Set<UUID> resourceUUIDList) {
        mResourceUUIDList = resourceUUIDList;
    }

    public List<Resource.ResourceType> getResourceTypeList() {
        return mResourceTypeList;
    }

    public void setResourceTypeList(List<Resource.ResourceType> resourceTypeList) {
        mResourceTypeList = resourceTypeList;
    }

    public int getDangerousLevel() {
        return mDangerousLevel;
    }

    public void setDangerousLevel(int dangerousLevel) {
        mDangerousLevel = dangerousLevel;
    }

    public List<Way> getWayList() {
        return mWayList;
    }

    public void setWayList(List<Way> wayList) {
        mWayList = wayList;
    }

    public String getInfo() {
        return mInfo;
    }

    public void setInfo(String info) {
        mInfo = info;
    }

    public String getAssertDrawableName() {
        return mAssertDrawableName;
    }

    public void setAssertDrawableName(String assertDrawableName) {
        mAssertDrawableName = assertDrawableName;
    }



    @Override
    public boolean putResource(Resource resource, String flag) {
        if (!isPossibleToPut(resource, flag)){
            return false;
        }
        Log.i("TAG", "PUT");
        mResourceList.add(resource);
        mResourceUUIDList.add(resource.getId());
        update();
        return true;
    }

    @Override
    public boolean isPossibleToPut(Resource resource, String flag) {
        if (resource == null){
            return false;
        }
        return true;
    }

    @Override
    public void deleteResource(Resource resource) {
        if (!mResourceList.remove(resource)){
            return;
        }
        mResourceUUIDList.remove(resource.getId());
        update();
    }

    @Override
    public void update() {
        BDHelper.updatePlace(this);
    }

    @Override
    public void delete() {
        BDHelper.deletePlace(this);
    }

    @Override
    public void add() {
        BDHelper.addPlace(this);
    }
}
