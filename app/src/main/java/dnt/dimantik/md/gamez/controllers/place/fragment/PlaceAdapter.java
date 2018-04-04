package dnt.dimantik.md.gamez.controllers.place.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileReader;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowPlaceDialogFragment;
import dnt.dimantik.md.gamez.game.logic.interfaces.GameInterface;
import dnt.dimantik.md.gamez.game.logic.clases.map.Place;
import dnt.dimantik.md.gamez.helper.classes.Assistant;

/**
 * Created by dimantik on 2/8/18.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder> {

    public static final int REQUEST_SHOW_PLACE = 0;

    private Fragment mFragment;
    private GameInterface mGameInterface;
    private FragmentManager mFM;
    private Context mContext;

    public PlaceAdapter(GameInterface gameInterface, FragmentManager fm, Context context, Fragment fragment) {
        mGameInterface = gameInterface;
        mFM = fm;
        mContext = context;
        mFragment = fragment;
    }

    @Override
    public PlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_place_item_view, parent, false);
        return new PlaceHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaceHolder holder, int position) {
        String id = mGameInterface.getCurrentLocation().getId() + "" + (position + 1);
        Place place = null;
        boolean isHere = false;
        place = mGameInterface.getPlaceForId(Integer.parseInt(id));
        if (place == mGameInterface.getCurrentPlace()){
            isHere = true;
        }
        holder.fillView(place, isHere);
    }

    @Override
    public int getItemCount() {
       return mGameInterface.getCurrentLocation().getPlaceList().size();
    }

    class PlaceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Button mGoToPlaceButton;
        private ImageButton mPlaceInfoButton;
        private TextView mPlaceName;

        private Place mPlace;
        private boolean mIsHere;

        public PlaceHolder(View itemView) {
            super(itemView);

            mGoToPlaceButton = (Button)itemView.findViewById(R.id.go_to_place);
            mPlaceInfoButton = (ImageButton)itemView.findViewById(R.id.info_place);
            mPlaceName = (TextView)itemView.findViewById(R.id.place_name);
            mGoToPlaceButton.setOnClickListener(this);
            mPlaceInfoButton.setOnClickListener(this);
        }



        public void fillView(Place place, boolean isHere){
            mPlace = place;
            mIsHere = isHere;

            mPlaceName.setText(place.getName());


            if (isHere) {
                mGoToPlaceButton.setVisibility(View.INVISIBLE);
            }

            ImageView imageView = (ImageView) itemView.findViewById(R.id.place_image);
            Assistant.fillImage(mContext, imageView, place.getAssertDrawableName());
        }

        @Override
        public void onClick(View view) {
            ShowPlaceDialogFragment dialog;
            switch (view.getId()){
                case R.id.go_to_place:
                    dialog = ShowPlaceDialogFragment.getInstance(mPlace.getId(), ShowPlaceDialogFragment.GO_TO_PLACE_ACTION);
                    dialog.setTargetFragment(mFragment, REQUEST_SHOW_PLACE);
                    dialog.show(mFM, "PLACE");
                    break;
                case R.id.info_place:
                    dialog = ShowPlaceDialogFragment.getInstance(mPlace.getId(), ShowPlaceDialogFragment.SHOW_INFO_ABOUT_PLACE);
                    dialog.show(mFM, "PLACE");
                    break;
            }
        }
    }
}
