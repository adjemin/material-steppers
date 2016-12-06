package com.adjemin.materialsteppers;

import android.os.Bundle;

/**
 * Created by angebagui on 04/12/2016.
 */

public interface Stepable {

     void onBack();

     void onNext();

     void onError();

     void onCompleted();

     void onCompleted(Bundle bundle);



}
