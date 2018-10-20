package com.sample.shashank.callbacksample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ConfirmDeleteDialog extends DialogFragment {

    private OnConfirmDeleteListener onConfirmDeleteListener;

    public void setOnConfirmDeleteListener(OnConfirmDeleteListener onConfirmDeleteListener){
        this.onConfirmDeleteListener = onConfirmDeleteListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm_delete, container, false);

        Button btnYes = view.findViewById(R.id.btn_yes);
        Button btnNo = view.findViewById(R.id.btn_no);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onConfirmDeleteListener != null){
                        onConfirmDeleteListener.onDelete();
                }
                dismiss();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(onConfirmDeleteListener != null){
                    onConfirmDeleteListener.onCancel();
                }
                dismiss();

            }
        });
        return view;
    }
}
