package nutriplan.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static nutriplan.view.Style.*;

public class MainFrame extends JFrame {

    JPanel mainPanel = new JPanel();
    JPanel menuPanel = new JPanel();
    JPanel logoPanel = new JPanel();

    JLabel label[] = new JLabel[numLabel];
    JFormattedTextField txtdook[] = new JFormattedTextField[numTxtdook];
    JTextArea txtObjetivo = new JTextArea();
    JButton button[] = new JButton[numButton];

    public MainFrame(){

        // Elementos
        inicializarComponentes();

        cadastroPacientes();
        cadastroPlanos();
        montagemPlanos();
        cadastroAlimentos();

        // Frame
        this.setTitle("NutriPlan");
        this.setSize(700, 450);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setIconImage(getIcon().getImage());
        this.add(mainPanel(), BorderLayout.CENTER);
        this.setVisible(true);

    }

// ELEMENTOS
    public JPanel mainPanel(){
        //PAINEL CENTRAL
        mainPanel.setBackground(verde);
        mainPanel.setSize(700, 650);
        mainPanel.setBorder(PADDING);
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                loweredbevel,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        GridBagConstraints gbc = new GridBagConstraints();
        mainPanel.add(logoPanel, configurarConstraints(gbc,0,0,1,1,1,0.7, new Insets(5,5,5,5)));
        mainPanel.add(menuPanel, configurarConstraints(gbc,0,1,1,1,1,0.3, new Insets(5,6,5,6)));

        return mainPanel;
    }

    public JPanel logoPanel(){
        ImageIcon imagemLogo = getImagemLogo(0.5);
        JLabel labelLogo = new JLabel(imagemLogo);

        //PAINEL DO LOGO
        logoPanel.setBackground(transparente);
        logoPanel.setLayout(new BorderLayout());

        logoPanel.add(labelLogo, BorderLayout.CENTER);

        return logoPanel;
    }


    public JPanel menuPanel(){
        // PAINEL FORMS
        menuPanel.setBackground(transparente);
        menuPanel.setBorder(loweredbevel);
        //layout
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        menuPanel.add(button[0]); //Cadastro de Pacientes
        menuPanel.add(button[1]); //Cadastro de Planos
        menuPanel.add(button[2]); //Montagem de Planos
        menuPanel.add(button[3]); //Cadastro de Alimentos

        return menuPanel;
    }

    public void label(){
        for (int i = 0; i < numLabel; i++) {
            label[i] = new JLabel();
            label[i].setFont(FONT_REGULAR_12);
            label[i].setHorizontalAlignment(SwingConstants.LEFT);
        }
    }

    public void txtdook(){
        for (int i=0;i<numTxtdook;i++){
            txtdook[i] = new JFormattedTextField();
            //txtdook[i].setPreferredSize(new Dimension(50, 30));
        }
    }

    public void button(){
        for (int i = 0; i < numButton; i++) {
            button[i] = new JButton();
            //button[i].setPreferredSize(new Dimension(150, 20));
        }
        button[0].setText("Cadastro de Pacientes");
        button[1].setText("Cadastro de Planos");
        button[2].setText("Montagem de Planos");
        button[3].setText("Cadastro de Alimentos");
    }

// AÇÕES

    public void inicializarComponentes(){
        txtdook();
        label();
        button();

        mainPanel = mainPanel();
        logoPanel = logoPanel();
        menuPanel = menuPanel();

    }

    public void cadastroPacientes() {
        button[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.setVisible(false);
                FramePaciente framePaciente = new FramePaciente(MainFrame.this);
                framePaciente.setVisible(true);
            }
        });
    }
    public void cadastroPlanos(){
        button[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.setVisible(false);
                FramePlano framePlano = new FramePlano(MainFrame.this);
                framePlano.setVisible(true);
            }
        });
    }
    public void montagemPlanos(){
        button[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.setVisible(false);
                FramePlanoAlimento framePlanoAlimento = new FramePlanoAlimento(MainFrame.this);
                framePlanoAlimento.setVisible(true);
            }
        });
    }
    public void cadastroAlimentos(){
        button[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.setVisible(false);
                FrameAlimento frameAlimentos = new FrameAlimento(MainFrame.this);
                frameAlimentos.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {

        MainFrame mainFrame = new MainFrame();

    }
}