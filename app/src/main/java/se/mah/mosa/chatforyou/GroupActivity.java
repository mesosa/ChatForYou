package se.mah.mosa.chatforyou;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mosa on 2014-11-21.
 */
public class GroupActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.group_fragment_layout, container, false);
            return rootView;
        }
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            final ListView lv = (ListView)getView().findViewById(R.id.listView);
            List<String> arraylist = new ArrayList<String>();
            arraylist.add("foo");
            arraylist.add("bar");
            arraylist.add("foo");
            arraylist.add("bar");
            arraylist.add("foo");
            arraylist.add("bar");
            arraylist.add("foo");
            arraylist.add("bar");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1, arraylist);
            lv.setAdapter(arrayAdapter);
            lv.setClickable(true);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                    switch(position) {
                        case 0:
                            FragmentManager fm = getFragmentManager();
                            Fragment fragment = new ChatActivity();
                            fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                            break;


                    }


                }
            });
            super.onViewCreated(view, savedInstanceState);
        }
    }


}
