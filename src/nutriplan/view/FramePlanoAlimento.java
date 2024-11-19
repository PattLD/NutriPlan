package nutriplan.view;

import nutriplan.controller.Conversao;
import nutriplan.controller.PlanoAlimentoController;
import nutriplan.dao.ExceptionDAO;
import nutriplan.model.PlanoAlimento;
import nutriplan.view.consulta.FrameConsultaAlimento;
import nutriplan.view.consulta.FrameConsultaPlano;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import static nutriplan.view.Style.*;

public class FramePlanoAlimento extends JFrame {

    double gramasComida = 0;
    double kcal100 = 0;
    double kcalComida = 0;
    int codComida;
    int codPlano;

    JPanel mainPanel = new JPanel();
    JPanel dadosPanel = new JPanel();
    JPanel imputPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel backPanel = new JPanel();

    JPanel consultaPaciente = new JPanel();
    JButton botaoConsultaPaciente = new JButton();
    JTextField txtConsultaPaciente = new JTextField();


    JPanel consultaAlimento = new JPanel();
    JButton botaoConsultaAlimento = new JButton();
    JTextField txtConsultaAlimento = new JTextField();

    JScrollPane tabela = new JScrollPane();
    DefaultTableModel modelTabela = new DefaultTableModel();
    JTable tabelaAlimentos = new JTable(modelTabela);

    JLabel[] label = new JLabel[numLabel];
    TitledBorder[] title = new TitledBorder[numLabel];
    JFormattedTextField[] txtdook = new JFormattedTextField[numTxtdook];
    JButton[] button = new JButton[numButton];

    DecimalFormat df = new DecimalFormat("#.##");

    private MainFrame mainFrame;

    boolean consultado;

    ArrayList<PlanoAlimento> listaPlanoAlimentos;

