package nutriplan.view.consulta;

import nutriplan.model.Paciente;
import nutriplan.view.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static nutriplan.view.Style.*;

public class FrameConsultaPlano extends JFrame {

    JPanel mainPanel = new JPanel();
    JPanel imputPanel = new JPanel();
    JPanel logoPanel = new JPanel();
    JScrollPane tabela = new JScrollPane();

    JButton botaoConsulta = new JButton();
    JTextField pesquisaPlano = new JTextField();

    JLabel txtConsultaPlano = titulo("Consultar Plano");
    JLabel txtNomePaciente = new JLabel("Nome do Paciente");
    DefaultTableModel modelTabela = new DefaultTableModel();
    JTable tabelaPlano = new JTable(modelTabela);

    private JFrame telaCadastro;

    ArrayList<Paciente> listaPacientes;

    public FrameConsultaPlano(JFrame telaCadastro) {
        inicializarComponentes();

        botaoConsultaAcao();
        clicarTabela();

        this.telaCadastro = telaCadastro;
        this.setTitle("NutriPlan");
        this.setSize(700, 650);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
        mainPanel.add(logoPanel, Style.configurarConstraints(gbcCentral,0,0,1,0.06,new Insets(5, 5, 0, 5)));

        mainPanel.add(txtConsultaPlano, Style.configurarConstraints(gbcCentral,0,1,1,0.01,new Insets(5, 5, 0, 5)));

        // "montagem do plano alimentar"
        mainPanel.add(imputPanel, Style.configurarConstraints(gbcCentral,0,2,1,0.01,new Insets(0, 5, 0, 5)));

        //imput de alimentos
        mainPanel.add(tabela, Style.configurarConstraints(gbcCentral,0,3,1,0.92,new Insets(0, 5, 5, 5)));

        gbcCentral.insets = new Insets(5, 5, 5, 5);

        return mainPanel;
    }
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
    public JPanel imputPanel() {
        // PAINEL FORMS
        imputPanel.setBackground(verdeClaro);
        imputPanel.setBorder(BorderFactory.createCompoundBorder(loweredbevel, SMALL_PADDING));
        //LAYOUT
        imputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcImput = new GridBagConstraints();

        imputPanel.add(txtNomePaciente, Style.configurarConstraints(gbcImput,0,0,2,1,1,1,new Insets(5, 5, 0, 5)));
        imputPanel.add(pesquisaPlano, Style.configurarConstraints(gbcImput,0,1, 1,1,0.9,1,new Insets(5, 5, 0, 5)));
        imputPanel.add(botaoConsulta, Style.configurarConstraints(gbcImput,1,1, 1,1,0.1,1,new Insets(5, 5, 0, 5)));

        return imputPanel;
    }
    public DefaultTableModel modelTabela(){
        String [] colunas = {"Código","Nome Completo", "Kcal diária", "Data de criação", "Objetivo"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int coluna) {
                // Define o tipo de cada coluna
                return switch (coluna) {
                    case 0 -> Integer.class;
                    case 1, 4 -> String.class;
                    case 3 -> Data.class;
                    case 2 -> Double.class;
                    default -> Object.class;
                };
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Torna todas as células não editáveis
                return false;
            }
        };
        return model;
    }
    public JScrollPane tabela(){
        tabela.setViewportView(tabelaPlano);
        tabela.setBackground(Color.RED);
        tabela.setMinimumSize(new Dimension(400, 170));

        return tabela;
    }

    public JTextField pesquisaPlano() {
        pesquisaPlano.setPreferredSize(new Dimension(300, 25));

        return pesquisaPlano;
    }

    public void inicializarComponentes(){
        botaoConsulta = Style.botaoConsulta();
        pesquisaPlano = pesquisaPlano();

        mainPanel = mainPanel();
        logoPanel = logoPanel();
        imputPanel = imputPanel();
        modelTabela = modelTabela();
        tabela = tabela();
        tabelaPlano.setModel(modelTabela);


    }
    public void clicarTabela(){

    }
    public void fecharJanela(){
        this.dispose();
        if (telaCadastro != null) {
            telaCadastro.setVisible(true); // Exibe o FRAME PACIENTE novamente
        }
    }

    public void botaoConsultaAcao(){
        botaoConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
