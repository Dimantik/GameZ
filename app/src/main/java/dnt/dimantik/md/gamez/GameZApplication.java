package dnt.dimantik.md.gamez;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.location.Location;

/**
 * Created by dimantik on 27.03.2018.
 */

public class GameZApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BDHelper.setup(this);
    }
}
