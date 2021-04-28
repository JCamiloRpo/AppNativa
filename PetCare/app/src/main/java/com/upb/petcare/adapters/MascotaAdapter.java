package com.upb.petcare.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.upb.petcare.activities.VacunaActivity;
import com.upb.petcare.entities.MascotaItem;
import com.upb.petcare.R;

import java.util.ArrayList;

public class MascotaAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<MascotaItem> items;

    public MascotaAdapter(Activity activity, ArrayList<MascotaItem> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() { return items.size(); }

    @Override
    public MascotaItem getItem(int position) { return items.get(position); }

    @Override
    public long getItemId(int position) { return items.get(position).getMascotaID(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MascotaItem item = items.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_pet, null);
        }
        ImageView imgPet = convertView.findViewById(R.id.ImgItemPet);
        TextView txtNombre = convertView.findViewById(R.id.TxtItemNombrePet);
        TextView txtEdad = convertView.findViewById(R.id.TxtItemEdadPet);
        TextView txtVacuna = convertView.findViewById(R.id.TxtItemVacunaPet);

        imgPet.setImageURI(Uri.parse("android.resource://com.upb.petcare/drawable/"+item.getImagen()));
        txtNombre.setText(item.getNombre());
        txtEdad.setText(item.getEdad()+" años");
        if(item.getVacuna().equals("Sin vacunas")) txtVacuna.setText(item.getVacuna());
        else txtVacuna.setText("Ultima vacuna: " + item.getVacuna());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VacunaActivity.idMascota = item.getMascotaID();
                VacunaActivity.mascota = item.getNombre();

                Intent i = new Intent(activity, VacunaActivity.class);
                activity.startActivity(i);
            }
        });
        return convertView;
    }
}
