package com.dinosoftlabs.foodies.android.ActivitiesAndFragments.RestaurantMenuItems;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dinosoftlabs.foodies.android.Models.RestaurantChildModel;
import com.dinosoftlabs.foodies.android.R;

import java.util.ArrayList;

/**
 * Created by AQEEL on 3/20/2018.
 */

public class Menuitems_Adapter extends RecyclerView.Adapter<Menuitems_Adapter.CustomViewHolder > {
    public Context context;
    ArrayList<RestaurantChildModel> dataList = new ArrayList<>();

    private Menuitems_Adapter.OnItemClickListener listener;

    public static boolean FLAG_OUT_OF_ORDER;

   public interface OnItemClickListener {
        void onItemClick(RestaurantChildModel item);
    }


    public Menuitems_Adapter(Context context, ArrayList<RestaurantChildModel> arrayList, Menuitems_Adapter.OnItemClickListener listener) {
        this.context = context;
        this.dataList=arrayList;
        this.listener = listener;
        Log.d("resp",""+dataList.size());
    }

    @Override
    public Menuitems_Adapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_restaurant_child,null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        Menuitems_Adapter.CustomViewHolder viewHolder = new Menuitems_Adapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
       return dataList.size();
    }

    @Override
    public void onBindViewHolder(final Menuitems_Adapter.CustomViewHolder holder, final int i) {

        RestaurantChildModel childTerbaru = dataList.get(i);

        String get_order_status = childTerbaru.getOrder_detail();
        String get_symbol = childTerbaru.getCurrency_symbol();
        if (get_order_status.equalsIgnoreCase("1"))
        {
            holder.price_tv.setText("Out of order");
            holder.price_tv.setTextSize(11);
            FLAG_OUT_OF_ORDER = true;
        }
        else {
            holder.price_tv.setText(get_symbol+childTerbaru.getPrice());
            holder.price_tv.setTextSize(14);
            FLAG_OUT_OF_ORDER = false;
        }
        holder.title_name_child.setText(childTerbaru.getChild_title());
        String subtitle = childTerbaru.getChild_sub_title().replaceAll("&amp;", "&");
        holder.sub_title_name_child.setText(subtitle);

        holder.bind(childTerbaru,listener);

    }



    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView title_name_child,sub_title_name_child,price_tv,order_status_tv;


        public CustomViewHolder(View view) {
            super(view);
            title_name_child=(TextView)view.findViewById(R.id.title_name_child);
            sub_title_name_child = view.findViewById(R.id.sub_title_name_child);
            price_tv = view.findViewById(R.id.price_tv);
            order_status_tv = view.findViewById(R.id.order_status_tv);
        }

        public void bind(final RestaurantChildModel item, final Menuitems_Adapter.OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });


        }

    }







}