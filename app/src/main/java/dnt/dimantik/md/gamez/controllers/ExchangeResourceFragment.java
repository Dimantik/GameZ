package dnt.dimantik.md.gamez.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowBagDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowClothesDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowImportantResourceDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowWeaponDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.Showable;
import dnt.dimantik.md.gamez.game.logic.GameInterface;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Bag;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Clothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.ImportantResource;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Transport;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Weapon;
import dnt.dimantik.md.gamez.helper.classes.Assistant;


public class ExchangeResourceFragment extends Fragment implements View.OnClickListener{

    public static final String FIRST_SET = "FS";
    public static final String SECOND_SET = "SS";

    public static final int REQUEST_SHOW_RESOURCE = 0;
    private static final String SHOW_RESOURCE = "SR";

    private static final String EXCHANGE_TYPE = "ET";

    public static final String EXCHANGE_PLAYER_BAG_TRANSPORT_BAG = "EPBTB";
    public static final String EXCHANGE_PLAYER_BAG_FIND_RESOURCE_LIST = "EPBFRL";

    private RecyclerView mFirstSetRecyclerView;
    private RecyclerView mSecondSetRecylerView;
    private TextView mMyResourcesTitle;
    private TextView mOtherResourcesTitle;
    private Button mPutIn;
    private Button mPutOut;

    private String mExchangeType;

    private GameInterface mGameInterface;

    private ExchangeResourcesAdapter mFirstSetAdapter;
    private ExchangeResourcesAdapter mSecondSetAdapter;

    private List<Resource> mToFirstSet;
    private List<Resource> mToSecondSet;

    private List<Resource> mFirstSet;
    private List<Resource> mSecondSet;

    private View mView;

    public static ExchangeResourceFragment newInstance(String exchangeType) {
        ExchangeResourceFragment fragment = new ExchangeResourceFragment();

        Bundle args = new Bundle();
        args.putString(EXCHANGE_TYPE, exchangeType);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mExchangeType = getArguments().getString(EXCHANGE_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_exchange_resources, null, false);
        setup();
        return mView;
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


    private void setup(){
        mGameInterface = ((MainActivity)getActivity()).getGameInterface();

        mFirstSetRecyclerView = (RecyclerView)mView.findViewById(R.id.first_set);
        mFirstSetRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mSecondSetRecylerView = (RecyclerView)mView.findViewById(R.id.second_set);
        mSecondSetRecylerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        mMyResourcesTitle = (TextView)mView.findViewById(R.id.my_bag_title);
        mOtherResourcesTitle = (TextView)mView.findViewById(R.id.other_resources_title);

        mPutIn = (Button)mView.findViewById(R.id.put_in);
        mPutOut = (Button)mView.findViewById(R.id.put_out);
        mPutIn.setOnClickListener(this);
        mPutOut.setOnClickListener(this);

        mFirstSet = mGameInterface.getResourceListFromPlayerBag();

        switch (mExchangeType){
            case EXCHANGE_PLAYER_BAG_FIND_RESOURCE_LIST:
                mSecondSet = mGameInterface.getFindResourceListInCurrentPlace();
                break;
            case EXCHANGE_PLAYER_BAG_TRANSPORT_BAG:
                mSecondSet = mGameInterface.getResourceListFromPlayerTransportBag();
                break;
        }

    }

    private void updateFirstSet(){
        mFirstSetAdapter = new ExchangeResourcesAdapter(mFirstSet, FIRST_SET);
        mFirstSetRecyclerView.setAdapter(mFirstSetAdapter);
    }

    private void updateSecondSet(){
        mSecondSetAdapter = new ExchangeResourcesAdapter(mSecondSet, SECOND_SET);
        mSecondSetRecylerView.setAdapter(mSecondSetAdapter);
    }

    private void updateUI(){
        String message = "Мой рюкзак " + mFirstSet.size() + "/" + mGameInterface.getPlayerBag().getCapacity();
        mMyResourcesTitle.setText(message);
        if (mExchangeType.equals(EXCHANGE_PLAYER_BAG_TRANSPORT_BAG)){
            message = "Багажник (" + mSecondSet.size() + ")";
            mOtherResourcesTitle.setText(message);
        } else if (mExchangeType.equals(EXCHANGE_PLAYER_BAG_FIND_RESOURCE_LIST)){
            message = "Найденные ресурсы (" + mSecondSet.size() + ")";
            mOtherResourcesTitle.setText(message);
        }

        updateFirstSet();
        updateSecondSet();

        mToFirstSet = new LinkedList<>();
        mToSecondSet = new LinkedList<>();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == REQUEST_SHOW_RESOURCE){
            updateUI();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.put_in:
                if (!putIn()){
                    Toast.makeText(getContext(), "У вас недостаточно места в рюкзаке!" , Toast.LENGTH_SHORT).show();
                } else {
                    updateUI();
                }
                break;
            case R.id.put_out:
                if (!putOut()){
                    Toast.makeText(getContext(), "У вас недостаточно места в машине!", Toast.LENGTH_SHORT).show();
                } else {
                    updateUI();
                }
                break;
            default:
                break;
        }
    }

