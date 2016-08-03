package com.example.owlslubic.project2;

/**
 * Created by owlslubic on 8/3/16.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
