/*
 * Copyright 2017 Jeremy Jamet / Kunzisoft.
 *
 * This file is part of KeePass DX.
 *
 *  KeePass DX is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  KeePass DX is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with KeePass DX.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.keepassdroid.view;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;

import com.keepassdroid.compat.StorageAF;
import com.keepassdroid.fileselect.BrowserDialog;
import com.keepassdroid.intents.Intents;
import com.keepassdroid.utils.Interaction;
import com.keepassdroid.utils.UriUtil;

import java.io.File;

import static android.app.Activity.RESULT_OK;


public class KeyFileHelper {

    public static final int GET_CONTENT = 25745;
    private static final int OPEN_DOC = 25845;
    private static final int FILE_BROWSE = 25645;

    private Activity activity;
    private Fragment fragment;
    private Uri mDbUri;

    public KeyFileHelper(Activity context) {
        this(context, null);
    }

    public KeyFileHelper(Activity context, Uri mDbUri) {
        this.activity = context;
        this.fragment = null;
        this.mDbUri = mDbUri;
    }

    public KeyFileHelper(Fragment context) {
        this(context, null);
    }

    public KeyFileHelper(Fragment context, Uri mDbUri) {
        this.activity = context.getActivity();
        this.fragment = context;
        this.mDbUri = mDbUri;
    }

    public View.OnClickListener getOpenFileOnClickViewListener() {
        return new View.OnClickListener() {

            public void onClick(View v) {
                if (StorageAF.useStorageFramework(activity)) {
                    Intent i = new Intent(StorageAF.ACTION_OPEN_DOCUMENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("*/*");
                    if(fragment != null)
                        fragment.startActivityForResult(i, OPEN_DOC);
                    else
                        activity.startActivityForResult(i, OPEN_DOC);
                } else {
                    Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType("*/*");

                    try {
                        if(fragment != null)
                            fragment.startActivityForResult(i, GET_CONTENT);
                        else
                            activity.startActivityForResult(i, GET_CONTENT);
                    } catch (ActivityNotFoundException e) {
                        lookForOpenIntentsFilePicker();
                    }
                }
            }
        };
    }

    private void lookForOpenIntentsFilePicker() {
        if (Interaction.isIntentAvailable(activity, Intents.OPEN_INTENTS_FILE_BROWSE)) {
            Intent i = new Intent(Intents.OPEN_INTENTS_FILE_BROWSE);

            // Get file path parent if possible
            try {
                if (mDbUri != null && mDbUri.toString().length() > 0) {
                    if (mDbUri.getScheme().equals("file")) {
                        File keyfile = new File(mDbUri.getPath());
                        File parent = keyfile.getParentFile();
                        if (parent != null) {
                            i.setData(Uri.parse("file://" + parent.getAbsolutePath()));
                        }
                    }
                }
            } catch (Exception e) {
                // Ignore
            }

            try {
                if(fragment != null)
                    fragment.startActivityForResult(i, FILE_BROWSE);
                else
                    activity.startActivityForResult(i, FILE_BROWSE);
            } catch (ActivityNotFoundException e) {
                showBrowserDialog();
            }
        } else {
            showBrowserDialog();
        }
    }

    private void showBrowserDialog() {
        BrowserDialog browserDialog = new BrowserDialog(activity);
        browserDialog.show();
    }


    public void onActivityResultCallback(
            int requestCode,
            int resultCode,
            Intent data,
            KeyFileCallback keyFileCallback) {

        switch (requestCode) {
            case FILE_BROWSE:
                if (resultCode == RESULT_OK) {
                    String filename = data.getDataString();
                    Uri keyUri = null;
                    if (filename != null) {
                        keyUri = UriUtil.parseDefaultFile(filename);
                    }
                    if (keyFileCallback != null)
                        keyFileCallback.onKeyFileResultCallback(keyUri);
                }
                break;
            case GET_CONTENT:
            case OPEN_DOC:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        Uri uri = data.getData();
                        if (uri != null) {
                            if (requestCode == GET_CONTENT) {
                                uri = UriUtil.translate(activity, uri);
                            }
                            if (keyFileCallback != null)
                                keyFileCallback.onKeyFileResultCallback(uri);
                        }
                    }
                }
                break;
        }
    }

    public interface KeyFileCallback {
        void onKeyFileResultCallback(Uri uri);
    }

}
