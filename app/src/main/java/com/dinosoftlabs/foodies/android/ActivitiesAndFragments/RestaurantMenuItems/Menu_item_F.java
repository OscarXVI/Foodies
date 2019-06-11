package com.dinosoftlabs.foodies.android.ActivitiesAndFragments.RestaurantMenuItems;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dinosoftlabs.foodies.android.ActivitiesAndFragments.AddToCartActivity;
import com.dinosoftlabs.foodies.android.Models.RestaurantChildModel;
import com.dinosoftlabs.foodies.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Menu_item_F extends Fragment {


    ArrayList<RestaurantChildModel> listChildData = new ArrayList<>();;

    View view;
    Context context;
    RecyclerView recyclerView;

    Menuitems_Adapter adapter;
    public static final int  PERMISSION_DATA_CART_ADED = 5;

    public Menu_item_F() {

    }

    @SuppressLint("ValidFragment")
    public Menu_item_F(ArrayList<RestaurantChildModel> listChild) {
        listChildData=listChild;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_menu_items, container, false);
        context=getContext();


        recyclerView = (RecyclerView) view.findViewById(R.id.recylerview);
        LinearLayoutManager layout = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layout);
        recyclerView.setHasFixedSize(false);

        adapter=new Menuitems_Adapter(context, listChildData, new Menuitems_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(RestaurantChildModel item) {


                //  Toast.makeText(getContext(), item.getChild_title(), Toast.LENGTH_SHORT).show();

                if (item.getOrder_detail().equalsIgnoreCase("1")) {

                } else {
                    Intent intent = new Intent(getActivity(), AddToCartActivity.class);
                    intent.putExtra("name", item.getChild_title());
                    intent.putExtra("desc", item.getChild_sub_title());
                    intent.putExtra("price", item.getPrice());
                    intent.putExtra("extra_id", item.getRestaurant_menu_item_id());
                    //  intent.putExtra("key",userId);
                    intent.putExtra("symbol", item.getCurrency_symbol());
                    //  getContext().startActivity(intent);

                    getActivity().startActivityForResult(intent, PERMISSION_DATA_CART_ADED);

                }




            }
        });

        recyclerView.setAdapter(adapter);


        return view;
    }

}
