package nutriplan.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import static nutriplan.view.Style.*;

public class FramePlano extends JFrame {

    String alimento = "";
    int quantidade = 0;
    int calorias = 0;
    private JPanel mainPanel;

    public FramePlano(){

        this.setTitle("NutriPlan");
        this.setSize(700, 650);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("src/images/icon.png");
        this.setIconImage(icon.getImage());

        ////////////////////////
        //PAINEIS

        //PAINEL CENTRAL
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setBackground(verde);
        mainPanel.setSize(700, 650);
        mainPanel.setBorder(PADDING);
        //layout
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcCentral = new GridBagConstraints();
        gbcCentral.weightx = 0.5;
        gbcCentral.gridwidth = 1;
        gbcCentral.gridheight = 1;
        gbcCentral.fill = GridBagConstraints.BOTH; // Permite preencher horizontalmente
        gbcCentral.anchor = GridBagConstraints.CENTER;
        gbcCentral.insets = new Insets(5, 5, 5, 5);

        //PAINEL DE DADOS
        JPanel dadosPanel = new JPanel();
        dadosPanel.setBackground(verdeClaro);
        dadosPanel.setBorder(loweredbevel);
        //dadosPanel.setPreferredSize(new Dimension(400, 160));
        //layout
        dadosPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcDados = new GridBagConstraints();
        gbcDados.insets = new Insets(1, 1, 1, 1);
        gbcDados.weightx = 0.5;
        gbcDados.gridwidth = 1;
        gbcDados.gridheight = 1;
        gbcDados.fill = GridBagConstraints.BOTH; // Permite preencher horizontalmente
        gbcDados.anchor = GridBagConstraints.CENTER;

        // PAINEL IMPUT
        JPanel imputPanel = new JPanel();
        imputPanel.setOpaque(true);
        imputPanel.setBackground(verdeClaro);
        imputPanel.setBorder(loweredbevel);
        //imputPanel.setPreferredSize(new Dimension(400, 100));
        //layout
        imputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcImput = new GridBagConstraints();
        gbcImput.insets = new Insets(1, 5, 1, 5);
        gbcImput.weightx = 0.5;
        gbcImput.gridwidth = 1;
        gbcImput.gridheight = 1;
        gbcImput.fill = GridBagConstraints.BOTH; // Permite preencher horizontalmente
        gbcImput.anchor = GridBagConstraints.CENTER;

        // PAINEL BOTAO
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBackground(transparente);
        //buttonPanel.setPreferredSize(new Dimension(400, 30));
        buttonPanel.setLayout(new FlowLayout());

        // PAINEL TABELA

        String [] colunas = {"Nome do alimento", "Quantidade (g)", "Kcal/100g"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBackground(Color.RED);
        scrollPane.setMinimumSize(new Dimension(400, 170));

        // PAINEL Voltar
        JPanel backPane = new JPanel();
        backPane.setBackground(transparente);
        //backPane.setPreferredSize(new Dimension(400, 30));
        backPane.setLayout(new FlowLayout());



        ////////////////////////
        //LABELS
        JLabel label[] = new JLabel[numLabel];
        for (int i = 0; i < numLabel; i++) {
            label[i] = new JLabel();
            label[i].setFont(FONT_ITALIC_10);
            label[i].setHorizontalAlignment(SwingConstants.LEFT);
        }


        label[0].setText("Montagem do Plano Alimentar");
        label[0].setForeground(Color.WHITE);  // Cor do texto
        label[0].setFont(FONT_BOLD_12);
        label[0].setHorizontalAlignment(SwingConstants.CENTER);
        label[0].setOpaque(true);
        label[0].setBackground(verdeEscuro);
        label[0].setBorder(loweredbevel);

        //LABEL - DADOS
        //titulos

        TitledBorder[] title = new TitledBorder[numLabel];
        for (int i = 0; i < numLabel; i++) {
            title[i] = BorderFactory.createTitledBorder(etched, "");
            title[i].setTitleFont(FONT_BOLD_10);
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
        label[12].setOpaque(true);
        label[12].setBackground(verde);
        label[12].setHorizontalAlignment(SwingConstants.CENTER);
        label[12].setBorder(raisedbevel);

        label[13].setText("PLANO");
        label[13].setFont(FONT_BOLD_12);
        label[13].setOpaque(true);
        label[13].setBackground(verde);
        label[13].setHorizontalAlignment(SwingConstants.CENTER);
        label[13].setBorder(raisedbevel);

        label[14].setText("Nome");
        label[15].setText("Calorias adicionadas");
        label[16].setText("Quantidade (g)");
        label[17].setText("Calorias restantes");
        label[18].setText("kcal/100g");
        label[19].setText("Calorias necessárias");

        label[20].setText("Dados do Paciente");
        label[20].setForeground(Color.WHITE);  // Cor do texto
        label[20].setFont(FONT_BOLD_12);
        label[20].setHorizontalAlignment(SwingConstants.CENTER);
        label[20].setOpaque(true);
        label[20].setBackground(verdeEscuro);
        label[20].setBorder(loweredbevel);

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

        // "dados do paciente"
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 0;
        gbcCentral.weighty = 0.01;
        gbcCentral.insets = new Insets(5, 5, 0, 5);
        mainPanel.add(label[20], gbcCentral);

        // dados
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 1;
        gbcCentral.weighty = 0.23;
        gbcCentral.insets = new Insets(0, 5, 0, 5);
        mainPanel.add(dadosPanel, gbcCentral);

        // "montagem do plano alimentar"
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 2;
        gbcCentral.weighty = 0.01;
        gbcCentral.insets = new Insets(0, 5, 0, 5);
        mainPanel.add(label[0], gbcCentral);

        //imput de alimentos
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 3;
        gbcCentral.weighty = 0.22;
        gbcCentral.insets = new Insets(0, 5, 5, 5);
        mainPanel.add(imputPanel, gbcCentral);

        gbcCentral.insets = new Insets(5, 5, 5, 5);

        //botoes
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 4;
        gbcCentral.weighty = 0.05;
        mainPanel.add(buttonPanel, gbcCentral);

        //tabela
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 5;
        gbcCentral.weighty = 0.43;
        mainPanel.add(scrollPane, gbcCentral);

        //voltar
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 6;
        gbcCentral.weighty = 0.05;
        mainPanel.add(backPane, gbcCentral);

        ////////////////////////
        // GRID DO PAINEL DADOS

        //nome
        gbcDados.gridx = 0;
        gbcDados.gridy = 0;
        gbcDados.gridwidth = 2;
        dadosPanel.add(label[1], gbcDados);

        gbcDados.gridwidth = 1;

        //sexo
        gbcDados.gridx = 2;
        gbcDados.gridy = 0;
        dadosPanel.add(label[2], gbcDados);

        //idade
        gbcDados.gridx = 3;
        gbcDados.gridy = 0;
        dadosPanel.add(label[3], gbcDados);

        //imc
        gbcDados.gridx = 0;
        gbcDados.gridy = 1;
        dadosPanel.add(label[4], gbcDados);


        gbcDados.gridx = 1;
        gbcDados.gridy = 1;
        dadosPanel.add(label[11], gbcDados);

        //peso
        gbcDados.gridx = 2;
        gbcDados.gridy = 1;
        dadosPanel.add(label[5], gbcDados);

        //altura
        gbcDados.gridx = 3;
        gbcDados.gridy = 1;
        dadosPanel.add(label[6], gbcDados);

        //objetivo
        gbcDados.gridx = 0;
        gbcDados.gridy = 2;
        gbcDados.gridwidth = 2;
        dadosPanel.add(label[7], gbcDados);

        //atividade
        gbcDados.gridx = 2;
        gbcDados.gridy = 2;
        gbcDados.gridwidth = 2;
        dadosPanel.add(label[8], gbcDados);

        //TMB
        gbcDados.gridx = 0;
        gbcDados.gridy = 3;
        gbcDados.gridwidth = 2;
        dadosPanel.add(label[9], gbcDados);

        //GET
        gbcDados.gridx = 2;
        gbcDados.gridy = 3;
        gbcDados.gridwidth = 2;
        dadosPanel.add(label[10], gbcDados);

        ////////////////////////
        // GRID DO PAINEL IMPUT

        //alimento
        gbcImput.gridx = 0;
        gbcImput.gridy = 0;
        gbcImput.gridwidth = 2;
        gbcImput.insets = new Insets(1, 5, 5, 5);
        imputPanel.add(label[12],gbcImput);

        //plano
        gbcImput.gridx = 2;
        gbcImput.gridy = 0;
        gbcImput.gridwidth = 2;
        imputPanel.add(label[13],gbcImput);

        gbcImput.gridwidth = 1;
        gbcImput.insets = new Insets(1, 5, 1, 5);

        //nome
        gbcImput.gridx = 0;
        gbcImput.gridy = 1;
        gbcImput.weightx = 0.1;
        imputPanel.add(label[14],gbcImput);
        //textdook
        gbcImput.gridx = 1;
        gbcImput.gridy = 1;
        gbcImput.weightx = 0.9;
        imputPanel.add(txtdook[0],gbcImput);

        //kcal adicionado
        gbcImput.gridx = 2;
        gbcImput.gridy = 1;
        gbcImput.weightx = 0.1;
        imputPanel.add(label[15],gbcImput);
        //textdook
        gbcImput.gridx = 3;
        gbcImput.gridy = 1;
        gbcImput.weightx = 0.9;
        imputPanel.add(txtdook[1],gbcImput);

        //quantidade
        gbcImput.gridx = 0;
        gbcImput.gridy = 2;
        gbcImput.weightx = 0.1;
        imputPanel.add(label[16],gbcImput);
        //textdook
        gbcImput.gridx = 1;
        gbcImput.gridy = 2;
        gbcImput.weightx = 0.9;
        imputPanel.add(txtdook[2],gbcImput);

        //calorias restantes
        gbcImput.gridx = 2;
        gbcImput.gridy = 2;
        gbcImput.weightx = 0.1;
        imputPanel.add(label[17],gbcImput);
        //textdook
        gbcImput.gridx = 3;
        gbcImput.gridy = 2;
        gbcImput.weightx = 0.9;
        imputPanel.add(txtdook[3],gbcImput);

        //kcal/100g
        gbcImput.gridx = 0;
        gbcImput.gridy = 3;
        gbcImput.weightx = 0.1;
        imputPanel.add(label[18],gbcImput);
        //textdook
        gbcImput.gridx = 1;
        gbcImput.gridy = 3;
        gbcImput.weightx = 0.9;
        imputPanel.add(txtdook[4],gbcImput);

        //calorias totais
        gbcImput.gridx = 2;
        gbcImput.gridy = 3;
        gbcImput.weightx = 0.1;
        imputPanel.add(label[19],gbcImput);
        //textdook
        gbcImput.gridx = 3;
        gbcImput.gridy = 3;
        gbcImput.weightx = 0.9;
        imputPanel.add(txtdook[5],gbcImput);

        ////////////////////////
        // GRID DO PAINEL BUTTON
        buttonPanel.add(button[0]);
        buttonPanel.add(button[1]);
        buttonPanel.add(button[2]);

        backPane.add(button[3], FlowLayout.LEFT);
        button[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                FramePlano.this.setVisible(false);

            }
        });

        this.add(mainPanel, BorderLayout.CENTER);

        this.setVisible(true);

    }



}