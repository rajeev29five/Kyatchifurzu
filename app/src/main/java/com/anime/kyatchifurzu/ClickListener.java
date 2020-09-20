package com.anime.kyatchifurzu;

import android.view.View;

public interface ClickListener {

    void onPositionClickListener(View view, int position);

    void onLongClickListener(int position);
}
