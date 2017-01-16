package adapter;


import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class HintAdapter extends ArrayAdapter<String> {


    public HintAdapter(Context context, List<String> objects, int resource) {
        super(context, resource, objects);
    }

    @Override
    public int getCount(){
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}
