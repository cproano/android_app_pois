package net.infobosccoma.titulars.views.impl.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import net.infobosccoma.titulars.models.business.entities.Titular;


public class TitularsAdapter extends ArrayAdapter<Titular> {
    private Context context;
    private LayoutInflater inflater;

    public TitularsAdapter(Activity context,  List<Titular> titulars) {
        super(context, 0, titulars);
        this.context = context;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

   @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TitularHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);

            holder = new TitularHolder();
            addViewsToHolder(convertView, holder);
            convertView.setTag(holder);
        } else {
            holder = (TitularHolder) convertView.getTag();
        }
        Titular titular = getItem(position);
        setDataIntoHolder(holder, titular);

        return convertView;
    }

    private void addViewsToHolder(View convertView, TitularHolder holder) {
        holder.title = (TextView) convertView.findViewById(android.R.id.text1);
        holder.subtitle= (TextView) convertView.findViewById(android.R.id.text2);
    }

    private void setDataIntoHolder(TitularHolder holder, Titular person) {
        holder.title.setText(person.getTitol());
        holder.subtitle.setText(person.getSubtitol());
    }

    class TitularHolder {
        TextView title;
        TextView subtitle;
    }

}
