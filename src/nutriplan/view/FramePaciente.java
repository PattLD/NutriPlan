package nutriplan.view;

import nutriplan.controller.Conversao;
import nutriplan.controller.PacienteController;
import nutriplan.model.Paciente;
import nutriplan.view.consulta.FrameConsultaPaciente;

import javax.swing.*;  // Pacote para os componentes da GUI (JFrame, JButton, JLabel, etc.)
import java.awt.*;     // Pacote para layouts e manipulação de gráficos
import java.awt.event.*;  // Pacote para manipulação de eventos (ActionListener)
import java.time.LocalDate;

import static nutriplan.view.Style.*;

public class FramePaciente extends JFrame {
    
	private String nome;
    private String CPF;
    private String sexo;
    private LocalDate dataNascimento;
    private double altura;
    private double peso;
	private String atividade;
    private String objetivo;
    private int idade;
    private double IMC;
    private double TMB;
    private double GET;

    private int codPaciente = 0;

    JPanel mainPanel = new JPanel();
    JPanel logoPanel = new JPanel();
    JPanel formsPanel = new JPanel();
    JPanel backPanel = new JPanel();
    JLabel[] label = new JLabel[numLabel];
    JFormattedTextField[] txtdook = new JFormattedTextField[numTxtdook];
    JTextArea txtObjetivo = new JTextArea();
    JButton[] button = new JButton[numButton];

    String[] genderStrings = { "SELECIONE", "Feminino", "Masculino" };
    JComboBox gender = new JComboBox(genderStrings);

    String[] exercisetrings = { "SELECIONE", "Sedentário", "Levemente ativo", "Moderamente ativo", "Muito ativo" };
    JComboBox exerciseFrequency = new JComboBox(exercisetrings);

    private MainFrame mainFrame;

    public FramePaciente(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        // Elementos
        inicializarComponentes();

        // Botões

        voltar();
        consultar();
        limpar();
        deletar();
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

        //cpf
        formsPanel.add(label[2], Style.configurarConstraints(gbcForms,0,2,1,1, 0.40, 0, insetsCima));
        //
        formsPanel.add(txtdook[1], Style.configurarConstraints(gbcForms,0,3,1,1,insetsBaixo));

        //sexo
        formsPanel.add(label[3], Style.configurarConstraints(gbcForms,1,2,1,1, 0.30, 0, insetsCima));
        //
        formsPanel.add(gender, Style.configurarConstraints(gbcForms,1,3,1,1,insetsBaixo));

        //data de nascimento
        formsPanel.add(label[4], Style.configurarConstraints(gbcForms,2,2,1,1, 0.20, 0, insetsCima));
        //
        formsPanel.add(txtdook[2], Style.configurarConstraints(gbcForms,2,3,1,1,insetsBaixo));

        //altura
        formsPanel.add(label[5], Style.configurarConstraints(gbcForms,0,4,1,1, 0.40, 0, insetsCima));
        //
        formsPanel.add(txtdook[3], Style.configurarConstraints(gbcForms,0,5,1,1,insetsBaixo));

        //peso
        formsPanel.add(label[6], Style.configurarConstraints(gbcForms,1,4,1,1, 0.30, 0, insetsCima));
        //
        formsPanel.add(txtdook[4], Style.configurarConstraints(gbcForms,1,5,1,1,insetsBaixo));

        //nivel de atividade
        formsPanel.add(label[7], Style.configurarConstraints(gbcForms,2,4,1,1, 0.20, 0, insetsCima));
        //
        formsPanel.add(exerciseFrequency, Style.configurarConstraints(gbcForms,2,5,1,1,insetsBaixo));

        //objetivo
//        formsPanel.add(label[8], Style.configurarConstraints(gbcForms,0,6,3,1, 1, 0, insetsCima));
        //
//        formsPanel.add(txtObjetivo, Style.configurarConstraints(gbcForms,0,7,3,1,1,1,insetsBaixo));

        return formsPanel;
    }
    public JPanel backPanel(){
        backPanel.setBackground(transparente);
        backPanel.setLayout(new FlowLayout());

        backPanel.add(button[0]);
        backPanel.add(button[1]);
        backPanel.add(button[2]);
        backPanel.add(button[3]);
        backPanel.add(button[4]);

        return backPanel;
    }

