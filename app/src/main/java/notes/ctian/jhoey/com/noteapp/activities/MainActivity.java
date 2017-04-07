package notes.ctian.jhoey.com.noteapp.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialize.util.KeyboardUtil;

import notes.ctian.jhoey.com.noteapp.R;
import notes.ctian.jhoey.com.noteapp.fragments.NoteListFragment;
import notes.ctian.jhoey.com.noteapp.fragments.SettingsFragment;
import notes.ctian.jhoey.com.noteapp.fragments.ToDoSummaryFragment;

public class MainActivity extends AppCompatActivity
        implements NoteListFragment.OnFragmentInteractionListener {

    Toolbar mToolbar;
    FrameLayout main_container;
    Fragment fragment;

    private com.mikepenz.materialdrawer.Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        main_container = (FrameLayout) findViewById(R.id.main_container);

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.title_home).withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.title_calendar).withIcon(FontAwesome.Icon.faw_calendar).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.title_settings).withIcon(FontAwesome.Icon.faw_gear).withIdentifier(3),
                        new PrimaryDrawerItem().withName(R.string.title_about).withIcon(FontAwesome.Icon.faw_info).withIdentifier(4)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem instanceof Nameable){
                            String name = ((Nameable)drawerItem).getName().getText(MainActivity.this);
                            mToolbar.setTitle(name);
                        }

                        if (drawerItem != null){
                            int selectedScren = drawerItem.getIdentifier();
                            switch (selectedScren){
                                case 1:
                                    //home
                                    try{
                                        fragment = new NoteListFragment();
                                        openFragment(fragment, "Home");
                                    }catch (Exception ex){
                                        ex.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    //calendar
                                    try{
                                        fragment = new ToDoSummaryFragment();
                                        openFragment(fragment, "Calendar");
                                    }catch (Exception ex){
                                        ex.printStackTrace();
                                    }
                                    break;
                                case 3:
                                    //settings
                                    try{
                                        fragment = new SettingsFragment();
                                        openFragment(fragment, "Settings");
                                    }catch (Exception ex){
                                        ex.printStackTrace();
                                    }
                                    break;
                                case 4:
                                    //about
                                    try{
                                    }catch (Exception ex){
                                        ex.printStackTrace();
                                    }
                                    break;
                            }
                        }
                        return false;
                    }


                })
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View view) {
                        KeyboardUtil.hideKeyboard(MainActivity.this);

                    }

                    @Override
                    public void onDrawerClosed(View view) {

                    }

                    @Override
                    public void onDrawerSlide(View view, float v) {

                    }
                })
                .withFireOnInitialOnClick(true)
                .withSavedInstance(savedInstanceState)
                .build();
    }

    private void openFragment(final Fragment fragment, String title){
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.main_container, fragment)
                .addToBackStack(null)
                .commit();
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void refresh(){
        ((NoteListFragment) fragment).setupList();
    }
}
