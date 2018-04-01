package dnt.dimantik.md.gamez.controllers.bag.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.MainActivity;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowClothesDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowImportantResourceDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowTransportDialog;
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

public class BagFragment extends Fragment {

    public static final int REQUEST_SHOW_RESOURCE = 0;

    public static final String RESOURCE_DIALOG = "RD";

    private GameInterface mGameInterface;

    private View mView;

    private TextView mCapacityTextView;
    private RecyclerView mBagRecyclerView;
    private ResourceBagAdapter mBagAdapter;

    private Bag mBag;

    public static BagFragment newInstance() {
        BagFragment fragment = new BagFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if (getArguments() != null) {}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_bag_, container, false);
        firstInitialize();
        return mView;
    }

    private void firstInitialize() {
        mGameInterface = ((MainActivity)getActivity()).getGameInterface();
        mBag = mGameInterface.getPlayer().getCurrentBag();

        mCapacityTextView = (TextView)mView.findViewById(R.id.bag_capacity);

        mBagRecyclerView = (RecyclerView) mView.findViewById(R.id.bag_recycler_view);
        mBagRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        updateUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == REQUEST_SHOW_RESOURCE){
            Toast.makeText(getContext(), "UPDATE", Toast.LENGTH_SHORT).show();
            updateUI();
        }
    }

    private void updateUI(){
        String message;
        if (mGameInterface.getPlayerBag() == null){
            message = "У вас нет рюкзака!";
            mCapacityTextView.setText(message);
        } else {
            message = mBag.getName() + "(" + mBag.getEngagedSpace() + "/" + mBag.getCapacity() + ")";
            mCapacityTextView.setText(message);
            if (mBagAdapter == null){
                mBagAdapter = new ResourceBagAdapter(mGameInterface.getPlayer().getCurrentBag().getResourceList());
                mBagRecyclerView.setAdapter(mBagAdapter);
            } else {
                mBagAdapter.setResourceList(mGameInterface.getPlayer().getCurrentBag().getResourceList());
                mBagAdapter.notifyDataSetChanged();
            }
        }
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
    }

    public interface OnFragmentInteractionListener {}

    class OneResourceItem extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Resource mResource;

        public OneResourceItem(View itemView) {
            super(itemView);
            setup();
        }

        private void setup(){
            itemView.setOnClickListener(this);
            int cardSize = mBagRecyclerView.getWidth();
            cardSize = (cardSize-40)/4;
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(cardSize, cardSize);
            itemView.setLayoutParams(params);
        }

        private void setResource(Resource resource){
            mResource = resource;
            fillImage();
        }

        private void fillImage(){
            ImageView imageView = (ImageView) itemView.findViewById(R.id.src_image) ;
            InputStream inputStream = null;
            try{
                inputStream = getActivity().getAssets().open(mResource.getAssertDrawable());
                Drawable d = Drawable.createFromStream(inputStream, null);
                imageView.setImageDrawable(d);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                try{
                    if(inputStream!=null)
                        inputStream.close();
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }

        @Override
        public void onClick(View view) {
            DialogFragment dialog = Assistant.getFragmentForShowResource(mResource, Showable.IN_BAG);
            dialog.setTargetFragment(BagFragment.this, REQUEST_SHOW_RESOURCE);
            dialog.show(getFragmentManager(), RESOURCE_DIALOG);
        }
    }

    class ResourceBagAdapter extends RecyclerView.Adapter<OneResourceItem>{

        private List<Resource> mResourceList;

        ResourceBagAdapter(List<Resource> resourceList) {
            mResourceList = resourceList;
        }

        void setResourceList(List<Resource> resourceList) {
            mResourceList = resourceList;
        }

        @Override
        public OneResourceItem onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.one_resource_item, null, false);
            return new OneResourceItem(view);
        }

        @Override
        public void onBindViewHolder(OneResourceItem holder, int position) {
            holder.setResource(mResourceList.get(position));
        }

        @Override
        public int getItemCount() {
            return mResourceList.size();
        }
    }
}
