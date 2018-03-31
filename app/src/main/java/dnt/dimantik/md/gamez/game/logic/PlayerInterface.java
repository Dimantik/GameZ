package dnt.dimantik.md.gamez.game.logic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dnt.dimantik.md.gamez.game.logic.clases.Player;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Bag;
import dnt.dimantik.md.gamez.game.logic.clases.resource.BodyClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Drug;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FeetClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Food;
import dnt.dimantik.md.gamez.game.logic.clases.resource.HeadClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.LegsClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Liquid;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Transport;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Weapon;

/**
 * Created by dimantik on 27.03.2018.
 */

public class PlayerInterface {

    private Player mPlayer;

    PlayerInterface(Player player){
        mPlayer = player;
    }

    public Player getPlayer() {
        return mPlayer;
    }

    public void setPlayer(Player player) {
        mPlayer = player;
    }


    // ИНТЕРФЕЙС ЖИЗНЕННЫХ ПОКАЗАТЕЛЕЙ

    public int getPlayerHealth(){
        return mPlayer.getHealth();
    }

    public int getPlayerSatiety(){
        return mPlayer.getSatiety();
    }

    public int getPlayerThirst(){
        return mPlayer.getThirst();
    }

    public int getPlayerEnergy(){
        return mPlayer.getEnergy();
    }

    public void upSatiety(Food food){
        if (food.getQuantity() <= 10){
            upSatiety(food.downAmount(food.getQuantity()));
            food.delete();
            update();
        } else {
            upSatiety(food.downAmount(10));
            food.update();
            update();
        }
    }

    public void upThirst(Liquid liquid){
        if (liquid.getQuantity() <= 10){
            upThirst(liquid.downAmount(liquid.getQuantity()));
            liquid.delete();
            update();
        } else {
            upThirst(liquid.downAmount(10));
            liquid.update();
            update();
        }
    }

    public void upHealth(Drug drug){
        if (drug.getQuantity() <= 10){
            upHealth(drug.downAmount(drug.getQuantity()));
            drug.delete();
            update();
        } else {
            upHealth(drug.downAmount(10));
            drug.update();
            update();
        }
    }

    public void upSatiety(int quantity){
        mPlayer.setSatiety(mPlayer.getSatiety() + quantity);
        if (mPlayer.getSatiety() > 100){
            mPlayer.setSatiety(100);
        }
    }

    public void upThirst(int quantity){
        mPlayer.setThirst(mPlayer.getThirst() + quantity);
        if (mPlayer.getThirst() > 100){
            mPlayer.setThirst(100);
        }
    }

    public void upHealth(int quantity){
        mPlayer.setHealth(mPlayer.getHealth() + quantity);
        if (mPlayer.getHealth() > 100){
            mPlayer.setHealth(100);
        }
    }

    public void downSatiety(int quantity){
        mPlayer.setSatiety(mPlayer.getSatiety() - quantity);
        if (mPlayer.getSatiety() < 0){
            mPlayer.setSatiety(0);
        }
    }

    public void downThirst(int quantity){
        mPlayer.setThirst(mPlayer.getThirst() - quantity);
        if (mPlayer.getThirst() < 0){
            mPlayer.setThirst(0);
        }
    }

    public void downHealth(int quantity){
        mPlayer.setHealth(mPlayer.getHealth() - quantity);
        if (mPlayer.getHealth() < 0){
            mPlayer.setHealth(0);
        }
    }

    public void downAllStatsForMinutes(int minutes){

    }


    // ИНТЕРФЕЙС СУМКИ

    public boolean addResourceToPlayerBag(Resource resource){
        return resource.addOwner(mPlayer.getCurrentBag());
    }

    public boolean addResourceListToPlayerBag(List<Resource> resourceList){
        if (mPlayer.getCurrentBag().getFreeSpace() < resourceList.size()){
            return false;
        } else {
            for (Resource resource : resourceList){
                addResourceToPlayerBag(resource);
            }
            return true;
        }
    }

    public boolean addResourceToPlayerTransportBag(Resource resource){
        return resource.addOwner(mPlayer.getCurrentTransport().getBag());
    }

    public boolean addResourceListToPlayerTransportBag(List<Resource> resourceList){
        if (mPlayer.getCurrentTransport().getBag().getFreeSpace() < resourceList.size()){
            return false;
        } else {
            for (Resource resource : resourceList){
                addResourceToPlayerTransportBag(resource);
            }
            return true;
        }
    }

    public List<Resource> getResourceListFromPlayerBag(){
        return mPlayer.getCurrentBag().getResourceList();
    }

