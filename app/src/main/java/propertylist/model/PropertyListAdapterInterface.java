package propertylist.model;

import java.util.ArrayList;

public interface PropertyListAdapterInterface {

    void onItemClickListener(Property property);

    void onFavoriteClickListener(Property property);

    void onScrollEnd(ArrayList<Property> propertyListItensPage);
}
