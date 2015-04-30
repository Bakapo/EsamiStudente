package it.uniba.di.ss1415.esamistudente;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HomeActivity extends ActionBarActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            TextView textView;
            LinearLayout v;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                v = (LinearLayout) getLayoutInflater().inflate(R.layout.main_menu_grid_icon2, null);
                imageView = (ImageView) v.findViewById(R.id.iconImageView);
                textView = (TextView) v.findViewById(R.id.iconTextView);
            } else {
                v = (LinearLayout) convertView;
                imageView = (ImageView) convertView.findViewById(R.id.iconImageView);
                textView = (TextView) convertView.findViewById(R.id.iconTextView);
            }

            imageView.setImageResource(mThumbIds[position]);
            textView.setText(labelIds[position]);
            return v;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.c_notizie, R.drawable.c_appelli,
                R.drawable.c_docenti, R.drawable.c_orario_lezioni,
                R.drawable.c_libretto, R.drawable.c_gruppo,
                R.drawable.c_segreteria, R.drawable.c_segr_didattica,
                R.drawable.c_biblioteca
        };

        private String[] labelIds = getResources().getStringArray(R.array.main_menu_labels);
    }
}
