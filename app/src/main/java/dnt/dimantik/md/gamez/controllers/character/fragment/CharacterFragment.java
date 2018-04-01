package dnt.dimantik.md.gamez.controllers.character.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.controllers.bag.fragment.BagFragment;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowBagDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowClothesDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowTransportDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowWeaponDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.Showable;
import dnt.dimantik.md.gamez.game.logic.AssertResourceName;
import dnt.dimantik.md.gamez.game.logic.GameInterface;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

public class CharacterFragment extends Fragment implements View.OnClickListener {

    private static final String SHOW_EQUIPMENT = "SE";
    private static final int REQUEST_SHOW_CURRENT_RESOURCE = 0;

    private View mView;

    private GameInterface mGameInterface;

    private ProgressBar mGameProgress;
    private TextView mProgressText;

    private ImageView mPlayerImage;

    private TextView mGameTime;
    private ImageView mGameTimeImage;

    private TextView mPlayerName;

    private ImageView mPowerImage;
    private TextView mPlayerPower;

    private ImageView mProtectionImage;
    private TextView mPlayerProtection;

    private ImageView mKillSkilImage;
    private TextView mPlayerKillSkil;

    private ProgressBar mFoodProgressBar;
    private ImageView mFoodImage;
    private TextView mProgressFoodText;

    private ProgressBar mDrinkProgressBar;
    private ImageView mDrinkImage;
    private TextView mProgressDrinkText;

    private ProgressBar mHealthProgressBar;
    private ImageView mHealthImage;
    private TextView mProgressHealthText;

    private ProgressBar mEnergyProgressBar;
    private ImageView mEnergyImage;
    private TextView mProgressEnergyText;

    private ImageView mHeadClothesImage;
    private CardView mCurrentHeadClothes;

    private ImageView mBodyClothesImage;
    private CardView mCurrentBodyClothes;

    private ImageView mLegsClothesImage;
    private CardView mCurrentLegsClothes;

    private ImageView mFeetClothesImage;
    private CardView mCurrentFeetClothes;

    private ImageView mTransportImage;
    private CardView mCurrentTransport;

    private ImageView mBagImage;
    private CardView mCurrentBag;

    private ImageView mFirstWeaponImage;
    private CardView mCurrentFirstWeapon;

    private ImageView mSecondWeaponImage;
    private CardView mCurrentSecondWeapon;


    public static CharacterFragment newInstance() {
        CharacterFragment fragment = new CharacterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGameInterface = ((MainActivity) getActivity()).getGameInterface();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_character, container, false);

