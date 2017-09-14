package com.example.renancardoso.aspectscontrol.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.renancardoso.aspectscontrol.Models.Aspects;
import com.example.renancardoso.aspectscontrol.R;

import io.realm.RealmResults;

/**
 * Created by RenanCardoso on 14/09/2017.
 */

public class AspectsAdapter extends BaseAdapter {

    private Context c;
    private RealmResults<Aspects> aspects;

    public AspectsAdapter(Context c, RealmResults<Aspects> aspects) {
        this.c = c;
        this.aspects = aspects;
    }

    @Override
    public int getCount() {
        return aspects.size();
    }

    @Override
    public Object getItem(int i) {
        return aspects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View result = view;

        if (result == null) {
            LayoutInflater inflater = LayoutInflater.from(c);
            result = inflater.inflate(R.layout.aspect_item, null);
        }

        TextView name = result.findViewById(R.id.tv_aspect_item_name);
        TextView average = result.findViewById(R.id.tv_aspect_item_average);

        name.setText(aspects.get(i).getName());
        average.setText("" + aspects.get(i).meanGrades());

        return result;
    }
}
