package se.mah.mosa.chatforyou;

import java.util.Map;

/**
 * Created by andreas on 25/11/14.
 */
public class Group {
    String id;

    String name;

    Map<String, Object> messages;

    public Group(){
    }

    public Group(String id, String name, Map<String, Object> messages) {
        this.id = id;
        this.name = name;
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, Object> messages) {
        this.messages = messages;
    }
}