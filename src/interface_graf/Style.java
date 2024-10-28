package interface_graf;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Style {
    public static Color verdeClaro = new Color(244, 255, 227, 255);
    public static Color verde = new Color(213, 238, 170, 255);
    public static Color verdeEscuro = new Color(7, 79, 19, 255);
    public static Color transparente = new Color(0, 0, 0, 0);

    public static Border etched = BorderFactory.createEtchedBorder();
    public static Border loweredbevel = BorderFactory.createLoweredBevelBorder();
    public static Border raisedbevel = BorderFactory.createRaisedBevelBorder();
    public static Border shadowBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 3),
            BorderFactory.createLoweredBevelBorder());

    public static final Font FONT_REGULAR_12 = new Font("Courier", Font.PLAIN, 12);
    public static final Font FONT_BOLD_12 = new Font("Courier", Font.BOLD, 12);
    public static final Font FONT_ITALIC_12 = new Font("Courier", Font.ITALIC, 12);
    public static final Font FONT_BOLD_ITALIC_12 = new Font("Courier", Font.BOLD | Font.ITALIC, 12);

    public static final Font FONT_REGULAR_10 = new Font("Courier", Font.PLAIN, 10);
    public static final Font FONT_BOLD_10 = new Font("Courier", Font.BOLD, 10);
    public static final Font FONT_ITALIC_10 = new Font("Courier", Font.ITALIC, 10);
    public static final Font FONT_BOLD_ITALIC_10 = new Font("Courier", Font.BOLD | Font.ITALIC, 10);

    public static final Border PADDING = BorderFactory.createEmptyBorder(20, 30, 20, 30);

    public static ImageIcon getimagemLogo(){
        double logoAlturaOriginal = 432.0;
        double logoLarguraOriginal = 1073.0;
        double redimencionamento = 0.20;
        int logoAltura = (int)(logoAlturaOriginal * redimencionamento);
        int logoLargura = (int)(logoLarguraOriginal * redimencionamento);

        ImageIcon imagemLogoOriginal = new ImageIcon("src/resouce/logo.png");
        Image imagemLogoRedimensionada = imagemLogoOriginal.getImage().getScaledInstance(logoLargura, logoAltura, Image.SCALE_SMOOTH);
        ImageIcon imagemLogo = new ImageIcon(imagemLogoRedimensionada);

        return imagemLogo;

    }
}
