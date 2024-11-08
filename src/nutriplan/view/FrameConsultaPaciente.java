package nutriplan.view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import static nutriplan.view.Style.*;

public class FrameConsultaPaciente extends javax.swing.JFrame {

    JPanel mainPanel = new JPanel();
    JPanel imputPanel = new JPanel();
    JPanel logoPanel = new JPanel();
    JScrollPane tabela = new JScrollPane();

    JButton botaoConsulta = new JButton();
    JTextField pesquisaPaciente = new JTextField();

    JLabel txtConsultaPaciente = titulo("Consultar Paciente");
    JLabel txtNomePaciente = new JLabel("Nome do Paciente");
    DefaultTableModel modelTabela = new DefaultTableModel();
    JTable tabelaPacientes = new JTable(modelTabela);


    private FramePaciente framePaciente;

    public FrameConsultaPaciente(FramePaciente framePaciente) {
        inicializarComponentes();

        botaoConsultaAcao();
        clicarTabela();

        this.framePaciente = framePaciente;
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

        mainPanel.add(txtConsultaPaciente, Style.configurarConstraints(gbcCentral,0,1,1,0.01,new Insets(5, 5, 0, 5)));

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
        imputPanel.add(pesquisaPaciente, Style.configurarConstraints(gbcImput,0,1, 1,1,0.9,1,new Insets(5, 5, 0, 5)));
        imputPanel.add(botaoConsulta, Style.configurarConstraints(gbcImput,1,1, 1,1,0.1,1,new Insets(5, 5, 0, 5)));

        return imputPanel;
    }
    public DefaultTableModel modelTabela(){
        String [] colunas = {"Código","Nome completo", "CPF", "Sexo", "Nascimento","Altura", "Peso", "Atividade"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int coluna) {
                // Define o tipo de cada coluna
                return switch (coluna) {
                    case 0 -> Integer.class;
                    case 1, 2, 3, 7 -> String.class;
                    case 4 -> Data.class;
                    case 5, 6 -> Double.class;
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
        tabela.setViewportView(tabelaPacientes);
        tabela.setBackground(Color.RED);
        tabela.setMinimumSize(new Dimension(400, 170));

        return tabela;
    }

    public JTextField pesquisaPaciente() {
        pesquisaPaciente.setPreferredSize(new Dimension(300, 25));

        return pesquisaPaciente;
    }

    public void inicializarComponentes(){
        botaoConsulta = Style.botaoConsulta();
        pesquisaPaciente = pesquisaPaciente();

        mainPanel = mainPanel();
        logoPanel = logoPanel();
        imputPanel = imputPanel();
        modelTabela = modelTabela();
        tabela = tabela();
        tabelaPacientes.setModel(modelTabela);


    }
    public void clicarTabela(){
        tabelaPacientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int codPaciente = (int) tabelaPacientes.getModel().getValueAt(tabelaPacientes.getSelectedRow(), 0);
                    String nome = (String) tabelaPacientes.getModel().getValueAt(tabelaPacientes.getSelectedRow(), 1);
                    String CPF = (String) tabelaPacientes.getModel().getValueAt(tabelaPacientes.getSelectedRow(), 2);
                    String sexo = (String) tabelaPacientes.getModel().getValueAt(tabelaPacientes.getSelectedRow(), 3);
                    LocalDate dataNascimento = (LocalDate) tabelaPacientes.getModel().getValueAt(tabelaPacientes.getSelectedRow(), 4);
                    double altura = (double) tabelaPacientes.getModel().getValueAt(tabelaPacientes.getSelectedRow(), 5);
                    double peso = (double) tabelaPacientes.getModel().getValueAt(tabelaPacientes.getSelectedRow(), 6);
                    String atividade = (String) tabelaPacientes.getModel().getValueAt(tabelaPacientes.getSelectedRow(), 7);

                    FrameConsultaPaciente.this.framePaciente.buscarPaciente(codPaciente,nome,CPF,sexo,dataNascimento,altura,peso,atividade);
                    FrameConsultaPaciente.this.framePaciente.setVisible(true);
                    FrameConsultaPaciente.this.dispose();
                }
            }
        });
    }
    public void fecharJanela(){
        this.dispose();
        if (framePaciente != null) {
            framePaciente.setVisible(true); // Exibe o FRAME PACIENTE novamente
        }
    }

    public void botaoConsultaAcao(){
        botaoConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                

            }
        });
    }
}
