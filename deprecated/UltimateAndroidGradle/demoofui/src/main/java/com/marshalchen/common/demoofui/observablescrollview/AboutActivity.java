/*
 * Copyright 2014 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marshalchen.common.demoofui.observablescrollview;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marshalchen.common.demoofui.BuildConfig;
import com.marshalchen.common.demoofui.R;

public class AboutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observable_scroll_view_activity_about);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeButtonEnabled(true);
        }
        ((TextView) findViewById(R.id.app_version)).setText(getString(R.string.msg_app_version, BuildConfig.VERSION_NAME));
        ((TextView) findViewById(R.id.lib_version)).setText(getString(R.string.msg_lib_version));

        initLicenses();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initLicenses() {
        LayoutInflater inflater = LayoutInflater.from(this);
        LinearLayout content = (LinearLayout) findViewById(R.id.licenses);

        String[] softwareList = getResources().getStringArray(R.array.software_list);
        String[] licenseList = getResources().getStringArray(R.array.license_list);
        content.addView(createItemsText(softwareList));
        for (int i = 0; i < softwareList.length; i++) {
            content.addView(createDivider(inflater));
            content.addView(createHeader(softwareList[i]));
            content.addView(createHtmlText(licenseList[i]));
        }
    }

    private TextView createHeader(final String name) {
        String s = "<big><b>" + name + "</b></big>";
        return createHtmlText(s, 8);
    }

    private TextView createItemsText(final String... names) {
        StringBuilder s = new StringBuilder();
        for (String name : names) {
            if (s.length() > 0) {
                s.append("<br>");
            }
            s.append("- ");
            s.append(name);
        }
        return createHtmlText(s.toString(), 8);
    }

    private TextView createHtmlText(final String s) {
        return createHtmlText(s, 8);
    }

    private TextView createHtmlText(final String s, final int margin) {
        TextView text = new TextView(this);
        text.setAutoLinkMask(Linkify.WEB_URLS | Linkify.EMAIL_ADDRESSES);
        text.setText(Html.fromHtml(s));
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        int marginPx = (0 < margin) ? (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, margin,
                getResources().getDisplayMetrics()) : 0;
        layoutParams.setMargins(0, marginPx, 0, marginPx);
        text.setLayoutParams(layoutParams);
        return text;
    }

    private View createDivider(final LayoutInflater inflater) {
        return inflater.inflate(R.layout.observable_scroll_view_divider, null);
    }
}
