package com.example.petcare;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class VacunaAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<VacunaItem> items;

    public VacunaAdapter(Activity activity, ArrayList<VacunaItem> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() { return items.size(); }

    @Override
    public VacunaItem getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) { return items.get(position).getIdVacuna(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VacunaItem item = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_vac, null);
        }
        TextView txtNombre = (TextView) convertView.findViewById(R.id.TxtItemNombreVac);
        Switch swtActiva = convertView.findViewById(R.id.SwtItemActivaVac);
        TextView txtFecha = (TextView) convertView.findViewById(R.id.TxtItemFechaVac);

        txtNombre.setText(item.getNombre());
        swtActiva.setChecked(item.getActiva());
        txtFecha.setText(item.getFecha());

        return convertView;
    }
}
