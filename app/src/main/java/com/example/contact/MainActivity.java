package com.example.contact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView contactListView;

    private Button ThemBtn, XoaBtn;

    private EditText hoVaTenEdit, sdtEdit;

    private List<Contact> contactList;

    private ContactAdapter adapter;

    private int staticInt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();

        contactList.add(new Contact(staticInt++,"Một", "123"));
        contactList.add(new Contact(staticInt++,"Hai", "234"));
        contactList.add(new Contact(staticInt++,"Ba", "345"));
        adapter.notifyDataSetChanged();

        ThemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoVaTen = hoVaTenEdit.getText().toString().trim();
                String sdt = sdtEdit.getText().toString().trim();

                if (hoVaTen.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập họ và tên", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (sdt.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!TextUtils.isDigitsOnly(sdt)) {
                    Toast.makeText(MainActivity.this, "Số điện thoại phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }

                contactList.add(new Contact(staticInt++, hoVaTen, sdt));
                adapter.notifyDataSetChanged();
            }
        });

        XoaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xác nhận");
                builder.setMessage("Dữ liệu bị xoá sẽ không thể khôi phục.");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<Contact> contactsToRemove = new ArrayList<>();
                        for (Contact c : contactList) {
                            if (c.isStatus()) {
                                contactsToRemove.add(c);
                            }
                        }
                        contactList.removeAll(contactsToRemove);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });

    }

    private void initComponent ()
    {
        contactListView = findViewById(R.id.list1);
        ThemBtn = findViewById(R.id.btthem);
        XoaBtn = findViewById(R.id.btxoa);
        hoVaTenEdit = findViewById(R.id.hoten);
        sdtEdit = findViewById(R.id.soDienThoai);
        contactList = new ArrayList<Contact>();
        adapter = new ContactAdapter(this,contactList);
        contactListView.setAdapter(adapter);
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "true", Toast.LENGTH_SHORT).show();
            }
        });
    }
}