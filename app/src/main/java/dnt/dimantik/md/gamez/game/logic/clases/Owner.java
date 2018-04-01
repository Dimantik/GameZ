package dnt.dimantik.md.gamez.game.logic.clases;

import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;

/**
 * Created by dimantik on 27.03.2018.
 */

public interface Owner {

    boolean putResource(Resource resource, String flag);
    boolean isPossibleToPut(Resource resource, String flag);
    void deleteResource(Resource resource);
    void update();

}
