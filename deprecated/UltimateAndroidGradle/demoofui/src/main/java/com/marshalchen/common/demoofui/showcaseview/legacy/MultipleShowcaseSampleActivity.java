package com.marshalchen.common.demoofui.showcaseview.legacy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.marshalchen.common.demoofui.R;


public class MultipleShowcaseSampleActivity extends Activity {

    private static final float SHOWCASE_KITTEN_SCALE = 1.2f;
    private static final float SHOWCASE_LIKE_SCALE = 0.5f;
    //ShowcaseViews mViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcase_activity_sample_legacy);

        findViewById(R.id.buttonLike).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "like_message", Toast.LENGTH_SHORT).show();
            }
        });

        //mOptions.block = false;
//        mViews = new ShowcaseViews(this,
//                new ShowcaseViews.OnShowcaseAcknowledged() {
//            @Override
//            public void onShowCaseAcknowledged(ShowcaseView showcaseView) {
//                Toast.makeText(MultipleShowcaseSampleActivity.this, R.string.dismissed_message, Toast.LENGTH_SHORT).show();
//            }
//        });
//        mViews.addView( new ShowcaseViews.ItemViewProperties(R.id.image,
//                R.string.showcase_image_title,
//                R.string.showcase_image_message,
//                SHOWCASE_KITTEN_SCALE));
//        mViews.addView( new ShowcaseViews.ItemViewProperties(R.id.buttonLike,
//                R.string.showcase_like_title,
//                R.string.showcase_like_message,
//                SHOWCASE_LIKE_SCALE));
//        mViews.show();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            enableUp();
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void enableUp() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
