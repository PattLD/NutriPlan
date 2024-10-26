package main;

public class Nutri {
    private String nome;
    private String objetivo;
    private int atividade;


    public Nutri(String nome, String objetivo, int atividade){
        this.nome=nome;
        this.objetivo=objetivo;
        this.atividade=atividade;
    }

    // getters
    public String getNome(){return nome;}
    public String getObjetivo(){return objetivo;}
    public int getAtividade(){return atividade;}

    //setters
    public void setNome(String nome){this.nome=nome;}
    public void setObjetivo(String objetivo){this.objetivo=objetivo;}
    public void setAtividade(int atividade){
        this.atividade=atividade;
    }

    public double calcularIMC(Paciente paciente){
        double altura = paciente.getAltura();
        double peso = paciente.getPeso();
        if(altura == 0){
            System.out.println("A Altura de paciente não pode ser zero!");
        }
        return peso / (altura * altura);
    }

    public double calcularBasal(Paciente paciente){
        double peso = paciente.getPeso();
        double altura = paciente.getAltura()*100;
        int idade = paciente.getIdade();

        if(paciente.getSexo().equalsIgnoreCase("masculino")){
            return 66 + (13.7*peso) + (5*altura) - (6.8*idade);
        }else{
            return 655 + (9.6*peso) + (1.8*altura) - (4.7*idade);
        }
    }

    public double calcularFatorAtividade(Paciente paciente, int atividade){
        double basal = calcularBasal(paciente);
        if(atividade==1){
            return calcularBasal(paciente)*1.2;
        }else if(atividade==2){
            return calcularBasal(paciente)*1.375;
        }else if(atividade==3){
            return calcularBasal(paciente)*1.55;
        }else{
            return calcularBasal(paciente)*1.725;
        }
    }



}
