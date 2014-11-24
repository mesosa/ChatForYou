package se.mah.mosa.chatforyou;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chat_layout, container, false);
        return rootView;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        final ListView lv = (ListView)getView().findViewById(R.id.listView);
        List<String> arraylist = new ArrayList<String>();
        arraylist.add("Hej");
        arraylist.add("Tjena!");
        arraylist.add("läget?");
        arraylist.add("mjo, detär nice");
        arraylist.add("vad ska du göra idag?");
        arraylist.add("nä, inte mycket");
        arraylist.add("ahapp.");
        arraylist.add("sorry, but noope");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1, arraylist);
        lv.setAdapter(arrayAdapter);
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

//                switch(position) {
//                    case 0:
//                        FragmentManager fm = getFragmentManager();
//                        Fragment fragment = new ChatActivity();
//                        fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
//                        break;


//                }


            }
        });
        super.onViewCreated(view, savedInstanceState);
    }


}