    public FramePlanoAlimento(MainFrame mainFrame){
        this.mainFrame = mainFrame;

        // Elementos
        inicializarComponentes();

        // Botões
        clicarTabela();

        adicionar();
        desmarcarPlano();
        limpar();
        deletar();
        botaoAtualizarTabela();
        voltar();

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
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);

    }

    // PANEL
    public JPanel mainPanel(){
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
        mainPanel.add(tabela, gbcCentral);

        //voltar
        gbcCentral.gridx = 0;
        gbcCentral.gridy = 6;
        gbcCentral.weighty = 0.05;
        mainPanel.add(backPanel, gbcCentral);

        return mainPanel;
    }
    public JPanel dadosPanel(){
        dadosPanel.setBackground(verdeClaro);
        dadosPanel.setBorder(loweredbevel);
        //dadosPanel.setPreferredSize(new Dimension(400, 160));
        //layout
        dadosPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcDados = new GridBagConstraints();
        Insets insets = new Insets(1, 1, 1, 1);
        gbcDados.weightx = 0.5;
        gbcDados.gridwidth = 1;
        gbcDados.gridheight = 1;
        gbcDados.fill = GridBagConstraints.BOTH; // Permite preencher horizontalmente
        gbcDados.anchor = GridBagConstraints.CENTER;

        ////////////////////////
        // GRID DO PAINEL DADOS

        //nome
        dadosPanel.add(consultaPaciente, configurarConstraints(gbcDados,0,0,2,1,0.7,1, insets));

        //sexo
        dadosPanel.add(label[2], configurarConstraints(gbcDados,2,0,1,1,0.3,1, insets));

        //idade
        dadosPanel.add(label[3], configurarConstraints(gbcDados,3,0,1,1,0.3,1, insets));

        //imc
       dadosPanel.add(label[4], configurarConstraints(gbcDados,0,1,2,1,0.7,1, insets));

        //peso
        dadosPanel.add(label[5], configurarConstraints(gbcDados,2,1,1,1,0.3,1, insets));

        //altura
        dadosPanel.add(label[6], configurarConstraints(gbcDados,3,1,1,1,0.3,1, insets));

        //objetivo
        dadosPanel.add(label[7], configurarConstraints(gbcDados,0,2,2,1,0.5,1, insets));

        //atividade
        dadosPanel.add(label[8], configurarConstraints(gbcDados,2,2,2,1,0.5,1, insets));

        //TMB
        dadosPanel.add(label[9], configurarConstraints(gbcDados,0,3,2,1,0.5,1, insets));

        //GET
        dadosPanel.add(label[10], configurarConstraints(gbcDados,2,3,2,1,0.5,1, insets));

        return dadosPanel;
    }
    public JPanel imputPanel(){
        imputPanel.setOpaque(true);
        imputPanel.setBackground(verdeClaro);
        imputPanel.setBorder(loweredbevel);
        //imputPanel.setPreferredSize(new Dimension(400, 100));
        //layout
        imputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbcImput = new GridBagConstraints();

        ////////////////////////
        // GRID DO PAINEL IMPUT
        Insets insets = new Insets(1,5,5,5);
        //alimento
        imputPanel.add(label[12],Style.configurarConstraints(gbcImput,0,0,2,1,1,1,insets));

        //plano
        imputPanel.add(label[13],Style.configurarConstraints(gbcImput,2,0,2,1,insets));

        //nome
        imputPanel.add(consultaAlimento,Style.configurarConstraints(gbcImput,0,1,2,2,1,1,insets));

        //kcal adicionado
        imputPanel.add(label[15],Style.configurarConstraints(gbcImput,2,1,1,1,0.1,1,insets));
        //textdook
        imputPanel.add(txtdook[1],Style.configurarConstraints(gbcImput,3,1,1,1,0.9,1,insets));

        //calorias restantes
        imputPanel.add(label[17],Style.configurarConstraints(gbcImput,2,2,1,1,0.1,1,insets));
        //textdook
        imputPanel.add(txtdook[3],Style.configurarConstraints(gbcImput,3,2,1,1,0.9,1,insets));

        //gramas
        imputPanel.add(label[16],Style.configurarConstraints(gbcImput,0,3,1,1,0.1,1,insets));
        //textdook
        imputPanel.add(txtdook[2],Style.configurarConstraints(gbcImput,1,3,1,1,0.9,1,insets));

        //calorias totais
        imputPanel.add(label[19],Style.configurarConstraints(gbcImput,2,3,1,1,0.1,1,insets));
        //textdook
        imputPanel.add(txtdook[5],Style.configurarConstraints(gbcImput,3,3,1,1,0.9,1,insets));

        return imputPanel;
    }
    public JPanel buttonPanel(){
        buttonPanel.setOpaque(false);
        buttonPanel.setBackground(transparente);
        //buttonPanel.setPreferredSize(new Dimension(400, 30));
        buttonPanel.setLayout(new FlowLayout());

        ////////////////////////
        // GRID DO PAINEL BUTTON
        buttonPanel.add(button[1]); //limpar
        buttonPanel.add(button[2]); //deletar
        buttonPanel.add(button[0]); //salvar


        return buttonPanel;
    }
    public DefaultTableModel modelTabela(){
        String [] colunas = {"Nome do alimento", "Quantidade (g)", "Kcal/100g", "Calorias totais"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int coluna) {
                // Define o tipo de cada coluna
                return switch (coluna) {
                    case 0 -> String.class;    // Nome do alimento
                    case 1, 2, 3 -> Double.class; // Quantidade e Kcal/100g
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
    public JPanel backPanel(){
        backPanel.setBackground(transparente);
        //backPane.setPreferredSize(new Dimension(400, 30));
        backPanel.setLayout(new FlowLayout());
        backPanel.add(button[4]); //voltar
        backPanel.add(button[3]); //atualizar
        backPanel.add(button[5]); //Desmarcar Plano"

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
                FramePlanoAlimento.this.setVisible(false);
                FrameConsultaPlano frameConsultaPlano = new FrameConsultaPlano((JFrame) FramePlanoAlimento.this);
                frameConsultaPlano.setVisible(true);
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
    public JPanel consultaAlimento(){
        try {
            ImageIcon lupa = new ImageIcon("src/images/consultaIcon.png");
            botaoConsultaAlimento.setIcon(lupa);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        botaoConsultaAlimento.setOpaque(true);
        botaoConsultaAlimento.setBackground(verdeEscuro);
        botaoConsultaAlimento.setBorder(loweredbevel);
        botaoConsultaAlimento.setSize(24,24);
        botaoConsultaAlimento.setFocusable(false);

        botaoConsultaAlimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FramePlanoAlimento.this.setVisible(false);
                FrameConsultaAlimento frameConsultaAlimento = new FrameConsultaAlimento((JFrame) FramePlanoAlimento.this);
                frameConsultaAlimento.setVisible(true);
            }
        });

        txtConsultaAlimento.setEditable(false);
        txtConsultaAlimento.setFocusable(false);
        txtConsultaAlimento.setText("");

        consultaAlimento.setBackground(transparente);
        consultaAlimento.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        consultaAlimento.add(txtConsultaAlimento, configurarConstraints(c,0,0, 1,1,0.95,1, new Insets(5,5,5,5)));
        consultaAlimento.add(botaoConsultaAlimento, configurarConstraints(c,1,0,1,1,0.05,1, new Insets(5,5,5,5)));

        return consultaAlimento;
    }

    // OUTROS ELEMENTOS
    public void label(){
        //INICIANDO
        for (int i = 0; i < numLabel; i++) {
            label[i] = new JLabel();
            label[i].setFont(FONT_ITALIC_10);
            label[i].setHorizontalAlignment(SwingConstants.LEFT);
        }

        for (int i = 0; i < numLabel; i++) {
            title[i] = BorderFactory.createTitledBorder(etched, "");
            title[i].setTitleFont(FONT_BOLD_10);
        }

        //TITULOS
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
        for(int i=1;i<10;i++) {
            int j = i+1;
            label[j].setBorder(title[i]);
        }

        label[2].setText(" ");
        label[3].setText(" ");
        label[4].setText(" ");
        label[5].setText(" ");
        label[6].setText(" ");
        label[7].setText(" ");
        label[8].setText(" ");
        label[9].setText(" ");
        label[10].setText(" ");

        //OUTROS LABEL

        label[0].setText("Montagem do Plano Alimentar");
        label[0].setForeground(Color.WHITE);  // Cor do texto
        label[0].setFont(FONT_BOLD_12);
        label[0].setHorizontalAlignment(SwingConstants.CENTER);
        label[0].setOpaque(true);
        label[0].setBackground(verdeEscuro);
        label[0].setBorder(loweredbevel);

        label[11].setText("REGULAR");

        label[12] = Style.titulo2("ALIMENTO");

        label[13] = Style.titulo2("PLANO");

        label[14].setText("Nome");
        label[15].setText("Calorias adicionadas");
        label[16].setText("Quantidade (g)");
        label[17].setText("Calorias restantes");
        label[18].setText("kcal/100g");
        label[19].setText("Calorias necessárias");

        label[20] = Style.titulo("Dados do Paciente");
    }
    public void txtdook(){
        for (int i=0;i<numTxtdook;i++){
            txtdook[i] = new JFormattedTextField();
            //txtdook[i].setPreferredSize(new Dimension(50, 30));
        }

        txtdook[1].setEditable(false);
        txtdook[1].setFocusable(false);

        txtdook[3].setEditable(false);
        txtdook[3].setFocusable(false);

        txtdook[5].setEditable(false);
        txtdook[5].setFocusable(false);
    }
    public void button(){
        for (int i = 0; i < numButton; i++) {
            button[i] = new JButton();
            //button[i].setPreferredSize(new Dimension(150, 20));
        }
        button[0].setText("Salvar");
        button[1].setText("Limpar alimento");
        button[2].setText("Remover");
        button[3].setText("Atualizar tabela");
        button[4].setText("Voltar");
        button[5].setText("Desmarcar Plano");
    }

    // AÇÕES
    public void inicializarComponentes(){
        txtdook();
        label();
        button();

        mainPanel = mainPanel();
        dadosPanel = dadosPanel();
        imputPanel = imputPanel();
        buttonPanel = buttonPanel();
        backPanel = backPanel();
        tabela = tabela();
        modelTabela = modelTabela();
        tabelaAlimentos.setModel(modelTabela);
        consultaPaciente = consultaPaciente();
        consultaAlimento = consultaAlimento();
    }
    public void clicarTabela(){
        tabelaAlimentos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tabelaAlimentos.getSelectedRow();


                    // Verifica se uma linha válida foi selecionada
                    if (selectedRow >= 0 && listaPlanoAlimentos !=null) {
                        PlanoAlimento planoAlimentoSelecionado = listaPlanoAlimentos.get(selectedRow);

                        buscarAlimento(
                                planoAlimentoSelecionado.getAlimento().getCodAlimento(),
                                planoAlimentoSelecionado.getAlimento().getNomeComida(),
                                planoAlimentoSelecionado.getAlimento().getKcal100(),
                                planoAlimentoSelecionado.getGramasComida()

                        );
                        consultado = true;
                    }


                }
            }
        });
    }
    public void limparTudo(){
        this.gramasComida = 0;
        this.kcal100 = 0;
        this.kcalComida = 0;
        this.codComida = 0;
        this.codPlano = 0;
        this.txtConsultaAlimento.setText("");
        this.txtConsultaPaciente.setText("");
        label[2].setText(" ");
        label[3].setText(" ");
        label[4].setText(" ");
        label[5].setText(" ");
        label[6].setText(" ");
        label[7].setText(" ");
        label[8].setText(" ");
        label[9].setText(" ");
        label[10].setText(" ");
        txtdook[1].setText("");
        txtdook[3].setText("");
        txtdook[2].setText("");
        txtdook[5].setText("");
    }
    public void limparAlimento(){
        this.gramasComida = 0;
        this.kcal100 = 0;
        this.kcalComida = 0;
        this.codComida = 0;
        this.txtConsultaAlimento.setText("");
        txtdook[2].setText("");
    }
    public void fecharJanela(){
        this.dispose();
        if (mainFrame != null) {
            mainFrame.setVisible(true); // Exibe o mainFrame novamente
        }
    }
    public void buscarPlano(String nome, String sexo, int idade, double IMC, double altura, double peso, String atividade, double TMB, double GET, double kcalNecessarias, String objetivo, int codPlano) {
        this.codPlano = codPlano;
        this.txtConsultaPaciente.setText(nome);
        label[2].setText(sexo);
        label[3].setText(String.valueOf(idade));
        label[4].setText(String.valueOf(IMC));
        label[5].setText(String.valueOf(peso));
        label[6].setText(String.valueOf(altura));
        label[7].setText(objetivo);
        label[8].setText(atividade);
        label[9].setText(String.valueOf(TMB));
        label[10].setText(String.valueOf(GET));
        txtdook[5].setText(String.valueOf(df.format(kcalNecessarias)));

        atualizarTabela();
    }
    public void buscarAlimento(int codComida, String nomeComida, double kcal100) {
        this.codComida = codComida;
        this.kcal100 = kcal100;
        this.txtConsultaAlimento.setText(nomeComida);
        this.txtdook[2].setText("");

    }
    public void buscarAlimento(int codComida, String nomeComida, double kcal100, double quantidade) {
        this.codComida = codComida;
        this.kcal100 = kcal100;
        this.txtConsultaAlimento.setText(nomeComida);
        this.txtdook[2].setText(String.valueOf(quantidade));

    }
    public void atualizarTabela(){
        modelTabela.setRowCount(0);
        PlanoAlimentoController planoAlimentoController = new PlanoAlimentoController();
        try {
            listaPlanoAlimentos = planoAlimentoController.listarPlanoAlimentos(this.codPlano);
            listaPlanoAlimentos.forEach((PlanoAlimento planoAlimento) -> {
                modelTabela.addRow(new Object[]{
                        planoAlimento.getAlimento().getNomeComida(),
                        planoAlimento.getGramasComida(),
                        planoAlimento.getAlimento().getKcal100(),
                        planoAlimento.getKcalComida()
                });
            });

            double caloriasAdicionadas = planoAlimentoController.somarCaloriasPlano(codPlano);
            txtdook[1].setText(String.valueOf(df.format(caloriasAdicionadas)));
            double caloriasTotais = Conversao.converterDouble(txtdook[5]);
            double caloriasRestantes = caloriasTotais - caloriasAdicionadas;
            txtdook[3].setText(String.valueOf(df.format(caloriasRestantes)));

        } catch (ExceptionDAO evt) {
            Logger.getLogger(FramePlanoAlimento.class.getName()).log(Level.SEVERE, null, evt);
        }
    }

    // BOTOES
    public void adicionar(){
        button[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlanoAlimentoController planoAlimentoController = new PlanoAlimentoController();

                gramasComida = Conversao.converterDouble(txtdook[2]);

                try {
                    kcalComida = PlanoAlimento.calcularKcalComida(gramasComida, kcal100);
                } catch (IllegalArgumentException evt) {
                    System.out.println("Erro: " + evt.getMessage());
                }

                System.out.println("quantidade: " +gramasComida);
                System.out.println("kcal 100: " +kcal100);
                System.out.println("kcal da comida: " +kcalComida);
                System.out.println("id plano: " +codPlano);
                System.out.println("id comida: " +codComida);

                boolean sucesso;

                try {
                    if(consultado == false) {
                        sucesso = planoAlimentoController.montarPlanoAlimento(codComida,codPlano,kcalComida,gramasComida);
                    } else {
                        sucesso = planoAlimentoController.alterarPlanoAlimento(codComida,codPlano,kcalComida,gramasComida);
                    }


                    if(sucesso){
                        JOptionPane.showMessageDialog(null,"O plano foi cadastrado com sucesso!");
                        limparAlimento();
                        atualizarTabela();
                        consultado = false;
                    } else {
                        JOptionPane.showMessageDialog(null,"Os campos não foram preenchidos corretamente.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Erro: " + ex.getMessage());
                }

            }
        });
    }
    public void limpar(){
        button[1].addActionListener(e -> limparAlimento());
    }
    public void desmarcarPlano(){
        button[5].addActionListener(e -> limparTudo());
    }
    public void deletar(){
        button[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean sucesso;
                PlanoAlimentoController planoAlimentoController = new PlanoAlimentoController();
                try {
                    sucesso = planoAlimentoController.apagarPlanoAlimento(FramePlanoAlimento.this.codComida, FramePlanoAlimento.this.codPlano);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(null, "Alimento apagado com sucesso!");
                        FramePlanoAlimento.this.limparAlimento();
                        FramePlanoAlimento.this.atualizarTabela();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao apagar o alimento. Por favor, selecione um alimento!");
                    }
                } catch (ExceptionDAO ex) {
                    Logger.getLogger(FrameAlimento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public void botaoAtualizarTabela(){
        button[3].addActionListener(e -> atualizarTabela());
    }
    public void voltar(){
        button[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                FramePlanoAlimento.this.setVisible(false);

            }
        });
    }
}
