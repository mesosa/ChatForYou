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

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.login_fragment_layout, container, false);
            return rootView;

        }
    }

    public void createAccount (View v){
        FragmentManager fm = getFragmentManager();
        Fragment fragment = new FragmentCreateAccount();
        fm.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
       // fm.beginTransaction().addToBackStack(null);
    }

    public void login (View v){
        mFirebase.authWithPassword("", "", new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onAuthenticationError(FirebaseError error) {
// Handle errors
            }
        });
        Intent intent = new Intent (this, GroupActivity.class);
        startActivity(intent);
    }


}
