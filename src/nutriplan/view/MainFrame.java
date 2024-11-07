package nutriplan.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static nutriplan.view.Style.*;

public class MainFrame extends JFrame {

    JPanel mainPanel = new JPanel();
    JPanel menuPanel = new JPanel();
    JPanel logoPanel = new JPanel();

    JLabel label[] = new JLabel[numLabel];
    JFormattedTextField txtdook[] = new JFormattedTextField[numTxtdook];
    JTextArea txtObjetivo = new JTextArea();
    JButton button[] = new JButton[numButton];

    public MainFrame(){

        // Elementos
        inicializarComponentes();

        JMenuBar menu = new JMenuBar();

        // create a menu
        JMenu paciente = new JMenu("Paciente");
        JMenu plano = new JMenu("Plano Alimentar");
        JMenu alimentos = new JMenu("Alimento");

        JMenu cadastrarPlano = new JMenu("Cadastrar");
        JMenu montarPlano = new JMenu("Montar");

        menu.add(paciente);
        menu.add(plano);
        menu.add(alimentos);

        plano.add(cadastrarPlano);
        plano.add(montarPlano);

        paciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.this.setVisible(false);
                FramePaciente framePaciente = new FramePaciente(MainFrame.this); // Exemplo de Frame
                framePaciente.setVisible(true);
            }
        });

        montarPlano.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.this.setVisible(false);
                FramePlanoAlimento framePlanoAlimento = new FramePlanoAlimento(MainFrame.this);
                framePlanoAlimento.setVisible(true);
            }
        });

        cadastrarPlano.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.this.setVisible(false);
                FramePlano framePlano = new FramePlano(MainFrame.this);
                framePlano.setVisible(true);
            }
        });

        alimentos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame.this.setVisible(false);
                FrameAlimento frameAlimentos = new FrameAlimento(MainFrame.this);
                frameAlimentos.setVisible(true);
            }
        });

        this.setJMenuBar(menu);

        // Frame
        this.setTitle("NutriPlan");
        this.setSize(700, 650);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setIconImage(getIcon().getImage());
        this.add(mainPanel(), BorderLayout.CENTER);
        this.setVisible(true);

    }

// ELEMENTOS
    public JPanel mainPanel(){
        //PAINEL CENTRAL
        mainPanel.setBackground(verde);
        mainPanel.setSize(700, 650);
        mainPanel.setBorder(PADDING);
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(logoPanel, BorderLayout.CENTER);
        return mainPanel;
    }

    public JPanel logoPanel(){
        ImageIcon imagemLogo = getImagemLogo(0.5);
        JLabel labelLogo = new JLabel(imagemLogo);

        //PAINEL DO LOGO
        logoPanel.setBackground(transparente);
        logoPanel.setLayout(new BorderLayout());

        logoPanel.add(labelLogo, BorderLayout.CENTER);

        return logoPanel;
    }


    public JPanel menuPanel(){
        // PAINEL FORMS
        menuPanel.setBackground(transparente);
        menuPanel.setBorder(loweredbevel);
        //layout
        menuPanel.setLayout(new GridBagLayout());
        menuPanel.setBorder(BorderFactory.createCompoundBorder(
                loweredbevel,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints gbcForms = new GridBagConstraints();
        gbcForms.insets = new Insets(1, 5, 1, 5);
        gbcForms.gridwidth = 1;
        gbcForms.gridheight = 1;
        gbcForms.fill = GridBagConstraints.BOTH; // Permite preencher horizontalmente
        gbcForms.anchor = GridBagConstraints.WEST;


        return menuPanel;
    }

    public void label(){
        for (int i = 0; i < numLabel; i++) {
            label[i] = new JLabel();
            label[i].setFont(FONT_REGULAR_12);
            label[i].setHorizontalAlignment(SwingConstants.LEFT);
        }

        label[0].setText("Cadastro de Pacientes");
        label[0].setForeground(Color.WHITE);  // Cor do texto
        label[0].setFont(FONT_BOLD_12);
        label[0].setHorizontalAlignment(SwingConstants.CENTER);
        label[0].setOpaque(true);
        label[0].setBackground(Style.verdeEscuro);
        label[0].setBorder(loweredbevel);

        label[1].setText("Nome completo");
        label[2].setText("CPF");
        label[3].setText("Sexo");
        label[4].setText("Data de Nascimento");
        label[5].setText("Altura");
        label[6].setText("Peso");
        label[7].setText("Nivel de atividade");
        label[8].setText("Objetivo:");
    }

    public void txtdook(){
        for (int i=0;i<numTxtdook;i++){
            txtdook[i] = new JFormattedTextField();
            //txtdook[i].setPreferredSize(new Dimension(50, 30));
        }

        txtdook[1] = criarCPFField();
        txtdook[2] = criarDataField();
        txtdook[3] = criarAlturaField();
        //txtdook[4] = criarPesoField();

        txtObjetivo.setLineWrap(true); // Quebra de linha automática
        txtObjetivo.setWrapStyleWord(true); // Quebra por palavras

        // Ajusta a fonte para manter um estilo similar ao JTextField
        txtObjetivo.setFont(new Font("Arial", Font.PLAIN, 12));

        // Adiciona uma borda com aparência de JTextField
        txtObjetivo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1), // Borda externa cinza
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }

    public void button(){
        for (int i = 0; i < numButton; i++) {
            button[i] = new JButton();
            //button[i].setPreferredSize(new Dimension(150, 20));
        }
        button[0].setText("Voltar");
        button[1].setText("Cadastro");
    }

// AÇÕES

    public void inicializarComponentes(){
        txtdook();
        label();
        button();

        mainPanel = mainPanel();
        logoPanel = logoPanel();

    }

}