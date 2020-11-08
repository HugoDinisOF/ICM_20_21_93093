package pt.ua.icm.hw02;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String[] mParam1;
    private View.OnClickListener mParam2;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(Set<String> param1, Serializable param2) {
        ListFragment fragment = new ListFragment();
        String[] argparam1=new String[param1.size()];
        param1.toArray(argparam1);
        Bundle args = new Bundle();
        args.putStringArray(ARG_PARAM1, argparam1);
        args.putSerializable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getStringArray(ARG_PARAM1);
            mParam2 = (View.OnClickListener) getArguments().getSerializable(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CityListAdapter ct = new CityListAdapter(this.getContext(),new LinkedList<String>(new HashSet<String>(Arrays.asList(mParam1))),mParam2);
        final View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView rc =rootView.findViewById(R.id.recyclerview);
        rc.setAdapter(ct);
        rc.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return rootView;
    }
}