        findView();
        setOnClickListener();

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateUI();
    }

    private void findView() {
        mGameProgress = (ProgressBar) mView.findViewById(R.id.game_progress);
        mProgressText = (TextView) mView.findViewById(R.id.progress_text);

        mGameTime = (TextView) mView.findViewById(R.id.game_time);
        mGameTimeImage = (ImageView) mView.findViewById(R.id.game_time_image);

        mPlayerImage = (ImageView) mView.findViewById(R.id.player_image);
        mPlayerName = (TextView) mView.findViewById(R.id.player_name);

        mPowerImage = (ImageView) mView.findViewById(R.id.power_image);
        mPlayerPower = (TextView) mView.findViewById(R.id.player_power);

        mProtectionImage = (ImageView) mView.findViewById(R.id.protection_image);
        mPlayerProtection = (TextView) mView.findViewById(R.id.player_protection);

        mKillSkilImage = (ImageView) mView.findViewById(R.id.kill_skill_image);
        mPlayerKillSkil = (TextView) mView.findViewById(R.id.player_kill_skill);

        mDrinkProgressBar = (ProgressBar) mView.findViewById(R.id.drink_progress_bar);
        mDrinkImage = (ImageView) mView.findViewById(R.id.drink_image);
        mProgressDrinkText = (TextView) mView.findViewById(R.id.progress_drink_text);

        mHealthProgressBar = (ProgressBar) mView.findViewById(R.id.health_progress_bar);
        mHealthImage = (ImageView) mView.findViewById(R.id.health_image);
        mProgressHealthText = (TextView) mView.findViewById(R.id.progress_health_text);

        mFoodProgressBar = (ProgressBar) mView.findViewById(R.id.food_progress_bar);
        mFoodImage = (ImageView) mView.findViewById(R.id.food_image);
        mProgressFoodText = (TextView) mView.findViewById(R.id.progress_food_text);

        mEnergyProgressBar = (ProgressBar) mView.findViewById(R.id.energy_progress_bar);
        mEnergyImage = (ImageView) mView.findViewById(R.id.energy_image);
        mProgressEnergyText = (TextView) mView.findViewById(R.id.progress_energy_text);

        mCurrentHeadClothes = (CardView) mView.findViewById(R.id.current_head_clothes);
        mHeadClothesImage = (ImageView) mView.findViewById(R.id.head_clothes_image);

        mCurrentBodyClothes = (CardView) mView.findViewById(R.id.current_body_clothes);
        mBodyClothesImage = (ImageView) mView.findViewById(R.id.body_clothes_image);

        mCurrentLegsClothes = (CardView) mView.findViewById(R.id.current_legs_clothes);
        mLegsClothesImage = (ImageView) mView.findViewById(R.id.legs_clothes_image);

        mCurrentFeetClothes = (CardView) mView.findViewById(R.id.current_feet_clothes);
        mFeetClothesImage = (ImageView) mView.findViewById(R.id.feet_clothes_image);

        mCurrentTransport = (CardView) mView.findViewById(R.id.current_transport);
        mTransportImage = (ImageView) mView.findViewById(R.id.transport_image);

        mCurrentBag = (CardView) mView.findViewById(R.id.current_bag);
        mBagImage = (ImageView) mView.findViewById(R.id.bag_image);

        mCurrentFirstWeapon = (CardView) mView.findViewById(R.id.current_first_weapon);
        mFirstWeaponImage = (ImageView) mView.findViewById(R.id.first_weapon_image);

        mCurrentSecondWeapon = (CardView) mView.findViewById(R.id.current_second_weapon);
        mSecondWeaponImage = (ImageView) mView.findViewById(R.id.second_weapon_image);
    }

    private void updateUI() {
        fillViews();
        fillIcons();
    }

    private void fillIcons() {
        if (mGameInterface.getPlayer().getCurrentHeadClothes() != null) {
            Assistant.fillImage(getContext(), mHeadClothesImage, mGameInterface.getPlayerHeadClothes().getAssertDrawable());
        }
        if (mGameInterface.getPlayer().getCurrentBodyClothes() != null) {
            Assistant.fillImage(getContext(), mBodyClothesImage, mGameInterface.getPlayerBodyClothes().getAssertDrawable());
        }
        if (mGameInterface.getPlayer().getCurrentLegsClothes() != null) {
            Assistant.fillImage(getContext(), mLegsClothesImage, mGameInterface.getPlayerLegsClothes().getAssertDrawable());
        }
        if (mGameInterface.getPlayer().getCurrentFeetClothes() != null) {
            Assistant.fillImage(getContext(), mFeetClothesImage, mGameInterface.getPlayerFeetClothes().getAssertDrawable());
        }
        if (mGameInterface.getPlayer().getCurrentFirstWeapon() != null) {
            Assistant.fillImage(getContext(), mFirstWeaponImage, mGameInterface.getPlayerFirstWeapon().getAssertDrawable());
        }
        if (mGameInterface.getPlayer().getCurrentSecondWeapon() != null) {
            Assistant.fillImage(getContext(), mSecondWeaponImage, mGameInterface.getPlayerSecondWeapon().getAssertDrawable());
        }
        if (mGameInterface.getPlayer().getCurrentBag() != null) {
            Assistant.fillImage(getContext(), mBagImage, mGameInterface.getPlayerBag().getAssertDrawable());
        }
        if (mGameInterface.getPlayer().getCurrentTransport() != null) {
            Assistant.fillImage(getContext(), mTransportImage, mGameInterface.getPlayerTransport().getAssertDrawable());
        }
        ImageView imageView = (ImageView)mView.findViewById(R.id.power_image);
        Assistant.fillImage(getContext(), imageView, AssertResourceName.POWER);
        imageView = (ImageView)mView.findViewById(R.id.protection_image) ;
        Assistant.fillImage(getContext(), imageView, AssertResourceName.PROTECTION);
        imageView = (ImageView)mView.findViewById(R.id.kill_skill_image) ;
        Assistant.fillImage(getContext(), imageView, AssertResourceName.MURDER_SKILL);
        imageView = (ImageView)mView.findViewById(R.id.player_image) ;
        Assistant.fillImage(getContext(), imageView, AssertResourceName.MAIN_HERO);
    }

    private void fillViews() {
        mProgressText.setText("День " + mGameInterface.getMapInterface().getCurrentDay() + " из " + mGameInterface.getMapInterface().getLastDay());
        mGameProgress.setMax(mGameInterface.getMapInterface().getLastDay());
        mGameProgress.setProgress(mGameInterface.getMapInterface().getCurrentDay());

        mGameTime.setText(Assistant.getTime(mGameInterface.getMapInterface().getAllMinutes()));

        mPlayerName.setText(mGameInterface.getPlayer().getName());

        mPlayerPower.setText("Сила: " + mGameInterface.getPlayer().getPower());

        mPlayerProtection.setText("Защита: " + mGameInterface.getPlayer().getProtection());

        mPlayerKillSkil.setText("Навык убийства: " + mGameInterface.getPlayer().getMurderSkill());

        mFoodProgressBar.setMax(100);
        mFoodProgressBar.setProgress(mGameInterface.getPlayer().getSatiety());
        mProgressFoodText.setText(mGameInterface.getPlayer().getSatiety() + "/100");

        mDrinkProgressBar.setMax(100);
        mDrinkProgressBar.setProgress(mGameInterface.getPlayer().getThirst());
        mProgressDrinkText.setText(mGameInterface.getPlayer().getThirst() + "/100");

        mHealthProgressBar.setMax(100);
        mHealthProgressBar.setProgress(mGameInterface.getPlayer().getHealth());
        mProgressHealthText.setText(mGameInterface.getPlayer().getHealth() + "/100");

        mEnergyProgressBar.setMax(100);
        mEnergyProgressBar.setProgress(mGameInterface.getPlayer().getEnergy());
        mProgressEnergyText.setText(mGameInterface.getPlayer().getEnergy() + "/100");
    }

    private void setOnClickListener() {
        mCurrentTransport.setOnClickListener(this);
        mCurrentBag.setOnClickListener(this);
        mCurrentHeadClothes.setOnClickListener(this);
        mCurrentBodyClothes.setOnClickListener(this);
        mCurrentLegsClothes.setOnClickListener(this);
        mCurrentFeetClothes.setOnClickListener(this);
        mCurrentFirstWeapon.setOnClickListener(this);
        mCurrentSecondWeapon.setOnClickListener(this);
        Log.i("TAG", "Size CURRENT PLACE = " + mGameInterface.getCurrentPlace().getResourceList().size() + "/" + mGameInterface.getCurrentPlace().getResourceUUIDList().size());
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        Resource resource = null;
        String phase = null;
        switch (v.getId()) {
            case R.id.current_head_clothes:
                if (mGameInterface.getPlayerHeadClothes() != null) {
                    resource = mGameInterface.getPlayerHeadClothes();
                    phase = Showable.CURRENT;
                }
                break;
            case R.id.current_body_clothes:
                if (mGameInterface.getPlayerBodyClothes() != null) {
                    resource = mGameInterface.getPlayerBodyClothes();
                    phase = Showable.CURRENT;
                }
                break;
            case R.id.current_legs_clothes:
                if (mGameInterface.getPlayerLegsClothes() != null) {
                    resource = mGameInterface.getPlayerLegsClothes();
                    phase = Showable.CURRENT;
                }
                break;
            case R.id.current_feet_clothes:
                if (mGameInterface.getPlayerFeetClothes() != null) {
                    resource = mGameInterface.getPlayerFeetClothes();
                    phase = Showable.CURRENT;
                }
                break;
            case R.id.current_bag:
                if (mGameInterface.getPlayer().getCurrentBag() != null) {
                    resource = mGameInterface.getPlayerBag();
                    phase = Showable.CURRENT;
                }
                break;
            case R.id.current_first_weapon:
                if (mGameInterface.getPlayer().getCurrentFirstWeapon() != null) {
                    resource = mGameInterface.getPlayerFirstWeapon();
                    phase = Showable.CURRENT_FIRST;
                }
                break;
            case R.id.current_second_weapon:
                if (mGameInterface.getPlayer().getCurrentSecondWeapon() != null) {
                    resource = mGameInterface.getPlayerSecondWeapon();
                    phase = Showable.CURRENT_SECOND;
                }
                break;
            case R.id.current_transport:
                if (mGameInterface.getPlayer().getCurrentTransport() != null) {
                    resource = mGameInterface.getPlayerTransport();
                    phase = Showable.CURRENT;
                }
                break;
        }

        DialogFragment dialog = Assistant.getFragmentForShowResource(resource, phase);
        if (dialog == null){
            return;
        }
        dialog.setTargetFragment(CharacterFragment.this, REQUEST_SHOW_CURRENT_RESOURCE);
        dialog.show(getFragmentManager(), Showable.CURRENT);
    }

}
