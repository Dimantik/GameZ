package dnt.dimantik.md.gamez.game.logic.bd.cursor;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import dnt.dimantik.md.gamez.helper.classes.Assistant;
import dnt.dimantik.md.gamez.game.logic.bd.scheme.PlayerDbSchema.PlayerTable.Cols;
import dnt.dimantik.md.gamez.game.logic.clases.Player;

/**
 * Created by dimantik on 3/5/18.
 */

public class PlayerCursorWrapper extends CursorWrapper {

    public PlayerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Player getPlayer(){
        UUID uuid = Assistant.convertToUUID(getString(getColumnIndex(Cols.UUID)));
        String name = getString(getColumnIndex(Cols.NAME));
        int health = getInt(getColumnIndex(Cols.HEALTH));
        int satiety = getInt(getColumnIndex(Cols.SATIETY));
        int energy = getInt(getColumnIndex(Cols.ENERGY));
        int thirst = getInt(getColumnIndex(Cols.THIRST));
        int power = getInt(getColumnIndex(Cols.POWER));
        int protection = getInt(getColumnIndex(Cols.PROTECTION));
        double murder_skill = getDouble(getColumnIndex(Cols.MURDER_SKILL));
        UUID currentHeadClothes = Assistant.convertToUUID(getString(getColumnIndex(Cols.CURRENT_HEAD_CLOTHES)));
        UUID currentBodyClothes = Assistant.convertToUUID(getString(getColumnIndex(Cols.CURRENT_BODY_CLOTHES)));
        UUID currentLegsClothes = Assistant.convertToUUID(getString(getColumnIndex(Cols.CURRENT_LEGS_CLOTHES)));
        UUID currentFeetClothes = Assistant.convertToUUID(getString(getColumnIndex(Cols.CURRENT_FEET_CLOTHES)));
        UUID currentFirstWeapon = Assistant.convertToUUID(getString(getColumnIndex(Cols.CURRENT_WEAPON_FIRST)));
        UUID currentSecondWeapon = Assistant.convertToUUID(getString(getColumnIndex(Cols.CURRENT_WEAPON_SECOND)));
        UUID currentBag = Assistant.convertToUUID(getString(getColumnIndex(Cols.CURRENT_BAG)));
        UUID currentTransport = Assistant.convertToUUID(getString(getColumnIndex(Cols.CURRENT_TRANSPORT)));
        UUID withoutBag = Assistant.convertToUUID(getString(getColumnIndex(Cols.WITHOUT_BAG)));

        Player player = new Player(name);
        player.setUUID(uuid);
        player.setSatiety(satiety);
        player.setThirst(thirst);
        player.setEnergy(energy);
        player.setHealth(health);
        player.setMurderSkill(murder_skill);
        player.setProtection(protection);
        player.setPower(power);
        player.setCurrentHeadClothesUUID(currentHeadClothes);
        player.setCurrentBodyClothesUUID(currentBodyClothes);
        player.setCurrentLegsClothesUUID(currentLegsClothes);
        player.setCurrentFeetClothesUUID(currentFeetClothes);
        player.setCurrentBagUUID(currentBag);
        player.setCurrentTransportUUID(currentTransport);
        player.setCurrentFirstWeaponUUID(currentFirstWeapon);
        player.setCurrentSecondWeaponUUID(currentSecondWeapon);
        player.setWithoutBagUUID(withoutBag);

        return player;
    }
}
