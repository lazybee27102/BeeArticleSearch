package com.coderschool.beeiscoding.beearticlesearch.FilterResult;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.coderschool.beeiscoding.beearticlesearch.R;


public class FilterResultFragment extends DialogFragment {
    private EditText editText_query;
    private Button button_chooseSection,button_ok,button_cancel;
    private Spinner spinner_time;
    private ArrayAdapter<String> adapter_time;
    private SwitchCompat switchCompat;
    private TextView textView_switchViewStatus;
    private FilterResult filterResult;
    private String[] arrTimes;



    private static final String ARG_QUERY = "ARG_QUERY";
    public FilterResultFragment() {
    }

    public interface getResponsefromFragment
    {
        void handleResult(FilterResult filterResult);
    }

    public static FilterResultFragment newInstance(String query)
    {
        FilterResultFragment fragment = new FilterResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString("ARG_QUERY", query);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter_result,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((FoundArticlesActivity)getActivity()).getResult().setSection("");
        registerWidget(view);
        handleEvent();

    }

    private void registerWidget(View view) {
        //FilterResult
        filterResult = new FilterResult();

        //query
        String query = getArguments().getString(ARG_QUERY);

        editText_query = (EditText)view.findViewById(R.id.editText_filter_query);
        editText_query.setText(query);
        //section
        button_chooseSection = (Button)view.findViewById(R.id.button_filter_chooseSection);
        //time
        spinner_time = (Spinner)view.findViewById(R.id.spinner_filter_time);
        arrTimes = getResources().getStringArray(R.array.timeArray);
        adapter_time = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,arrTimes);
        spinner_time.setAdapter(adapter_time);
        //switchCompat
        switchCompat = (SwitchCompat)view.findViewById(R.id.switchedView_filter_sort);
        textView_switchViewStatus = (TextView)view.findViewById(R.id.textView_filter_switchedView_status);

        //button
        button_ok = (Button)view.findViewById(R.id.button_filter_OK);
        button_cancel = (Button)view.findViewById(R.id.button_filter_cancel);
    }

    private void handleEvent() {
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText_query.getText().toString().trim().length()!=0)
                {
                    filterResult.setQuery(editText_query.getText().toString().trim());
                    filterResult.setTime(spinner_time.getSelectedItem().toString());
                    filterResult.setSort(textView_switchViewStatus.getText().toString());


                    getResponsefromFragment delegate = (getResponsefromFragment)getActivity();
                    delegate.handleResult(filterResult);
                    dismiss();
                }else
                {
                    Toast.makeText(getContext(), "Please insert querry", Toast.LENGTH_SHORT).show();
                }


                
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        button_chooseSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionChooseFragment fragment = SectionChooseFragment.newInstance();
                fragment.show(getActivity().getSupportFragmentManager(),"SECTION");
            }
        });


        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    textView_switchViewStatus.setText("Newest");
                else
                    textView_switchViewStatus.setText("Oldest");
            }
        });

    }
}
