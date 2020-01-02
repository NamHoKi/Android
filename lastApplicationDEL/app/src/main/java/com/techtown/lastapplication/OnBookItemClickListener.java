package com.techtown.lastapplication;

import android.view.View;

public interface OnBookItemClickListener {
    public void onItemClick(BookAdapter.ViewHolder holder, View view, int position);
}
