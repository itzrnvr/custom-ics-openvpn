/*
 * Copyright (c) 2012-2023 Arne Schwabe
 * Distributed under the GNU GPL v2 with additional terms. For full terms see the file doc/LICENSE.txt
 */

package de.blinkt.openvpn.core;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.util.Objects;

import de.blinkt.openvpn.utils.RealPaths;

public class BundleReader {
    Context context;

    public BundleReader(Context context){
        this.context = context;
    }

    public File[] extract(Uri uri, String password) throws ZipException {


        File[] files = null;
        String destination = null;

        String source = RealPaths.getRealPathFromURI(uri, context);
        Toast.makeText(context, source, Toast.LENGTH_LONG).show();

        try {
            ZipFile zipFile = new ZipFile(source);
            String folderName = zipFile.getFile().getName();
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password.toCharArray());
            }

            File outFile = new File(context.getExternalFilesDir(null).getParent(),folderName);
            outFile.mkdirs();
            destination = outFile.getAbsolutePath();

            zipFile.extractAll(destination);
            Toast.makeText(context, destination, Toast.LENGTH_LONG).show();
        } catch (ZipException e) {
            e.printStackTrace();
        }

        return loadProfiles(destination);
    }

    private File[] loadProfiles(String source){
        File yourDir = new File(source);
        return yourDir.listFiles();
    }
}
