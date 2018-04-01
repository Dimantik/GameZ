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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.game.logic.GameInterface;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Drug;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Food;
import dnt.dimantik.md.gamez.game.logic.clases.resource.ImportantResource;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Liquid;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

/**
 * Created by dimantik on 1/20/18.
 */

public class ShowImportantResourceDialog extends DialogFragment implements Showable {

    public static final String PHASE = "PHASE";
    public static final String RESOURCE_UUID = "RU";

    private GameInterface mGameInterface;

    private ImportantResource mImportantResource;
    private String mPhase;

    private View mView;
    private Button mFirstActButton;
    private Button mSecondActButton;
    private Button mThirdActButton;

    private TextView mFirstCharacteristic;
    private TextView mImportantIndicatorText;
    private ProgressBar mImportantIndicatorProgress;

    public static ShowImportantResourceDialog getInstance(UUID uuid, String phase){
        ShowImportantResourceDialog fragment = new ShowImportantResourceDialog();

        Bundle args = new Bundle();
        args.putSerializable(RESOURCE_UUID, uuid.toString());
        args.putString(PHASE, phase);
        fragment.setArguments(args);

        return fragment;
    }

    public static ShowImportantResourceDialog getInstance(UUID uuid, String phase, String transportUUID){
        ShowImportantResourceDialog fragment = new ShowImportantResourceDialog();

        Bundle args = new Bundle();
        args.putSerializable(RESOURCE_UUID, uuid.toString());
        args.putString(PHASE, phase);
        fragment.setArguments(args);

        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        getImportantResource();
        setup();
        setListenerForActButton();

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setView(mView);
        return adb.create();
    }

    private void getImportantResource(){
        mGameInterface = ((MainActivity)getActivity()).getGameInterface();
        mImportantResource = (ImportantResource) mGameInterface.getResource(Assistant.convertToUUID(getArguments().getString(RESOURCE_UUID)));
        mPhase = getArguments().getString(PHASE);
    }

    private void setup(){
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_resource_with_1_text_3_button_1_progress, null, false);

        TextView resourceName = (TextView)mView.findViewById(R.id.resource_name);
        resourceName.setText(mImportantResource.getName());

        mFirstCharacteristic = (TextView)mView.findViewById(R.id.first_characteristic);

        mFirstActButton = (Button)mView.findViewById(R.id.first_act_button);
        mSecondActButton = (Button)mView.findViewById(R.id.second_act_button);
        mThirdActButton = (Button)mView.findViewById(R.id.third_act_button);

        mImportantIndicatorText = (TextView)mView.findViewById(R.id.progress_important_indicator_text);
        mImportantIndicatorProgress = (ProgressBar)mView.findViewById(R.id.progress_bar);

        mImportantIndicatorProgress.setMax(100);

        if (mImportantResource instanceof Drug){
            mFirstActButton.setText("Принять");
        } else if (mImportantResource instanceof Food){
            mFirstActButton.setText("Скушать");
        } else if (mImportantResource instanceof Liquid){
            mFirstActButton.setText("Выпить");
        }

        update();

        ImageView imageView = (ImageView)mView.findViewById(R.id.resource_image);
        Assistant.fillImage(getContext(), imageView, mImportantResource.getAssertDrawable());
    }

    private void update(){
        String message = "Количество: " + mImportantResource.getQuantity();
        mFirstCharacteristic.setText(message);
        if (mImportantResource instanceof Drug){
            mImportantIndicatorProgress.setProgress(mGameInterface.getPlayerHealth());
            message = mGameInterface.getPlayerHealth() + "/100";
            mImportantIndicatorText.setText(message);
        } else if (mImportantResource instanceof Food){
            mImportantIndicatorProgress.setProgress(mGameInterface.getPlayerSatiety());
            message = mGameInterface.getPlayerSatiety() + "/100";
            mImportantIndicatorText.setText(message);
        } else if (mImportantResource instanceof Liquid){
            mImportantIndicatorProgress.setProgress(mGameInterface.getPlayerThirst());
            message = mGameInterface.getPlayerThirst() + "/100";
            mImportantIndicatorText.setText(message);

        }
    }

    private void setListenerForActButton(){
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                useResource();
            }
        });

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

    private void useResource(){
        mGameInterface.upImportantIndicator(mImportantResource);
        if (mImportantResource.getQuantity() <= 0){
            setUpdate();
            getDialog().cancel();
        } else {
            update();
        }
    }

    private void setButtonListenerIfInBag(){
        mThirdActButton.setText("Выбросить");
        mThirdActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throwOut();
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

    private void setButtonListenerIfInTransportBag(){
        mThirdActButton.setText("Выбросить");
        mThirdActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throwOut();
            }
        });
        mSecondActButton.setText("Положить в сумку");
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInBag();
            }
        });
    }

    private void setButtonListenerIfInFindResources(){
        mSecondActButton.setText("Положить в сумку");
        mSecondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               putInBag();
            }
        });
        mThirdActButton.setText("Положить в машину");
        mThirdActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putInTransportBag();
            }
        });
    }

    private void throwOut(){
        mGameInterface.addResourceToCurrentPlace(mImportantResource, null);
        setUpdate();
        getDialog().cancel();
    }

    private void putInTransportBag(){
        boolean result = mGameInterface.addResourceToPlayerTransportBag(mImportantResource, null);
        if (result){
            setUpdate();
            getDialog().cancel();
        } else {
            showMessage();
        }
    }

    private void putInBag(){
        boolean result = mGameInterface.addResourceToPlayerBag(mImportantResource, null);
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
