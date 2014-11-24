package se.mah.mosa.chatforyou;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mosa on 2014-11-21.
 */
public class GroupActivity extends Activity {


    String name;
    public GroupActivity(){
    }
    public GroupActivity(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



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
    public static class PlaceholderFragment extends Fragment  {

        EditText ETgroupName;
        ListView lv;
        List<String> arraylist;
        Firebase mFirebase;

        public PlaceholderFragment() {
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mFirebase.setAndroidContext(getActivity());
            mFirebase = new Firebase("https://radiant-inferno-8373.firebaseio.com");

            mFirebase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String list_items = dataSnapshot.getValue().toString();

                    String[] values = list_items.split(",");

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, values);
                    lv.setAdapter(arrayAdapter);

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.group_fragment_layout, container, false);
            Button btn = (Button) rootView.findViewById(R.id.btnAddGroup);
            ETgroupName = (EditText) rootView.findViewById(R.id.editText);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFirebase.child("group " + ETgroupName.getText().toString()).setValue(ETgroupName.getText().toString());

                    mFirebase.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot snapshot, String s) {
// Add children to your list, and then notify the adapter of the changes
                            arraylist.add(s);
                            System.out.println(snapshot.getValue()+ " " + s);  //prints "Do you have data? You'll love Firebase."

                        }
                        @Override
                        public void onChildChanged(DataSnapshot snapshot, String s) {
                        }
                        @Override
                        public void onChildRemoved(DataSnapshot snapshot) {
                        }
                        @Override
                        public void onChildMoved(DataSnapshot snapshot, String s) {
                        }
                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                        }
                    });

                    arraylist.add(ETgroupName.getText().toString());
               //     ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, arraylist);
                //    lv.setAdapter(arrayAdapter);

                }
            });

            lv = (ListView)rootView.findViewById(R.id.listView);
            arraylist = new ArrayList<String>();


            return rootView;
        }
//        @Override
//        public void onViewCreated(View view, Bundle savedInstanceState) {
//            final ListView lv = (ListView)getView().findViewById(R.id.listView);
//            List<String> arraylist = new ArrayList<String>();
//            arraylist.add("foo");
//            arraylist.add("bar");
//            arraylist.add("foo");
//            arraylist.add("bar");
//            arraylist.add("foo");
//            arraylist.add("bar");
//            arraylist.add("foo");
//            arraylist.add("bar");
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1, arraylist);
//            lv.setAdapter(arrayAdapter);
//            lv.setClickable(true);
//            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//
//                    switch(position) {
//                        case 0:
//                            FragmentManager fm = getFragmentManager();
//                            Fragment fragment = new ChatActivity();
//                            fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
//                            break;
//
//
//                    }
//
//
//                }
//            });
//            super.onViewCreated(view, savedInstanceState);
//        }
    }


}
