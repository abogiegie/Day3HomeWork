package com.example.chapter3.homework;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listView = getView().findViewById(R.id.news_list_view);
        listView.setAdapter(new ListViewAdapter(this.getContext()));


    }

    public static class ListViewAdapter extends BaseAdapter {

        private Context mContext;

        public ListViewAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return 15;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                view = inflater.inflate(R.layout.news_list_item, null);
            } else {
                view = convertView;
            }
            ((TextView)((ViewGroup)view).getChildAt(1)).setText("user" + position);
            ((TextView)((ViewGroup)view).getChildAt(2)).setText("word" + position);
            return view;
        }
    }
}
