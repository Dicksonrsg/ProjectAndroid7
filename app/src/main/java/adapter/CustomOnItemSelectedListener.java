package adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class CustomOnItemSelectedListener implements OnItemSelectedListener {

    int count = 0;

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(count > 0) {
            Toast.makeText(parent.getContext(),
                    "Language Selected: " + parent.getItemAtPosition(position).toString(),
                    Toast.LENGTH_SHORT).show();
        }
        count ++;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
