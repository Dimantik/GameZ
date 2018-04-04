package dnt.dimantik.md.gamez.controllers.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.game.logic.interfaces.GameInterface;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Cartridges;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

/**
 * Created by dimantik on 1/20/18.
 */

public class ShowCartridgesDialog extends DialogFragment implements Showable {

    public static final String PHASE = "PHASE";
    public static final String RESOURCE_UUID = "RU";

    private GameInterface mGameInterface;

    private Cartridges mCartridges;
    private String mPhase;

    private View mView;
    private Button mFirstActButton;
    private Button mSecondActButton;

    private TextView mFirstCharacteristic;

    public static ShowCartridgesDialog getInstance(UUID uuid, String phase){
        ShowCartridgesDialog fragment = new ShowCartridgesDialog();

        Bundle args = new Bundle();
        args.putSerializable(RESOURCE_UUID, uuid.toString());
        args.putString(PHASE, phase);
        fragment.setArguments(args);

        return fragment;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {

        getImportantResource();
        setup();
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setView(mView);
        return adb.create();
    }

    private void getImportantResource(){
        mGameInterface = ((MainActivity)getActivity()).getGameInterface();
        mCartridges = (Cartridges) mGameInterface.getResource(Assistant.convertToUUID(getArguments().getString(RESOURCE_UUID)));
        mPhase = getArguments().getString(PHASE);
    }

    private void setup(){
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_resource_with_1_text_2_button, null, false);

        TextView resourceName = (TextView)mView.findViewById(R.id.resource_name);
        resourceName.setText(mCartridges.getName());

        mFirstCharacteristic = (TextView)mView.findViewById(R.id.first_characteristic);
        String message = "Колличество:  " + mCartridges.getQuantity();
        mFirstCharacteristic.setText(message);

        mFirstActButton = (Button)mView.findViewById(R.id.first_act_button);
        mSecondActButton = (Button)mView.findViewById(R.id.second_act_button);


        ImageView imageView = (ImageView)mView.findViewById(R.id.resource_image);
        Assistant.fillImage(getContext(), imageView, mCartridges.getAssertDrawable());

        switch (mPhase){
            case IN_BAG:
                setButtonListenerIfInBag();
                break;
            case IN_TRANSPORT_BAG:
                setButtonListenerIfInTransportBag();
                break;
            case IN_FIND_RESOURCES:
                setButtonListenerIfInFindResources();
                break;
        }
    }


    private void setButtonListenerIfInBag(){
        mSecondActButton.setText("Выбросить");
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throwOut();
            }
        });
        mFirstActButton.setText("Положить в машину");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInTransportBag();
            }
        });
    }

    private void setButtonListenerIfInTransportBag(){
        mSecondActButton.setText("Выбросить");
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throwOut();
            }
        });
        mFirstActButton.setText("Положить в сумку");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInBag();
            }
        });
    }

    private void setButtonListenerIfInFindResources(){
        mFirstActButton.setText("Положить в сумку");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               putInBag();
            }
        });
        mSecondActButton.setText("Положить в машину");
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInTransportBag();
            }
        });
    }

    private void throwOut(){
        mGameInterface.addResourceToCurrentPlace(mCartridges, null);
        setUpdate();
        getDialog().cancel();
    }

    private void putInTransportBag(){
        boolean result = mGameInterface.addResourceToPlayerTransportBag(mCartridges, null);
        if (result){
            setUpdate();
            getDialog().cancel();
        } else {
            showMessage();
        }
    }

    private void putInBag(){
        boolean result = mGameInterface.addResourceToPlayerBag(mCartridges, null);
        if (result){
            setUpdate();
            getDialog().cancel();
        } else {
            showMessage();
        }
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
