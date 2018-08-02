package tech.blur.eventhub.features.event.assigned.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import tech.blur.eventhub.R;
import tech.blur.eventhub.features.BaseActivity;
import tech.blur.eventhub.features.MvpPresenter;
import tech.blur.eventhub.features.MvpView;
import tech.blur.eventhub.features.core.events.AdapterEvent;
import tech.blur.eventhub.features.core.events.model.Event;
import tech.blur.eventhub.features.event.add.presentation.AddEventActivity;
import tech.blur.eventhub.features.event.full.presentation.FullEventActivity;
import tech.blur.eventhub.features.event.list.presentation.EventsListActivity;
import tech.blur.eventhub.features.event.my.presenter.MyEventsActivity;
import tech.blur.eventhub.features.event.my.presenter.MyEventsPresenter;
import tech.blur.eventhub.features.event.my.presenter.MyEventsPresenterFactory;
import tech.blur.eventhub.features.event.my.presenter.MyEventsView;

import java.util.List;

public class AssignedActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, AssignedView
{

    private RecyclerView recyclerEvents;
    private ProgressBar progressBar;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterEvent adapterEvent;
    private SwipeRefreshLayout swipeRefreshLayout;

    private AssignedPresenter presenter;

    public static void start(Context context){
        final Intent intent = new Intent(context, AssignedActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(tech.blur.eventhub.R.layout.activity_all_events);
        Toolbar toolbar = findViewById(tech.blur.eventhub.R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(tech.blur.eventhub.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEventActivity.start(AssignedActivity.this);
            }
        });

        DrawerLayout drawer = findViewById(tech.blur.eventhub.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, tech.blur.eventhub.R.string.navigation_drawer_open, tech.blur.eventhub.R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(tech.blur.eventhub.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //------------------------------------------------------------------------------------------

        progressBar = findViewById(tech.blur.eventhub.R.id.allEvent_progressBar);
        swipeRefreshLayout = findViewById(tech.blur.eventhub.R.id.event_list_swiperefresh);
        recyclerEvents = findViewById(tech.blur.eventhub.R.id.allEvent_recycler_view);
        layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        adapterEvent = new AdapterEvent(this, new AdapterEvent.SelectEventListener() {
            @Override
            public void onEventSelected(Event event) {
                //presenter.onEventSelected(event);
                FullEventActivity.start(AssignedActivity.this, event.getId());
                Log.e("Event selected", event.getId());
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }

        });
        recyclerEvents.setAdapter(adapterEvent);
        recyclerEvents.setLayoutManager(layoutManager);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(tech.blur.eventhub.R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(tech.blur.eventhub.R.menu.main,menu);
        MenuItem item = menu.findItem(tech.blur.eventhub.R.id.search_event);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //presenter.startSearching();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // presenter.onSearchedTextChanged(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        switch (id){
            case tech.blur.eventhub.R.id.search_event : return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == tech.blur.eventhub.R.id.nav_allEvents) {
            // Handle the camera action
            EventsListActivity.start(this,true);
        } else if (id == tech.blur.eventhub.R.id.nav_myEvents) {
            MyEventsActivity.start(this);
        } else if (id == tech.blur.eventhub.R.id.nav_assignedEvents) {

        } else if (id == tech.blur.eventhub.R.id.nav_settings) {

        }

        DrawerLayout drawer = findViewById(tech.blur.eventhub.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerEvents.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerEvents.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showEventList(List<Event> list) {
        adapterEvent.setEvents(list);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected MvpPresenter<AssignedView> getPresenter() {
        presenter = AssignedPresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }
}
