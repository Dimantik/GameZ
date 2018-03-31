package dnt.dimantik.md.gamez.game.logic.clases;

import dnt.dimantik.md.gamez.game.logic.clases.resource.Bag;
import dnt.dimantik.md.gamez.game.logic.clases.resource.BodyClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FeetClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.HeadClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.LegsClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Transport;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Weapon;

/**
 * Created by dimantik on 28.03.2018.
 */

public interface EquipmentOwner extends Owner {

    void deleteCurrentHeadClothes(HeadClothes headClothes);
    void deleteCurrentBodyClothes(BodyClothes bodyClothes);
    void deleteCurrentLegsClothes(LegsClothes legsClothes);
    void deleteCurrentFeetClothes(FeetClothes feetClothes);

    void deleteCurrentWeapon(Weapon weapon);
    void deleteCurrentBag(Bag bag);
    void deleteCurrentTransport(Transport transport);

    void addCurrentHeadClothes(HeadClothes headClothes);
    void addCurrentBodyClothes(BodyClothes bodyClothes);
    void addCurrentLegsClothes(LegsClothes legsClothes);
    void addCurrentFeetClothes(FeetClothes feetClothes);

    void addCurrentWeapon(Weapon weapon, int slot);
    void addCurrentBag(Bag bag);
    void addCurrentTransport(Transport transport);
}
