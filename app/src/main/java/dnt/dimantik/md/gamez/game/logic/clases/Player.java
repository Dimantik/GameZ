package dnt.dimantik.md.gamez.game.logic.clases;

import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Bag;
import dnt.dimantik.md.gamez.game.logic.clases.resource.BodyClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FeetClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.HeadClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.LegsClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Transport;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Weapon;

/**
 * Created by dimantik on 10/19/17.
 */

public class Player implements BDInterface, EquipmentOwner {

    private String mName;
    private UUID mUUID;

    private int mPower;
    private int mProtection;
    private double mMurderSkill;

    private int mHealth;
    private int mSatiety;
    private int mThirst;
    private int mEnergy;

    private HeadClothes mCurrentHeadClothes;
    private BodyClothes mCurrentBodyClothes;
    private FeetClothes mCurrentFeetClothes;
    private LegsClothes mCurrentLegsClothes;
    private UUID mCurrentHeadClothesUUID;
    private UUID mCurrentBodyClothesUUID;
    private UUID mCurrentFeetClothesUUID;
    private UUID mCurrentLegsClothesUUID;

    private Weapon mCurrentFirstWeapon;
    private Weapon mCurrentSecondWeapon;
    private UUID mCurrentFirstWeaponUUID;
    private UUID mCurrentSecondWeaponUUID;

    private Transport mCurrentTransport;
    private UUID mCurrentTransportUUID;

    private Bag mCurrentBag;
    private UUID mCurrentBagUUID;

    Player(UUID uuid, String name){
        mUUID = uuid;
        mName = name;
    }
    public Player(String name){
        this(UUID.randomUUID(), name);
    }

    private void countProtection(){
        mProtection = 0;
        if (mCurrentHeadClothes != null){
            mProtection += mCurrentHeadClothes.getProtection();
        }
        if (mCurrentBodyClothes != null){
            mProtection += mCurrentBodyClothes.getProtection();
        }
        if (mCurrentLegsClothes != null){
            mProtection += mCurrentLegsClothes.getProtection();
        }
        if (mCurrentFeetClothes != null){
            mProtection += mCurrentFeetClothes.getProtection();
        }
    }


