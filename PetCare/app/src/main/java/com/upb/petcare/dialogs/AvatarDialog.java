package com.upb.petcare.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.upb.petcare.R;
import com.upb.petcare.activities.MascotaAddActivity;
import com.upb.petcare.activities.MascotaEditActivity;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AvatarDialog extends DialogFragment implements View.OnClickListener {
    Activity actividad;
    ImageButton btnCerrar;
    ImageView imgPet;
    
    public AvatarDialog(){ }

    @Nullable
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        return crearDialogo();
    }

    private AlertDialog crearDialogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_avatars,null);
        builder.setView(v);

        btnCerrar = v.findViewById(R.id.BtnVtnCerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        v.findViewById(R.id.ImgOpcion1).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion2).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion3).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion4).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion5).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion6).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion7).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion8).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion9).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion10).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion11).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion12).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion13).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion14).setOnClickListener(this);
        v.findViewById(R.id.ImgOpcion15).setOnClickListener(this);
        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof Activity)
            this.actividad = (Activity) context;
        else
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
    }

    @Override
    public void onClick(View view) {
        if(MascotaAddActivity.add){
            MascotaAddActivity.img = view.getContentDescription().toString().toLowerCase();
            imgPet = actividad.findViewById(R.id.ImgPet);
            imgPet.setImageURI(Uri.parse("android.resource://com.upb.petcare/drawable/"+ MascotaAddActivity.img));
        }
        else {
            MascotaEditActivity.img = view.getContentDescription().toString().toLowerCase();
            imgPet = actividad.findViewById(R.id.ImgPetEdit);
            imgPet.setImageURI(Uri.parse("android.resource://com.upb.petcare/drawable/"+ MascotaEditActivity.img));
        }
        dismiss();
    }
}