    public boolean putIn(){
        return mGameInterface.addResourceListToPlayerBag(mToFirstSet);
    }

    public boolean putOut(){
        if (mExchangeType.equals(EXCHANGE_PLAYER_BAG_FIND_RESOURCE_LIST)){
            mGameInterface.addResourceListInCurrentPlace(mToSecondSet);
            return true;
        } else if (mExchangeType.equals(EXCHANGE_PLAYER_BAG_TRANSPORT_BAG)) {
            return mGameInterface.addResourceListToPlayerTransportBag(mToSecondSet);
        }
        return false;
    }

    class ExchangeResourceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private String mNumberSet;
        private Resource mResource;

        private CheckBox mCheckBox;

        ExchangeResourceHolder(final View itemView) {
            super(itemView);
            mView = itemView;
            itemView.setOnClickListener(this);
            setup();
        }

        private void setup(){
            mCheckBox = (CheckBox)itemView.findViewById(R.id.to_exchange);
            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    exchange();
                }
            });

            int cardSize = mFirstSetRecyclerView.getWidth();
            cardSize = (cardSize-40)/4;

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(cardSize, cardSize);
            itemView.setLayoutParams(params);
        }


        void bind(Resource resource, String numberSet){
            mResource = resource;
            mNumberSet = numberSet;
            ImageView imageView = (ImageView)mView.findViewById(R.id.resource_image);
            Assistant.fillImage(getContext(), imageView, mResource.getAssertDrawable());
        }

        private void exchange(){
            if (mNumberSet.equals(FIRST_SET)){
                if (mCheckBox.isChecked()){
                    mToSecondSet.add(mResource);
                } else {
                    mToSecondSet.remove(mResource);
                }
                if (mToFirstSet.size() != 0){
                    mToFirstSet = new LinkedList<>();
                    updateSecondSet();
                }
            } else {
                if (mCheckBox.isChecked()){
                    mToFirstSet.add(mResource);
                } else {
                    mToFirstSet.remove(mResource);
                }
                if (mToSecondSet.size() != 0){
                    mToSecondSet = new ArrayList<Resource>();
                    updateFirstSet();
                }
            }
        }

        @Override
        public void onClick(View view) {

            String phase = null;
            if (mNumberSet.equals(FIRST_SET)){
                phase = Showable.IN_BAG;
            } else {
                if (mExchangeType.equals(EXCHANGE_PLAYER_BAG_FIND_RESOURCE_LIST)){
                    phase = Showable.IN_FIND_RESOURCES;
                } else if (mExchangeType.equals(EXCHANGE_PLAYER_BAG_TRANSPORT_BAG)) {
                    phase = Showable.IN_TRANSPORT_BAG;
                }
            }

            DialogFragment dialog = Assistant.getFragmentForShowReaource(mResource, phase);
            dialog.setTargetFragment(ExchangeResourceFragment.this, REQUEST_SHOW_RESOURCE);
            dialog.show(getFragmentManager(), SHOW_RESOURCE);
        }
    }

    class ExchangeResourcesAdapter extends RecyclerView.Adapter<ExchangeResourceHolder>{

        private List<Resource> mResourceList;
        private String mSetNumber;

        public void setResourceList(List<Resource> resourceList) {
            mResourceList = resourceList;
        }

        public ExchangeResourcesAdapter(List<Resource> resourceList, String setNumber) {
            mResourceList = resourceList;
            mSetNumber = setNumber;
        }

        @Override
        public ExchangeResourceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_resource_item_to_exchange, parent, false);
            return new ExchangeResourceHolder(view);
        }

        @Override
        public void onBindViewHolder(ExchangeResourceHolder holder, int position) {
            holder.bind(mResourceList.get(position), mSetNumber);
        }

        @Override
        public int getItemCount() {
            return mResourceList.size();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
