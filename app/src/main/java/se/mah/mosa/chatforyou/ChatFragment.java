package se.mah.mosa.chatforyou;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ChatFragment extends Fragment {

    private final static String TAG = "ChatFragment";

    private static final String ARG_GROUP = "group";

    private String groupId;

    EditText message;

    Firebase mFirebaseGroup, mFirebaseMessages;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");

    ListView list;

    ChatAdapter adapter;

    ArrayList<ChatMessage> data = new ArrayList<ChatMessage>();

    public static ChatFragment newInstance(String id) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_GROUP, id);
        fragment.setArguments(args);
        return fragment;
    }

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            groupId = getArguments().getString(ARG_GROUP);

            mFirebaseGroup = new Firebase("https://radiant-inferno-8373.firebaseio.com/").child(groupId);
            mFirebaseMessages = mFirebaseGroup.child("messages");
            mFirebaseMessages.addChildEventListener(listener);
        }

        adapter = new ChatAdapter(getActivity(), data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.chat_layout, container, false);

        message = (EditText) root.findViewById(R.id.sendmessagetext);

        list = (ListView) root.findViewById(R.id.chatlist);
        list.setAdapter(adapter);

        Button send = (Button) root.findViewById(R.id.sendmsgbutton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid = getActivity().getPreferences(Context.MODE_PRIVATE).getString("uid", "null");

                Date now = new Date();
                ChatMessage m = new ChatMessage(message.getText().toString(), uid, sdf.format(now));
                mFirebaseMessages.push().setValue(m);
            }
        });

        return root;
    }

    private ChildEventListener listener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot snapshot, String s) {
            ChatMessage msg = snapshot.getValue(ChatMessage.class);
            data.add(msg);
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
