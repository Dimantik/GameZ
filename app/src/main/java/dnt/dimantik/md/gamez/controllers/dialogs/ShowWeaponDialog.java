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
import dnt.dimantik.md.gamez.game.logic.clases.Player;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FireArms;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Weapon;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

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
        setup();
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setView(mView);
        return adb.create();
    }

    private void getWeapon(){
        mGameInterface = ((MainActivity)getActivity()).getGameInterface();
        mWeapon = (Weapon) mGameInterface.getResource(UUID.fromString((String)getArguments().getSerializable(EQUIPMENT_UUID)));
        mPhase = (String) getArguments().getSerializable(PHASE);
    }

    private void setup(){
        if (mWeapon instanceof FireArms){
            mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_resource_with_2_text_4_button, null, false);
            TextView secondCharacteristic = (TextView)mView.findViewById(R.id.second_characteristic);
            String message = "Колличество патронов:  " + ((FireArms)mWeapon).getCartridgesQuantity();
            secondCharacteristic.setText(message);
        } else {
            mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_resource_with_1_text_4_button, null, false);
        }

        TextView weaponName = (TextView)mView.findViewById(R.id.resource_name);
        weaponName.setText(mWeapon.getName());

        TextView firstCharacteristic = (TextView)mView.findViewById(R.id.first_characteristic);
        String message = "Мощность:  " + mWeapon.getPower();
        firstCharacteristic.setText(message);

        ImageView imageView = (ImageView)mView.findViewById(R.id.resource_image);
        Assistant.fillImage(getContext(), imageView, mWeapon.getAssertDrawable());

        mFirstActButton = (Button)mView.findViewById(R.id.first_act_button);
        mSecondActButton = (Button)mView.findViewById(R.id.second_act_button);
        mThirdActButton = (Button)mView.findViewById(R.id.third_act_button);
        mFourthActButton = (Button)mView.findViewById(R.id.fourth_act_button);

        switch (mPhase){
            case CURRENT_FIRST:
                setupIfInCurrentFirst();
                break;
            case CURRENT_SECOND:
                setupIfInCurrentSecond();
                break;
            case IN_BAG:
                setupIfInBag();
                break;
            case IN_TRANSPORT_BAG:
                setupIfInTransportBag();
                break;
            case IN_FIND_RESOURCES:
                setupIfInFindResourceList();
                break;
            default:
                Toast.makeText(getContext(), "NO", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void setupIfInCurrentFirst(){
        mFirstActButton.setText("Поместить в слот 2");
        mSecondActButton.setText("Положить в сумку");
        mThirdActButton.setText("Положить в транспорт");
        mFourthActButton.setText("Выкинуть");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInSlot(Player.SECOND_SLOT);
            }
        });
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInBag();
            }
        });
        mThirdActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInTransportBag();
            }
        });
        mFourthActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throwOut();
            }
        });
    }

    private void setupIfInCurrentSecond(){
        mFirstActButton.setText("Поместить в слот 1");
        mSecondActButton.setText("Положить в сумку");
        mThirdActButton.setText("Положить в транспорт");
        mFourthActButton.setText("Выкинуть");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInSlot(Player.FIRST_SLOT);
            }
        });
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInBag();
            }
        });
        mThirdActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInTransportBag();
            }
        });
        mFourthActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throwOut();
            }
        });
    }

    private void setupIfInBag(){
        mFirstActButton.setText("Положить в слот 1");
        mSecondActButton.setText("Положить в слот 2");
        mThirdActButton.setText("Положить в транспорт");
        mFourthActButton.setText("Выкинуть");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInSlot(Player.FIRST_SLOT);
            }
        });
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInSlot(Player.SECOND_SLOT);
            }
        });
        mThirdActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInTransportBag();
            }
        });
        mFourthActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throwOut();
            }
        });
    }

    private void setupIfInTransportBag(){
        mFirstActButton.setText("Положить в слот 1");
        mSecondActButton.setText("Положить в слот 2");
        mThirdActButton.setText("Положить в сумку");
        mFourthActButton.setText("Выкинуть");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInSlot(Player.FIRST_SLOT);
            }
        });
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInSlot(Player.SECOND_SLOT);
            }
        });
        mThirdActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInBag();
            }
        });
        mFourthActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throwOut();
            }
        });
    }

    private void setupIfInFindResourceList(){
        mFirstActButton.setText("Положить в слот 1");
        mSecondActButton.setText("Положить в слот 2");
        mThirdActButton.setText("Положить в сумку");
        mFourthActButton.setText("Положить в трансопрт");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInSlot(Player.FIRST_SLOT);
            }
        });
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInSlot(Player.SECOND_SLOT);
            }
        });
        mThirdActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInBag();
            }
        });
        mFourthActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInTransportBag();
            }
        });
    }

    private void putInSlot(String flag){
        if (flag.equals(Player.FIRST_SLOT)){
            mGameInterface.setPlayerFirstWeapon(mWeapon);
        } else if (flag.equals(Player.SECOND_SLOT)) {
            mGameInterface.setPlayerSecondWeapon(mWeapon);
        }
        setUpdate();
        getDialog().cancel();
    }

    private void putInTransportBag(){
        boolean result = mGameInterface.addResourceToPlayerTransportBag(mWeapon, null);
        if (result){
            setUpdate();
            getDialog().cancel();
        } else {
            showMessage();
        }
    }

    private void putInBag(){
        boolean result = mGameInterface.addResourceToPlayerBag(mWeapon, null);
        if (result){
            setUpdate();
            getDialog().cancel();
        } else {
            showMessage();
        }
    }

    private void throwOut(){
        mGameInterface.addResourceToCurrentPlace(mWeapon, null);
        setUpdate();
        getDialog().cancel();
    }

    public void showMessage(){
        Toast.makeText(getContext(), "Недостаточно места!", Toast.LENGTH_SHORT).show();
    }

    private void setUpdate(){
        if (getTargetFragment() == null){
            return;
        }

        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
    }

}
