package interface_graf;

import backend.Paciente;

import javax.swing.*;  // Pacote para os componentes da GUI (JFrame, JButton, JLabel, etc.)
import javax.swing.border.TitledBorder;
import java.awt.*;     // Pacote para layouts e manipulação de gráficos
import java.awt.event.*;  // Pacote para manipulação de eventos (ActionListener)

import static interface_graf.Style.*;

public class Frame1 extends JFrame {
    
	private Paciente paciente;

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




    
	public Frame1(){

        this.setTitle("NutriPlan");
        this.setSize(700, 650);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        ////////////////////////
        //BORDAS


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



        // PAINEL IMPUT
        JPanel imputPanel = new JPanel();
        imputPanel.setBackground(verdeClaro);
        imputPanel.setBorder(loweredbevel);
        //imputPanel.setPreferredSize(new Dimension(400, 330));
        //layout
        imputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcImput = new GridBagConstraints();
        gbcImput.insets = new Insets(1, 5, 1, 5);
        gbcImput.weightx = 1;
        gbcImput.gridwidth = 1;
        gbcImput.gridheight = 1;
        gbcImput.fill = GridBagConstraints.BOTH; // Permite preencher horizontalmente

        // PAINEL BOTAO
        JPanel backPane = new JPanel();
        backPane.setBackground(transparente);
        backPane.setLayout(new FlowLayout());



        ////////////////////////
        //LABELS
        JLabel label[] = new JLabel[numLabel];
        for (int i = 0; i < numLabel; i++) {
            label[i] = new JLabel();
            label[i].setFont(FONT_REGULAR_12);
            label[i].setHorizontalAlignment(SwingConstants.LEFT);
        }

        ImageIcon imagemLogo = getimagemLogo();

        // Adiciona a imagem ao JLabel
        JLabel labelLogo = new JLabel(imagemLogo);

        // Adiciona o JLabel ao frame
        logoPanel.add(labelLogo);

        label[0].setText("Cadastro de Pacientes");
        label[0].setForeground(Color.WHITE);  // Cor do texto
        label[0].setFont(FONT_BOLD_12);
        label[0].setHorizontalAlignment(SwingConstants.CENTER);
        label[0].setOpaque(true);
        label[0].setBackground(Style.verdeEscuro);
        label[0].setBorder(loweredbevel);

        //LABEL - DADOS
        //titulos
        Font boldFont = FONT_BOLD_12;

        TitledBorder[] title = new TitledBorder[numLabel];
        for (int i = 0; i < numLabel; i++) {
            title[i] = BorderFactory.createTitledBorder(etched, "");
            title[i].setTitleFont(boldFont);
        }
        //renomeando
        title[0].setTitle("Nome");
        title[1].setTitle("Sexo");
        title[2].setTitle("Idade");
        title[3].setTitle("IMC");
        title[4].setTitle("Peso");
        title[5].setTitle("Altura");
        title[6].setTitle("Objetivo");
        title[7].setTitle("Nivel de atividade");
        title[8].setTitle("Taxa Metabólica Basal (TMB)");
        title[9].setTitle("Gasto Energético Total (GET)");
        //labels
        for(int i=0;i<10;i++) {
            int j = i+1;
            label[j].setBorder(title[i]);
        }
        label[1].setText("Nome");
        label[2].setText("Sexo");
        label[3].setText("Idade");
        label[4].setText("IMC");
        label[5].setText("Peso");
        label[6].setText("Altura:");
        label[7].setText("Objetivo:");
        label[8].setText("Nivel de atividade:");
        label[9].setText("Taxa Metabólica Basal (TMB):");
        label[10].setText("Gasto Energético Total (GET):");

        label[11].setText("REGULAR");


        //LABELS - IMPUT
        label[12].setText("ALIMENTO");
        label[12].setFont(FONT_BOLD_12);
        label[12].setHorizontalAlignment(SwingConstants.CENTER);
        label[12].setBorder(raisedbevel);

        label[13].setText("PLANO");
        label[13].setFont(FONT_BOLD_12);
        label[13].setHorizontalAlignment(SwingConstants.CENTER);
        label[13].setBorder(raisedbevel);

        label[14].setText("Nome");
        label[15].setText("Calorias adicionadas");
        label[16].setText("Quantidade (g)");
        label[17].setText("Calorias restantes");
        label[18].setText("kcal/100g");
        label[19].setText("Calorias necessárias");

        ////////////////////////
        // BOTOES
        JButton button[] = new JButton[numButton];
        for (int i = 0; i < numButton; i++) {
            button[i] = new JButton();
            //button[i].setPreferredSize(new Dimension(150, 20));
        }
        button[0].setText("Adicionar");
        button[1].setText("Remover");
        button[2].setText("Atualizar");
        button[3].setText("Voltar");

        ////////////////////////
        // CAIXA DE TEXTO
        JTextField txtdook[] = new JTextField[numTxtdook];
        for (int i=0;i<numTxtdook;i++){
            txtdook[i] = new JTextField();
            //txtdook[i].setPreferredSize(new Dimension(50, 30));
        }

        txtdook[1].setEditable(false);
        txtdook[1].setEditable(false);

        txtdook[3].setEditable(false);
        txtdook[3].setEditable(false);

        txtdook[5].setEditable(false);
        txtdook[5].setEditable(false);

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
        mainPanel.add(imputPanel, gbcCentral);

        gbcCentral.insets = new Insets(5, 5, 5, 5);

        //voltar
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 3;
        gbcCentral.weighty = 0.01;
        mainPanel.add(backPane, gbcCentral);

        ////////////////////////
        // GRID DO PAINEL DADOS

        backPane.add(button[3], FlowLayout.LEFT);
        button[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Frame2 frame2 = new Frame2();
                frame2.setVisible(true);
                Frame1.this.setVisible(false);

            }
        });

        this.add(mainPanel, BorderLayout.CENTER);

        this.setVisible(true);
		
	}
	
	public Paciente getPaciente() {
	    return paciente;
	}
}