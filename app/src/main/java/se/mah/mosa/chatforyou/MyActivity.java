package se.mah.mosa.chatforyou;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class MyActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            FragmentManager fm = getFragmentManager();
            Fragment fragment = new FragmentAbout();
            fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null)
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        EditText ETusername;
        EditText ETpassword;


         Firebase mFirebase;

        public PlaceholderFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            mFirebase.setAndroidContext(getActivity());
            mFirebase = new Firebase("https://radiant-inferno-8373.firebaseio.com");


            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.login_fragment_layout, container, false);
            ETpassword = (EditText) rootView.findViewById(R.id.tvPassword);
            ETusername = (EditText) rootView.findViewById(R.id.tvUsername);


            Button btn = (Button) rootView.findViewById(R.id.btnLogin);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFirebase.authWithPassword(ETusername.getText().toString(), ETpassword.getText().toString(), new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (getActivity().getApplicationContext(), GroupActivity.class);

                            startActivity(intent);
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError error) {
                            int code = error.getCode();

                            if(code == FirebaseError.EMAIL_TAKEN ) {
                                Toast.makeText(getActivity(), "email taken ", Toast.LENGTH_SHORT).show();
                            }else {


                                Toast.makeText(getActivity(), "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            });
            return rootView;
        }
    }

    public void createAccount (View v){
        FragmentManager fm = getFragmentManager();
        Fragment fragment = new FragmentCreateAccount();
        fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
       // fm.beginTransaction().addToBackStack(null);
    }

//    public void login (View v){
//
//
//    }


}
