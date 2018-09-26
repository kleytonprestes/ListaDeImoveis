package propertylist.model;

import propertylist.model.Property;

public interface PropertyListClick {

    void onItemClickListener(Property property);

    void onFavoriteClickListener(Property property);
}
