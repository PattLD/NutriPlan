package backend;

public class Relatorio{

    public static void gerarRelatorio(Paciente paciente){
        System.out.println(" Relatório do backend.Paciente: ");
        paciente.mostrarDados();
        System.out.println(" Planos alimentares: ");

    }
}

