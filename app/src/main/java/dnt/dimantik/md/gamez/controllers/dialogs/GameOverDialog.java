package dnt.dimantik.md.gamez.controllers.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.controllers.MenuActivity;
import dnt.dimantik.md.gamez.game.logic.clases.resources.Bag;
import dnt.dimantik.md.gamez.game.logic.game.settings.AssertResourceName;
import dnt.dimantik.md.gamez.game.logic.interfaces.GameInterface;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

/**
 * Created by dimantik on 1/20/18.
 */

public class GameOverDialog extends DialogFragment {

    private static final String CAUSE = "C";

    private View mView;

    public static GameOverDialog getInstance(String cause){
        GameOverDialog fragment = new GameOverDialog();

        Bundle args = new Bundle();
        args.putSerializable(CAUSE, cause);
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
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_game_over, null, false);

        setCancelable(false);

        TextView title = (TextView)mView.findViewById(R.id.title);
        TextView cause = (TextView)mView.findViewById(R.id.cause);
        Button startNewGame = (Button)mView.findViewById(R.id.first_act_button);
        Button exitToMenu = (Button)mView.findViewById(R.id.second_act_button);
        ImageView imageView = (ImageView)mView.findViewById(R.id.game_over_image);

        Assistant.fillImage(getContext(), imageView, AssertResourceName.GAME_OVER);

        title.setText("Вы умерил(");
        cause.setText(getArguments().getString(CAUSE));

        exitToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MenuActivity.class);
                startActivity(intent);
            }
        });
        startNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.getIntent(getContext(), MainActivity.CREATE_NEW_GAME, "Dimantik");
                startActivity(intent);
            }
        });
    }

}
