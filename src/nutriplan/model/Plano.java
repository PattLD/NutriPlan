package nutriplan.model;

public class Plano {
    private int kcalNecessarias;
    private int kcalAdicionado;

    private String nomeComida;
    private double gramasComida;
    private double kcal100;
    private double kcalComida;

    private Paciente paciente;

    public Plano(String nomeComida, double gramasComida, double kcal100, double kcalComida) {
    }

    //GETTERS
    public int getKcalNecessarias() {
        return kcalNecessarias;
    }

    public int getKcalAdicionado() {
        return kcalAdicionado;
    }
    public String getNomeComida() {
        return nomeComida;
    }
    public double getGramasComida() {
        return gramasComida;
    }
    public double getKcal100() {
        return kcal100;
    }
    public double getKcalComida() {
        return kcalComida;
    }
    public Paciente getPaciente() {
        return paciente;
    }

    //SETTERS
    public void setKcalNecessarias(int kcalNecessarias) {
        this.kcalNecessarias = kcalNecessarias;
    }
    public void setKcalAdicionado(int kcalAdicionado) {
        this.kcalAdicionado = kcalAdicionado;
    }
    public void setNomeComida(String nomeComida) {
        this.nomeComida = nomeComida;
    }
    public void setGramasComida(double gramasComida) {
        this.gramasComida = gramasComida;
    }
    public void setKcal100(double kcal100) {
        this.kcal100 = kcal100;
    }
    public void setKcalComida(double kcalComida) {
        this.kcalComida = kcalComida;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void cadastrarPlano(Plano plano){

    }

    public void calcularKcalNecessarias(){

    }

//    public void montarDieta() {
//        while (kcalAdicionado < kcalNecessarias) {
//            Scanner ler=new Scanner(System.in);
//
//            System.out.println("\n---- Adicionar Alimento ----");
//            System.out.print("Nome do Alimento: ");
//            String alimento=ler.nextLine();
//
//            System.out.print("Quantidade (g): ");
//            int quantidade = ler.nextInt();
//
//            System.out.print("Calorias desse alimento: ");
//            int calorias = ler.nextInt();
//            ler.nextLine();
//
//            kcalAdicionado = kcalAdicionado + calorias;
//
//            System.out.println("Alimento adicionado: " + quantidade + "g de " + alimento + " (" + calorias + " cal)");
//            System.out.println("Calorias restantes: " + (kcalNecessarias - kcalAdicionado));
//
//            if (kcalAdicionado >= kcalNecessarias) {
//                System.out.println("As calorias diárias foram atingidas!");
//            }
//        }
//    }

}



