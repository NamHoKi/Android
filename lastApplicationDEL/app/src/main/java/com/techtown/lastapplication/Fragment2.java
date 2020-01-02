package com.techtown.lastapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *
 */
public class Fragment2 extends Fragment {
    RecyclerView recyclerView;
    BookAdapter adapter;

    OnDatabaseCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (OnDatabaseCallback) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);

        ArrayList<BookInfo> result = callback.selectAll();
        adapter.setItems(result);

        adapter.setOnItemClickListener(new OnBookItemClickListener() {
            @Override
            public void onItemClick(BookAdapter.ViewHolder holder, View view, int position) {
                BookInfo item = adapter.getItem(position);

             //  Toast.makeText(getContext(), "아이템 선택됨 : " + item.getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity().getApplicationContext(),PostActivity.class);

                intent.putExtra("title",item.getName());
                intent.putExtra("nickname",item.getAuthor());
                intent.putExtra("content",item.getContents());
                intent.putExtra("lat",item.getLat());
                intent.putExtra("lon",item.getLon());
                intent.putExtra("photo", item.getphotoimage());
                intent.putExtra("position",position);

                startActivity(intent);
            }
        });

        ImageButton button = (ImageButton)rootView.findViewById(R.id.Rebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BookInfo> result = callback.selectAll();
                adapter.setItems(result);
                adapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }

}
