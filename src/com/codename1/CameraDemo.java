package com.codename1;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 * The camera demo shows off the capture API allowing us to grab a photo to display to the user. To learn more
 * about the capture API check out the <a href="https://www.codenameone.com/javadoc/com/codename1/capture/Capture.html">JavaDocs for Capture</a>
 * and the <a href="https://www.codenameone.com/manual/">developer guide</a> 
 * (<a href="https://www.codenameone.com/files/developer-guide.pdf">pdf</a>).
 */
public class CameraDemo {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);    
    }
    
    public void start(){
        if(current != null) {
            current.show();
            return;
        }
    
        Form f = new Form("Camera", new BorderLayout());
        Button b = new Button("Take A Picture");
        ImageViewer l = new ImageViewer();
        
        b.addActionListener(evt -> {
            String path = Capture.capturePhoto();
            try {
                Image i1 = Image.createImage(path);
                l.setImage(i1);
                f.revalidate();
            }catch (Exception ex) {
                Log.e(ex);
                Dialog.show("Error", "Error during image loading: " + ex, "OK", null);
            }
        });
        
        f.add(BorderLayout.SOUTH, ComponentGroup.enclose(b)).
                add(BorderLayout.CENTER, l);
        
        f.show();
    }

    public void stop(){
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy(){
    }
}
