package propertylist.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kleyton.com.br.testegrupozap.R;
import utils.Utils;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyViewHolder> {

    private Context context;
    private ArrayList<Property> propertyList;

    public PropertyListAdapter(Context context, ArrayList<Property> propertyList) {
        this.context = context;
        this.propertyList = propertyList;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.property_item_list, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        Property property = propertyList.get(position);
        holder.propertyPrice.setText(property.getPricingInfos().getPrice());
        holder.propertyInfos.setText(property.getBathrooms() + "banheiro(s)");

        if (property.getImages() != null)
            Utils.loadImage(context, property.getImages()[0], holder.propertyImage);
    }

    @Override
    public int getItemCount() {
        return propertyList != null ? propertyList.size() : 0;
    }
}
