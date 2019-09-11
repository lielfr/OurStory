package org.tsofen.ourstory.EditCreateMemory;

import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.UploadTask;

import org.tsofen.ourstory.FirebaseImageWrapper;

import java.util.LinkedList;
import java.util.List;

public class Uploadable {
    Uri localUri, remoteUri;
    boolean isUploaded = false;

    public Uploadable() {
        super();
    }

    public Uploadable(Uri localUri) {
        this.localUri = localUri;
    }

    public Uploadable(String localUri) {
        this.localUri = Uri.parse(localUri);
    }

    public static List<Uploadable> fromServerList(List<String> l) {
        List<Uploadable> ret = new LinkedList<>();
        for (String uriStr : l) {
            Uploadable item = new Uploadable();
            item.isUploaded = true;
            item.remoteUri = Uri.parse(uriStr);
        }
        return ret;
    }

    public static List<String> getListForUpload(List<Uploadable> clientList) {
        List<String> ret = new LinkedList<>();
        for (Uploadable item : clientList) {
            ret.add(item.remoteUri.toString());
        }
        return ret;
    }

    public void upload() {
        FirebaseImageWrapper wrapper = new FirebaseImageWrapper();
        if (!isUploaded) {
            wrapper.uploadImg(localUri)
                    .addOnSuccessListener(taskSnapshot -> isUploaded = true);
        }
    }
}
