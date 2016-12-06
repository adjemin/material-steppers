package com.adjemin.materialsteppers;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by angebagui on 04/12/2016.
 */

public abstract class StepFragment extends Fragment implements StepLifeCycle{

    private StepDataListener dataListener;


    public abstract String name();

    public void addExtra(Bundle data){
        dataListener.onAddExtra(data);
    }

    public StepDataListener getDataListener() {
        return dataListener;
    }

    public void setDataListener(StepDataListener dataListener) {
        this.dataListener = dataListener;
    }
}
