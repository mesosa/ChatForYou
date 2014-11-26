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
 * Created by Mosa_fag on 2014-11-24.
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

}
