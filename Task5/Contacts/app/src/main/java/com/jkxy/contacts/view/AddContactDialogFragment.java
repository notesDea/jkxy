package com.jkxy.contacts.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jkxy.contacts.MainActivity;
import com.jkxy.contacts.R;

/**
 * Created by notes on 2016/7/28.
 */
public class AddContactDialogFragment extends DialogFragment {


    //实现接口，接收回调
    public interface AddContactDialogListener {
        void onDialogPositiveClick(String name, String phoneNumber);

        void onDialogNegativeClick();
    }

    //传递事件的接口的实例
    AddContactDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (AddContactDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //构建Dialog，并设置按钮处理
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.add_contact_dialog, null);
        final EditText etName = (EditText) dialogView.findViewById(R.id.etName);
        final EditText etPhone = (EditText) dialogView.findViewById(R.id.etPhone);

        builder.setView(dialogView)
                //确认添加按钮
                .setPositiveButton(R.string.submit, null)
                //取消按钮
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onDialogNegativeClick();
                    }
                });
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phoneNum = etPhone.getText().toString();
                if ("".equals(name) || "".equals(phoneNum)) {
                    Toast.makeText(getContext(), "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    mListener.onDialogPositiveClick(name, phoneNum);
                    dialog.dismiss();
                }
            }
        });
        return dialog;
    }
}
