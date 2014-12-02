package se.mah.mosa.chatforyou;

/**
 * Created by mosa  on 2014-11-24.
 */
public class ChatMessage {

    String message;
    String from;
    String time;

    public ChatMessage() {

    }

    public ChatMessage(String message, String from, String time) {
        this.message = message;
        this.from = from;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
