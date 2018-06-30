import java.util.Scanner;

public class ProcessManager {
    private int contClicloExec;
    private int tempoRestante;
    private int quantum;

    private int pidProcessoExecucao;
    private int pidProcessoFinalizado;

    private int processoAtual;
    private String nomeProcessoAtual;
    private String cargaProcessoAtual;
    private Process processoPronto;
    private Process processoExecucao;
    private Process processoEspera;

    public Process createProcess() {
        return new Process();
    }

    public void addFilaPronto(Process process) {
        this.processoPronto = process;
        this.processoAtual = this.processoPronto.getpIdProcesso();
        this.nomeProcessoAtual = this.processoPronto.getInfoNomeProcesso();
        this.cargaProcessoAtual = this.processoPronto.getCargaTrabalho();
    }

    public void showProcess(){
        Scanner seguinte = new Scanner(System.in);
        while (true) {
            System.out.println("| Contador de ciclo de execução : " + contClicloExec);
            System.out.println("| Processos na fila de pronto (" + (processoPronto != null ? 1 + ") : " + processoPronto.getpIdProcesso() : 0 + ") : -"));
            System.out.println("| Processo em execução : " + (processoExecucao != null ? processoExecucao.getpIdProcesso() : "-"));
            System.out.println("| Tempo restante : " + tempoRestante);
            System.out.println("| Carga de trabalho : " + (processoExecucao != null ? processoExecucao.getCargaTrabalho() : "-"));
            System.out.println("| Processos esperando : " + (processoEspera != null ? processoEspera.getpIdProcesso() : "-"));
            System.out.println("| Carga de trabalho : " + (processoEspera != null ? processoEspera.getCargaTrabalho() : "-"));
            System.out.println("| Processos finalizados : " + pidProcessoFinalizado);
            System.out.println("========================================================");
            System.out.println("Processo " + this.processoAtual + " " + this.nomeProcessoAtual + " / Estado Atual : " + (processoPronto != null ? "Pronto" : processoExecucao != null ? "Executando" : "Sem status"));
            if (processoPronto != null && processoExecucao == null) {
                System.out.println("Carga de trabalho: " + processoExecucao.getCargaTrabalho());
                System.out.println("Próxima ação: " + "Selecionar um processo para executar");
                processoExecucao = processoPronto;
                processoPronto = null;
            } else if(processoExecucao != null) {

            } else {
                contClicloExec++;
            }
            System.out.println("Pressione qualquer Tecla para continuar");
            seguinte.nextLine();
        }
    }
}
