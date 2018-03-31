package dnt.dimantik.md.gamez.controllers.place.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.game.logic.GameInterface;
import dnt.dimantik.md.gamez.game.logic.clases.GameData;

public class MapFragment extends Fragment {

    private View mView;
    private OnFragmentInteractionListener mListener;
    private GameInterface mGameInterface;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_location, container, false);
        setup();
        return mView;
    }

    private void setup(){
        mGameInterface = ((MainActivity)getActivity()).getGameInterface();
        Log.i("TAG", "Size CURRENT PLACE = " + mGameInterface.getCurrentPlace().getResourceList().size() + "/" + mGameInterface.getCurrentPlace().getResourceUUIDList().size());

        setValues();

        mViewPager = (ViewPager)mView.findViewById(R.id.location_view_pager);
        mViewPager.setAdapter(new LocationFragmentPagerAdapter(getChildFragmentManager()));

        mTabLayout = (TabLayout)mView.findViewById(R.id.location_tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    private void setValues(){
        ProgressBar gameProgress = (ProgressBar)mView.findViewById(R.id.game_progress);
        TextView progressText = (TextView)mView.findViewById(R.id.progress_text);
        progressText.setText("День " + mGameInterface.getCurrentDay() + " из " + mGameInterface.getLastDay());
        gameProgress.setMax(mGameInterface.getLastDay());
        gameProgress.setProgress(mGameInterface.getCurrentDay());

        ProgressBar food = (ProgressBar)mView.findViewById(R.id.food_progress_bar);
        ProgressBar health = (ProgressBar)mView.findViewById(R.id.health_progress_bar);
        ProgressBar water = (ProgressBar)mView.findViewById(R.id.drink_progress_bar);
        ProgressBar energy = (ProgressBar)mView.findViewById(R.id.energy_progress_bar);
        TextView foodText = (TextView)mView.findViewById(R.id.progress_food_text);
        TextView drinkText = (TextView)mView.findViewById(R.id.progress_drink_text);
        TextView healthText = (TextView)mView.findViewById(R.id.progress_health_text);
        TextView energyText = (TextView)mView.findViewById(R.id.progress_energy_text);
        food.setProgress(mGameInterface.getPlayer().getSatiety());
        health.setProgress(mGameInterface.getPlayer().getHealth());
        water.setProgress(mGameInterface.getPlayer().getThirst());
        energy.setProgress(mGameInterface.getPlayer().getEnergy());
        foodText.setText(mGameInterface.getPlayer().getSatiety() + "/100");
        drinkText.setText(mGameInterface.getPlayer().getThirst() + "/100");
        healthText.setText(mGameInterface.getPlayer().getHealth() + "/100");
        energyText.setText(mGameInterface.getPlayer().getEnergy() + "/100");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //if (context instanceof OnFragmentInteractionListener) {
        //    mListener = (OnFragmentInteractionListener) context;
        //} else {
        //    throw new RuntimeException(context.toString()
        //            + " must implement OnFragmentInteractionListener");
        //}
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
    }
}
