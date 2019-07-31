package org.chen;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.chen.interfaces.HandleMergeImageField;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

/**
 * Hello world!
 *
 */
public class App
{
    public static String getDataDir(Class c) {
        File dir = new File(System.getProperty("user.dir"));
        dir = new File(dir, "src");
        dir = new File(dir, "main");
        dir = new File(dir, "resources");

        for (String s : c.getName().split("\\.")) {
            dir = new File(dir, s);
            if (dir.isDirectory() == false)
                dir.mkdir();
        }
        System.out.println("Using data directory: " + dir.toString());
        return dir.toString() + File.separator;
    }

    public static void main(String[] args) throws Exception {
        String dataDir = getDataDir(App.class);
        Document doc = new Document(dataDir + "hello.docx");

        File image = new File(dataDir + "my-image.jpg");
        byte[] imageBytes = Files.readAllBytes(image.toPath());
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        doc.getMailMerge().setFieldMergingCallback(new HandleMergeImageField());
        doc.getMailMerge().execute(
                new String[] { "Name", "MyImage" },
                new Object[] { "Chen Semapat", base64Image }
        );
        doc.save(dataDir + "hello.pdf", SaveFormat.PDF);

        System.out.println("Done!");

    }
}
