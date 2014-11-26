package se.mah.mosa.chatforyou;

/**
 * Created by andreas on 25/11/14.
 */
public class Group {
    String name;
    public Group(){
    }
    public Group(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}