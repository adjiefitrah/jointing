package example.com.jointing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.jointing.R;

/**
 * Created by pc on 13/12/2017.
 */

public class MenuAdapter extends BaseAdapter {
    private final String[] list;
    private  final int[] img_id;
    private Context mContext;

    public MenuAdapter(Context c, String[] list_grid, int[] img_grid){
        mContext = c;
        this.img_id = img_grid;
        this.list = list_grid;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.activity_menu_item, null);
            TextView textView = (TextView) grid.findViewById(R.id.txt_menu);
            ImageView imageView = (ImageView)grid.findViewById(R.id.img_menu);
            textView.setText(list[position]);
            imageView.setImageResource(img_id[position]);
        } else {
            grid = convertView;
        }

        return grid;
    }
}
