package com.mobiledevelopment.werewolf;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
// import com.mobiledevelopment.im_werewolf.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_NewParty_Players extends Fragment
{
    public Fragment_NewParty_Players()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment__new_party__players, container, false);
    }
}
