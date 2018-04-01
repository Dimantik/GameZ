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

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.game.logic.GameInterface;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Bag;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

/**
 * Created by dimantik on 1/20/18.
 */

public class ShowBagDialog extends DialogFragment {

    private static final String PHASE = "PHASE";
    private static final String BAG_UUID = "EU";

    private View mView;
    private Button mFirstActButton;

    private GameInterface mGameInterface;

    private Bag mBag;

    private String mPhase;

    public static ShowBagDialog getInstance(String bagUUID, String phase){
        ShowBagDialog fragment = new ShowBagDialog();

        Bundle args = new Bundle();
        args.putSerializable(PHASE, phase);
        args.putSerializable(BAG_UUID, bagUUID);
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
        mBag = (Bag) mGameInterface.getResource(UUID.fromString((String)getArguments().getSerializable(BAG_UUID)));
        mPhase = (String) getArguments().getSerializable(PHASE);
    }

    private void setup(){
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_resource_with_1_text_1_button, null, false);

        mFirstActButton = (Button)mView.findViewById(R.id.first_act_button);

        TextView equipmentName = (TextView)mView.findViewById(R.id.resource_name);
        TextView firstCharacteristic = (TextView)mView.findViewById(R.id.first_characteristic);

        equipmentName.setText(mBag.getName());
        String message = "Вместимость:  " + mBag.getCapacity();
        firstCharacteristic.setText(message);

        ImageView imageView = (ImageView)mView.findViewById(R.id.resource_image);
        Assistant.fillImage(getContext(), imageView, mBag.getAssertDrawable());

        switch (mPhase){
            case Showable.CURRENT:
                setupInCurrent();
                break;
            case Showable.IN_FIND_RESOURCES:
                setupIfInFindResources();
                break;
        }

    }

    private void setupInCurrent() {
        mFirstActButton.setText("Выбросить");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameInterface.addResourceToCurrentPlace(mBag, null);
                setToUpdate();
                getDialog().cancel();
            }
        });
    }

    private void setupIfInFindResources(){
        mFirstActButton.setText("Надеть");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameInterface.setPlayerBag(mBag);
                setToUpdate();
                getDialog().cancel();
            }
        });
    }

    private void setToUpdate(){
        if (getTargetFragment() == null){
            return;
        }
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, new Intent());
    }
}
