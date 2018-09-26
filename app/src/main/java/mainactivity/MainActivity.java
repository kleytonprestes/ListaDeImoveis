package mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import kleyton.com.br.testegrupozap.R;
import propertylist.view.PropertyListActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton vivaRealBtn;
    ImageButton zapBtn;
    public static final String IS_FROM_ZAP = "is_from_zap";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initViews();
    }

    private void initViews() {
        vivaRealBtn = findViewById(R.id.vivareal_button);
        zapBtn = findViewById(R.id.zap_button);

        vivaRealBtn.setOnClickListener(this);
        zapBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.vivareal_button:
                initNewActivity(false);
                break;

            case R.id.zap_button:
                initNewActivity(true);
                break;
        }
    }

    private void initNewActivity(boolean isFromZap) {
        Intent intent = new Intent(this, PropertyListActivity.class);
        intent.putExtra(IS_FROM_ZAP, isFromZap);
        startActivity(intent);

    }
}
