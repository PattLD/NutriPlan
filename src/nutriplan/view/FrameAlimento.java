package nutriplan.view;

import nutriplan.controller.AlimentoController;
import nutriplan.controller.Conversao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static nutriplan.view.Style.*;

public class FrameAlimento extends JFrame {

    int codComida = 0;
    String nomeComida = "";
    double kcal100 = 0;

    JPanel mainPanel = new JPanel();
    JPanel logoPanel = new JPanel();
    JPanel formsPanel = new JPanel();
    JPanel backPanel = new JPanel();
    JLabel[] label = new JLabel[numLabel];
    JFormattedTextField[] txtdook = new JFormattedTextField[numTxtdook];
    JButton[] button = new JButton[numButton];

    private MainFrame mainFrame;

    public FrameAlimento(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        // Elementos
        inicializarComponentes();

        // Botões

        voltar();
        consultar();
        limpar();
        salvar();

        // Frame
        this.setTitle("NutriPlan");
        this.setSize(700, 450);
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //abrir main frame ao fechar
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                fecharJanela();
            }
        });

        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setIconImage(getIcon().getImage());
        this.add(mainPanel(), BorderLayout.CENTER);
        this.setVisible(true);

    }

    // PANEL
    public JPanel logoPanel(){
        ImageIcon imagemLogo = getImagemLogo(0.20);
        JLabel labelLogo = new JLabel(imagemLogo);

        //PAINEL DO LOGO
        logoPanel.setBackground(transparente);
        //layout
        logoPanel.setLayout(new BorderLayout());

        logoPanel.add(labelLogo);

        return logoPanel;
    }
    public JPanel mainPanel(){
        //PAINEL CENTRAL
        mainPanel.setBackground(verde);
        mainPanel.setSize(700, 650);
        mainPanel.setBorder(PADDING);
        //layout
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcCentral = new GridBagConstraints();

        // GRID DO PAINEL CENTRAL

        // logo
        mainPanel.add(logoPanel, Style.configurarConstraints(gbcCentral,0,0,1,0.5,new Insets(5, 5, 0, 5)));

        // "montagem do plano alimentar"
        mainPanel.add(label[0], Style.configurarConstraints(gbcCentral,0,1,1,0.01,new Insets(5, 5, 0, 5)));

        //imput de alimentos
        mainPanel.add(formsPanel, Style.configurarConstraints(gbcCentral,0,2,1,0.3,new Insets(0, 5, 5, 5)));

        gbcCentral.insets = new Insets(5, 5, 5, 5);

        //voltar
        mainPanel.add(backPanel, Style.configurarConstraints(gbcCentral,0,3,1,0.2,new Insets(5, 5, 5, 5)));

        return mainPanel;
    }
    public JPanel formsPanel(){
        // PAINEL FORMS
        formsPanel.setBackground(verdeClaro);
        formsPanel.setBorder(loweredbevel);
        //layout
        formsPanel.setLayout(new GridBagLayout());
        formsPanel.setBorder(BorderFactory.createCompoundBorder(
                loweredbevel,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints gbcForms = new GridBagConstraints();

        // GRID DO PAINEL DADOS

        Insets insetsCima = new Insets(5, 5, 2, 5);
        Insets insetsBaixo = new Insets(2, 5, 5, 5);

        //nome
        formsPanel.add(label[1], Style.configurarConstraints(gbcForms,0,0,3,1,insetsCima));
        //
        formsPanel.add(txtdook[0], Style.configurarConstraints(gbcForms,0,1,3,1,insetsBaixo));

        //kcal100
        formsPanel.add(label[2], Style.configurarConstraints(gbcForms,0,2,1,1, 0.40, 0, insetsCima));
        //
        formsPanel.add(txtdook[1], Style.configurarConstraints(gbcForms,0,3,1,1,insetsBaixo));

        return formsPanel;
    }
    public JPanel backPanel(){
        backPanel.setBackground(transparente);
        backPanel.setLayout(new FlowLayout());

        backPanel.add(button[0]);
        backPanel.add(button[1]);
        backPanel.add(button[2]);
        backPanel.add(button[3]);

        return backPanel;
    }

    // OUTROS ELEMENTOS
    public void label(){
        for (int i = 0; i < numLabel; i++) {
            label[i] = new JLabel();
            label[i].setFont(FONT_REGULAR_12);
            label[i].setHorizontalAlignment(SwingConstants.LEFT);
        }

        label[0] = Style.titulo("Cadastro de Alimento");

        label[1].setText("Nome do alimento");
        label[2].setText("Kcal por 100g");
    }
    public void txtdook(){
        for (int i=0;i<numTxtdook;i++){
            txtdook[i] = new JFormattedTextField();
            //txtdook[i].setPreferredSize(new Dimension(50, 30));
        }
    }
    public void button(){
        for (int i = 0; i < numButton; i++) {
            button[i] = new JButton();
            //button[i].setPreferredSize(new Dimension(150, 20));
        }
        button[0].setText("Voltar");
        button[1].setText("Consultar");
        button[2].setText("Limpar");
        button[3].setText("Salvar");
    }

    // AÇÕES
    public void inicializarComponentes(){
        txtdook();
        label();
        button();

        mainPanel = mainPanel();
        logoPanel = logoPanel();
        formsPanel = formsPanel();
        backPanel = backPanel();

    }
    public void limparTela(){
        txtdook[0].setText("");
        txtdook[1].setText("");
    }
    public void fecharJanela(){
        this.dispose();
        if (mainFrame != null) {
            mainFrame.setVisible(true); // Exibe o mainFrame novamente
        }
    }
    public void buscarAlimento(int codComida, String nomeComida, double kcal100) {
        this.codComida = codComida;
        txtdook[0].setText(nomeComida);
        txtdook[1].setText(String.valueOf(kcal100));
    }

    // BOTOES
    public void voltar() {
        button[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrameAlimento.this.setVisible(false);
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
    public void consultar() {
        button[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FrameAlimento.this.setVisible(false);
                FrameConsultaAlimento frameConsultaAlimentos = new FrameConsultaAlimento(FrameAlimento.this); // Exemplo de Frame
                frameConsultaAlimentos.setVisible(true);
            }
        });
    }
    public void limpar() {
        button[2].addActionListener(e -> limparTela());
    }
    public void salvar(){
        button[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                AlimentoController alimentoController = new AlimentoController();

                nomeComida = txtdook[0].getText();
                kcal100 = Conversao.converterDouble(txtdook[1]);

                boolean sucesso;

                try {
                    sucesso = alimentoController.cadastrarAlimento(nomeComida, kcal100);

                    if(sucesso){
                        JOptionPane.showMessageDialog(null,"O alimento foi cadastrado com sucesso!");
                        limparTela();
                    } else {
                        JOptionPane.showMessageDialog(null,"Os campos não foram preenchidos corretamente.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Erro: " + ex.getMessage());
                }

            }
        });
    }



}