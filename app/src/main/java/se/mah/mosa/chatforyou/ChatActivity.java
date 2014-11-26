package se.mah.mosa.chatforyou;

import android.app.Fragment;
import android.os.Bundle;
import android.text.format.Time;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ChatActivity#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ChatActivity extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText sendMsg;
    Button btn;
    ListView lv;
    List<String> arraylist;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> childrenList;

    Firebase mFirebase;
    ChatMessages cm;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatActivity.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatActivity newInstance(String param1, String param2) {
        ChatActivity fragment = new ChatActivity();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public ChatActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            childrenList = new ArrayList();


            lv = (ListView)getView().findViewById(R.id.chatlist);

            mFirebase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                    for(DataSnapshot ds : children){

                        childrenList.add(ds.getName());

                    }


                    arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, childrenList);
                    lv.setAdapter(arrayAdapter);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
           ;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chat_layout, container, false);
        arraylist = new ArrayList<String>();
        btn = (Button) rootView.findViewById(R.id.sendmsgbutton);
        sendMsg = (EditText) rootView.findViewById(R.id.sendmessagetext);
        mFirebase.setAndroidContext(getActivity());
        mFirebase = new Firebase("https://radiant-inferno-8373.firebaseio.com/");

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Firebase hopperRef = mFirebase.child("name");
                Map<String, Object> msg = new HashMap<String, Object>();
                String id = hopperRef.push().getName();
                cm = new ChatMessages(id, "Hello", sendMsg.getText().toString(), "hehe");

                hopperRef.push().setValue(cm);

               // msg.put(id, cm);

                //hopperRef.updateChildren(msg);

                hopperRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot snapshot, String s) {

                        arraylist.add(s);
                        System.out.println(snapshot.getValue() + " " + s + "CHANGED");
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

        return rootView;
    }

    /*
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
*/

}
