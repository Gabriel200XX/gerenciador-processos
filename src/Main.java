import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quantum;
        String nomeProcesso;
        String cargaTrabalho;
        boolean continua = true;

        while (continua) {
            generateHeader();

            ProcessManager processManager = new ProcessManager();
            Process process = processManager.createProcess();
            System.out.print("| Informe o quantum: ");
            quantum = scanner.nextInt();
            System.out.print("| Informe o nome do novo processo: ");
            nomeProcesso = scanner.next();
            System.out.print("| Informe a carga de trabalho: ");
            cargaTrabalho = scanner.next();
            process.setInfoNomeProcesso(nomeProcesso);
            process.setCargaTrabalho(cargaTrabalho);
            processManager.setQUANTUM(quantum);
            processManager.addFilaPronto(process);
            System.out.println("| Processo " + process.getpIdProcesso() + " Criado!");
            System.out.println("| Iniciando simulação de execução do processo...");

            continua = processManager.process();
            if (continua) {
                System.out.println("Deseja criar um novo processo (S/N)");
                continua = scanner.next().equals("S");
            }
        }
    }

    public static void generateHeader() {
        System.out.println("+------------------------------------------------------+");
        System.out.println("|               GERENCIADOR DE PROCESSOS               |");
        System.out.println("+------------------------------------------------------+");
    }
}