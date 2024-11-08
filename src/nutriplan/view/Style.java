package nutriplan.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class Style {
    public static Color verdeClaro = new Color(244, 255, 227, 255);
    public static Color verde = new Color(213, 238, 170, 255);
    public static Color verdeEscuro = new Color(7, 79, 19, 255);
    public static Color transparente = new Color(0, 0, 0, 0);

    public static Border etched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    public static Border loweredbevel = BorderFactory.createLoweredBevelBorder();
    public static Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    public static Border raisedbevel = BorderFactory.createRaisedBevelBorder();
    public static Border shadowBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 3),
            BorderFactory.createLoweredBevelBorder());

    public static int numLabel = 30;
    public static int numPanel = 10;
    public static int numButton = 5;
    public static int numTxtdook = 6;

    //FONTS
    public static final Font FONT_REGULAR_12 = new Font("Courier", Font.PLAIN, 12);
    public static final Font FONT_BOLD_12 = new Font("Courier", Font.BOLD, 12);
    public static final Font FONT_ITALIC_12 = new Font("Courier", Font.ITALIC, 12);
    public static final Font FONT_BOLD_ITALIC_12 = new Font("Courier", Font.BOLD | Font.ITALIC, 12);

    public static final Font FONT_REGULAR_10 = new Font("Courier", Font.PLAIN, 10);
    public static final Font FONT_BOLD_10 = new Font("Courier", Font.BOLD, 10);
    public static final Font FONT_ITALIC_10 = new Font("Courier", Font.ITALIC, 10);
    public static final Font FONT_BOLD_ITALIC_10 = new Font("Courier", Font.BOLD | Font.ITALIC, 10);

    public static final Border PADDING = BorderFactory.createEmptyBorder(20, 30, 20, 30);
    public static final Border SMALL_PADDING = BorderFactory.createEmptyBorder(10, 15, 10, 15);

    public static Insets insetsCima = new Insets(5, 5, 2, 5);
    public static Insets insetsBaixo = new Insets(2, 5, 5, 5);
    public static Insets zero = new Insets(0, 0, 0, 0);

    //Consulta
    public static JButton botaoConsulta(){
        JButton botaoConsulta = new JButton();
        try {
            ImageIcon lupa = new ImageIcon("src/images/consultaIcon.png");
            botaoConsulta.setIcon(lupa);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        botaoConsulta.setOpaque(true);
        botaoConsulta.setBackground(verdeEscuro);
        botaoConsulta.setBorder(loweredbevel);
        botaoConsulta.setSize(24,24);
        botaoConsulta.setFocusable(false);

        return botaoConsulta;
    }
    public static JPanel consulta(){
        JButton botaoConsulta = botaoConsulta();

        JTextField txtConsulta = new JTextField();
        txtConsulta.setEditable(false);
        txtConsulta.setFocusable(false);

        JPanel consulta = new JPanel();
        consulta.setOpaque(false);
        consulta.setBackground(Color.white);
        consulta.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        consulta.add(txtConsulta, configurarConstraints(c,0,0, 1,1,0.95,1, new Insets(5,5,5,5)));
        consulta.add(botaoConsulta, configurarConstraints(c,1,0,1,1,0.05,1, new Insets(5,5,5,5)));

        TitledBorder titledBorder = BorderFactory.createTitledBorder(etched,"Nome");
        titledBorder.setTitleFont(FONT_BOLD_10);
        consulta.setBorder(titledBorder);

        return consulta;
    }
    public static JPanel consulta(ActionListener e){
        JButton botaoConsulta = botaoConsulta();
        botaoConsulta.addActionListener(e);

        JTextField txtConsulta = new JTextField();
        txtConsulta.setEditable(false);
        txtConsulta.setFocusable(false);

        JPanel consulta = new JPanel();
        consulta.setBackground(transparente);
        consulta.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        consulta.add(txtConsulta, configurarConstraints(c,0,0, 1,1,0.95,1, new Insets(5,5,5,5)));
        consulta.add(botaoConsulta, configurarConstraints(c,1,0,1,1,0.05,1, new Insets(5,5,5,5)));

        return consulta;
    }

    //IMAGENS
    public static ImageIcon getImagemLogo(double num){
        double logoAlturaOriginal = 432.0;
        double logoLarguraOriginal = 1073.0;
        double redimencionamento = num;
        int logoAltura = (int)(logoAlturaOriginal * redimencionamento);
        int logoLargura = (int)(logoLarguraOriginal * redimencionamento);

        ImageIcon imagemLogoOriginal = new ImageIcon("src/images/logo.png");
        Image imagemLogoRedimensionada = imagemLogoOriginal.getImage().getScaledInstance(logoLargura, logoAltura, Image.SCALE_SMOOTH);
        ImageIcon imagemLogo = new ImageIcon(imagemLogoRedimensionada);

        return imagemLogo;

    }
    public static ImageIcon getBigIcon(double num){
        double logoAlturaOriginal = 512.0;
        double logoLarguraOriginal = 512.0;
        double redimencionamento = num;
        int logoAltura = (int)(logoAlturaOriginal * redimencionamento);
        int logoLargura = (int)(logoLarguraOriginal * redimencionamento);

        ImageIcon imagemLogoOriginal = new ImageIcon("src/images/Icon-512.png");
        Image imagemLogoRedimensionada = imagemLogoOriginal.getImage().getScaledInstance(logoLargura, logoAltura, Image.SCALE_SMOOTH);
        ImageIcon imagemLogo = new ImageIcon(imagemLogoRedimensionada);

        return imagemLogo;

    }
    public static ImageIcon getIcon(){
        ImageIcon icon = new ImageIcon("src/images/icon.png");
        return icon;
    }

    //LABEL
    public static JLabel titulo(String texto){
        JLabel titulo = new JLabel();
        titulo.setForeground(Color.WHITE);  // Cor do texto
        titulo.setFont(FONT_BOLD_12);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setOpaque(true);
        titulo.setBackground(Style.verdeEscuro);
        titulo.setBorder(loweredbevel);
        titulo.setText(texto);

        return titulo;
    }
    public static JLabel titulo2(String texto){
        JLabel titulo = new JLabel();
        titulo.setText(texto);
        titulo.setFont(FONT_BOLD_12);
        titulo.setOpaque(true);
        titulo.setBackground(verde);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(raisedbevel);
        return titulo;
    }

    //FORMATAÇÃO DE TEXTFIELD
    public static JFormattedTextField criarCPFField() {
        try {
            MaskFormatter CPFFormatter = new MaskFormatter("###.###.###-##");
            CPFFormatter.setPlaceholderCharacter('_');
            JFormattedTextField CPFField = new JFormattedTextField(CPFFormatter);
            return CPFField;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static JFormattedTextField criarDataField() {
        try {
            MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
            dateFormatter.setPlaceholderCharacter('_');
            JFormattedTextField dateField = new JFormattedTextField(dateFormatter);
            return dateField;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static JFormattedTextField criarAlturaField() {
        try {
            MaskFormatter heightFormatter = new MaskFormatter("#.##");
            heightFormatter.setPlaceholderCharacter('_');
            JFormattedTextField heightField = new JFormattedTextField(heightFormatter);

            return heightField;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //GRIDBAGCONSTRAINTS
    public static GridBagConstraints configurarConstraints(GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, Insets insets) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.insets = insets;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        return gbc;
    }
    public static GridBagConstraints configurarConstraints(GridBagConstraints gbc, int gridx, int gridy, double weightx, double weighty, Insets insets) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.insets = insets;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        return gbc;
    }

    public static GridBagConstraints configurarConstraints(GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight, Insets insets) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.insets = insets;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        return gbc;
    }

}
