package nutriplan.view;

import nutriplan.controller.Conversao;
import nutriplan.controller.PacienteController;
import nutriplan.controller.PlanoController;
import nutriplan.model.Paciente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

import static nutriplan.view.Style.*;

public class FramePlano extends JFrame {

	private double kcal_necessaria;
    private LocalDate data_criacao;
    private String objetivo;

    JPanel paciente = new JPanel();
    JPanel plano = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel logoPanel = new JPanel();

    JPanel formsPanel = new JPanel();
    JPanel backPanel = new JPanel();

    JPanel consultaPaciente = new JPanel();
    JButton botaoConsultaPaciente = new JButton();
    JTextField txtConsultaPaciente = new JTextField();

    JLabel[] label = new JLabel[numLabel];
    JFormattedTextField[] txtdook = new JFormattedTextField[numTxtdook];
    JTextArea txtObjetivo = new JTextArea();
    JButton[] button = new JButton[numButton];

    private MainFrame mainFrame;

    public FramePlano(MainFrame mainFrame){
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
        this.setSize(700, 650);
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
        mainPanel.add(logoPanel, Style.configurarConstraints(gbcCentral,0,0,1,0.08,new Insets(5, 5, 0, 5)));

        // "montagem do plano alimentar"
        mainPanel.add(label[0], Style.configurarConstraints(gbcCentral,0,1,1,0.01,new Insets(5, 5, 0, 5)));

        //imput de alimentos
        mainPanel.add(formsPanel, Style.configurarConstraints(gbcCentral,0,2,1,0.95,new Insets(0, 5, 5, 5)));

        gbcCentral.insets = new Insets(5, 5, 5, 5);

        //voltar
        mainPanel.add(backPanel, Style.configurarConstraints(gbcCentral,0,3,1,0.01,new Insets(5, 5, 5, 5)));

        return mainPanel;
    }
    public JPanel paciente(){
        // PAINEL FORMS
        paciente.setOpaque(false);
        paciente.setBackground(transparente);
        //layout
        paciente.setLayout(new GridBagLayout());
        GridBagConstraints gbcPaciente = new GridBagConstraints();


        //CONSULTA
        paciente.add(consultaPaciente, Style.configurarConstraints(gbcPaciente,0,0,2,1,1,1,zero));

        //imc
        paciente.add(label[2], Style.configurarConstraints(gbcPaciente,0,1,2,1, zero));
        //
        paciente.add(txtdook[1], Style.configurarConstraints(gbcPaciente,0,2,2,1,zero));

        //tmb
        paciente.add(label[3], Style.configurarConstraints(gbcPaciente,0,3,1,1, zero));
        //
        paciente.add(txtdook[2], Style.configurarConstraints(gbcPaciente,0,4,1,1,new Insets(0,0,0,10)));

        //get
        paciente.add(label[4], Style.configurarConstraints(gbcPaciente,1,3,1,1, zero));
        //
        paciente.add(txtdook[3], Style.configurarConstraints(gbcPaciente,1,4,1,1,new Insets(0,0,0,0)));


        return paciente;
    }
    public JPanel plano(){
        // PAINEL FORMS
        plano.setOpaque(false);
        plano.setBackground(transparente);
        //layout
        plano.setLayout(new GridBagLayout());
        GridBagConstraints gbcPlano = new GridBagConstraints();


        //data criacao
        plano.add(label[6], Style.configurarConstraints(gbcPlano,0,0,1,1,1,0.01, insetsCima));
        //
        plano.add(txtdook[4], Style.configurarConstraints(gbcPlano,0,1,1,1,1,0.01,insetsBaixo));

        //kcal diario
        plano.add(label[7], Style.configurarConstraints(gbcPlano,1,0,1,1,1,0.01,insetsCima));
        //
        plano.add(txtdook[5], Style.configurarConstraints(gbcPlano,1,1,1,1,1,0.01,insetsBaixo));

        //objetivo
        plano.add(label[8], Style.configurarConstraints(gbcPlano,0,2,2,1,1,0.01, insetsCima));
        //
        plano.add(txtObjetivo, Style.configurarConstraints(gbcPlano,0,3,2,1,1,0.9,insetsBaixo));

        return plano;
    }
    public JPanel formsPanel(){
        // PAINEL FORMS
        formsPanel.setBackground(verdeClaro);
        formsPanel.setBorder(loweredbevel);
        //layout
        formsPanel.setLayout(new GridBagLayout());
        formsPanel.setBorder(BorderFactory.createCompoundBorder(
                loweredbevel,
                BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        GridBagConstraints gbcForms = new GridBagConstraints();

        // GRID DO PAINEL DADOS
        //PACIENTE
        formsPanel.add(label[1], Style.configurarConstraints(gbcForms,0,0,1,1,1,0.01,zero));

        formsPanel.add(paciente, Style.configurarConstraints(gbcForms,0,1,1,1,1,0.2, new Insets(10,10,10,10)));

        //PLANO
        formsPanel.add(label[5], Style.configurarConstraints(gbcForms,0,2,1,1,1,0.01, zero));

        formsPanel.add(plano, Style.configurarConstraints(gbcForms,0,3,1,1,1,0.8, new Insets(10,10,10,10)));

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
    public JPanel consultaPaciente(){

        try {
            ImageIcon lupa = new ImageIcon("src/images/consultaIcon.png");
            botaoConsultaPaciente.setIcon(lupa);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        botaoConsultaPaciente.setOpaque(true);
        botaoConsultaPaciente.setBackground(verdeEscuro);
        botaoConsultaPaciente.setBorder(loweredbevel);
        botaoConsultaPaciente.setSize(24,24);
        botaoConsultaPaciente.setFocusable(false);

        botaoConsultaPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FramePlano.this.setVisible(false);
                FrameConsultaPaciente frameConsultaPaciente = new FrameConsultaPaciente((JFrame) FramePlano.this);
                frameConsultaPaciente.setVisible(true);
            }
        });

        txtConsultaPaciente.setEditable(false);
        txtConsultaPaciente.setFocusable(false);
        txtConsultaPaciente.setText("");

        consultaPaciente.setBackground(transparente);
        consultaPaciente.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        consultaPaciente.add(txtConsultaPaciente, configurarConstraints(c,0,0, 1,1,0.95,1, new Insets(5,5,5,5)));
        consultaPaciente.add(botaoConsultaPaciente, configurarConstraints(c,1,0,1,1,0.05,1, new Insets(5,5,5,5)));

        return consultaPaciente;
    }

    // OUTROS ELEMENTOS
    public void label(){
        for (int i = 0; i < numLabel; i++) {
            label[i] = new JLabel();
            label[i].setFont(FONT_REGULAR_12);
            label[i].setHorizontalAlignment(SwingConstants.LEFT);
        }

        label[0] = Style.titulo("Cadastro de Plano");

        label[1] = Style.titulo2("PACIENTE");


        label[2].setText("IMC");
        label[2].setFont(FONT_REGULAR_10);
        label[3].setText("Taxa Metabólica Basal (TMB)");
        label[3].setFont(FONT_REGULAR_10);
        label[4].setText("Gasto Energético Total (GET)");
        label[4].setFont(FONT_REGULAR_10);

        label[5] = Style.titulo2("PLANO");

        label[6].setText("Data de criação");
        label[7].setText("Calorias Diárias (kcal)");
        label[8].setText("Objetivo:");
    }
    public void txtdook(){
        for (int i=0;i<numTxtdook;i++){
            txtdook[i] = new JFormattedTextField();
            //txtdook[i].setPreferredSize(new Dimension(50, 30));
        }

        txtdook[1].setEditable(false);
        txtdook[1].setFocusable(false);
        txtdook[2].setEditable(false);
        txtdook[2].setFocusable(false);
        txtdook[3].setEditable(false);
        txtdook[3].setFocusable(false);

        txtdook[4] = criarDataField();

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
        button[1].setText("Consultar");
        button[2].setText("Limpar");
        button[3].setText("Salvar");
    }

    // AÇÕES
    public void inicializarComponentes(){
        txtdook();
        label();
        button();

        paciente = paciente();
        plano = plano();
        mainPanel = mainPanel();
        logoPanel = logoPanel();
        formsPanel = formsPanel();
        backPanel = backPanel();
        consultaPaciente = consultaPaciente();

    }
    public void limparTela(){
        txtdook[0].setText("");
        txtdook[1].setText("");
        txtdook[2].setText("");
        txtdook[3].setText("");
        txtdook[4].setText("");
        txtObjetivo.setText("");
    }
    public void fecharJanela(){
        this.dispose();
        if (mainFrame != null) {
            mainFrame.setVisible(true); // Exibe o mainFrame novamente
        }
    }

    // BOTOES
    public void voltar() {
        button[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FramePlano.this.setVisible(false);
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
    public void consultar() {
        button[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void limpar() {
        button[2].addActionListener(e -> limparTela());
    }
    public void salvar(){
        button[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlanoController planoController = new PlanoController();

//                nome = txtdook[0].getText();
//                CPF = txtdook[1].getText();
//                sexo = (String) gender.getSelectedItem();
//                dataNascimento = Conversao.converterLocalDate(txtdook[2]);
//                altura = Conversao.converterDouble(txtdook[3]);
//                peso = Conversao.converterDouble(txtdook[4]);
//                atividade = (String) exerciseFrequency.getSelectedItem();
//                objetivo = txtObjetivo.getText();
//
//                idade = Paciente.calcularIdade(dataNascimento);
//                IMC = Paciente.calcularIMC(altura, peso);
//                TMB = Paciente.calcularTMB(sexo,altura,peso,idade);
//                GET = Paciente.calcularGET(atividade,TMB);
//
//                try {
//                    boolean sucesso = pacienteController.cadastrarPaciente(nome, CPF, sexo, dataNascimento, altura, peso, atividade, objetivo, idade, IMC, TMB, GET);
//                    if(sucesso){
//                        JOptionPane.showMessageDialog(null,"O paciente foi cadastrado com sucesso!");
//                        limparTela();
//                    } else {
//                        JOptionPane.showMessageDialog(null,"Os campos não foram preenchidos corretamente.");
//                    }
//                } catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null,"Erro: " + ex.getMessage());
//                }

            }
        });
    }



}