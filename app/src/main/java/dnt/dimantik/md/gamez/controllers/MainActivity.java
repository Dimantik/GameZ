package dnt.dimantik.md.gamez.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.UUID;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.bag.fragment.BagFragment;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowTransportDialog;
import dnt.dimantik.md.gamez.controllers.character.fragment.CharacterFragment;
import dnt.dimantik.md.gamez.controllers.place.fragment.ActionFragment;
import dnt.dimantik.md.gamez.controllers.place.fragment.MapFragment;
import dnt.dimantik.md.gamez.game.logic.GameInterface;

public class MainActivity extends AppCompatActivity implements ActionFragment.OnFragmentInteractionListener,
        ShowTransportDialog.OnFragmentInteractionListener{

    private static final String TYPE = "TYPE";
    private static final String NAME = "NAME";

    public static final String LOAD_CURRENT_GAME = "LCG";
    public static final String CREATE_NEW_GAME = "CNG";

    FragmentManager mFM;

    private ImageView mCharacterImg;
    private ImageView mMapImg;
    private ImageView mBagImg;

    private ProgressBar mProgressBar;

    private GameInterface mGameInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        firstInitialize();
    }

    private void firstInitialize(){
        mCharacterImg = (ImageView)findViewById(R.id.character_img);
        mCharacterImg.setVisibility(View.INVISIBLE);
        mMapImg = (ImageView)findViewById(R.id.map_img);
        mMapImg.setVisibility(View.INVISIBLE);
        mBagImg = (ImageView)findViewById(R.id.bag_img);
        mBagImg.setVisibility(View.INVISIBLE);
        mProgressBar = (ProgressBar)findViewById(R.id.progress_load);

        ProcessDownloadTask task = new ProcessDownloadTask();
        task.execute();
    }

    private void afterLoad(){
        mProgressBar.setVisibility(View.INVISIBLE);
        mCharacterImg.setVisibility(View.VISIBLE);
        mMapImg.setVisibility(View.VISIBLE);
        mBagImg.setVisibility(View.VISIBLE);
        mFM = getSupportFragmentManager();
        if (mFM.findFragmentById(R.id.content) == null) {
            mCharacterImg.setImageDrawable(getResources().getDrawable(R.drawable.character_pressed));
            mMapImg.setImageDrawable(getResources().getDrawable(R.drawable.map));
            mBagImg.setImageDrawable(getResources().getDrawable(R.drawable.bag));
            Fragment fragment = CharacterFragment.newInstance();
            mFM.beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit();
        }
        setupBottomNavigation();
    }

    private void setupBottomNavigation(){
        mCharacterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCharacterImg.setImageDrawable(getResources().getDrawable(R.drawable.character_pressed));
                mMapImg.setImageDrawable(getResources().getDrawable(R.drawable.map));
                mBagImg.setImageDrawable(getResources().getDrawable(R.drawable.bag));
                Fragment fragment = CharacterFragment.newInstance();
                mFM.beginTransaction()
                        .replace(R.id.content, fragment)
                        .commit();
            }
        });
        mMapImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCharacterImg.setImageDrawable(getResources().getDrawable(R.drawable.character));
                mMapImg.setImageDrawable(getResources().getDrawable(R.drawable.map_pressed));
                mBagImg.setImageDrawable(getResources().getDrawable(R.drawable.bag));
                Fragment fragment = MapFragment.newInstance();
                mFM.beginTransaction()
                        .replace(R.id.content, fragment)
                        .commit();
            }
        });
        mBagImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCharacterImg.setImageDrawable(getResources().getDrawable(R.drawable.character));
                mMapImg.setImageDrawable(getResources().getDrawable(R.drawable.map));
                mBagImg.setImageDrawable(getResources().getDrawable(R.drawable.bag_pressed));
                Fragment fragment = BagFragment.newInstance();
                mFM.beginTransaction()
                        .replace(R.id.content, fragment)
                        .commit();
            }
        });
    }

    @Override
    public void putExchangeResourceBagFindResourceListFragment() {
        Fragment fragment = ExchangeResourceFragment.newInstance(ExchangeResourceFragment.EXCHANGE_PLAYER_BAG_FIND_RESOURCE_LIST);
        mFM.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    @Override
    public void putExchangeResourceBagTransportBagFragment() {
        Fragment fragment = ExchangeResourceFragment.newInstance(ExchangeResourceFragment.EXCHANGE_PLAYER_BAG_TRANSPORT_BAG);
        mFM.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    private class ProcessDownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String type = getIntent().getStringExtra(TYPE);
            if (type.equals(LOAD_CURRENT_GAME)){
                mGameInterface = GameInterface.continueGame();
            } else if (type.equals(CREATE_NEW_GAME)) {
                mGameInterface = GameInterface.createNewGame(getIntent().getStringExtra(NAME));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "Hello, " + mGameInterface.getPlayerInterface().getPlayer().getName(), Toast.LENGTH_LONG).show();
            afterLoad();
        }
    }

    public static Intent getIntent(Context context, String type, String name){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(TYPE, type);
        intent.putExtra(NAME, name);
        return intent;
    }

    public static Intent getIntent(Context context, String type){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(TYPE, type);
        return intent;
    }


    public GameInterface getGameInterface(){
        return mGameInterface;
    }


    @Override
    public void onBackPressed() {}
}
