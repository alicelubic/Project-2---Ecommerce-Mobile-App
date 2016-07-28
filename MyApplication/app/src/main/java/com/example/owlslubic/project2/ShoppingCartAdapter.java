package com.example.owlslubic.project2;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by owlslubic on 7/27/16.
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    CursorAdapter mCursorAdapter;

    Context mContext;

    public ShoppingCartAdapter(Context context, Cursor cursor) {

        mContext = context;
        mCursorAdapter = new CursorAdapter(mContext, cursor, 0) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_shopping_cart, parent, false);
//                RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(view);

                return view;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                cursor = DatabaseHelper.getInstance(context).getPlantInfoFromShoppingCartTable();

            //not sure why we cant access the member variables in here


            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;


        CardView mCardView;
        TextView mPrice, mName, mQuantity;
        ImageView mPlantImage;
        ImageButton mDecrement, mIncrement, mRemove;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cardview_cart);
            mPrice = (TextView) itemView.findViewById(R.id.textview_price_card_cart);
            mName = (TextView) itemView.findViewById(R.id.textview_name_card_cart);
            mQuantity = (TextView) itemView.findViewById(R.id.textview_quantity_cart);
            mPlantImage = (ImageView) itemView.findViewById(R.id.imageview_card_cart);
            mDecrement = (ImageButton) itemView.findViewById(R.id.imagebutton_decrement);
            mIncrement = (ImageButton) itemView.findViewById(R.id.imagebutton_increment);
            mRemove = (ImageButton) itemView.findViewById(R.id.imagebutton_remove_item);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mCursorAdapter.newView(mContext, mCursorAdapter.getCursor(), parent);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mCursorAdapter.getCursor().moveToPosition(position);

        holder.mPrice.setText("Free");
        mCursorAdapter.bindView(holder.itemView, mContext, mCursorAdapter.getCursor());
    }

    @Override
    public int getItemCount() {
        return mCursorAdapter.getCount();
    }


}



