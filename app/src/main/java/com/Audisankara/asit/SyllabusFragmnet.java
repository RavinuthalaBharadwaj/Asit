package com.Audisankara.asit;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SyllabusFragmnet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SyllabusFragmnet extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SyllabusFragmnet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SyllabusFragmnet.
     */
    // TODO: Rename and change types and number of parameters
    public static SyllabusFragmnet newInstance(String param1, String param2) {
        SyllabusFragmnet fragment = new SyllabusFragmnet();
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

    private CardView cvSyllabus;
    private FrameLayout frameLayout;
    private LinearLayout presentLyt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_syllabus_fragmnet, container, false);
        cvSyllabus = view.findViewById(R.id.cvSyllabus);
        frameLayout = view.findViewById(R.id.fmlyt);
        presentLyt = view.findViewById(R.id.PresentLyt);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = requireActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.Homefragback));
        }
        cvSyllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(requireActivity(),SyllabusViewerActivity.class);
                assert getArguments() != null;
                switch (getArguments().getString("syllabuslink")) {
                    case "gs://audisankara-institute.appspot.com/Syllabus/CyberSecurity.png" :
                        i.putExtra("SyllabusLink","https://firebasestorage.googleapis.com/v0/b/audisankara-institute.appspot.com/o/Syllabus%2FCyberSecurity.png?alt=media&token=129069f9-f3e9-476d-b166-6828c94da1a9");
                        startActivity(i);
                        break;
                    case "FlatLink":
                        i.putExtra("SyllabusLink","https://firebasestorage.googleapis.com/v0/b/audisankara-institute.appspot.com/o/Syllabus%2FFLAT.png?alt=media&token=6458bd43-9820-42da-9b97-27b9590eb45f");
                        startActivity(i);
                        break;
                    case "ControlSystemsLink" :
                        Toast.makeText(requireContext(), "Control System's one", Toast.LENGTH_SHORT).show();
                        break;
                    case "DataMiningLink" :
                        i.putExtra("SyllabusLink","https://firebasestorage.googleapis.com/v0/b/audisankara-institute.appspot.com/o/Syllabus%2FDataMining.png?alt=media&token=2de5ce8e-3304-40d3-adc9-016ff5fa906d");
                        startActivity(i);
                        break;
                    case "ComputerNetworksLink" :
                        i.putExtra("SyllabusLink","https://firebasestorage.googleapis.com/v0/b/audisankara-institute.appspot.com/o/Syllabus%2FComputerNetworks.png?alt=media&token=8365b636-1715-40b6-b5c6-e9b0e70c2133");
                        startActivity(i);
                        break;
                    case "UhvLink" :
                        i.putExtra("SyllabusLink","https://firebasestorage.googleapis.com/v0/b/audisankara-institute.appspot.com/o/Syllabus%2FUHV.png?alt=media&token=e1bc3afc-d914-48d0-ad38-49956c0df562");
                        startActivity(i);
                        break;
                    case "DBMSLINK" :
                        i.putExtra("SyllabusLink","https://firebasestorage.googleapis.com/v0/b/asit-a51d7.appspot.com/o/SemesterOne%2FSyllabus%2FDBMS.png?alt=media&token=6a464dcf-3e2a-42e1-9e08-78dcf80e9caf");
                        startActivity(i);
                        break;
                    case "SELINK" :
                        i.putExtra("SyllabusLink","https://firebasestorage.googleapis.com/v0/b/asit-a51d7.appspot.com/o/SemesterOne%2FSyllabus%2FSE.png?alt=media&token=8dbbb747-d1f5-4a89-97a2-6ee8e74a9241");
                        startActivity(i);
                        break;
                    case "JAVALINK" :
                        i.putExtra("SyllabusLink","https://firebasestorage.googleapis.com/v0/b/asit-a51d7.appspot.com/o/SemesterOne%2FSyllabus%2FJAVA.png?alt=media&token=4dab450b-dea9-41ce-9081-46abe0aca183");
                        startActivity(i);
                        break;
                    case "COALINK" :
                        i.putExtra("SyllabusLink","https://firebasestorage.googleapis.com/v0/b/asit-a51d7.appspot.com/o/SemesterOne%2FSyllabus%2FCOA.png?alt=media&token=59b2c0d6-0d3f-466f-9059-e1ab3064484c");
                        startActivity(i);
                        break;
                    case "PBNMLINK" :
                        i.putExtra("SyllabusLink","https://firebasestorage.googleapis.com/v0/b/asit-a51d7.appspot.com/o/SemesterOne%2FSyllabus%2FProbabilty.png?alt=media&token=1e3a6b36-05c6-4e94-85b5-51a7d1981dea");
                        startActivity(i);
                        break;
                }
            }
        });
        return  view;
    }
}