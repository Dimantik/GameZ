package dnt.dimantik.md.gamez.controllers.place.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dnt.dimantik.md.gamez.R;

public class ActionFragment extends Fragment {

    private View mView;
    private Button mFindResources;

    private OnFragmentInteractionListener mListener;

    public static ActionFragment newInstance() {
        ActionFragment fragment = new ActionFragment();

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
        mView = inflater.inflate(R.layout.fragment_action, container, false);
        setup();
        return mView;
    }

    private void setup(){
        mFindResources = (Button)mView.findViewById(R.id.find_resources);
        mFindResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.putExchangeResourceBagFindResourceListFragment();
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
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

    public interface OnFragmentInteractionListener {

        void putExchangeResourceBagFindResourceListFragment();
    }
}
