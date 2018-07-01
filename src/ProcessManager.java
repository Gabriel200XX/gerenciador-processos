import java.util.Scanner;

public class ProcessManager {
    private int contClicloExec;
    private int quantum;
    private static int QUANTUM;

    private int processoAtual;
    private String nomeProcessoAtual;
    private String cargaProcessoAtual;
    private Process processoPronto;
    private Process processoExecucao;
    private Process processoEspera;
    private Process processoFinalizado;

    public Process createProcess() {
        return new Process();
    }

    public void addFilaPronto(Process process) {
        this.processoPronto = process;
        this.processoAtual = this.processoPronto.getpIdProcesso();
        this.nomeProcessoAtual = this.processoPronto.getInfoNomeProcesso();
        this.cargaProcessoAtual = this.processoPronto.getCargaTrabalho();
    }

    public void setQUANTUM(int QUANTUM) {
        ProcessManager.QUANTUM = QUANTUM;
        this.quantum = QUANTUM;
    }

    public void process(){
        Scanner seguinte = new Scanner(System.in);
        while (true) {
            if (processoPronto != null && processoExecucao == null) {
                showProcess();
                processoExecucao = processoPronto;
                System.out.println("Carga de trabalho: " + processoExecucao.getCargaTrabalho());
                System.out.println("Próxima ação: " + "Selecionar um processo para executar");
            } else if (processoPronto != null && processoExecucao != null) {
                processoPronto = null;
                showProcess();
                System.out.println("Carga de trabalho: " + processoExecucao.getCargaTrabalho());
                System.out.println("Próxima ação: " + "Executar um ciclo de CPU");
                contClicloExec++;
            } else if (processoExecucao != null) {
                if (quantum > 0) {
                    processoExecucao.execCargaTrabalho();
                    showProcess();
                    System.out.println("Carga de trabalho: " + processoExecucao.getCargaTrabalho());
                    System.out.println("Próxima ação: " + "Executar um ciclo de CPU");
                    quantum--;
                    contClicloExec++;
                } else {
                    processoEspera = processoExecucao;
                }
            } else if (processoExecucao != null && processoEspera != null) {
                processoExecucao = null;
                showProcess();
                System.out.println("Carga de trabalho: " + processoExecucao.getCargaTrabalho());
                System.out.println("Próxima ação: " + "Aguardando um cliclo de E/S");
                contClicloExec++;
            } else if (processoEspera != null) {
                showProcess();
                System.out.println("Carga de trabalho: " + processoExecucao.getCargaTrabalho());
                System.out.println("Próxima ação: " + "Aguardando um cliclo de E/S");
                contClicloExec++;
            }
            System.out.println("Pressione qualquer Tecla para continuar");
            seguinte.nextLine();
        }
    }

    public void showProcess() {
        System.out.println("+------------------------------------------------------+");
        System.out.println("|               GERENCIADOR DE PROCESSOS               |");
        System.out.println("+------------------------------------------------------+");
        System.out.println("| Contador de ciclo de execução : " + contClicloExec);
        System.out.println("| Processos na fila de pronto (" + (processoPronto != null && processoExecucao == null ? 1 + ") : " + processoPronto.getpIdProcesso() : 0 + ") : -"));
        System.out.println("| Processo em execução : " + (processoExecucao != null ? processoExecucao.getpIdProcesso() : "-"));
        System.out.println("| Tempo restante : " + quantum);
        System.out.println("| Carga de trabalho : " + (processoExecucao != null ? processoExecucao.getCargaTrabalho() : "-"));
        System.out.println("| Processos esperando : " + (processoEspera != null ? processoEspera.getpIdProcesso() : "-"));
        System.out.println("| Carga de trabalho : " + (processoEspera != null ? processoEspera.getCargaTrabalho() : "-"));
        System.out.println("| Processos finalizados : " + (processoFinalizado != null ? processoFinalizado.getpIdProcesso() : "-"));
        System.out.println("+------------------------------------------------------+");
        System.out.println("Processo " + this.processoAtual + " " + this.nomeProcessoAtual + " / Estado Atual : " + (processoPronto != null && processoExecucao == null ? "Pronto" : processoExecucao != null ? "Executando" : "Sem status"));
    }
}
