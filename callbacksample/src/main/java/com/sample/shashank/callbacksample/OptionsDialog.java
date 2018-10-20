package com.sample.shashank.callbacksample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class OptionsDialog extends DialogFragment {


    private OnSelectListener onSelectListener;

    public void SetOnSelectListener(OnSelectListener onSelectListener){
        this.onSelectListener = onSelectListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_options, container, false);

        Button btnEdit = view.findViewById(R.id.btn_edit);
        Button btnDelete = view.findViewById(R.id.btn_delete);
        Button btnCancel = view.findViewById(R.id.btn_cancel);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onSelectListener != null){
                    onSelectListener.onEdit();
                }
                dismiss();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConfirmDeleteDialog confirmDeleteDialog = new ConfirmDeleteDialog();

                confirmDeleteDialog.show(getFragmentManager(), ConfirmDeleteDialog.class.getSimpleName());
                confirmDeleteDialog.setOnConfirmDeleteListener(new OnConfirmDeleteListener() {
                    @Override
                    public void onDelete() {
                        if(onSelectListener != null){
                            onSelectListener.onDelete();
                        }
                        dismiss();
                    }

                    @Override
                    public void onCancel() {
                        if(onSelectListener != null){
                            onSelectListener.onCancel();
                        }
                        dismiss();
                    }
                });
                if(onSelectListener != null){
                    onSelectListener.onDelete();
                }
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onSelectListener != null){
                    onSelectListener.onCancel();
                }
                dismiss();
            }

        });
        return view;
    }
}
