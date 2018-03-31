package dnt.dimantik.md.gamez.controllers.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.game.logic.GameInterface;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FireArms;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Weapon;

/**
 * Created by dimantik on 1/20/18.
 */

public class ShowWeaponDialog extends DialogFragment implements Showable{

    private static final String PHASE = "PHASE";
    private static final String EQUIPMENT_UUID = "EU";


    private View mView;
    private Button mFirstActButton;
    private Button mSecondActButton;
    private Button mThirdActButton;
    private Button mFourthActButton;

    private GameInterface mGameInterface;

    private Weapon mWeapon;
    private String mPhase;
    private int mSlot;

    public static ShowWeaponDialog getInstance(String equipmentUUID, String phase){
        ShowWeaponDialog fragment = new ShowWeaponDialog();

        Bundle args = new Bundle();
        args.putSerializable(PHASE, phase);
        args.putSerializable(EQUIPMENT_UUID, equipmentUUID);
        fragment.setArguments(args);

        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        getWeapon();
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setView(mView);
        return adb.create();
    }

    private void getWeapon(){
        mGameInterface = ((MainActivity)getActivity()).getGameInterface();
        mWeapon = (Weapon) mGameInterface.getResource(UUID.fromString((String)getArguments().getSerializable(EQUIPMENT_UUID)));
        mPhase = (String) getArguments().getSerializable(PHASE);
        switch (mPhase){
            case CURRENT_FIRST:
                mSlot = 1;
                break;
            case CURRENT_SECOND:
                mSlot = 2;
                break;
        }
    }

    private void setup(){

    }

}
