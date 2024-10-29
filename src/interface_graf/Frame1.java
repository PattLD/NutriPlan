package interface_graf;

import backend.Paciente;

import javax.swing.*;  // Pacote para os componentes da GUI (JFrame, JButton, JLabel, etc.)
import javax.swing.border.TitledBorder;
import java.awt.*;     // Pacote para layouts e manipulação de gráficos
import java.awt.event.*;  // Pacote para manipulação de eventos (ActionListener)
import java.net.URL;

import static interface_graf.Style.*;

public class Frame1 extends JFrame {
    
	public static Paciente paciente;

    int numLabel = 30;
    int numPanel = 10;
    int numButton = 5;
    int numTxtdook = 6;

	private String sexo;
	private String objetivo;
	private String atividade;
	private String nome;
	private int idade;
	private double peso;
	private double altura;

    private String[] genderStrings = { "SELECIONE", "Feminino", "Masculino" };
    private String[] exercisetrings = { "SELECIONE", "Sedentário", "Levemente ativo", "Moderamente ativo", "Muito ativo" };


    public Frame1(){

        this.setTitle("NutriPlan");
        this.setSize(700, 650);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("src/resouce/icon.png");
        this.setIconImage(icon.getImage());

        ////////////////////////
        //PAINEIS

        //PAINEL CENTRAL
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(verde);
        mainPanel.setSize(700, 650);
        mainPanel.setBorder(PADDING);
        //layout
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcCentral = new GridBagConstraints();
        gbcCentral.fill = GridBagConstraints.BOTH; // Permite preencher horizontalmente
        //gbcCentral.anchor = GridBagConstraints.CENTER;
        gbcCentral.insets = new Insets(5, 20, 5, 5);

        //PAINEL DO LOGO
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(transparente);
        //logoPanel.setPreferredSize(new Dimension(400, 130));
        //layout
        logoPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcDados = new GridBagConstraints();
        gbcDados.insets = new Insets(1, 1, 1, 1);
        gbcDados.weightx = 0.5;
        gbcDados.gridwidth = 1;
        gbcDados.gridheight = 1;
        gbcDados.fill = GridBagConstraints.BOTH; // Permite preencher horizontalmente
        gbcDados.anchor = GridBagConstraints.CENTER;



        // PAINEL FORMS
        JPanel formsPanel = new JPanel();
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

        // PAINEL BOTAO
        JPanel backPane = new JPanel();
        backPane.setBackground(transparente);
        backPane.setLayout(new BorderLayout());



        ////////////////////////
        //LABELS

        //IMAGEM
        ImageIcon imagemLogo = getimagemLogo();
        JLabel labelLogo = new JLabel(imagemLogo);

        // Adiciona o JLabel ao frame




        //LABEL - CADASTRO
        JLabel label[] = new JLabel[numLabel];
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

        JComboBox gender = new JComboBox(genderStrings);
        gender.setSelectedIndex(2);
        gender.setSelectedIndex(0);

        JComboBox exerciseFrequency = new JComboBox(exercisetrings);
        exerciseFrequency.setSelectedIndex(4);
        exerciseFrequency.setSelectedIndex(0);

        ////////////////////////
        // BOTOES
        JButton button[] = new JButton[numButton];
        for (int i = 0; i < numButton; i++) {
            button[i] = new JButton();
            //button[i].setPreferredSize(new Dimension(150, 20));
        }
        button[0].setText("Voltar");
        button[1].setText("Cadastro");

        ////////////////////////
        // CAIXA DE TEXTO
        JTextField txtdook[] = new JTextField[numTxtdook];
        for (int i=0;i<numTxtdook;i++){
            txtdook[i] = new JTextField();
            //txtdook[i].setPreferredSize(new Dimension(50, 30));
        }

        JTextArea txtObjetivo = new JTextArea();
        txtObjetivo.setLineWrap(true); // Quebra de linha automática
        txtObjetivo.setWrapStyleWord(true); // Quebra por palavras

        // Ajusta a fonte para manter um estilo similar ao JTextField
        txtObjetivo.setFont(new Font("Arial", Font.PLAIN, 12));

        // Adiciona uma borda com aparência de JTextField
        txtObjetivo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1), // Borda externa cinza
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

                ////////////////////////
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
        mainPanel.add(backPane, gbcCentral);

        ////////////////////////
        //ADD LOGO
        logoPanel.add(labelLogo);

        ////////////////////////
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


        backPane.add(button[0], BorderLayout.LINE_START);
        backPane.add(button[1], BorderLayout.LINE_END);

        button[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                abrirFrame2();

            }
        });

        this.add(mainPanel, BorderLayout.CENTER);

        this.setVisible(true);
		
	}

    public void abrirFrame2(){
        Frame2 frame2 = new Frame2();
        frame2.setVisible(true);
        Frame1.this.setVisible(false);
    }
	
	public Paciente getPaciente() {
	    return paciente;
	}
}