    // OUTROS ELEMENTOS
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
        button[1].setText("Consultar");
        button[2].setText("Limpar");
        button[3].setText("Deletar");
        button[4].setText("Salvar");
    }

    // COMBOBOX
    public JComboBox gender(){
        gender.setSelectedIndex(0);

        return gender;
    }
    public JComboBox exerciseFrequency(){
        exerciseFrequency.setSelectedIndex(0);

        return exerciseFrequency;
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

        gender = gender();
        exerciseFrequency = exerciseFrequency();

    }
    public void limparTela(){
        txtdook[0].setText("");
        txtdook[1].setText("");
        gender.setSelectedIndex(0);
        txtdook[2].setText("");
        txtdook[3].setText("");
        txtdook[4].setText("");
        exerciseFrequency.setSelectedIndex(0);
        txtObjetivo.setText("");
    }
    public void fecharJanela(){
        this.dispose();
        if (mainFrame != null) {
            mainFrame.setVisible(true); // Exibe o mainFrame novamente
        }
    }
    public void buscarPaciente (int codPaciente, String nome, String CPF, String sexo, LocalDate dataNascimento, double altura, double peso, String atividade){
        this.codPaciente = codPaciente;
        txtdook[0].setText(nome);
        txtdook[1].setText(CPF);
        for (int i=0;i<gender.getItemCount();i++){
            if (gender.getItemAt(i).equals(sexo)){
                gender.setSelectedIndex(i);
            }
        }
        txtdook[2].setText(Conversao.converterDateString(dataNascimento));
        txtdook[3].setText(String.valueOf(altura));
        txtdook[4].setText(String.valueOf(peso));
        for (int i=0;i<exerciseFrequency.getItemCount();i++){
            if (exerciseFrequency.getItemAt(i).equals(atividade)){
                exerciseFrequency.setSelectedIndex(i);
            }
        }
    }

    // BOTOES
    public void voltar() {
        button[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FramePaciente.this.setVisible(false);
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
    public void consultar() {
        button[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FramePaciente.this.setVisible(false);
                FrameConsultaPaciente frameConsultaPaciente = new FrameConsultaPaciente(FramePaciente.this); // Exemplo de Frame
                frameConsultaPaciente.setVisible(true);
            }
        });
    }
    public void limpar() {
        button[2].addActionListener(e -> limparTela());
    }
    public void deletar(){}
    public void salvar(){
        button[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Inclusão de dados
                PacienteController pacienteController = new PacienteController();

                nome = txtdook[0].getText();
                CPF = txtdook[1].getText();
                sexo = (String) gender.getSelectedItem();
                dataNascimento = Conversao.converterLocalDate(txtdook[2]);
                altura = Conversao.converterDouble(txtdook[3]);
                peso = Conversao.converterDouble(txtdook[4]);
                atividade = (String) exerciseFrequency.getSelectedItem();
                objetivo = txtObjetivo.getText();

                idade = Paciente.calcularIdade(dataNascimento);
                IMC = Paciente.calcularIMC(altura, peso);
                TMB = Paciente.calcularTMB(sexo,altura,peso,idade);
                GET = Paciente.calcularGET(atividade,TMB);
                boolean sucesso = false;

                try {
                    if(FramePaciente.this.codPaciente == 0){
                        sucesso = pacienteController.cadastrarPaciente(nome, CPF, sexo, dataNascimento, altura, peso, atividade, idade, IMC, TMB, GET);
                    } else {
                        sucesso = pacienteController.alterarPaciente(FramePaciente.this.codPaciente, nome, CPF, sexo, dataNascimento, altura, peso, atividade, idade, IMC, TMB, GET);
                    }

                    if(sucesso){
                        JOptionPane.showMessageDialog(null,"O paciente foi cadastrado com sucesso!");
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