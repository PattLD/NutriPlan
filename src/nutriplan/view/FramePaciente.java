package nutriplan.view;

import nutriplan.model.Paciente;

import javax.swing.*;  // Pacote para os componentes da GUI (JFrame, JButton, JLabel, etc.)
import java.awt.*;     // Pacote para layouts e manipulação de gráficos
import java.awt.event.*;  // Pacote para manipulação de eventos (ActionListener)
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    JPanel mainPanel = new JPanel();
    JPanel logoPanel = new JPanel();
    JPanel formsPanel = new JPanel();
    JPanel backPanel = new JPanel();
    JLabel label[] = new JLabel[numLabel];
    JFormattedTextField txtdook[] = new JFormattedTextField[numTxtdook];
    JTextArea txtObjetivo = new JTextArea();
    JButton button[] = new JButton[numButton];

    String[] genderStrings = { "SELECIONE", "Feminino", "Masculino" };
    JComboBox gender = new JComboBox(genderStrings);

    String[] exercisetrings = { "SELECIONE", "Sedentário", "Levemente ativo", "Moderamente ativo", "Muito ativo" };
    JComboBox exerciseFrequency = new JComboBox(exercisetrings);

    public FramePaciente(){

        // Elementos
        inicializarComponentes();

        // Botões
        cadastrar();
        voltar();

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

    // PANEL
    public JPanel logoPanel(){
        ImageIcon imagemLogo = getimagemLogo(0.20);
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
        gbcCentral.fill = GridBagConstraints.BOTH; // Permite preencher horizontalmente
        //gbcCentral.anchor = GridBagConstraints.CENTER;
        gbcCentral.insets = new Insets(5, 20, 5, 5);

        // GRID DO PAINEL CENTRAL

        gbcCentral.weightx = 1;

        // logo
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 0;
        gbcCentral.weighty = 0.08;
        gbcCentral.insets = new Insets(5, 5, 0, 5);
        mainPanel.add(logoPanel, gbcCentral);

        // "montagem do plano alimentar"
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 1;
        gbcCentral.weighty = 0.01;
        gbcCentral.insets = new Insets(5, 5, 0, 5);
        mainPanel.add(label[0], gbcCentral);

        //imput de alimentos
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 2;
        gbcCentral.weighty = 0.95;
        gbcCentral.insets = new Insets(0, 5, 5, 5);
        mainPanel.add(formsPanel, gbcCentral);

        gbcCentral.insets = new Insets(5, 5, 5, 5);

        //voltar
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 3;
        gbcCentral.weighty = 0.01;
        mainPanel.add(backPanel, gbcCentral);

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
        gbcForms.insets = new Insets(1, 5, 1, 5);
        gbcForms.gridwidth = 1;
        gbcForms.gridheight = 1;
        gbcForms.fill = GridBagConstraints.BOTH; // Permite preencher horizontalmente
        gbcForms.anchor = GridBagConstraints.WEST;

        // GRID DO PAINEL DADOS


        //nome
        gbcForms.gridx = 0;
        gbcForms.gridy = 0;
        gbcForms.gridwidth = 3;
        gbcForms.insets = new Insets(5, 5, 2, 5);
        formsPanel.add(label[1], gbcForms);
        //
        gbcForms.gridx = 0;
        gbcForms.gridy = 1;
        gbcForms.gridwidth = 3;
        gbcForms.insets = new Insets(2, 5, 5, 5);
        formsPanel.add(txtdook[0], gbcForms);

        //cpf
        gbcForms.gridx = 0;
        gbcForms.gridy = 2;
        gbcForms.gridwidth = 1;
        gbcForms.weightx = 0.40;
        gbcForms.insets = new Insets(5, 5, 2, 5);
        formsPanel.add(label[2], gbcForms);
        //
        gbcForms.gridx = 0;
        gbcForms.gridy = 3;
        gbcForms.gridwidth = 1;
        gbcForms.insets = new Insets(2, 5, 5, 5);
        formsPanel.add(txtdook[1], gbcForms);

        //sexo
        gbcForms.gridx = 1;
        gbcForms.gridy = 2;
        gbcForms.gridwidth = 1;
        gbcForms.weightx = 0.30;
        gbcForms.insets = new Insets(5, 5, 2, 5);
        formsPanel.add(label[3], gbcForms);
        //
        gbcForms.gridx = 1;
        gbcForms.gridy = 3;
        gbcForms.gridwidth = 1;
        gbcForms.insets = new Insets(2, 5, 5, 5);
        formsPanel.add(gender, gbcForms);

        //data de nascimento
        gbcForms.gridx = 2;
        gbcForms.gridy = 2;
        gbcForms.gridwidth = 1;
        gbcForms.weightx = 0.20;
        gbcForms.insets = new Insets(5, 5, 2, 5);
        formsPanel.add(label[4], gbcForms);
        //
        gbcForms.gridx = 2;
        gbcForms.gridy = 3;
        gbcForms.gridwidth = 1;
        gbcForms.insets = new Insets(2, 5, 5, 5);
        formsPanel.add(txtdook[2], gbcForms);

        //altura
        gbcForms.gridx = 0;
        gbcForms.gridy = 4;
        gbcForms.gridwidth = 1;
        gbcForms.weightx = 0.40;
        gbcForms.insets = new Insets(5, 5, 2, 5);
        formsPanel.add(label[5], gbcForms);
        //
        gbcForms.gridx = 0;
        gbcForms.gridy = 5;
        gbcForms.gridwidth = 1;
        gbcForms.insets = new Insets(2, 5, 5, 5);
        formsPanel.add(txtdook[3], gbcForms);

        //peso
        gbcForms.gridx = 1;
        gbcForms.gridy = 4;
        gbcForms.gridwidth = 1;
        gbcForms.weightx = 0.30;
        gbcForms.insets = new Insets(5, 5, 2, 5);
        formsPanel.add(label[6], gbcForms);
        //
        gbcForms.gridx = 1;
        gbcForms.gridy = 5;
        gbcForms.gridwidth = 1;
        gbcForms.insets = new Insets(2, 5, 5, 5);
        formsPanel.add(txtdook[4], gbcForms);

        //nivel de atividade
        gbcForms.gridx = 2;
        gbcForms.gridy = 4;
        gbcForms.gridwidth = 1;
        gbcForms.weightx = 0.20;
        gbcForms.insets = new Insets(5, 5, 2, 5);
        formsPanel.add(label[7], gbcForms);
        //
        gbcForms.gridx = 2;
        gbcForms.gridy = 5;
        gbcForms.gridwidth = 1;
        gbcForms.insets = new Insets(2, 5, 5, 5);
        formsPanel.add(exerciseFrequency, gbcForms);

        //objetivo
        gbcForms.gridx = 0;
        gbcForms.gridy = 6;
        gbcForms.gridwidth = 3;
        gbcForms.weightx = 1;
        gbcForms.insets = new Insets(5, 5, 2, 5);
        formsPanel.add(label[8], gbcForms);
        //
        gbcForms.gridx = 0;
        gbcForms.gridy = 7;
        gbcForms.gridwidth = 3;
        gbcForms.weighty = 0.5;
        gbcForms.insets = new Insets(2, 5, 5, 5);
        formsPanel.add(txtObjetivo, gbcForms);

        return formsPanel;
    }
    public JPanel backPanel(){
        backPanel.setBackground(transparente);
        backPanel.setLayout(new BorderLayout());

        backPanel.add(button[0], BorderLayout.LINE_START);
        backPanel.add(button[1], BorderLayout.LINE_END);

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
        button[1].setText("Cadastro");
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

    // CONVERSÃO DE DADOS
    public Double converterDouble(JFormattedTextField txt){
        String doubleText = txt.getText().replace(",", "."); // Substitui a vírgula pelo ponto
        try {
            return Double.parseDouble(doubleText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido: " + doubleText, "Erro", JOptionPane.ERROR_MESSAGE);
            return 0.0;
        }
    }
    public LocalDate converterLocalDate(JFormattedTextField txt){
        String dateString = txt.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;

        try {
            date = LocalDate.parse(dateString, formatter);
            System.out.println("Data convertida: " + date);
        } catch (DateTimeParseException ex) {
            System.out.println("Data inválida: " + dateString);
            JOptionPane.showMessageDialog(this, "Data inválida: " + dateString, "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return date;
    }

    // BOTOES
    public void cadastrar(){
        button[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Inclusão de dados
                nome = txtdook[0].getText();
                CPF = txtdook[1].getText();
                sexo = (String) gender.getSelectedItem();
                dataNascimento = converterLocalDate(txtdook[2]);
                altura = converterDouble(txtdook[3]);
                peso = converterDouble(txtdook[4]);
                atividade = (String) exerciseFrequency.getSelectedItem();
                objetivo = txtObjetivo.getText();

                try {
                    Paciente paciente = new Paciente(nome, CPF, sexo, dataNascimento, altura, peso, atividade, objetivo);
                    paciente.mostrarDados();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FramePaciente.this, "Erro ao cadastrar paciente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
    public void voltar() {
        button[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FramePaciente.this.setVisible(false);
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }

}