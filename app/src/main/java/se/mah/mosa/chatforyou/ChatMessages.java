package se.mah.mosa.chatforyou;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by Mosa on 2014-11-24.
 */
public class ChatMessages extends Activity{
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
//gör typ samma sak som du gjorde i ChatActivity men rätt.

    }
}
