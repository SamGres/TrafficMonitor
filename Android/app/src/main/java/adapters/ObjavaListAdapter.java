package adapters;


import android.content.Context;
import android.icu.util.ValueIterator;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.*;
import android.content.*;
import android.view.*;
import com.example.lukak.samgre.Obvestilo;
import com.example.lukak.samgre.R;

import java.util.ArrayList;
import java.util.List;

public class ObjavaListAdapter extends ArrayAdapter<Obvestilo> {

    private Context context;
    private List<Obvestilo> obvestilos;

    public ObjavaListAdapter(Context context,List<Obvestilo> obvestilos){
        super(context, R.layout.obvestila_list_layout , obvestilos);
        this.context = context;
        this.obvestilos = obvestilos;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.obvestila_list_layout,parent,false);




        return  super.getView(position,convertView,parent);
    }
}
