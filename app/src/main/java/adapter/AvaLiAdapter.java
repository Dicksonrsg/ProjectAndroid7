package adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cia.ryuu.view.R;

import java.util.ArrayList;
import java.util.List;

import model.Teacher;

public class AvaLiAdapter extends BaseAdapter{

    private Context context;
    private List<Teacher> teachers;

    public AvaLiAdapter(Context context, List<Teacher> teachers) {
        this.context = context;
        this.teachers = teachers;
    }

    @Override
    public int getCount() {
        return teachers.size();
    }

    @Override
    public Object getItem(int position) {
        return teachers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Teacher teacher = teachers.get(position);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.teachers_layout, null);
        }

        TextView tvName = (TextView) view.findViewById(R.id.tl_tvName);
        tvName.setText(teacher.getName());

        TextView tvPhone = (TextView) view.findViewById(R.id.tl_tvPhone);
        tvPhone.setText(teacher.getPhone());

        TextView tvLanguage = (TextView) view.findViewById(R.id.tl_tvLanguage);
        tvLanguage.setText(teacher.getLanguage());

        return view;
    }
}
