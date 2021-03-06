package com.keepassdroid.utils;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.kunzisoft.keepass.BuildConfig;
import com.kunzisoft.keepass.R;
import com.keepassdroid.AboutActivity;
import com.keepassdroid.settings.SettingsActivity;
import com.keepassdroid.stylish.StylishActivity;


public class MenuUtil {

    public static void donationMenuInflater(MenuInflater inflater, Menu menu) {
        if(!(BuildConfig.FULL_VERSION && BuildConfig.GOOGLE_PLAY_VERSION))
            inflater.inflate(R.menu.donation, menu);
    }

    public static void defaultMenuInflater(MenuInflater inflater, Menu menu) {
        donationMenuInflater(inflater, menu);
        inflater.inflate(R.menu.default_menu, menu);
    }

    public static boolean onDonationItemSelected(StylishActivity activity) {
        try {
            Util.gotoUrl(activity, R.string.donate_url);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, R.string.error_failed_to_launch_link, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public static boolean onDefaultMenuOptionsItemSelected(StylishActivity activity, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_donate:
                return onDonationItemSelected(activity);

            case R.id.menu_app_settings:
                Intent i = new Intent(activity, SettingsActivity.class);
                activity.startActivity(i);
                return true;

            case R.id.menu_about:
                Intent intent = new Intent(activity, AboutActivity.class);
                activity.startActivity(intent);
                return true;

            default:
                return true;
        }
    }
}
