package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    Switch sw;
    ListView lv;
    ArrayList<String>  items;
    myadapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed=findViewById(R.id.edtext);
        sw=findViewById(R.id.switch1);
        lv=findViewById(R.id.lview);
        items=new ArrayList<>();
        ad=new myadapter(MainActivity.this);
        lv.setAdapter(ad);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0)
                {
                    AlertDialog.Builder dialog= new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Do you want to delete this?");
                    dialog.setMessage("The selected row is: "+i);
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            items.remove(i);
                            ad.notifyDataSetChanged();
                        }
                    });
                    dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    dialog.show();
                }
                return true;
            }
        });
    }

    public void additem(View view) {
        items.add(ed.getText().toString());
        ed.setText("");
        ed.requestFocus();
        ad.notifyDataSetChanged();
    }
    class myadapter extends BaseAdapter
    {
        Activity activity;
        myadapter(Activity activity)
        {
            this.activity=activity;
        }
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater=LayoutInflater.from(activity);
            view=layoutInflater.inflate(R.layout.itemlist,null);
            TextView tv=view.findViewById(R.id.item);
            if(sw.isChecked()) {
                tv.setBackgroundColor(Color.RED);
                tv.setTextColor(Color.WHITE);
            }

            tv.setText(items.get(i));
            return view;
        }
    }
}