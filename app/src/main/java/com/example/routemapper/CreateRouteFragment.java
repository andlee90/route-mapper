package com.example.routemapper;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.*;
import static android.widget.TextView.*;

public class CreateRouteFragment extends Fragment implements OnClickListener, OnEditorActionListener
{
    private static final String ARG_ROUTE_ID = "ArgRouteId";

    private String mName;
    private String mDate;
    private String mColor;
    private String mLocation;
    private Integer mGrade;
    private String mSetter;

    private EditText mNameEdit;
    private EditText mDateEdit;
    private EditText mColorEdit;
    private EditText mLocationEdit;
    private EditText mGradeEdit;
    private EditText mSetterEdit;


    public static CreateRouteFragment newInstance(int id)
    {
        Bundle args = new Bundle();
        args.putInt(ARG_ROUTE_ID, id);
        CreateRouteFragment fragment = new CreateRouteFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_create_route, container, false);

        mNameEdit = (EditText)rootView.findViewById(R.id.edit_route_name);
        mNameEdit.setOnEditorActionListener(this);
        mDateEdit = (EditText)rootView.findViewById(R.id.edit_route_date);
        mDateEdit.setOnEditorActionListener(this);
        mColorEdit = (EditText)rootView.findViewById(R.id.edit_route_color);
        mColorEdit.setOnEditorActionListener(this);
        mLocationEdit = (EditText)rootView.findViewById(R.id.edit_route_location);
        mLocationEdit.setOnEditorActionListener(this);
        mGradeEdit = (EditText)rootView.findViewById(R.id.edit_route_grade);
        mGradeEdit.setOnEditorActionListener(this);
        mSetterEdit = (EditText)rootView.findViewById(R.id.edit_route_setter);
        mSetterEdit.setOnEditorActionListener(this);

        Button addRoute = (Button)rootView.findViewById(R.id.button_add_route);
        addRoute.setOnClickListener(this);
        Button cancelRoute = (Button)rootView.findViewById(R.id.button_cancel_route);
        cancelRoute.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        Toast toast;
        String toastContent;

        switch(v.getId())
        {
            case R.id.button_add_route:
                if (mName != null && mDate != null && mColor != null && mLocation != null && mGrade != null && mSetter != null)
                {
                    toastContent = "New route " + mName + " created";
                    toast = Toast.makeText(getActivity(), toastContent, Toast.LENGTH_LONG);
                    toast.show();

                    RouteDBHelper dbHelper = new RouteDBHelper(getActivity());
                    dbHelper.insertRoute(mName, mDate, mColor, mLocation, mGrade, mSetter);

                    getActivity().finish();
                }
                else
                {
                    toastContent = "Please fill out all fields or Cancel";
                    toast = Toast.makeText(getActivity(), toastContent, Toast.LENGTH_LONG);
                    toast.show();
                }
                break;

            case R.id.button_cancel_route:
                getActivity().finish();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
    {
        switch(v.getId())
        {
            case R.id.edit_route_name:
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    mName = mNameEdit.getText().toString();
                }
                break;

            case R.id.edit_route_date:
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    mDate = mDateEdit.getText().toString();
                }
                break;
            case R.id.edit_route_color:
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    mColor = mColorEdit.getText().toString();
                }
                break;
            case R.id.edit_route_location:
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    mLocation = mLocationEdit.getText().toString();
                }
                break;
            case R.id.edit_route_grade:
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    mGrade = Integer.parseInt(mGradeEdit.getText().toString());
                }
                break;
            case R.id.edit_route_setter:
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    mSetter = mSetterEdit.getText().toString();
                }
                break;
        }
        return false;
    }
}
