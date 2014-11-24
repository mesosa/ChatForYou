package se.mah.mosa.chatforyou;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link FragmentCreateAccount#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FragmentCreateAccount extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    EditText ETusername1;
    EditText ETpassword1;

    private String user = "";
    private String password = "";

    private Firebase mFirebase;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCreateAccount.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCreateAccount newInstance(String param1, String param2) {
        FragmentCreateAccount fragment = new FragmentCreateAccount();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public FragmentCreateAccount() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mFirebase.setAndroidContext(getActivity());
        mFirebase = new Firebase("https://radiant-inferno-8373.firebaseio.com/");


        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.create_account_fragment_layout, container, false);

        ETpassword1 = (EditText) root.findViewById(R.id.tvCAPassword);
        ETusername1 = (EditText) root.findViewById(R.id.tvCAUsername);


        Button btn = (Button) root.findViewById(R.id.btnCreateAccountSubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebase.createUser(ETusername1.getText().toString(),ETpassword1.getText().toString(), new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_SHORT).show();
                        getFragmentManager().popBackStackImmediate();

                    }
                    @Override
                    public void onError(FirebaseError firebaseError) {
                        int code = firebaseError.getCode();


                        if( code == FirebaseError.EMAIL_TAKEN ){
                            Toast.makeText(getActivity(), "email taken ", Toast.LENGTH_SHORT).show();

                        }else if (code == FirebaseError.INVALID_EMAIL){
                            Toast.makeText(getActivity(), "email invalid", Toast.LENGTH_SHORT).show();

                        }else if (code == FirebaseError.UNKNOWN_ERROR){
                        Toast.makeText(getActivity(), "unknown error", Toast.LENGTH_SHORT).show();

                    }else if (code == FirebaseError.DISCONNECTED){
                        Toast.makeText(getActivity(), "disconnected", Toast.LENGTH_SHORT).show();

                    }else if (code == FirebaseError.DENIED_BY_USER){
                            Toast.makeText(getActivity(), "DENIED_BY_USER", Toast.LENGTH_SHORT).show();

                        }else if (code == FirebaseError.INVALID_TOKEN){
                            Toast.makeText(getActivity(), "INVALID_TOKEN", Toast.LENGTH_SHORT).show();

                        }else if (code == FirebaseError.INVALID_PROVIDER){
                            Toast.makeText(getActivity(), "INVALID_PROVIDER", Toast.LENGTH_SHORT).show();

                        }else if (code == FirebaseError.INVALID_AUTH_ARGUMENTS){
                            Toast.makeText(getActivity(), "INVALID_AUTH_ARGUMENTS", Toast.LENGTH_SHORT).show();

                        }else if (code == FirebaseError.AUTHENTICATION_PROVIDER_DISABLED){
                            Toast.makeText(getActivity(), "AUTHENTICATION_PROVIDER_DISABLED", Toast.LENGTH_SHORT).show();

                        }
                        else {

                            Toast.makeText(getActivity(), "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return root;
    }


//    public void createaccount (View v){
//
//        user = ETusername1.getText().toString();
//        password =  ETpassword1.getText().toString();
//
//        mFirebase.createUser(user,password, new Firebase.ResultHandler() {
//            @Override
//            public void onSuccess() {
//                Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_SHORT).show();
//                getFragmentManager().popBackStackImmediate();
//
//            }
//            @Override
//            public void onError(FirebaseError firebaseError) {
//                Toast.makeText(getActivity(), "Registration unsuccessful", Toast.LENGTH_SHORT).show();
//            }
//        });


  //  }
//


}
