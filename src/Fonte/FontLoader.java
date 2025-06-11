package Fonte;

import java.awt.*;
import java.io.File;

// Carrega a fonte customizada
public class FontLoader {
    public static Font loadFont(String path, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path))
                    .deriveFont(size);
        } catch (Exception e) {
            e.fillInStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int)size);
        }
    }
}
