package com.mciad.astralcompass;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class GraphFragmentBar extends Fragment {
    //might make more sense to have one addCard screen which draws different buttons depending on the hero

    private Button doneButton;
    //check boxes and such


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graph_fragment_bar, container, false);
        doneButton = view.findViewById(R.id.doneButton);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Set New Card Details", Toast.LENGTH_SHORT).show();
                ((GraphActivity) getActivity()).setViewFragment(0);

            }
        });
        return view;
    }
}
