package se.mah.mosa.chatforyou;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mosa on 2014-12-02.
 */
public class GroupAdapter extends ArrayAdapter<Group> {
    LayoutInflater mLayoutInflater;

    public GroupAdapter(Context context, ArrayList<Group> data) {
        super(context, R.layout.row_group, data);
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();

            convertView = mLayoutInflater.inflate(R.layout.row_group, parent, false);

            holder.name = (TextView) convertView.findViewById(R.id.row_group_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());

        return convertView;
    }

    private class ViewHolder {
        TextView name;
    }
}
