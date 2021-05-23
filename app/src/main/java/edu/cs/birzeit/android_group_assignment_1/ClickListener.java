package edu.cs.birzeit.android_group_assignment_1;

import android.view.View;

public interface ClickListener<T> {

        void onClick(View view, T data, int position);
    }