    public List<Resource> getResourceListFromPlayerTransportBag(){
        return mPlayer.getCurrentTransport().getBag().getResourceList();
    }

    public Bag getPlayerBag(){
        return mPlayer.getCurrentBag();
    }

    public Transport getPlayerTransport(){
        return mPlayer.getCurrentTransport();
    }







    // ИНТЕРФЕЙС СНАРЯЖЕНИЯ

    public HeadClothes getCurrentHeadClothes(){
        return mPlayer.getCurrentHeadClothes();
    }

    public BodyClothes getCurrentBodyClothes(){
        return mPlayer.getCurrentBodyClothes();
    }

    public LegsClothes getCurrentLegsClothes(){
        return mPlayer.getCurrentLegsClothes();
    }

    public FeetClothes getCurrentFeetClothes(){
        return mPlayer.getCurrentFeetClothes();
    }

    public Bag getCurrentBag(){
        return mPlayer.getCurrentBag();
    }

    public Transport getCurrentTransport(){
        return mPlayer.getCurrentTransport();
    }

    public Weapon getCurrentFirstWeapon(){
        return mPlayer.getCurrentFirstWeapon();
    }

    public Weapon getCurrentSecondWeapon(){
        return mPlayer.getCurrentSecondWeapon();
    }


    public void setCurrentHeadClothes(HeadClothes headClothes){
        if (headClothes == null){
            return;
        }
        headClothes.deleteOwner();
        headClothes.addPlayerOwner(mPlayer);
    }

    public void setCurrentBodyClothes(BodyClothes bodyClothes){
        if (bodyClothes == null){
            return;
        }
        bodyClothes.deleteOwner();
        bodyClothes.addPlayerOwner(mPlayer);
    }

    public void setCurrentLegsClothes(LegsClothes legsClothes){
        if (legsClothes == null){
            return;
        }
        legsClothes.deleteOwner();
        legsClothes.addPlayerOwner(mPlayer);
    }

    public void setCurrentFeetClothes(FeetClothes feetClothes){
        if (feetClothes == null){
            return;
        }
        feetClothes.deleteOwner();
        feetClothes.addPlayerOwner(mPlayer);
    }

    public void setCurrentFirstWeapon(Weapon weapon){
        if (weapon == null){
            return;
        }
        weapon.deleteOwner();
        weapon.addPlayerOwner(mPlayer, Player.FIRST_SLOT);
    }

    public void setCurrentSecondWeapon(Weapon weapon){
        if (weapon == null){
            return;
        }
        weapon.deleteOwner();
        weapon.addPlayerOwner(mPlayer, Player.SECOND_SLOT);
    }

    public void setCurrentTransport(Transport transport){
        if (transport == null){
            return;
        }
        transport.deleteOwner();
        transport.addPlayerOwner(mPlayer);
    }

    public void setCurrentBag(Bag bag){
        if (bag == null){
            return;
        }
        bag.deleteOwner();
        bag.addPlayerOwner(mPlayer);
    }


    //Снять текущую экипировку
    //Снять защиту головы
    private HeadClothes removeCurrentHeadClothes(){
        HeadClothes headClothes = mPlayer.getCurrentHeadClothes();
        mPlayer.setCurrentHeadClothes(null, true);
        return headClothes;
    }

    //Снять защиту тела
    private BodyClothes removeCurrentBodyClothes(){
        BodyClothes bodyClothes = mPlayer.getCurrentBodyClothes();
        mPlayer.setCurrentBodyClothes(null, true);
        return bodyClothes;
    }

    //Снять защиту с ног
    private LegsClothes removeCurrentLegsClothes(){
        LegsClothes legsClothes = mPlayer.getCurrentLegsClothes();
        mPlayer.setCurrentLegsClothes(null, true);
        return legsClothes;
    }

    //Снять защиту с ступней
    private FeetClothes removeCurrentFeetClothes(){
        FeetClothes feetClothes = mPlayer.getCurrentFeetClothes();
        mPlayer.setCurrentFeetClothes(null, true);
        return feetClothes;
    }

    //Снять оружие из слота 1
    private Weapon removeCurrentFirstWeapon(){
        Weapon weapon = mPlayer.getCurrentFirstWeapon();
        mPlayer.setCurrentFirstWeapon(null, true);
        return weapon;
    }

    //Снять оружие из слота 2
    private Weapon removeCurrentSecondWeapon(){
        Weapon weapon = mPlayer.getCurrentSecondWeapon();
        mPlayer.setCurrentSecondWeapon(null, true);
        return weapon;
    }

