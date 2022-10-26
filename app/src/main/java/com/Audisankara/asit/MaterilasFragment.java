package com.Audisankara.asit;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.Audisankara.asit.Adaptors.MaterialAdaptor;
import com.Audisankara.asit.Adaptors.SearchAdaptor;
import com.Audisankara.asit.Models.MaterialModel;
import com.Audisankara.asit.Models.SearchModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MaterilasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaterilasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MaterilasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MaterilasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MaterilasFragment newInstance(String param1, String param2) {
        MaterilasFragment fragment = new MaterilasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ArrayList<MaterialModel> MaterialModelArray;
    private RecyclerView MaterialModelReceylerView;
    private MaterialAdaptor adaptor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_materilas, container, false);
        buildRecyclerView();
        MaterialModelReceylerView = view.findViewById(R.id.MaterialRecycler);
        MaterialModelReceylerView.setHasFixedSize(true);
        MaterialModelReceylerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adaptor = new MaterialAdaptor(MaterialModelArray);
        MaterialModelReceylerView.setAdapter(adaptor);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }
    return view;
    }

    private void buildRecyclerView() {
        MaterialModelArray = new ArrayList<MaterialModel>();

        MaterialModelArray.add(new MaterialModel("Unit-1","Get the Materials of unit-1",R.drawable.mesh7));
        MaterialModelArray.add(new MaterialModel("Unit-2","Get the Materials of unit-1",R.drawable.mesh5));
        MaterialModelArray.add(new MaterialModel("Unit-3","Get the Materials of unit-1",R.drawable.mesh7));
        MaterialModelArray.add(new MaterialModel("Unit-4","Get the Materials of unit-1",R.drawable.mesh5));
        MaterialModelArray.add(new MaterialModel("Unit-5","Get the Materials of unit-1",R.drawable.mesh7));
    }
}