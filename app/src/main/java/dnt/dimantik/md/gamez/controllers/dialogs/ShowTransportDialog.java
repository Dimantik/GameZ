package dnt.dimantik.md.gamez.controllers.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
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
import dnt.dimantik.md.gamez.game.logic.clases.resource.Transport;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

/**
 * Created by dimantik on 1/20/18.
 */

public class ShowTransportDialog extends DialogFragment implements Showable {

    private static final String PHASE = "PHASE";
    private static final String TRANSPORT_UUID = "TU";

    private OnFragmentInteractionListener mListener;

    private View mView;
    private Button mFirstActButton;

    private GameInterface mGameInterface;

    private Transport mTransport;
    private String mPhase;

    public static ShowTransportDialog getInstance(String transportUUID, String phase){
        ShowTransportDialog fragment = new ShowTransportDialog();

        Bundle args = new Bundle();
        args.putSerializable(PHASE, phase);
        args.putSerializable(TRANSPORT_UUID, transportUUID);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
        mTransport = (Transport) mGameInterface.getResource(UUID.fromString((String)getArguments().getSerializable(TRANSPORT_UUID)));
        mPhase = (String) getArguments().getSerializable(PHASE);
    }

    private void setup(){
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_show_resource_with_2_text_2_button, null, false);

        TextView transportName = (TextView)mView.findViewById(R.id.resource_name);
        TextView firstCharacteristic = (TextView)mView.findViewById(R.id.first_characteristic);
        TextView secondCharacteristic = (TextView)mView.findViewById(R.id.second_characteristic);

        transportName.setText(mTransport.getName());
        String message = "Мощность:  " + mTransport.getPower();
        firstCharacteristic.setText(message);
        message = "Защищенность:  " + mTransport.getProtection();
        secondCharacteristic.setText(message);

        ImageView imageView = (ImageView)mView.findViewById(R.id.resource_image);
        Assistant.fillImage(getContext(), imageView, mTransport.getAssertDrawable());

        mFirstActButton = (Button)mView.findViewById(R.id.first_act_button);
        Button secondActButton = (Button)mView.findViewById(R.id.second_act_button);

        secondActButton.setText("Заглянуть в багажник");
        secondActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.putExchangeResourceBagTransportBagFragment();
                getDialog().cancel();
            }
        });

        switch (mPhase){
            case CURRENT:
                setupIfCurrent();
                break;
            case IN_FIND_RESOURCES:
                setupIfInFindResources();
                break;
        }

        if (getTargetFragment() != null){
            Toast.makeText(getContext(), "NOT NULL", Toast.LENGTH_SHORT).show();
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
        }
    }

    private void setupIfCurrent(){
        mFirstActButton.setText("Оставить трансапорт");

        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGameInterface.addResourceInCurrentPlace(mGameInterface.getPlayerTransport());
                getDialog().cancel();
            }
        });
    }

    private void setupIfInFindResources(){
        mFirstActButton.setText("Взять транспорт");
        mFirstActButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mGameInterface.setPlayerTransport(mTransport);
            }
        });
    }

    public interface OnFragmentInteractionListener {

        void putExchangeResourceBagTransportBagFragment();

    }
}