    //Убрать машину
    private Transport removeCurrentTransport(){
        Transport transport = mPlayer.getCurrentTransport();
        mPlayer.setCurrentTransport(null, true);
        return transport;
    }

    //убрать рюкзак
    private Bag removeCurrentBag(){
        Bag bag = mPlayer.getCurrentBag();
        mPlayer.setCurrentBag(null, true);
        return bag;
    }

























    public List<Resource> getPlayerResourceListInBag(){
        return mPlayer.getCurrentBag().getResourceList();
    }

    public void deleteResourceFromBag(Resource resource){
        mPlayer.getCurrentBag().deleteResource(resource);
    }

    public boolean addResourceToBag(Resource resource){
        return mPlayer.getCurrentBag().putResource(resource);
    }

    //Снять и положить в сумку текущую экипировку
    //Снять и положить в сумку защиту головы
    public boolean putInBagCurrentHeadClothes(){
        if (mPlayer.getCurrentBag().getFreeSpace() == 0){
            return false;
        } else {
            HeadClothes headClothes = removeCurrentHeadClothes();
            return mPlayer.getCurrentBag().putResource(headClothes);
        }
    }

    //Снять и положить в сумку защиту тела
    public boolean putInBagCurrentBodyClothes(){
        if (mPlayer.getCurrentBag().getFreeSpace() == 0){
            return false;
        } else {
            BodyClothes bodyClothes = removeCurrentBodyClothes();
            return mPlayer.getCurrentBag().putResource(bodyClothes);
        }
    }

    //Снять и положить в сумку защиту ног
    public boolean putInBagCurrentLegsClothes(){
        if (mPlayer.getCurrentBag().getFreeSpace() == 0) {
            return false;
        } else {
            LegsClothes legsClothes = removeCurrentLegsClothes();
            return mPlayer.getCurrentBag().putResource(legsClothes);
        }
    }

    //Снять и положить в сумку защиту ступней
    public boolean putInBagCurrentFeetClothes(){
        if (mPlayer.getCurrentBag().getFreeSpace() == 0){
            return false;
        } else {
            FeetClothes feetClothes = removeCurrentFeetClothes();
            return mPlayer.getCurrentBag().putResource(feetClothes);
        }
    }

    //Снять и положить в сумку оружие из слота 2
    public boolean putInBagCurrentFirstWeapon(){
        if (mPlayer.getCurrentBag().getFreeSpace() == 0){
            return false;
        } else {
            Weapon weapon = removeCurrentFirstWeapon();
            return mPlayer.getCurrentBag().putResource(weapon);
        }
    }

    //Снять и положить в сумку оружие из слота 1
    public boolean putInBagCurrentSecondWeapon(){
        if (mPlayer.getCurrentBag().getFreeSpace() == 0){
            return false;
        } else {
            Weapon weapon = removeCurrentSecondWeapon();
            return mPlayer.getCurrentBag().putResource(weapon);
        }
    }


    //Поменять сумку если поместилиь не все ресурсы то вернуть их
    public List<Resource> changeBag(Bag bag){
        List<Resource> excessResourceList = new LinkedList<>();
        for (Resource resource : mPlayer.getCurrentBag().getResourceList()){
            if (!bag.putResource(resource)) {
                excessResourceList.add(resource);
            }
        }

        mPlayer.setCurrentBag(bag, true);

        return excessResourceList;
    }

    //Выкинуть текущую экипировку

    //Выкинуть текущую защиту головы
    public HeadClothes throwOutCurrentHeadClothes(){
        return removeCurrentHeadClothes();
    }

    //Выкинуть текущую защиту тела
    public BodyClothes throwOutCurrentBodyClothes(){
        return removeCurrentBodyClothes();
    }

    //Выкинуть текущую защиту ног
    public LegsClothes throwOutCurrentLegsClothes(){
        return removeCurrentLegsClothes();
    }

    //Выкинуть текущую защиту ступней
    public FeetClothes throwOutCurrentFeetClothes(){
        return removeCurrentFeetClothes();
    }

    //Выкинуть текущее оружие из слота 1
    public Weapon throwOutCurrentFirstWeapon(){
        return removeCurrentFirstWeapon();
    }

    //Выкинуть текущее оружие из слота 2
    public Weapon throwOutCurrentSecondWeapon(){
        return removeCurrentSecondWeapon();
    }


    public void addToBd(){
        mPlayer.add();
    }

    public void update(){
        mPlayer.update();
    }

    public void updatePlayerBag(){
        mPlayer.getCurrentBag().update();
    }

}
