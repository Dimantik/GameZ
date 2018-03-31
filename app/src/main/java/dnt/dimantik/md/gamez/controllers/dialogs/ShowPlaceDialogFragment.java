package dnt.dimantik.md.gamez.controllers.dialogs;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.game.logic.GameInterface;
import dnt.dimantik.md.gamez.game.logic.clases.location.Place;

/**
 * Created by dimantik on 1/20/18.
 */

public class ShowPlaceDialogFragment extends DialogFragment {

    private static final String PLACE_ID = "LOCATION_ID";
    private static final String ACTION_TYPE = "ACTION_TYPE";
    public static final String GO_TO_PLACE_ACTION = "GTPA";
    public static final String SHOW_INFO_ABOUT_PLACE = "SIAP";

    private int mId;
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
        mId = getArguments().getInt(PLACE_ID);
        mActionType = getArguments().getString(ACTION_TYPE);
        mPlace = mGameInterface.getPlaceForId(mId);
    }

    private void setup(){
        if (mActionType.equals(GO_TO_PLACE_ACTION)){
            mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_place_action_go, null, false);
            TextView placeName = (TextView)mView.findViewById(R.id.place_name);
            placeName.setText("Отправиться сюда? " + mPlace.getName());
        } else if (mActionType.equals(SHOW_INFO_ABOUT_PLACE)) {
            mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_place_action_info, null, false);

            TextView placeName = (TextView)mView.findViewById(R.id.place_name);
            placeName.setText(mPlace.getName());

            ImageView one = (ImageView)mView.findViewById(R.id.place_dangerous_level_one);
            ImageView two = (ImageView)mView.findViewById(R.id.place_dangerous_level_two);
            ImageView three = (ImageView)mView.findViewById(R.id.place_dangerous_level_three);
            switch (mPlace.getDangerousLevel()){
                case 0:
                    one.setVisibility(View.INVISIBLE);
                    two.setVisibility(View.INVISIBLE);
                    three.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    two.setVisibility(View.INVISIBLE);
                    three.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    three.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    break;
                default:
                    break;
            }

            TextView placeInfo = (TextView)mView.findViewById(R.id.place_info);
            placeInfo.setText(mPlace.getInfo());
        }
        fillImage();
    }

    private void fillImage(){
        ImageView imageView = (ImageView) mView.findViewById(R.id.place_image) ;
        InputStream inputStream = null;
        try{
            inputStream = getActivity().getAssets().open(mPlace.getAssertDrawableName());
            Drawable d = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(d);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                if(inputStream!=null)
                    inputStream.close();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

}
