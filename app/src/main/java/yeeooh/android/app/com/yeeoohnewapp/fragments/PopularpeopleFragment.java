package yeeooh.android.app.com.yeeoohnewapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yeeooh.android.app.com.yeeoohnewapp.R;

/**
 * Created by YEEOOH on 4/19/2017.
 */

public class PopularpeopleFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.people, container, false);
    }
}
