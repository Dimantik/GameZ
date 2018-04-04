package dnt.dimantik.md.gamez.controllers.place.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.ExchangeResourceFragment;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.game.logic.clases.map.Location;
import dnt.dimantik.md.gamez.game.logic.interfaces.GameInterface;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

public class LocationFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private GameInterface mGameInterface;

    private RecyclerView mLocationRecyclerView;
    private LocationAdapter mLocationAdapter;

    private View mView;

    public static LocationFragment newInstance() {
        LocationFragment fragment = new LocationFragment();
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

        mView =  inflater.inflate(R.layout.fragment_location, container, false);
        setup();
        return mView;
    }

    private void setup(){
        mGameInterface = ((MainActivity)getActivity()).getGameInterface();

        mLocationRecyclerView = (RecyclerView)mView.findViewById(R.id.location_list);
        mLocationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        if (mLocationAdapter == null){
            mLocationAdapter = new LocationAdapter(new LinkedList<Location>(mGameInterface.getLocationList()));
            mLocationRecyclerView.setAdapter(mLocationAdapter);
        } else {
            mLocationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ActionFragment.OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private class LocationHolder extends RecyclerView.ViewHolder{

        private Location mLocation;
        private boolean mIsHere;

        public LocationHolder(View itemView) {
            super(itemView);
        }

        public void onBind(Location location, boolean isHere){
            mLocation = location;
            mIsHere = isHere;

            TextView locationName = (TextView)itemView.findViewById(R.id.location_name);
            locationName.setText(mLocation.getId() + "");

            TextView locationInfo = (TextView)itemView.findViewById(R.id.location_info);
            String message = "Информация:  " + mLocation.getInfo();
            locationInfo.setText(message);

            TextView travelTime = (TextView)itemView.findViewById(R.id.travel_time);
            Button goToLocation = (Button)itemView.findViewById(R.id.go_to_location);
            if (mIsHere){
                message = "Вы находитесь тут";
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 0);
                goToLocation.setLayoutParams(params);
            } else {
                int requiredTime = mGameInterface.getRequiredTimeForTravelFromLocation(mLocation);
                message = "Время в пути:  " + requiredTime;

                if (!mGameInterface.isEnoughFuelToTravel(requiredTime)){
                    message += "\n\nВам не хватит топлива в транспорте, чтобы добраться до конечно точки, если вы поедите, то вам придется оставить транспорт по пути";
                }

                goToLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mGameInterface.travelForLocation(mLocation);
                        mListener.replaceMapFragment();
                    }
                });
            }
            travelTime.setText(message);

            ImageView imageView = (ImageView)itemView.findViewById(R.id.location_image);
            Assistant.fillImage(getContext(), imageView, mLocation.getAssertDrawableName());
        }

    }

    private class LocationAdapter extends RecyclerView.Adapter<LocationHolder>{

        private List<Location> mLocationList;

        public LocationAdapter(List<Location> locationList) {
            mLocationList = locationList;
        }

        public void setLocationList(List<Location> locationList) {
            mLocationList = locationList;
        }

        @Override
        public LocationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_location_item_view, parent, false);
            return new LocationHolder(view);
        }

        @Override
        public void onBindViewHolder(LocationHolder holder, int position) {
            Location location = mLocationList.get(position);
            boolean isHere = false;
            if (mGameInterface.getCurrentLocation() == location){
                isHere = true;
            }
            holder.onBind(location, isHere);
        }

        @Override
        public int getItemCount() {
            return mLocationList.size();
        }
    }

    public interface OnFragmentInteractionListener {

        void replaceMapFragment();
    }

}
