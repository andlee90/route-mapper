package com.example.routemapper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class RouteListFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_route_list, container, false);
        ListView lv = (ListView)rootView.findViewById(R.id.listview);
        Button b = (Button)rootView.findViewById(R.id.button_add);

        return rootView;
    }
}
