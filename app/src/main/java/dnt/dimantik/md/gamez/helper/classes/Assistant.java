package dnt.dimantik.md.gamez.helper.classes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import dnt.dimantik.md.gamez.R;
import dnt.dimantik.md.gamez.controllers.bag.fragment.BagFragment;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowBagDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowClothesDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowImportantResourceDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowTransportDialog;
import dnt.dimantik.md.gamez.controllers.dialogs.ShowWeaponDialog;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Bag;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Clothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FireArms;
import dnt.dimantik.md.gamez.game.logic.clases.resource.ImportantResource;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Transport;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Weapon;

/**
 * Created by dimantik on 1/30/18.
 */

public class Assistant {

    public static String getTime(int minutes){
        minutes = minutes - (minutes/(24*60));
        int hour = minutes/60;
        int currentMinutes = minutes - hour*60;
        String result = "";
        if (hour < 10){
            result += "0" + hour + ":";
        } else {
            result += hour + ":";
        }
        if (currentMinutes < 10){
            result += "0" + currentMinutes;
        } else {
            result += currentMinutes;
        }
        return result;
    }

    public static String wrapUUID(UUID uuid){
        if (uuid != null){
            return uuid.toString();
        } else {
            return "null";
        }
    }

    public static UUID convertToUUID(String uuidString){
        try {
            UUID uuid = UUID.fromString(uuidString);
            return uuid;
        } catch (Exception e){
            return null;
        }
    }

    public static void fillImage(Context context, ImageView imageView, String assertDrawablePath){
        InputStream inputStream = null;
        try{
            inputStream = context.getAssets().open(assertDrawablePath);
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

    public static int getCardSize(int width){
        return (width-40)/4;
    }

    public static DialogFragment getFragmentForShowResource(Resource resource, String phase){
        DialogFragment dialog = null;

        if (resource == null) {
            return null;
        }

        if (resource instanceof ImportantResource){
            dialog = ShowImportantResourceDialog.getInstance(resource.getId(), phase);
        } else if (resource instanceof Clothes){
            dialog = ShowClothesDialog.getInstance(resource.getId().toString(), phase);
        } else if (resource instanceof Weapon){
            dialog = ShowWeaponDialog.getInstance(resource.getId().toString(), phase);
        } else if (resource instanceof Transport){
            dialog = ShowTransportDialog.getInstance(resource.getId().toString(), phase);
        } else if (resource instanceof Bag){
            dialog = ShowBagDialog.getInstance(resource.getId().toString(), phase);
        }

        return dialog;
    }
}
