package com.jkxy.contacts;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.Data;
import android.widget.TextView;
import android.widget.Toast;

import com.jkxy.contacts.view.AddContactDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AddContactDialogFragment.AddContactDialogListener {



    ArrayList<String> phoneNumber = new ArrayList<>();
    private ListView contactsView;
    private ArrayAdapter<String> adapter;
    private List<String> contactsList = new ArrayList<>();
    private List<Long> contactId = new ArrayList<>();
    private Button btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddContact = (Button) findViewById(R.id.btnAddContact);
        contactsView = (ListView) findViewById(R.id.contacts_view);
        readContacts();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsList);
        contactsView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        initDialog();

    }

    private void initDialog() {
        //添加联系人
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddContactDialog();
            }
        });

        //删除联系人
        contactsView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setNeutralButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteContact(contactId.get(position));
                        contactsList.remove(position);
                        contactId.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.show();
                return true;
            }
        });

        //点击拨号、发短信事件
        contactsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = createTelOrSmsDialog(position);
                dialog.show();

            }
        });
    }

    //选择拨号、发短信对话框
    public Dialog createTelOrSmsDialog(int position) {
        TextView tvChoosePhone, tvChooseSms;
        View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.choose_dialog, null);
        LayoutInflater inflater = getLayoutInflater();
        tvChoosePhone = (TextView) dialogView.findViewById(R.id.tvTel);
        tvChooseSms = (TextView) dialogView.findViewById(R.id.tvSms);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final Uri telUri = Uri.parse("tel:" + phoneNumber.get(position));
        final Uri smsUri = Uri.parse("sms:" + phoneNumber.get(position));

        builder.setView(dialogView)
                .setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //取消操作
                    }
                });

        //选择打电话功能
        tvChoosePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telIntent = new Intent(Intent.ACTION_VIEW, telUri);
                startActivity(telIntent);
            }
        });
        //选择发短信功能
        tvChooseSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, smsUri);
                startActivity(smsIntent);
            }
        });

        return builder.create();
    }



    //读取联系人，显示到活动的ListView
    private void readContacts() {
        Cursor cursor = null;
        contactId.clear();
        //查询并添加联系人到ListView
        try {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    , null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String displayName = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String id = cursor.getString(cursor.getColumnIndex(
                            Phone.NAME_RAW_CONTACT_ID));
                    phoneNumber.add(number);
                    contactsList.add(displayName + "\n" + number);
                    contactId.add(Long.valueOf(id));
                }
                cursor.close();
            }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    //添加联系人
    public void addContact(String name, String phoneNumber) {
        ContentValues values = new ContentValues();
        Uri rawContactUri = getContentResolver().insert(RawContacts.CONTENT_URI, values);
         //新建联系人的ID
        long rawContactId = ContentUris.parseId(rawContactUri);
        values.clear();
        //添加姓名
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE); //MIMETYPE
        values.put(StructuredName.GIVEN_NAME, name); //GIVEN_NAME="Data2"
        getContentResolver().insert(Data.CONTENT_URI, values);
        values.clear();
        //添加电话号码
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        values.put(Phone.NUMBER, phoneNumber);
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        getContentResolver().insert(Data.CONTENT_URI, values);
        values.clear();
        contactsList.add(0, name + "\n" + phoneNumber);
        contactId.add(0, rawContactId);
        Toast.makeText(this, "联系人添加成功", Toast.LENGTH_SHORT).show();
    }

    //删除联系人
    public void deleteContact(long rawContactId) {
        getContentResolver().delete(
                //删除的ID：content://com.android.contacts.raw_contacts/rawContactID
                ContentUris.withAppendedId(RawContacts.CONTENT_URI, rawContactId), null, null);
        adapter.notifyDataSetChanged();

    }

    public void showAddContactDialog() {
        // 创建对象并show出对话框
        DialogFragment dialog = new AddContactDialogFragment();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    //添加联系人的确定按钮
    @Override
    public void onDialogPositiveClick(String name, String phoneNumber) {
        if ("".equals(name) || "".equals(phoneNumber)) {
            Toast.makeText(MainActivity.this, "姓名或电话号码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            addContact(name, phoneNumber);
            adapter.notifyDataSetChanged();
        }
    }

    //添加联系人的取消按钮
    @Override
    public void onDialogNegativeClick() {
        //取消操作
    }
}
