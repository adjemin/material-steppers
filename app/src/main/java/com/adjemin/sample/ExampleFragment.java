package com.adjemin.sample;

import com.adjemin.materialsteppers.StepFragment;

/**
 * Created by angebagui on 05/12/2016.
 */

public class ExampleFragment extends StepFragment {

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean isNext() {
        return true;
    }

    @Override
    public boolean isOptional() {
        return false;
    }

    @Override
    public void onNext() {

    }

    @Override
    public void onBack() {

    }

    @Override
    public String optional() {
        return null;
    }

    @Override
    public String error() {
        return null;
    }
}
