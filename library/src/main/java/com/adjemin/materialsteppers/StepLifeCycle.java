package com.adjemin.materialsteppers;

/**
 * Created by angebagui on 05/12/2016.
 */

public interface StepLifeCycle {

    boolean isNext();

    boolean isOptional();

    void onNext();

    void onBack();

    String optional();

    String error();

}
