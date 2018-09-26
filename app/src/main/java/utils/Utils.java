package utils;

import android.content.Context;
import android.content.res.Resources;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

import kleyton.com.br.testegrupozap.R;
import propertylist.model.Property;

/**
 * Created by kleyton on 17/01/18.
 */

public class Utils {

    public static void loadImage(final Context context, final String path, final ImageView imageView) {

        if (path.isEmpty()) {

            return;
        }
        Picasso.with(context)
                .load(path)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(context).load(path).networkPolicy(NetworkPolicy.NO_CACHE)
                                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                                .into(imageView);
                    }
                });
    }

    public static String formatPrice(Property property) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String price = property.getPricingInfos().getPrice();
        float formatPrice = Float.parseFloat(price);

        return nf.format(formatPrice);
    }

    public static String formatInfos(Property property, Context context) {

        Resources resources = context.getResources();
        String bathRooms = resources.getString(R.string.bathRooms);
        String badRooms = resources.getString(R.string.badRooms);
        String usableArea = resources.getString(R.string.usableArea);

        StringBuilder infos = new StringBuilder();
        infos.append(String.format(bathRooms, property.getBathrooms())).append(", ");
        infos.append(String.format(badRooms, property.getBedrooms())).append(", ");
        infos.append(String.format(usableArea, property.getUsableAreas()));


        return infos.toString();
    }
}
