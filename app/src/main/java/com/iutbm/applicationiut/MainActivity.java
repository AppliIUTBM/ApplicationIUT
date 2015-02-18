package com.iutbm.applicationiut;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bugsense.trace.BugSenseHandler;
import com.iutbm.applicationiut.Constants.MailsConstants;


public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private final int section_accueil = 0;
    private final int section_edt = 1;
    private final int section_mails = 2;
    private final int section_ecocampus = 3;
    private final int section_universite = 4;
    private final int section_iut = 5;
    private final int section_facebook = 6;
    private final int section_formations = 7;
    private final int section_agenda = 8;
    /**
     * The id used to identifiy the robodemo "instance" related to this activity.
     */
    private final static String DEMO_ACTIVITY_ID = "activity_main";
    /**
     * A boolean holding the internal state of the activity under RoboDemo, whether or not to display RoboDemo.
     */
    private boolean showDemo = true;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private boolean needHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        setContentView(R.layout.activity_main);

        BugSenseHandler.initAndStartSession(MainActivity.this, "5fdabd9c");

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        SharedPreferences settings = getSharedPreferences("help", 0);
        needHelp = settings.getBoolean("aide", true);
        if (!settings.contains("aide")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("aide", true);
            editor.commit();
        }
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

        // Appel fragment
        //fragmentManager.beginTransaction()
        //.replace(R.id.container, new Fragment())
        //        .commit();

        switch (position) {
            case section_accueil:
                ft.replace(R.id.container, new AccueilFragment()).commit();
                break;
            case section_mails:
                ft.replace(R.id.container, new MailsReceiverFragment()).addToBackStack("retour2").commit();
                break;
            case section_formations:
                ft.replace(R.id.container, new FormationsFragment()).addToBackStack("retour3").commit();
                break;
            case section_ecocampus:
                ft.replace(R.id.container, new EcoCampusFragment()).addToBackStack("retour4").commit();
                break;
            case section_agenda:
                ft.replace(R.id.container, new ContactsFragment()).addToBackStack("retour5").commit();
                break;
            case section_facebook:
                ft.replace(R.id.container, new FacebookFragment()).addToBackStack("retour6").commit();
                break;
            case section_edt:
                Intent intent = new Intent(getApplicationContext(),EDTActivity.class);
                startActivity(intent);
                break;
            case section_iut:
                Intent toIut = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.iut-bm.univ-fcomte.fr/‎"));
                startActivity(toIut);
                break;
            case section_universite:
                Intent toUniversite = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.univ-fcomte.fr/"));
                startActivity(toUniversite);
                break;
        }

    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        //actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent;
        switch (item.getItemId()) {
            case R.id.calendar_action:
                intent = new Intent(this,ConfigEDTActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_deconnect:
                intent = new Intent(MailsConstants.ACTION_STOP_SERVICE);
                intent.putExtra(MailsConstants.KEY_STOP_SERVICE, true);
                sendBroadcast(intent);
                SharedPreferences sharedPref = getSharedPreferences(MailsConstants.KEY_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(), R.string.disconnected, Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
