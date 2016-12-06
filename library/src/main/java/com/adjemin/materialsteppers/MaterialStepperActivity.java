package com.adjemin.materialsteppers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.adjemin.materialsteppers.view.NonSwipeableViewPager;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.view.ViewPager.SCROLL_STATE_SETTLING;

/**
 * Created by angebagui on 04/12/2016.
 */

public  abstract class MaterialStepperActivity extends AppCompatActivity implements Stepable, StepDataListener, ViewPager.OnPageChangeListener {

    public abstract int layout();

    public abstract void init();

    private List<StepFragment> stepFragments = new ArrayList<>();

    private Bundle mBundle = new Bundle();

    private NonSwipeableViewPager mStepViewPager;

    private StepperAdapter mStepperAdapter;


    private TextView stepBackView;
    private TextView stepNextView;
    private int mCurrentPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());

        init();
        mStepViewPager = (NonSwipeableViewPager) findViewById(R.id.stepViewPager);
        mStepperAdapter = new StepperAdapter(getSupportFragmentManager());
        mStepViewPager.setAdapter(mStepperAdapter);

        mStepViewPager.addOnPageChangeListener(this);

        stepBackView = (TextView)findViewById(R.id.stepBack);
        stepNextView = (TextView)findViewById(R.id.stepNext);

        stepBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentPosition <= 0)
                    return;
                StepFragment stepFragment = stepFragments.get(mCurrentPosition);
                stepFragment.onBack();
                onBack();

            }
        });

        stepNextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StepFragment stepFragment = stepFragments.get(mCurrentPosition);
                stepFragment.onNext();
                onNext();
            }
        });

    }


    public void addStepFragment(StepFragment stepFragment) {
        stepFragment.setDataListener(this);
        stepFragments.add(stepFragment);
        mStepperAdapter.addStepFragment(stepFragment);

    }


    @Override
    public void onBack() {

        if (stepFragments.size() <= 0)
            return;

        final int currentStepPosition = mStepViewPager.getCurrentItem();

        mCurrentPosition =  currentStepPosition - 1;

        updateUi();



    }

    @Override
    public void onNext() {
        if (stepFragments.size() <= 0)
            return;

        final int currentStepPosition = mStepViewPager.getCurrentItem();
        StepFragment stepFragment = stepFragments.get(currentStepPosition);
        if (stepFragment == null)
            return;

        if(!stepFragment.isNext()){
            onError();
            return;
        }


        final int completedPosition = stepFragments.size()-1;
        if (currentStepPosition == completedPosition){
            onCompleted();
            onCompleted(mBundle);
            return;
        }

        mCurrentPosition = currentStepPosition  + 1;

        updateUi();
    }

    private void updateUi(){

        mStepViewPager.setCurrentItem(mCurrentPosition);
    }

    @Override
    public void onError() {
        int stepPosition = mStepViewPager.getCurrentItem();
        StepFragment stepFragment = stepFragments.get(stepPosition);
        Toast.makeText(this, "Error "+stepFragment.error(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompleted() {
        Toast.makeText(this, "Completed()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddExtra(Bundle value) {
        mBundle.putAll(value);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {

        final int completedPosition = stepFragments.size()-1;
        if (position == completedPosition){

            stepNextView.setText(getText(R.string.completed_button_title));
        }else {

            stepNextView.setText(getText(R.string.next_button_title));

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

        if (state == SCROLL_STATE_SETTLING){
            onCompleted();
        }

    }

    public List<StepFragment> getStepFragments() {
        return stepFragments;
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
