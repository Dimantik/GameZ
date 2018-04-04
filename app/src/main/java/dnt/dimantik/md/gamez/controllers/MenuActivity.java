package dnt.dimantik.md.gamez.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.dialogs.CreateNewPlayerDialogFragment;
import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        setup();
    }

    private void setup(){
        Button startNewGame = (Button)findViewById(R.id.start_new_game);
        Button continueCurrentGame = (Button)findViewById(R.id.continue_current_game);
        startNewGame.setOnClickListener(this);
        continueCurrentGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_new_game:
                startNewGame();
                break;
            case R.id.continue_current_game:
                continueCurrentGame();
                break;
        }
    }

    private void continueCurrentGame(){
        if (BDHelper.checkIsExistenceGame()){
            Intent intent = MainActivity.getIntent(getApplicationContext(), MainActivity.LOAD_CURRENT_GAME, "");
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Вы еще не начинали игру!", Toast.LENGTH_SHORT).show();
        }
    }

    private void startNewGame(){
        CreateNewPlayerDialogFragment dialogFragment = CreateNewPlayerDialogFragment.getInstance();
        dialogFragment.show(getSupportFragmentManager(), "NEW_PLAYER");
    }

    @Override
    public void onBackPressed() {

    }
}
