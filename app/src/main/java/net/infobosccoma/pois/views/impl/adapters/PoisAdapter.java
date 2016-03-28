package net.infobosccoma.pois.views.impl.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import net.infobosccoma.pois.models.business.entities.Pois;
import net.infobosccoma.pois.models.business.entities.Titular;
import net.infobosccoma.poiscloud.R;


public class PoisAdapter extends ArrayAdapter<Pois> {
    private Context context;
    private LayoutInflater inflater;

    public PoisAdapter(Activity context, List<Pois> pois) {
        super(context, 0, pois);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

   @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       PoisHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_pois_detail_row, parent, false);

            holder = new PoisHolder();
            addViewsToHolder(convertView, holder);
            convertView.setTag(holder);
        } else {
            holder = (PoisHolder) convertView.getTag();
        }
        Pois pois = getItem(position);
        setDataIntoHolder(holder, pois);

        return convertView;
    }

    private void addViewsToHolder(View convertView, PoisHolder holder) {

        holder.pois = (TextView) convertView.findViewById(R.id.detail_pois);
        holder.latitude= (TextView) convertView.findViewById(R.id.detail_latitude);
        holder.longitude  = (TextView) convertView.findViewById(R.id.detail_longitude);
        holder.city = (TextView) convertView.findViewById(R.id.detail_city);
    }

    private void setDataIntoHolder(PoisHolder holder, Pois pois) {
        holder.pois.setText(pois.getName());
        holder.latitude.setText(pois.getLatitude());
        holder.longitude.setText(pois.getLongitude());
        holder.city.setText(pois.getCity());
    }

    class PoisHolder {
        TextView pois, latitude, longitude, city;
    }

}
