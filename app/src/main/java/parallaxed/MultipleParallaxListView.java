package parallaxed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.tsukanov.vladimir.parallaxclipbigdig.CustomListAdapter;
import com.tsukanov.vladimir.parallaxclipbigdig.R;

public class MultipleParallaxListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_multiple_parallax);
        ParallaxListView listView = (ParallaxListView) findViewById(R.id.list_view);
        CustomListAdapter adapter = new CustomListAdapter(LayoutInflater.from(this));
        listView.setAdapter(adapter);
    }

}
