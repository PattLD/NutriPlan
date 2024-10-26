package test;

import javax.swing.*;  // Pacote para os componentes da GUI (JFrame, JButton, JLabel, etc.)
import java.awt.*;     // Pacote para layouts e manipulação de gráficos
import java.awt.event.*;  // Pacote para manipulação de eventos (ActionListener)

public class Frame1 extends JFrame {
    
	private Paciente paciente;
	
	private String sexo;
	private String objetivo;
	private String atividade;
	private String nome;
	private int idade;
	private double peso;
	private double altura;
	
    
	Frame1(){
		int numLabel = 20; 
	    int numPanel = 10; 
	    int numButton = 5; 
	    int numTxtdook = 6;
		
		String[] genderStrings = { "", "Feminino", "Masculino" };
	    String[] exercisetrings = { "", "Sedentário", "Levemente ativo", "Moderamente ativo", "Muito ativo" };
		
		/* Instanciação */
        this.setTitle("NutriPlan");
        this.setSize(700, 650);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        

        JPanel panel[] = new JPanel[numPanel];
        for (int i = 0; i < numPanel; i++) {
            panel[i] = new JPanel();
            panel[i].setBackground(Color.LIGHT_GRAY);
            panel[i].setPreferredSize(new Dimension(60,60));
        }
        
        panel[4].setLayout(new GridBagLayout());
        
        JButton button[] = new JButton[numButton];
        for (int i = 0; i < numButton; i++) {
            button[i] = new JButton();
            button[i].setPreferredSize(new Dimension(150, 30)); 
        }
        button[0].setText("Cadastrar Paciente");
        
        JLabel label[] = new JLabel[numLabel];
        for (int i = 0; i < numLabel; i++) {
            label[i] = new JLabel();
        }
        
        JTextField txtdook[] = new JTextField[numTxtdook];
        for (int i=0;i<numTxtdook;i++){
            txtdook[i] = new JTextField();
            txtdook[i].setPreferredSize(new Dimension(400, 40));
        }
        
        JComboBox gender = new JComboBox(genderStrings);
        gender.setSelectedIndex(2);
        gender.setSelectedIndex(0);
        
        JComboBox exerciseFrequency = new JComboBox(exercisetrings);
        exerciseFrequency.setSelectedIndex(4);
        exerciseFrequency.setSelectedIndex(0);
        
        label[0].setText("Nome:");
        label[1].setText("Sexo:");
        label[2].setText("Idade:");
        label[3].setText("Peso:");
        label[4].setText("Altura:");
        label[5].setText("Objetivo:");
        label[6].setText("Nivel de atividade:");
        
        for(int i=0;i<7;i++){
            label[i].setFont(new Font("Courier", Font.BOLD, 12));
            label[i].setHorizontalAlignment(SwingConstants.RIGHT);
        }
        
        label[7].setText("Nutricionista João Pedro Bindá");
        label[7].setFont(new Font("Courier", Font.BOLD, 23)); 
        label[7].setHorizontalAlignment(SwingConstants.CENTER);  
        
        label[8].setText("Cadastro do paciente");
        label[8].setForeground(Color.WHITE);  // Cor do texto
        label[8].setFont(new Font("Courier", Font.BOLD, 12)); 
        label[8].setHorizontalAlignment(SwingConstants.CENTER); 
        label[8].setOpaque(true);
        label[8].setBackground(new java.awt.Color(158, 158, 158));
        label[8].setBorder(BorderFactory.createLoweredBevelBorder());
        label[8].setPreferredSize(new Dimension(200, 20));
        
        
        
        // Adicionar no panel central [4]

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.weightx = 0.5;  
        gbc.fill = GridBagConstraints.BOTH; // Permite preencher horizontalmente
        gbc.anchor = GridBagConstraints.CENTER;
        
        // NUTRICIONISTA
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        panel[4].add(label[7], gbc);

        // "Cadastro de Pacientes"
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        panel[4].add(label[8], gbc);

        gbc.gridwidth = 1; // Resetando para componentes que ocupam uma coluna
        
        gbc.weightx = 0.5;  
        gbc.fill = GridBagConstraints.HORIZONTAL; // Permite preencher horizontalmente
        gbc.anchor = GridBagConstraints.WEST;
        
        //COLUNA 0
        
        for (int j = 0; j < 7; j++) {
            gbc.gridx = 0;
            gbc.gridy = j + 2; 
            panel[4].add(label[j], gbc);
        }

        // CAIXAS DE SELEÇÃO
        gbc.gridx = 1; 

            // A linha 3 
        gbc.gridy = 3;
        panel[4].add(gender, gbc);

            // A linha 8 
        gbc.gridy = 8;
        panel[4].add(exerciseFrequency, gbc);

        // COLUNA 1
        for (int i = 2; i < 9; i++) {
            // Ignorar a linha 3 e 8 onde já adicionamos componentes especiais
            if (i == 3 || i == 8) {
                continue; 
            }

            gbc.gridx = 1; 
            gbc.gridy = i; 
            int txtIndex = i - 2; // Ajuste para corresponder ao índice de txtdook

            // Verifica se o índice está dentro do limite do array
            if (txtIndex < txtdook.length) {
                panel[4].add(txtdook[txtIndex], gbc); 
            }
        }
            
        // BOTAO

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;  
        gbc.gridheight = 1; 
        gbc.weightx = 0.5;  
        gbc.fill = GridBagConstraints.NONE; 
        gbc.anchor = GridBagConstraints.CENTER;
       
        panel[4].add(button[0], gbc);
        
        gbc.gridwidth = 1;
        
        
        
        this.add(panel[0], BorderLayout.NORTH);
        this.add(panel[1], BorderLayout.WEST);
        this.add(panel[2], BorderLayout.SOUTH);
        this.add(panel[3], BorderLayout.EAST);
        this.add(panel[4], BorderLayout.CENTER);
        
        this.setVisible(true);
        
        button[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	// Inclusão de dados
            	nome = txtdook[0].getText();
            	sexo = (String) gender.getSelectedItem();
            	idade = Integer.parseInt(txtdook[2].getText());
            	peso = Double.parseDouble(txtdook[3].getText());
            	altura = Double.parseDouble(txtdook[4].getText());
            	objetivo = txtdook[5].getText();
            	atividade = (String) exerciseFrequency.getSelectedItem();
            	
            	Paciente paciente=new Paciente(sexo, nome, idade, peso, altura, atividade, objetivo);
                
            	paciente.mostrarDados();
            	
                System.out.println("Taxa metabólica basal (TMB): " + paciente.calcularBasal());
                System.out.println("Gasto energético total: " + paciente.calcularFatorAtividade());
                System.out.println("IMC do paciente: " + paciente.calcularIMC());
                
                // Abrir nova janela
                Frame2 frame2 = new Frame2();
                frame2.setVisible(true);
                Frame1.this.setVisible(false);
                
            }
        });
		
	}
	
	public Paciente getPaciente() {
	    return paciente;
	}
}