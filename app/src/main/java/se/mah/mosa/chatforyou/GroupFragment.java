package se.mah.mosa.chatforyou;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mosa on 2014-12-02.
 */
public class GroupFragment extends Fragment{

    private static final String TAG = "GroupFragment";

    Firebase mFirebase = new Firebase("https://radiant-inferno-8373.firebaseio.com/");

    EditText name;
    ListView list;

    GroupAdapter adapter;

    ArrayList<Group> data = new ArrayList<Group>();

    public static GroupFragment newInstance() {
        GroupFragment fragment = new GroupFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public GroupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        mFirebase.addChildEventListener(mChildEventListener);

        adapter = new GroupAdapter(getActivity(), data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.group_fragment_layout, container, false);

        name = (EditText) root.findViewById(R.id.editText);

        list = (ListView) root.findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getFragmentManager().beginTransaction().replace(R.id.container, ChatFragment.newInstance(data.get(position).id)).addToBackStack("chat").commit();
            }
        });

        Button btn = (Button) root.findViewById(R.id.btnAddGroup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase group = mFirebase.push();

                Group g = new Group(group.getName(), name.getText().toString(), new HashMap<String, Object>());

                group.setValue(g);
            }
        });

        return root;
    }


    private ChildEventListener mChildEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot snapshot, String s) {
            Group g = snapshot.getValue(Group.class);

            data.add(g);

            adapter.notifyDataSetChanged();
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
    };
}
