package com.example.tema5_baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

        private Context context;
        private List<Datos> items;
        private int selectedPosition = -1; // Para seleccionar un único botón de radio

        public CustomAdapter(Context context, List<Datos> items) {
            this.context = context;
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item1, parent, false);
            }

            ImageView imageView = convertView.findViewById(R.id.itemImage);
            TextView titleTextView = convertView.findViewById(R.id.itemTitle);
            TextView contentTextView = convertView.findViewById(R.id.itemContent);
            RadioButton radioButton = convertView.findViewById(R.id.itemRadioButton);

            Datos item = items.get(position);

            imageView.setImageResource(item.getImageResId());
            titleTextView.setText(item.getTitle());
            contentTextView.setText(item.getContent());

            // Gestionar el estado del botón de radio
            radioButton.setChecked(position == selectedPosition);
            radioButton.setOnClickListener(v -> {
                selectedPosition = position;
                notifyDataSetChanged();
            });

            return convertView;
        }
    }

