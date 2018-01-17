package hotboys69.dat153.nameapp;

/**
 * Created by Emilracerbil on 2018-01-16.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;


public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // maps the image references to the names
    public HashMap<Integer, String> map;

    //reference of pic0 is on positions[0] etc
    public ArrayList<Integer> positions;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return map.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return positions.get(position);
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource((int) positions.get(position));
        return imageView;
    }


}