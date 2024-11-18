package nutriplan.view.consulta;

import nutriplan.controller.AlimentoController;
import nutriplan.dao.ExceptionDAO;
import nutriplan.model.Alimento;
import nutriplan.view.FrameAlimento;
import nutriplan.view.FramePlanoAlimento;
import nutriplan.view.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static nutriplan.view.Style.*;

public class FrameConsultaAlimento extends JFrame {

    JPanel mainPanel = new JPanel();
    JPanel imputPanel = new JPanel();
    JPanel logoPanel = new JPanel();
    JScrollPane tabela = new JScrollPane();

    JButton botaoConsulta = new JButton();
    JTextField pesquisaAlimento = new JTextField();

    JLabel txtConsultaAlimento = titulo("Consultar Alimento");
    JLabel txtNomeAlimento = new JLabel("Nome do Alimento");
    DefaultTableModel modelTabela = new DefaultTableModel();
    JTable tabelaAlimentos = new JTable(modelTabela);


    private JFrame telaCadastro = new JFrame();

    public FrameConsultaAlimento(JFrame telaCadastro) {
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

        mainPanel.add(txtConsultaAlimento, Style.configurarConstraints(gbcCentral,0,1,1,0.01,new Insets(5, 5, 0, 5)));

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

        imputPanel.add(txtNomeAlimento, Style.configurarConstraints(gbcImput,0,0,2,1,1,1,new Insets(5, 5, 0, 5)));
        imputPanel.add(pesquisaAlimento, Style.configurarConstraints(gbcImput,0,1, 1,1,0.9,1,new Insets(5, 5, 0, 5)));
        imputPanel.add(botaoConsulta, Style.configurarConstraints(gbcImput,1,1, 1,1,0.1,1,new Insets(5, 5, 0, 5)));

        return imputPanel;
    }
    public DefaultTableModel modelTabela(){
        String [] colunas = {"Código","Nome", "Calorias por 100g"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int coluna) {
                // Define o tipo de cada coluna
                return switch (coluna) {
                    case 0 -> Integer.class;
                    case 1 -> String.class;
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
        tabela.setViewportView(tabelaAlimentos);
        tabela.setBackground(Color.RED);
        tabela.setMinimumSize(new Dimension(400, 170));

        return tabela;
    }

    public JTextField pesquisaAlimento() {
        pesquisaAlimento.setPreferredSize(new Dimension(300, 25));

        return pesquisaAlimento;
    }

    public void inicializarComponentes(){
        botaoConsulta = Style.botaoConsulta();
        pesquisaAlimento = pesquisaAlimento();

        mainPanel = mainPanel();
        logoPanel = logoPanel();
        imputPanel = imputPanel();
        modelTabela = modelTabela();
        tabela = tabela();
        tabelaAlimentos.setModel(modelTabela);

    }
    public void clicarTabela(){
        tabelaAlimentos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int codAlimento = (int) tabelaAlimentos.getModel().getValueAt(tabelaAlimentos.getSelectedRow(), 0);
                    String nomeComida = (String) tabelaAlimentos.getModel().getValueAt(tabelaAlimentos.getSelectedRow(), 1);
                    double kcal100 = (double) tabelaAlimentos.getModel().getValueAt(tabelaAlimentos.getSelectedRow(), 2);

                    if (telaCadastro instanceof FrameAlimento) {
                        FrameAlimento frameAlimento = (FrameAlimento) telaCadastro;
                        frameAlimento.buscarAlimento(codAlimento, nomeComida, kcal100);
                        frameAlimento.setVisible(true);
                        FrameConsultaAlimento.this.dispose();
                    } else if (telaCadastro instanceof FramePlanoAlimento) {
                        FramePlanoAlimento framePlanoAlimento = (FramePlanoAlimento) telaCadastro;
                        framePlanoAlimento.buscarAlimento(codAlimento,nomeComida,kcal100);
                        framePlanoAlimento.setVisible(true);
                        FrameConsultaAlimento.this.dispose();
                    } else {
                        System.out.println("Erro: telaCadastro não é uma instância de FrameAlimento.");
                    }
                }
            }
        });
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
                String nome = pesquisaAlimento.getText();
                modelTabela.setRowCount(0);
                AlimentoController alimentoController = new AlimentoController();
                try {
                    ArrayList<Alimento> alimentos = alimentoController.listarAlimentos(nome);
                    alimentos.forEach((Alimento alimento) -> {
                        modelTabela.addRow(new Object[]{alimento.getCodAlimento(),
                                alimento.getNomeComida(),
                                alimento.getKcal100()});
                    });
                    tabelaAlimentos.setModel(modelTabela);
                } catch (ExceptionDAO evt) {
                    Logger.getLogger(FrameConsultaAlimento.class.getName()).log(Level.SEVERE, null, evt);
                }

            }
        });
    }
}
