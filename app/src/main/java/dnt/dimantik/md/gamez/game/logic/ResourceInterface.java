package dnt.dimantik.md.gamez.game.logic;

import java.util.Map;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;

/**
 * Created by dimantik on 29.03.2018.
 */

public class ResourceInterface {

    private Map<UUID, Resource> mResourceMap;

    public ResourceInterface(Map<UUID, Resource> resourceMap) {
        mResourceMap = resourceMap;
    }

    public void addResource(Resource resource){
        mResourceMap.put(resource.getId(), resource);
    }

    public void addAllResources(Map<UUID, Resource> resourceMap){
        mResourceMap.putAll(resourceMap);
    }


    public Resource getResource(UUID uuid){
        return mResourceMap.get(uuid);
    }

    public Map<UUID, Resource> getResourceMap() {
        return mResourceMap;
    }

    public void setResourceMap(Map<UUID, Resource> resourceMap) {
        mResourceMap = resourceMap;
    }

    public void addToBd(){
        for (Resource resource : mResourceMap.values()){
            resource.add();
        }
    }

}
