package dnt.dimantik.md.gamez.controllers.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.game.logic.GameInterface;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Clothes;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

/**
 * Created by dimantik on 1/20/18.
 */

public class ShowClothesDialog extends DialogFragment implements Showable{

    private static final String PHASE = "PHASE";
    private static final String EQUIPMENT_UUID = "EU";

    private View mView;
    private Button mFirstActButton;
    private Button mSecondActButton;
    private Button mThirdButton;

    private GameInterface mGameInterface;

    private Clothes mClothes;
    private String mPhase;

    public static ShowClothesDialog getInstance(String equipmentUUID, String phase){
        ShowClothesDialog fragment = new ShowClothesDialog();

        Bundle args = new Bundle();
        args.putSerializable(PHASE, phase);
        args.putSerializable(EQUIPMENT_UUID, equipmentUUID);
        fragment.setArguments(args);

        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        getResource();
        setup();
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setView(mView);
        return adb.create();
    }

    private void getResource(){
        mGameInterface = ((MainActivity)getActivity()).getGameInterface();
        mClothes = (Clothes)mGameInterface.getResource(UUID.fromString((String)getArguments().getSerializable(EQUIPMENT_UUID)));
        mPhase = (String) getArguments().getSerializable(PHASE);
    }

    private void setup(){
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_resource_with_1_text_3_button, null, false);

        TextView resourceName = (TextView)mView.findViewById(R.id.resource_name);
        TextView firstCharacteristic = (TextView)mView.findViewById(R.id.first_characteristic);

        resourceName.setText(mClothes.getName());
        String message = "Защита:  "  + mClothes.getProtection();
        firstCharacteristic.setText(message);

        mFirstActButton = (Button)mView.findViewById(R.id.first_act_button);
        mSecondActButton = (Button)mView.findViewById(R.id.second_act_button);
        mThirdButton = (Button)mView.findViewById(R.id.third_act_button);

        switch (mPhase){
            case CURRENT:
                setupIfCurrent();
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
        }

        ImageView imageView = (ImageView)mView.findViewById(R.id.resource_image);
        Assistant.fillImage(getContext(), imageView, mClothes.getAssertDrawable());
    }

    private void setupIfCurrent(){
        mFirstActButton.setText("Положить в сумку");
        mSecondActButton.setText("Положить в машину");
        mThirdButton.setText("Выкинуть");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!putInPlayerBag()){
                    Toast.makeText(getContext(), "Не хватает места в рюкзаке!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!putInTransport()){
                    Toast.makeText(getContext(), "Не хватает места в машину!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mThirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInResourceListInCurrentPlace();
            }
        });
    }

    private void setupIfInBag(){
        mFirstActButton.setText("Надеть");
        mSecondActButton.setText("Положить в машину");
        mThirdButton.setText("Выкинуть");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrent();
            }
        });
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!putInTransport()){
                    Toast.makeText(getContext(), "Не хватает места в машине!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mThirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInResourceListInCurrentPlace();
            }
        });
    }

    private void setupIfInTransportBag(){
        mFirstActButton.setText("Положить в сумку");
        mSecondActButton.setText("Надеть");
        mThirdButton.setText("Выкинуть");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!putInPlayerBag()){
                    Toast.makeText(getContext(), "Не хватает места в рюкзаке!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrent();
            }
        });
        mThirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInResourceListInCurrentPlace();
            }
        });
    }

    private void setupIfInFindResourceList(){
        mFirstActButton.setText("Положить в сумку");
        mSecondActButton.setText("Положить в машину");
        mThirdButton.setText("Надеть");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!putInPlayerBag()){
                    Toast.makeText(getContext(), "Не хватает места в рбкзаке!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!putInTransport()){
                    Toast.makeText(getContext(), "Не хватает места в машине!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mThirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrent();
            }
        });
    }

    private void setCurrent(){
        mGameInterface.setPlayerEquipment(mClothes);
        setToUpdate();
        getDialog().cancel();
    }

    private boolean putInPlayerBag(){
        boolean result = mGameInterface.addResourceToPlayerBag(mClothes, null);
        if (result){
            setToUpdate();
        }
        getDialog().cancel();
        return result;
    }

    private boolean putInTransport(){
        boolean result = mGameInterface.addResourceToPlayerTransportBag(mClothes, null);
        if (result){
            setToUpdate();
        }
        getDialog().cancel();
        return result;
    }

    private void putInResourceListInCurrentPlace(){
        mGameInterface.addResourceToCurrentPlace(mClothes, null);
        setToUpdate();
        getDialog().cancel();
    }

    private void setToUpdate(){
        if (getTargetFragment() == null){
            return;
        }
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, new Intent());
    }

}
