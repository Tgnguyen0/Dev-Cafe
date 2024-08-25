package app.InitFont;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

public class CustomFont {
    private Font fernandoFont;
    private Font twistyPixelFont;

    public CustomFont() {

        try {
            // Load the font file (assuming it's in the project directory)
            // CSFONT-TwistyPixel
            // FVF Fernando 08
            File fontFile = new File("dev_cafe/font/FVF Fernando 08.ttf"); // For vscode
            //File fontFile = new File("font/FVF Fernando 08.ttf"); // for eclipse, intelj
            this.fernandoFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            // Register the font with the graphics environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fernandoFont);
            
            this.fernandoFont = this.fernandoFont.deriveFont(9.0f);
        } catch (Exception e) {
            e.printStackTrace();
            this.fernandoFont = new Font("Arial", Font.PLAIN, 12);
        }

        try {
            // Load the font file (assuming it's in the project directory)
            // CSFONT-TwistyPixel
            // FVF Fernando 08
            File fontFile = new File("dev_cafe/font/CSFONT-TwistyPixel.ttf"); // For vscode
            //File fontFile = new File("font/CSFONT-TwistyPixel.ttf"); // for eclipse, intelj
            this.twistyPixelFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            // Register the font with the graphics environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(twistyPixelFont);
            
            this.twistyPixelFont = this.twistyPixelFont.deriveFont(9.0f);
        } catch (Exception e) {
            e.printStackTrace();
            this.twistyPixelFont = new Font("Arial", Font.PLAIN, 12);
        }
    }

    public Font getFernandoFont(float size) {
        return this.fernandoFont.deriveFont(size);
    }

    public Font getTwistyPixelFont() {
        return this.twistyPixelFont;
    }    
}
