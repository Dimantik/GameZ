package dnt.dimantik.md.gamez.game.logic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.clases.Owner;
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

    public boolean addResourceToPlayerBag(Resource resource, String flag){
        if (mPlayer.getCurrentBag().isPossibleToPut(resource, flag)) {
            resource.deleteOwner();
            resource.addOwner(mPlayer.getCurrentBag(), flag);
            return true;
        }
        return false;
    }

    public boolean addResourceListToPlayerBag(List<Resource> resourceList, String flag){
        if (mPlayer.getCurrentBag().getFreeSpace() < resourceList.size()){
            return false;
        } else {
            for (Resource resource : resourceList){
                addResourceToPlayerBag(resource, flag);
            }
            return true;
        }
    }

    public boolean addResourceToPlayerTransportBag(Resource resource, String flag){
        resource.deleteOwner();
        return resource.addOwner(mPlayer.getCurrentTransport().getBag(), flag);
    }

    public boolean addResourceListToPlayerTransportBag(List<Resource> resourceList, String flag){
        if (mPlayer.getCurrentTransport().getBag().getFreeSpace() < resourceList.size()){
            return false;
        } else {
            for (Resource resource : resourceList){
                addResourceToPlayerTransportBag(resource, flag);
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


    public void setCurrentHeadClothes(HeadClothes newHeadClothes){
        Owner ownerNewHeadClothes = newHeadClothes.deleteOwner();
        HeadClothes oldHeadClothes = getCurrentHeadClothes();
        if (oldHeadClothes != null){
            oldHeadClothes.deleteOwner();
            oldHeadClothes.addOwner(ownerNewHeadClothes, null);
        }
        newHeadClothes.addOwner(mPlayer, null);
    }

    public void setCurrentBodyClothes(BodyClothes newBodyClothes){
        Owner ownerNewBodyClothes = newBodyClothes.deleteOwner();
        BodyClothes oldBodyClothes = getCurrentBodyClothes();
        if (oldBodyClothes != null){
            oldBodyClothes.deleteOwner();
            oldBodyClothes.addOwner(ownerNewBodyClothes, null);
        }
        newBodyClothes.addOwner(mPlayer, null);
    }

    public void setCurrentLegsClothes(LegsClothes newLegsClothes){
        LegsClothes oldLegsClothes = getCurrentLegsClothes();
        Owner ownerNewLegsClothes = newLegsClothes.deleteOwner();
        if (oldLegsClothes != null){
            oldLegsClothes.deleteOwner();
            oldLegsClothes.addOwner(ownerNewLegsClothes, null);
        }
        newLegsClothes.addOwner(mPlayer, null);
    }

    public void setCurrentFeetClothes(FeetClothes newFeetClothes){
        FeetClothes oldFeetClothes = getCurrentFeetClothes();
        Owner ownerNewLegsClothes = newFeetClothes.deleteOwner();
        if (oldFeetClothes != null){
            oldFeetClothes.deleteOwner();
            oldFeetClothes.addOwner(ownerNewLegsClothes, null);
        }
        newFeetClothes.addOwner(mPlayer, null);
    }

    public void setCurrentFirstWeapon(Weapon newWeapon){
        Weapon oldWeapon = getCurrentFirstWeapon();
        Owner owner = newWeapon.deleteOwner();
        if (oldWeapon != null){
            oldWeapon.deleteOwner();
            oldWeapon.addOwner(owner, Player.SECOND_SLOT);
        }
        newWeapon.addOwner(mPlayer, Player.FIRST_SLOT);
    }

    public void setCurrentSecondWeapon(Weapon newWeapon){
        Weapon oldWeapon = getCurrentSecondWeapon();
        Owner owner = newWeapon.deleteOwner();
        if (oldWeapon != null){
            oldWeapon.deleteOwner();
            oldWeapon.addOwner(owner, Player.FIRST_SLOT);
        }
        newWeapon.addOwner(mPlayer, Player.SECOND_SLOT);
    }

    public void setCurrentTransport(Transport newTransport){
        Transport oldTransport = getCurrentTransport();
        Owner ownerNewTransport = newTransport.deleteOwner();
        Owner ownerOldTransport = oldTransport.deleteOwner();

        oldTransport.addOwner(ownerNewTransport, null);
        newTransport.addOwner(ownerOldTransport, null);
    }

    public void setCurrentBag(Bag newBag){
        Bag oldBag = getCurrentBag();
        Owner ownerNewBag = newBag.deleteOwner();
        Owner ownerOldBag = oldBag.deleteOwner();

        oldBag.addOwner(ownerNewBag, null);
        newBag.addOwner(ownerOldBag, null);
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
        return mPlayer.getCurrentBag().putResource(resource, null);
    }

    //Снять и положить в сумку текущую экипировку
    //Снять и положить в сумку защиту головы
    public boolean putInBagCurrentHeadClothes(){
        if (mPlayer.getCurrentBag().getFreeSpace() == 0){
            return false;
        } else {
            HeadClothes headClothes = removeCurrentHeadClothes();
            return mPlayer.getCurrentBag().putResource(headClothes, null);
        }
    }

    //Снять и положить в сумку защиту тела
    public boolean putInBagCurrentBodyClothes(){
        if (mPlayer.getCurrentBag().getFreeSpace() == 0){
            return false;
        } else {
            BodyClothes bodyClothes = removeCurrentBodyClothes();
            return mPlayer.getCurrentBag().putResource(bodyClothes, null);
        }
    }

    //Снять и положить в сумку защиту ног
    public boolean putInBagCurrentLegsClothes(){
        if (mPlayer.getCurrentBag().getFreeSpace() == 0) {
            return false;
        } else {
            LegsClothes legsClothes = removeCurrentLegsClothes();
            return mPlayer.getCurrentBag().putResource(legsClothes, null);
        }
    }

    //Снять и положить в сумку защиту ступней
    public boolean putInBagCurrentFeetClothes(){
        if (mPlayer.getCurrentBag().getFreeSpace() == 0){
            return false;
        } else {
            FeetClothes feetClothes = removeCurrentFeetClothes();
            return mPlayer.getCurrentBag().putResource(feetClothes, null);
        }
    }

    //Снять и положить в сумку оружие из слота 2
    public boolean putInBagCurrentFirstWeapon(){
        if (mPlayer.getCurrentBag().getFreeSpace() == 0){
            return false;
        } else {
            Weapon weapon = removeCurrentFirstWeapon();
            return mPlayer.getCurrentBag().putResource(weapon, null);
        }
    }

    //Снять и положить в сумку оружие из слота 1
    public boolean putInBagCurrentSecondWeapon(){
        if (mPlayer.getCurrentBag().getFreeSpace() == 0){
            return false;
        } else {
            Weapon weapon = removeCurrentSecondWeapon();
            return mPlayer.getCurrentBag().putResource(weapon, null);
        }
    }


    //Поменять сумку если поместилиь не все ресурсы то вернуть их
    public List<Resource> changeBag(Bag bag){
        List<Resource> excessResourceList = new LinkedList<>();
        for (Resource resource : mPlayer.getCurrentBag().getResourceList()){
            if (!bag.putResource(resource, null)) {
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
