package propertylist.model;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.NumberFormat;
import java.util.ArrayList;

import kleyton.com.br.testegrupozap.R;
import utils.Utils;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyViewHolder> {

    private Context context;
    private ArrayList<Property> propertyList;
    private PropertyListClick propertyListClick;

    public PropertyListAdapter(Context context, ArrayList<Property> propertyList, PropertyListClick propertyListClick) {
        this.context = context;
        this.propertyList = propertyList;
        this.propertyListClick = propertyListClick;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.property_item_list, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        final Property property = propertyList.get(position);
        holder.propertyPrice.setText(formatPrice(property));
        holder.propertyInfos.setText(formatInfos(property));

        if (property.getImages() != null)
            Utils.loadImage(context, property.getImages().get(0), holder.propertyImage);

        if (property.isFavorite()) {
            holder.propertyFavorite.setImageDrawable(context.getResources().getDrawable(R.drawable.star_red));
        } else {
            holder.propertyFavorite.setImageDrawable(context.getResources().getDrawable(R.drawable.star_blank));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                propertyListClick.onItemClickListener(property);
            }
        });

        holder.propertyFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                propertyListClick.onFavoriteClickListener(property);
            }
        });
    }


    private String formatPrice(Property property) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String price = property.getPricingInfos().getPrice();
        float formatPrice = Float.parseFloat(price);

        return nf.format(formatPrice);
    }

    private String formatInfos(Property property) {

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
    @Override
    public int getItemCount() {
        return propertyList != null ? propertyList.size() : 0;
    }
}
