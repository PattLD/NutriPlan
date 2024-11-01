package nutriplan.model;

import java.util.Scanner;

class Plano {
    private int caloriasNecessarias;
    private int caloriasConsumidas;

    private Paciente paciente;

    public Plano(int caloriasNecessarias){
        this.caloriasNecessarias = caloriasNecessarias;
        this.caloriasConsumidas = 0;
    }

    public void montarDieta() {
        while (caloriasConsumidas < caloriasNecessarias) {
            Scanner ler=new Scanner(System.in);

            System.out.println("\n---- Adicionar Alimento ----");
            System.out.print("Nome do Alimento: ");
            String alimento=ler.nextLine();

            System.out.print("Quantidade (g): ");
            int quantidade = ler.nextInt();

            System.out.print("Calorias desse alimento: ");
            int calorias = ler.nextInt();
            ler.nextLine();

            caloriasConsumidas = caloriasConsumidas + calorias;

            System.out.println("Alimento adicionado: " + quantidade + "g de " + alimento + " (" + calorias + " cal)");
            System.out.println("Calorias restantes: " + (caloriasNecessarias - caloriasConsumidas));

            if (caloriasConsumidas >= caloriasNecessarias) {
                System.out.println("As calorias diárias foram atingidas!");
            }
        }
    }
}



