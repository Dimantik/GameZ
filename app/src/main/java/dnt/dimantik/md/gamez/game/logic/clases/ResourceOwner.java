package dnt.dimantik.md.gamez.game.logic.clases;

import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;

/**
 * Created by dimantik on 28.03.2018.
 */

public interface ResourceOwner extends Owner {

    boolean putResource(Resource resource);
    void deleteResource(Resource resource);

}
