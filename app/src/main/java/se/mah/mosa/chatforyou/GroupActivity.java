package se.mah.mosa.chatforyou;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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


    public  String name;

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
        ArrayList<String> childrenList;


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
             childrenList = new ArrayList();


            mFirebase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                    for(DataSnapshot ds : children){

                        childrenList.add(ds.getName());

                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1, childrenList);
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

                    Group gp = new Group(ETgroupName.getText().toString());
                    mFirebase.setValue(gp);
                    

                    mFirebase.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot snapshot, String s) {
                            System.out.println(snapshot.getValue()+ " " + s);

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


                }
            });

            lv = (ListView)rootView.findViewById(R.id.listView);
            arraylist = new ArrayList<String>();

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                    switch(position) {
                        case 0:
                            FragmentManager fm = getFragmentManager();
                            Fragment fragment = new ChatActivity();
                            fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
                            break;
                        case 1:
                            FragmentManager fm2 = getFragmentManager();
                            Fragment fragment2 = new ChatActivity();
                            fm2.beginTransaction().replace(R.id.container, fragment2).addToBackStack(null).commit();
                            break;
                    }


                }
            });

            return rootView;
        }
    }

}
