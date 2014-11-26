package se.mah.mosa.chatforyou;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

/**
 * Created by Mosa on 2014-11-24.
 */
public class ChatMessages{
    String id;
    String from;
    String message;
    String timestamp;


    public ChatMessages() {
    }
    public ChatMessages(String id, String from, String message, String timestamp) {
        this.id = id;
        this.from = from;
        this.message = message;
        this.timestamp = timestamp;

    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //     public static class PlaceholderFragment extends Fragment  {

    public static class PlaceholderFragment extends Fragment {

        String timestamp;
        Firebase mFirebase;
        public void onCreate(Bundle savedInstance){

            mFirebase.setAndroidContext(getActivity());
            mFirebase = new Firebase("https://radiant-inferno-8373.firebaseio.com");

        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

            View rootView = inflater.inflate(R.layout.chat_layout, container, false);
            Button b = (Button)rootView.findViewById(R.id.sendmsgbutton);
            EditText msgTxt = (EditText)rootView.findViewById(R.id.sendmessagetext);


            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

            return rootView;

        }
    }
}
