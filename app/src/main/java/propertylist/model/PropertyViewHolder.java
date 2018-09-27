package propertylist.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kleyton.com.br.testegrupozap.R;

public class PropertyViewHolder extends RecyclerView.ViewHolder {

    public ImageView propertyImage;
    public ImageView propertyFavorite;
    public TextView propertyPrice;
    public TextView propertyInfos;

    public PropertyViewHolder(View itemView) {
        super(itemView);

        propertyImage = itemView.findViewById(R.id.property_image);
        propertyFavorite = itemView.findViewById(R.id.property_favorite);
        propertyPrice = itemView.findViewById(R.id.property_price);
        propertyInfos = itemView.findViewById(R.id.property_infos);
    }
}
