package com.example.routemapper;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import static android.view.View.*;
import static android.widget.AdapterView.*;

public class RouteListFragment extends Fragment implements OnClickListener, OnItemClickListener
{
    public final static String KEY_EXTRA_ROUTE_ID = "KEY_EXTRA_ROUTE_ID";

    RouteDBHelper dbHelper;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_route_list, container, false);
        ListView lv = (ListView)rootView.findViewById(R.id.listview);
        Button b = (Button)rootView.findViewById(R.id.button_add);
        b.setOnClickListener(this);

        // Doing this on the UI thread is bad.
        // TODO: Implement a proper background loader

        dbHelper = new RouteDBHelper(getActivity());
        final Cursor cursor = dbHelper.getAllRoutes();

        // This is dumb
        // TODO: Implement a proper adapter

        String [] columns = new String[] {
                RouteDBHelper.ROUTE_COLUMN_NAME,
                RouteDBHelper.ROUTE_COLUMN_GRADE
        };

        int [] widgets = new int[] {
                R.id.routeName,
                R.id.routeGrade
        };

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.item_route_list,
                cursor, columns, widgets, 0);

        lv.setAdapter(cursorAdapter);
        lv.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch(getId())
        {
            case R.id.button_add:
                Intent intent = new Intent(getActivity(), CreateRouteActivity.class);
                intent.putExtra(KEY_EXTRA_ROUTE_ID, 0);
                startActivity(intent);
                break;

            // Other buttons to come.
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

    }
}
