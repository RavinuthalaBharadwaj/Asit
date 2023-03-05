package com.Audisankara.asit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Audisankara.asit.Adaptors.SearchAdaptor;
import com.Audisankara.asit.Models.SearchModel;
import com.Audisankara.asit.helper.RecylerViewInterface;
import com.Audisankara.asit.objects.SearchRecyclerDATA;

import java.util.ArrayList;


public class SearchFrag extends Fragment implements RecylerViewInterface {



    public SearchFrag() {
        // Required empty public constructor
    }

    public EditText etSearch;
    public static ArrayList<SearchModel> searchModelArray;
    private RecyclerView SearchRecyclerView;
    public static SearchAdaptor adaptor;
    private CardView cvAcademics,cvExamCell,cvScoring,cvReceipts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        etSearch = view.findViewById(R.id.etSearch);
        buildRecyclerView();
        SearchRecyclerView = view.findViewById(R.id.SearchRecyclerView);
        SearchRecyclerView.setHasFixedSize(true);
        SearchRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
         adaptor = new SearchAdaptor(searchModelArray,this);
        SearchRecyclerView.setAdapter(adaptor);


        cvAcademics = view.findViewById(R.id.cvAcademics);
        cvExamCell = view.findViewById(R.id.cvExamCell);
        cvScoring = view.findViewById(R.id.CvScoring);
        cvReceipts = view.findViewById(R.id.cvReceipt);


        cvAcademics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptor.filterList(SearchRecyclerDATA.INSTANCE.academicsRecycler());
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fliter(s.toString());
               // MainActivityForSecondYear.chipNavigationBar.setVisibility(View.VISIBLE);
            }
        });
        return  view;
    }

    private void fliter(String toString) {
        ArrayList<SearchModel> filteredlist = new ArrayList<SearchModel>();

        for(SearchModel item :searchModelArray ) {
            if(item.getName().toLowerCase().contains(toString.toLowerCase())) {
                filteredlist.add(item);
            }
        }

        if(filteredlist.isEmpty()) {
            Toast.makeText(requireContext(), "not found", Toast.LENGTH_SHORT).show();
        }else{
            adaptor.filterList(filteredlist);
        }
    }
    private void buildRecyclerView() {
        searchModelArray = new ArrayList<SearchModel>();

        searchModelArray.add(new SearchModel("Syllabus","Get the syllabus of Present Semester\nof Btech 3rd year .","Academics",R.color.white));
        searchModelArray.add(new SearchModel("Receipts","Ever stand in line for hours,\nto submit piece of paper?","Exam cell",R.color.CustomRed));
        searchModelArray.add(new SearchModel("Previous Papers","Get the Model papers\nof last semester .","Academics",R.color.CustomPurple));
        searchModelArray.add(new SearchModel("Materials","Skipped so many classes?\nno worry's we got you","Academics",R.color.CustomPurple));
        searchModelArray.add(new SearchModel("Attendance","Worrying about attendance?\nnah not anymore ✨.","Scoring",R.color.teal));
        searchModelArray.add(new SearchModel("Assignments","Writing assignments is now\neasier than before","Academics",R.color.CustomPurple));
        searchModelArray.add(new SearchModel("Results","One step all it takes\nTo see your HardWork","Scoring",R.color.teal));
    }

    @Override
    public void onResume() {
        super.onResume();
        etSearch.setText("");
    }

    @Override
    public void onItemClick(int position, Context context) {

        switch (position) {
            case 1:
                MainActivity.fm.beginTransaction().replace(R.id.container,new UploadReceipt()).addToBackStack(null).commit();
                break;
            case 2:
            case 0:
            case 3:
            case 5:
                startActivity(new Intent(requireContext(),BooksActivity.class));
                break;
            case 4:
                MainActivity.fm.beginTransaction().replace(R.id.container,new AttendaceFrag()).addToBackStack(null).commit();
                break;
            case 6:
                Uri uri = Uri.parse("http://202.53.75.138:2001/asit/student.php?%20=%20student%20results%20for%20ASIT");
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(uri);
                startActivity(i);
                break;
        }
    }
}