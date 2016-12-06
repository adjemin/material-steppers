package com.adjemin.materialsteppers;


import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by angebagui on 05/12/2016.
 */

public class TextStepperActivity extends MaterialStepperActivity {


    private TextView stepperLevelView;

    @Override
    public int layout() {
        return R.layout.mobile_step_text_layout;
    }

    @Override
    public void init() {
        stepperLevelView = (TextView)findViewById(R.id.stepLevelView);

    }

    @Override
    public void onCompleted(Bundle bundle) {

    }

    @Override
    public void onBack() {
        super.onBack();
        final int step = getCurrentPosition()+1;
        stepperLevelView.setText(getString(R.string.text_stepper_level, ""+step,""+getStepFragments().size()));
    }

    @Override
    public void onNext() {
        super.onNext();
        final int step = getCurrentPosition()+1;
        stepperLevelView.setText(getString(R.string.text_stepper_level, ""+step,""+getStepFragments().size()));

    }

    @Override
    protected void onResume() {
        super.onResume();
        final int step = getCurrentPosition()+1;
        stepperLevelView.setText(getString(R.string.text_stepper_level, ""+step,""+getStepFragments().size()));
    }
}
