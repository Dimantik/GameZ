package dnt.dimantik.md.gamez.controllers.dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;

/**
 * Created by dimantik on 1/20/18.
 */

public class CreateNewPlayerDialogFragment extends DialogFragment {

    private View mView;

    public static CreateNewPlayerDialogFragment getInstance(){
        CreateNewPlayerDialogFragment fragment = new CreateNewPlayerDialogFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setup();
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setView(mView);
        return adb.create();
    }

    private void setup(){
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog_create_new_player, null, false);
        final EditText name = (EditText)mView.findViewById(R.id.name_field);
        Button save = (Button)mView.findViewById(R.id.save_name);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = name.getText().toString();
                if (nameStr.equals("")){
                    Toast.makeText(getActivity(), "Никнейм не может быть путсым!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = MainActivity.getIntent(getContext(), MainActivity.CREATE_NEW_GAME, nameStr);
                    startActivity(intent);
                }
            }
        });
    }

}
