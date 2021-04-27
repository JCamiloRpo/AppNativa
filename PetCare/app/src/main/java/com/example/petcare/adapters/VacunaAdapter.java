package com.example.petcare.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.petcare.R;
import com.example.petcare.activities.VacunaEditActivity;
import com.example.petcare.entities.VacunaItem;

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
    public long getItemId(int position) { return items.get(position).getVacunaID(); }

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
        swtActiva.setChecked(item.getActiva()==1);
        txtFecha.setText(item.getFecha());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VacunaEditActivity.idVacuna = item.getVacunaID();

                Intent i = new Intent(activity, VacunaEditActivity.class);
                activity.startActivity(i);
            }
        });

        return convertView;
    }
}