    public void sleep(int minutes){
        mEnergy += (minutes/6);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPower() {
        return mPower;
    }

    public void setPower(int power) {
        mPower = power;
    }

    public int getProtection() {
        return mProtection;
    }

    public void setProtection(int protection) {
        mProtection = protection;
    }

    public double getMurderSkill() {
        return mMurderSkill;
    }

    public void setMurderSkill(double murderSkill) {
        mMurderSkill = murderSkill;
    }

    public int getHealth() {
        return mHealth;
    }

    public void setHealth(int health) {
        mHealth = health;
    }

    public int getSatiety() {
        return mSatiety;
    }

    public void setSatiety(int satiety) {
        mSatiety = satiety;
    }

    public int getThirst() {
        return mThirst;
    }

    public void setThirst(int thirst) {
        mThirst = thirst;
    }

    public int getEnergy() {
        return mEnergy;
    }

    public void setEnergy(int energy) {
        mEnergy = energy;
    }

    public HeadClothes getCurrentHeadClothes() {
        return mCurrentHeadClothes;
    }

    public void setCurrentHeadClothes(HeadClothes currentHeadClothes, boolean setUUID) {
        mCurrentHeadClothes = currentHeadClothes;
        if (setUUID){
            if (currentHeadClothes == null){
                mCurrentHeadClothesUUID = null;
            } else {
                mCurrentHeadClothesUUID = mCurrentHeadClothes.getId();
            }
        }
    }

    public BodyClothes getCurrentBodyClothes() {
        return mCurrentBodyClothes;
    }

    public void setCurrentBodyClothes(BodyClothes currentBodyClothes, boolean setUUID) {
        mCurrentBodyClothes = currentBodyClothes;
        if (setUUID){
            if (currentBodyClothes == null){
                mCurrentBodyClothesUUID = null;
            } else {
                mCurrentBodyClothesUUID = mCurrentBodyClothes.getId();
            }
        }
    }

    public FeetClothes getCurrentFeetClothes() {
        return mCurrentFeetClothes;
    }

    public void setCurrentFeetClothes(FeetClothes currentFeetClothes, boolean setUUID) {
        mCurrentFeetClothes = currentFeetClothes;
        if (setUUID){
            if (currentFeetClothes == null){
                mCurrentFeetClothesUUID = null;
            } else {
                mCurrentFeetClothesUUID = mCurrentFeetClothes.getId();
            }
        }
    }

    public LegsClothes getCurrentLegsClothes() {
        return mCurrentLegsClothes;
    }

    public void setCurrentLegsClothes(LegsClothes currentLegsClothes, boolean setUUID) {
        mCurrentLegsClothes = currentLegsClothes;
        if (setUUID){
            if (currentLegsClothes == null){
                mCurrentLegsClothesUUID = null;
            } else {
                mCurrentLegsClothesUUID = mCurrentLegsClothes.getId();
            }
        }
    }

    public Weapon getCurrentFirstWeapon() {
        return mCurrentFirstWeapon;
    }

    public void setCurrentFirstWeapon(Weapon currentFirstWeapon, boolean setUUID) {
        mCurrentFirstWeapon = currentFirstWeapon;
        if (setUUID){
            if (currentFirstWeapon == null){
                mCurrentFirstWeaponUUID = null;
            } else {
                mCurrentFirstWeaponUUID = mCurrentFirstWeapon.getId();
            }
        }
    }

    public Weapon getCurrentSecondWeapon() {
        return mCurrentSecondWeapon;
    }

    public void setCurrentSecondWeapon(Weapon currentSecondWeapon, boolean setUUID) {
        mCurrentSecondWeapon = currentSecondWeapon;
        if (setUUID){
            if (currentSecondWeapon == null){
                mCurrentSecondWeaponUUID = null;
            } else {
                mCurrentSecondWeaponUUID = mCurrentSecondWeapon.getId();
            }
        }
    }

    public Transport getCurrentTransport() {
        return mCurrentTransport;
    }

    public void setCurrentTransport(Transport currentTransport, boolean setUUID) {
        mCurrentTransport = currentTransport;
        if (setUUID){
            if (currentTransport == null){
                mCurrentTransportUUID = null;
            } else {
                mCurrentTransportUUID = mCurrentTransport.getId();
            }
        }
    }

    public Bag getCurrentBag() {
        return mCurrentBag;
    }

    public void setCurrentBag(Bag currentBag, boolean setUUID) {
        mCurrentBag = currentBag;
        if (setUUID){
            if (currentBag == null){
                mCurrentBagUUID = null;
            } else {
                mCurrentBagUUID = mCurrentBag.getId();
            }
        }
    }

    public UUID getCurrentHeadClothesUUID() {
        return mCurrentHeadClothesUUID;
    }

    public void setCurrentHeadClothesUUID(UUID currentHeadClothesUUID) {
        mCurrentHeadClothesUUID = currentHeadClothesUUID;
    }

    public UUID getCurrentBodyClothesUUID() {
        return mCurrentBodyClothesUUID;
    }

    public void setCurrentBodyClothesUUID(UUID currentBodyClothesUUID) {
        mCurrentBodyClothesUUID = currentBodyClothesUUID;
    }

    public UUID getCurrentFeetClothesUUID() {
        return mCurrentFeetClothesUUID;
    }

    public void setCurrentFeetClothesUUID(UUID currentFeetClothesUUID) {
        mCurrentFeetClothesUUID = currentFeetClothesUUID;
    }

    public UUID getCurrentLegsClothesUUID() {
        return mCurrentLegsClothesUUID;
    }

    public void setCurrentLegsClothesUUID(UUID currentLegsClothesUUID) {
        mCurrentLegsClothesUUID = currentLegsClothesUUID;
    }

    public UUID getCurrentFirstWeaponUUID() {
        return mCurrentFirstWeaponUUID;
    }

    public void setCurrentFirstWeaponUUID(UUID currentFirstWeaponUUID) {
        mCurrentFirstWeaponUUID = currentFirstWeaponUUID;
    }

    public UUID getCurrentSecondWeaponUUID() {
        return mCurrentSecondWeaponUUID;
    }

    public void setCurrentSecondWeaponUUID(UUID currentSecondWeaponUUID) {
        mCurrentSecondWeaponUUID = currentSecondWeaponUUID;
    }

    public UUID getCurrentTransportUUID() {
        return mCurrentTransportUUID;
    }

    public void setCurrentTransportUUID(UUID currentTransportUUID) {
        mCurrentTransportUUID = currentTransportUUID;
    }

    public UUID getCurrentBagUUID() {
        return mCurrentBagUUID;
    }

    public void setCurrentBagUUID(UUID currentBagUUID) {
        mCurrentBagUUID = currentBagUUID;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
    }

    @Override
    public void update() {
        BDHelper.updatePlayer(this);
    }

    @Override
    public void delete() {
        BDHelper.deletePlayer(this);
    }

    @Override
    public void add() {
        BDHelper.addPlayer(this);
    }

    @Override
    public void deleteCurrentHeadClothes(HeadClothes headClothes) {
        if (headClothes == mCurrentHeadClothes){
            setCurrentHeadClothes(null, true);
            countProtection();
        }
    }

    @Override
    public void deleteCurrentBodyClothes(BodyClothes bodyClothes) {
        if (bodyClothes == mCurrentBodyClothes){
            setCurrentBodyClothes(null, true);
            countProtection();
        }
    }

    @Override
    public void deleteCurrentLegsClothes(LegsClothes legsClothes) {
        if (legsClothes == mCurrentLegsClothes){
            setCurrentLegsClothes(null, true);
            countProtection();
        }
    }

    @Override
    public void deleteCurrentFeetClothes(FeetClothes feetClothes) {
        if (feetClothes == mCurrentFeetClothes){
            setCurrentFeetClothes(null, true);
            countProtection();
        }
    }

    @Override
    public void deleteCurrentWeapon(Weapon weapon) {
        if (mCurrentFirstWeapon == weapon){
            setCurrentFirstWeapon(null, true);
        } else if (mCurrentSecondWeapon == weapon) {
            setCurrentSecondWeapon(null, true);
        }
    }

    @Override
    public void deleteCurrentBag(Bag bag) {
        setCurrentBag(null, true);
    }

    @Override
    public void deleteCurrentTransport(Transport transport) {
        setCurrentTransport(null, true);
    }

    @Override
    public void addCurrentHeadClothes(HeadClothes headClothes) {
        setCurrentHeadClothes(headClothes, true);
    }

    @Override
    public void addCurrentBodyClothes(BodyClothes bodyClothes) {
        setCurrentBodyClothes(bodyClothes, true);
    }

    @Override
    public void addCurrentLegsClothes(LegsClothes legsClothes) {
        setCurrentLegsClothes(legsClothes, true);
    }

    @Override
    public void addCurrentFeetClothes(FeetClothes feetClothes) {
        setCurrentFeetClothes(feetClothes, true);
    }

    public final static int FIRST_SLOT = 1;
    public final static int SECOND_SLOT = 2;

    @Override
    public void addCurrentWeapon(Weapon weapon, int slot) {
        switch (slot){
            case FIRST_SLOT:
                setCurrentFirstWeapon(weapon, true);
                break;
            case SECOND_SLOT:
                setCurrentSecondWeapon(weapon, true);
                break;
        }
    }

    @Override
    public void addCurrentBag(Bag bag) {
        setCurrentBag(bag, true);
    }

    @Override
    public void addCurrentTransport(Transport transport) {
        setCurrentTransport(transport, true);
    }
}
