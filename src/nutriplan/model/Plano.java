package nutriplan.model;

class Plano {
    private int kcalNecessarias;
    private int kcalAdicionado;

    private String nomeComida;
    private int gramasComida;
    private int kcal100;
    private int kcalComida;

    private Paciente paciente;

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
    public int getGramasComida() {
        return gramasComida;
    }
    public int getKcal100() {
        return kcal100;
    }
    public int getKcalComida() {
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
    public void setGramasComida(int gramasComida) {
        this.gramasComida = gramasComida;
    }
    public void setKcal100(int kcal100) {
        this.kcal100 = kcal100;
    }
    public void setKcalComida(int kcalComida) {
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



