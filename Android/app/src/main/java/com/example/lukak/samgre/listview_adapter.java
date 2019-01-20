package com.example.lukak.samgre;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class listview_adapter extends ArrayAdapter<Post> {

    ArrayList<Post> list;
    public listview_adapter(final Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
        list = posts;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
         Post Post = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_template, parent, false);
        }
        final ImageView SlikaImage = (ImageView) convertView.findViewById(R.id.slika);
        TextView Naziv = (TextView) convertView.findViewById(R.id.naziv);
        SlikaImage.setImageResource(R.drawable.nevarnost);

        switch (Post.cause) {
            case "Zastoj":
                SlikaImage.setImageResource(R.drawable.prometnizastoj);


                break;

            case "Radar":
                SlikaImage.setImageResource(R.drawable.merjenjehitrosti);


                break;

            case "Izredni dogodek":
                SlikaImage.setImageResource(R.drawable.nevarnost);


                break;

            case "Nesreča":
                SlikaImage.setImageResource(R.drawable.prometnanesreca);

                break;

            case "Prepoved za tovornjake":
                SlikaImage.setImageResource(R.drawable.prepovedtovorna);

                break;

            case "Zaprta cesta":
                SlikaImage.setImageResource(R.drawable.prepovedanpromet);
                break;

            case "Delo na cesti":
                SlikaImage.setImageResource(R.drawable.delonacesti);

                break;

            case "Sneg":
                SlikaImage.setImageResource(R.drawable.sneg);
                break;




        }

        Naziv.setText(Post.getDescription());

        return convertView;


    }
}
