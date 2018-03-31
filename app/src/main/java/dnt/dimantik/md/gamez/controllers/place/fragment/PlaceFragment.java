package dnt.dimantik.md.gamez.controllers.place.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.game.logic.GameInterface;

public class PlaceFragment extends Fragment {

    private View mView;
    private GameInterface mGameInterface;

    private RecyclerView mRecyclerView;
    private PlaceAdapter mPlaceAdapter;

    private OnFragmentInteractionListener mListener;

    public static PlaceFragment newInstance() {
        PlaceFragment fragment = new PlaceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_place, container, false);
        setup();
        return mView;
    }

    private void setup(){
        mGameInterface = ((MainActivity)getActivity()).getGameInterface();

        mRecyclerView = (RecyclerView)mView.findViewById(R.id.place_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        if (mPlaceAdapter == null){
            mPlaceAdapter = new PlaceAdapter(mGameInterface, getFragmentManager(), getContext());
            mRecyclerView.setAdapter(mPlaceAdapter);
        } else {
            mPlaceAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

    }

}
