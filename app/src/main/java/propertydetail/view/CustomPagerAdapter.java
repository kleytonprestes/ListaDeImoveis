package propertydetail.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import kleyton.com.br.testegrupozap.R;
import utils.Utils;

class CustomPagerAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<String> imagesList;

    public CustomPagerAdapter(Context context, ArrayList<String> imagesList) {
        this.context = context;
        layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imagesList = imagesList;
    }

    @Override
    public int getCount() {
        return this.imagesList != null ? imagesList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = itemView.findViewById(R.id.image_background);
        Utils.loadImage(context, imagesList.get(position), imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
