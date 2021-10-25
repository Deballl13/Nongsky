package com.nongskuy.nongskuy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nongskuy.nongskuy.adapter.PromoAdapter;
import com.nongskuy.nongskuy.model.Populer;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopulerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopulerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rvBeranda, rvPopuler;

    public PopulerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PopulerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PopulerFragment newInstance(String param1, String param2) {
        PopulerFragment fragment = new PopulerFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_populer, container, false);

        rvBeranda = view.findViewById(R.id.recyclerViewBerandaPopuler);
        rvBeranda.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvBeranda.setAdapter(new PromoAdapter(getData()));

        return view;
    }

    public ArrayList<Populer> getData(){
        ArrayList<Populer> listPopuler = new ArrayList<>();
        listPopuler.add(new Populer(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Populer(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Populer(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Populer(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Populer(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Populer(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Populer(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Populer(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Populer(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));
        listPopuler.add(new Populer(
                4.2,
                "McDonald’s Padang",
                "Jl. Khatib Sulaeman",
                "Cepat saji",
                4.5,
                "km"
        ));

        return listPopuler;
    }
}