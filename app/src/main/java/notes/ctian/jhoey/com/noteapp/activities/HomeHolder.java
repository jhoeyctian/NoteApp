package notes.ctian.jhoey.com.noteapp.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import notes.ctian.jhoey.com.noteapp.R;
import notes.ctian.jhoey.com.noteapp.fragments.NoteFragment;
import notes.ctian.jhoey.com.noteapp.fragments.NoteListFragment;
import notes.ctian.jhoey.com.noteapp.fragments.ToDoItemsFragment;
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

                    currentFragment = new NoteListFragment();
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
//            ((NoteListFragment) currentFragment).setupList();
            Toast.makeText(getApplicationContext(), "refresh", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pageholder_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.actionAdd){
            if(currentFragment !=null && currentFragment instanceof NoteListFragment){
                NoteFragment fragment = new NoteFragment();
                fragment.show(getFragmentManager(), "newNote");
            }
            if(currentFragment !=null && currentFragment instanceof ToDoSummaryFragment){
                ToDoItemsFragment fragment = new ToDoItemsFragment();
                fragment.show(getFragmentManager(), "todo");
            }
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
