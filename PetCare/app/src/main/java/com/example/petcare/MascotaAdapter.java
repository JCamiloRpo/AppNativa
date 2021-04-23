package com.example.petcare;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MascotaAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<MascotaView> items;

    public MascotaAdapter(Activity activity, ArrayList<MascotaView> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() { return items.size(); }

    @Override
    public MascotaView getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) { return items.get(position).getIdMascota(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MascotaView item = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_pet, null);
        }
        ImageView imgPet = (ImageView) convertView.findViewById(R.id.ImgItemPet);
        TextView txtNombre = (TextView) convertView.findViewById(R.id.TxtItemNombrePet);
        TextView txtEdad = (TextView) convertView.findViewById(R.id.TxtItemEdadPet);
        TextView txtVacuna = (TextView) convertView.findViewById(R.id.TxtItemVacunaPet);

        imgPet.setImageResource(item.getImagen());
        txtNombre.setText(item.getNombre());
        txtEdad.setText(item.getEdad());
        txtVacuna.setText(item.getVacuna());

        return convertView;
    }
}
