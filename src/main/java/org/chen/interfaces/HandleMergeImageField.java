package org.chen.interfaces;

import com.aspose.words.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

public class HandleMergeImageField implements IFieldMergingCallback {
    public void fieldMerging(FieldMergingArgs args) throws Exception {

    }

    public void imageFieldMerging(ImageFieldMergingArgs args) throws Exception {
        if(args.getFieldValue() != null) {
            byte[] imageBytes = Base64.getDecoder().decode(args.getFieldValue().toString());
            InputStream stream = new ByteArrayInputStream(imageBytes);
            args.setImageStream(stream);
        }
    }
}