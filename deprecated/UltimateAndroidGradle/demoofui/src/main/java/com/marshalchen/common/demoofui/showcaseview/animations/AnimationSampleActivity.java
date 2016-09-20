package com.marshalchen.common.demoofui.showcaseview.animations;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.marshalchen.common.uimodule.showcaseview.ApiUtils;
import com.marshalchen.common.uimodule.showcaseview.ShowcaseView;
import com.marshalchen.common.uimodule.showcaseview.targets.Target;
import com.marshalchen.common.uimodule.showcaseview.targets.ViewTarget;
import com.marshalchen.common.demoofui.R;


/**
 * Created by Alex on 26/10/13.
 */
public class AnimationSampleActivity extends Activity implements View.OnClickListener {

    private ShowcaseView showcaseView;
    private int counter = 0;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private final ApiUtils apiUtils = new ApiUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showcase_activity_animation);

        textView1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        showcaseView = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(findViewById(R.id.textView)))
                .setOnClickListener(this)
                .build();
        showcaseView.setButtonText("next");
    }


    private void setAlpha(float alpha, View... views) {
        if (apiUtils.isCompatWithHoneycomb()) {
            for (View view : views) {
                view.setAlpha(alpha);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (counter) {
            case 0:
                showcaseView.setShowcase(new ViewTarget(textView2), true);
                break;

            case 1:
                showcaseView.setShowcase(new ViewTarget(textView3), true);
                break;

            case 2:
                showcaseView.setTarget(Target.NONE);
                showcaseView.setContentTitle("Check it out");
                showcaseView.setContentText("You don't always need a target to showcase");
                showcaseView.setButtonText("close");
                setAlpha(0.4f, textView1, textView2, textView3);
                break;

            case 3:
                showcaseView.hide();
                setAlpha(1.0f, textView1, textView2, textView3);
                break;
        }
        counter++;
    }
}
