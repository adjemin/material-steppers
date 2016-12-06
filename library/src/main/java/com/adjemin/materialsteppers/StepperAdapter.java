package com.adjemin.materialsteppers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angebagui on 05/12/2016.
 */

public class StepperAdapter extends FragmentPagerAdapter{

    private List<Fragment> stepFragments;

    public StepperAdapter(FragmentManager fm) {
        super(fm);
        stepFragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return stepFragments.get(position);
    }

    @Override
    public int getCount() {
        return stepFragments.size();
    }

    public void addStepFragment(Fragment fragment){
        stepFragments.add(fragment);
        notifyDataSetChanged();
    }

    public void removeStepFragment(Fragment fragment){
        stepFragments.remove(fragment);
    }


}
