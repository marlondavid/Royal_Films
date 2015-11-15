package com.marlonortiz.royal_films;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String[] menu;

    private DrawerLayout drawerLayout;

    private ListView listView;

    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = getResources().getStringArray(R.array.menu);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);

        ArrayList<list_menu> items = new ArrayList<list_menu>();
        items.add(new list_menu(menu[0],R.drawable.ic_account));
        items.add(new list_menu(menu[1],R.drawable.ic_film));
        items.add(new list_menu(menu[2],R.drawable.ic_theater));
        items.add(new list_menu(menu[3], R.drawable.ic_contact));

        listView.setAdapter(new List_Adapter(this, items));

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.drawable.ic_menu,R.string.drawer_open,R.string.drawer_close){
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
               // getActionBar().setTitle(mTitle);
               // invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
              //  invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
       // getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       if(drawerToggle.onOptionsItemSelected(item)){
           return true;
       }

        return super.onOptionsItemSelected(item);
    }

    private void selectitem (int position){

        Fragment fragment=null;

        switch(position){
            case 1:
                fragment = new FilmsFragment();
                break;
        }

        FragmentManager fragmentManager = getFragmentManager() ;
        fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();

        listView.setItemChecked(position, true);

        setTitle(menu[position]);
        drawerLayout.closeDrawer(listView);

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectitem(position);
            Toast.makeText(getApplicationContext(),menu[position], Toast.LENGTH_SHORT).show();//Toast.makeText(context, text, duration).show();
        }
    }




}
