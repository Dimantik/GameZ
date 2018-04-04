package dnt.dimantik.md.gamez.controllers.place.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by dimantik on 2/3/18.
 */

public class LocationFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] titles = {
            "Карта местности",
            "Действия"
    };

    public LocationFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PlaceFragment();
            case 1:
                return new ActionFragment();
            default:
                return new PlaceFragment();
        }
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
