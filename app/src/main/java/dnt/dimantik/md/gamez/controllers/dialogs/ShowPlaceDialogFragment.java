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

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.game.logic.game.settings.AssertResourceName;
import dnt.dimantik.md.gamez.game.logic.interfaces.GameInterface;
import dnt.dimantik.md.gamez.game.logic.clases.map.Place;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

/**
 * Created by dimantik on 1/20/18.
 */

public class ShowPlaceDialogFragment extends DialogFragment {

    private static final String PLACE_ID = "LOCATION_ID";
    private static final String ACTION_TYPE = "ACTION_TYPE";

    public static final String GO_TO_PLACE_ACTION = "GTPA";
    public static final String SHOW_INFO_ABOUT_PLACE = "SIAP";

    private String mActionType;
    private GameInterface mGameInterface;
    private Place mPlace;
    private View mView;

    public static ShowPlaceDialogFragment getInstance(int placeId, String actionType){
        ShowPlaceDialogFragment fragment = new ShowPlaceDialogFragment();

        Bundle args = new Bundle();
        args.putInt(PLACE_ID, placeId);
        args.putString(ACTION_TYPE, actionType);
        fragment.setArguments(args);

        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        getPlace();
        setup();
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setView(mView);
        return adb.create();
    }

    private void getPlace(){
        mGameInterface = ((MainActivity)getActivity()).getGameInterface();
        int id = getArguments().getInt(PLACE_ID);
        mActionType = getArguments().getString(ACTION_TYPE);
        mPlace = mGameInterface.getPlaceForId(id);
    }

    private void setup(){
        if (mActionType.equals(GO_TO_PLACE_ACTION)){
            mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_place_action_go, null, false);

            TextView placeName = (TextView)mView.findViewById(R.id.place_name);
            String message = "Отправиться сюда? " + mPlace.getName();
            placeName.setText(message);

        } else if (mActionType.equals(SHOW_INFO_ABOUT_PLACE)) {
            mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_place_action_info, null, false);

            TextView placeName = (TextView)mView.findViewById(R.id.place_name);
            placeName.setText("" + mPlace.getId());

            TextView placeProtection = (TextView)mView.findViewById(R.id.place_protection);
            String message = "Защищенность места:  " + mPlace.getProtection();
            placeProtection.setText(message);

            ImageView one = (ImageView)mView.findViewById(R.id.place_dangerous_level_one);
            ImageView two = (ImageView)mView.findViewById(R.id.place_dangerous_level_two);
            ImageView three = (ImageView)mView.findViewById(R.id.place_dangerous_level_three);

            switch (mPlace.getDangerousLevel()){
                case 0:
                    break;
                case 1:
                    Assistant.fillImage(getContext(), one, AssertResourceName.SKULL);
                    break;
                case 2:
                    Assistant.fillImage(getContext(), one, AssertResourceName.SKULL);
                    Assistant.fillImage(getContext(), two, AssertResourceName.SKULL);
                    break;
                case 3:
                    Assistant.fillImage(getContext(), one, AssertResourceName.SKULL);
                    Assistant.fillImage(getContext(), two, AssertResourceName.SKULL);
                    Assistant.fillImage(getContext(), three, AssertResourceName.SKULL);
                    break;
                default:
                    break;
            }

            TextView placeInfo = (TextView)mView.findViewById(R.id.place_info);
            placeInfo.setText(mPlace.getInfo());

        }

        ImageView placeImage = (ImageView)mView.findViewById(R.id.place_image);
        Assistant.fillImage(getContext(), placeImage, mPlace.getAssertDrawableName());

        TextView requiredTimeToTravel = (TextView)mView.findViewById(R.id.important_message);
        String message;
        if (mGameInterface.getCurrentPlace() == mPlace){
            message = "Вы находитесь тут";
        } else {
            message = "Время в пути:  " + mGameInterface.getRequiredTimeForTravelFromPlace(mPlace);
        }

        requiredTimeToTravel.setText(message);

        Button buttonGo = (Button)mView.findViewById(R.id.go_to_place);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameInterface.travelForPlace(mPlace);
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
