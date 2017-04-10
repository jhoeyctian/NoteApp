package notes.ctian.jhoey.com.noteapp.activities;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import notes.ctian.jhoey.com.noteapp.R;
import notes.ctian.jhoey.com.noteapp.fragments.NoteListFragment;
import notes.ctian.jhoey.com.noteapp.fragments.ToDoSummaryFragment;

public class HomeHolder extends AppCompatActivity
        implements NoteListFragment.OnFragmentInteractionListener {

    BottomBar bottomBar;
    FrameLayout contentContainer;
    FragmentManager fm;

    Fragment currentFragment;
    Fragment notes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_holder);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        contentContainer = (FrameLayout) findViewById(R.id.contentContainer);

        fm = getSupportFragmentManager();

        notes = new NoteListFragment();

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if(tabId == R.id.tabNotes){

                    fm.beginTransaction().replace(contentContainer.getId(),notes).commit();

                } else if(tabId == R.id.tabTodo){
                    currentFragment = new ToDoSummaryFragment();
                    fm.beginTransaction().replace(contentContainer.getId(),currentFragment).commit();
                }
            }
        });
    }


    public void refresh(){

        if(currentFragment != null && currentFragment instanceof NoteListFragment){
            ((NoteListFragment) currentFragment).setupList();
        }

        /*if(currentFragment != null && currentFragment instanceof IncomeFragment){
            ((IncomeFragment) currentFragment).refreshList();
        }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pageholder_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        /*if(id == R.id.actionLogout) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Warning");
            alertDialogBuilder
                    .setMessage("Are you sure you want to sign out?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            firebaseAuth = FirebaseAuth.getInstance();
                            firebaseAuth.signOut();
                            dialog.cancel();
                            onBackPressed();
                            Toast.makeText(PageHolder.this, "Signed out successfully", Toast.LENGTH_LONG).show();

                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }*/

        /*if(id == R.id.actionUserGuide) {
            InstructionFragment instructionFragment = new InstructionFragment();
            instructionFragment.show(getSupportFragmentManager(), "instructionFragment");
        }*/

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
