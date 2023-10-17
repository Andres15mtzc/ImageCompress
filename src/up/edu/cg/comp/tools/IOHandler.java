package up.edu.cg.comp.tools;

import java.awt.image.BufferedImage;
import java.io.File;

public abstract class IOHandler {
    public abstract void showInfo(String info);
    public abstract int getInt(String info, String notValidInput);
    public abstract File getFile(String info, String notValidInput, String type);
    public abstract BufferedImage getImage(String info, String notValidInput);
}