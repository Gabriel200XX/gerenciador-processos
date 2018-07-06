import java.util.Scanner;

public class ProcessManager {
    private int contClicloExec;
    private int quantum;
    private static int QUANTUM;
    private boolean continua = true;

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

    public boolean process(){
        boolean retorno = true;
        Scanner seguinte = new Scanner(System.in);
        // Não se pode fazer uma só chamada do método showProcess(), pois a cada entrada no if, é feito um processamento diferente.
        while (continua) {
            if (processoPronto != null && processoExecucao == null && processoEspera == null) {
                showProcess();
                if(processoPronto.checkCargaTrabalhoCPU()) {
                    processoExecucao = processoPronto;
                    System.out.println("Carga de trabalho: " + processoExecucao.getCargaTrabalho());
                } else {
                    processoEspera = processoPronto;
                    System.out.println("Carga de trabalho: " + processoEspera.getCargaTrabalho());
                }
                System.out.println("Próxima ação: " + "Selecionar um processo para executar");
            } else if (processoPronto != null && processoExecucao != null) {
                processoPronto = null;
                showProcess();
                System.out.println("Carga de trabalho: " + processoExecucao.getCargaTrabalho());
                System.out.println("Próxima ação: " + "Executar um ciclo de CPU");
                contClicloExec++;
            } else if (processoPronto != null && processoEspera != null) {
                processoPronto = null;
                showProcess();
                System.out.println("Carga de trabalho: " + processoEspera.getCargaTrabalho());
                System.out.println("Próxima ação: " + "Aguardando um cliclo de E/S");
                contClicloExec++;
            } else if (processoExecucao != null || processoEspera != null) {
                if (processoExecucao != null) {
                    if (quantum > 0) {
                        if (!processoExecucao.getCargaTrabalho().equals("")) {
                            processoExecucao.execCargaTrabalho();
                            quantum--;
                            if (!processoExecucao.getCargaTrabalho().equals("") && processoExecucao.checkCargaTrabalhoCPU()) {
                                showProcess();
                                System.out.println("Carga de trabalho: " + processoExecucao.getCargaTrabalho());
                                System.out.println("Próxima ação: " + "Executar um ciclo de CPU");
                            } else if (processoExecucao.getCargaTrabalho().equals("")) {
                                processoFinalizado = processoExecucao;
                                processoExecucao = null;
                                showProcess();
                                System.out.println("Carga de trabalho: " + processoFinalizado.getCargaTrabalho());
                                System.out.println("Próxima ação: " + "Terminando");
                            } else {
                                processoEspera = processoExecucao;
                                processoExecucao = null;
                                showProcess();
                                System.out.println("Carga de trabalho: " + processoEspera.getCargaTrabalho());
                                System.out.println("Próxima ação: " + "Aguardar um ciclo de E/S");
                            }
                            contClicloExec++;
                        } else {
                            finishProcess();
                            System.out.println("Carga de trabalho: -");
                            System.out.println("Próxima ação: " + "O processo será excluído");
                        }
                    } else {
                        processoPronto = processoExecucao;
                        processoExecucao = null;
                        quantum = QUANTUM;
                        continue;
                        // showProcess();
                    }
                } else if (processoEspera != null) {
                    if (quantum > 0) {
                        if(!processoEspera.getCargaTrabalho().equals("")) {
                            processoEspera.execCargaTrabalho();
                            quantum--;
                            if (!processoEspera.getCargaTrabalho().equals("") && !processoEspera.checkCargaTrabalhoCPU()) {
                                showProcess();
                                System.out.println("Carga de trabalho: " + processoEspera.getCargaTrabalho());
                                System.out.println("Próxima ação: " + "Aguardar um ciclo de E/S");
                            } else if (processoEspera.getCargaTrabalho().equals("")) {
                                processoFinalizado = processoEspera;
                                processoEspera = null;
                                showProcess();
                                System.out.println("Carga de trabalho: " + processoFinalizado.getCargaTrabalho());
                                System.out.println("Próxima ação: " + "Terminando");
                            } else {
                                processoPronto = processoEspera;
                                processoEspera = null;
                                showProcess();
                                System.out.println("Carga de trabalho: " + processoPronto.getCargaTrabalho());
                                System.out.println("Próxima ação: " + "Executar um ciclo de CPU");
                            }
                            contClicloExec++;
                        } else {
                            finishProcess();
                            System.out.println("Carga de trabalho: -");
                            System.out.println("Próxima ação: " + "O processo será excluído");
                        }
                    } else {
                        processoPronto = processoEspera;
                        processoEspera = null;
                        quantum = QUANTUM;
                        continue;
                        // showProcess();
                    }
                }
            } else {
                String valor;
                System.out.println("+======================================================+");
                System.out.println("Processo excluído");
                continua = false;
            }
            System.out.println("Pressione qualquer Tecla para continuar");
            seguinte.nextLine();
        }
        return retorno;
    }

    private void showProcess() {
        System.out.println("+------------------------------------------------------+");
        System.out.println("|               GERENCIADOR DE PROCESSOS               |");
        System.out.println("+------------------------------------------------------+");
        System.out.println("| Contador de ciclo de execução : " + contClicloExec);
        System.out.println("| Processos na fila de pronto (" + (processoPronto != null && processoExecucao == null ? 1 + ") : " + processoPronto.getpIdProcesso() : 0 + ") : -"));
        System.out.println("| Processo em execução : " + (processoExecucao != null ? processoExecucao.getpIdProcesso() : "-"));
        System.out.println("| Tempo restante : " + (processoPronto == null ? quantum : "-"));
        System.out.println("| Carga de trabalho : " + (processoExecucao != null && !processoExecucao.getCargaTrabalho().equals("") ? processoExecucao.getCargaTrabalho() : "-"));
        System.out.println("| Processos esperando : " + (processoEspera != null ? processoEspera.getpIdProcesso() : "-"));
        System.out.println("| Carga de trabalho : " + (processoEspera != null ? processoEspera.getCargaTrabalho() : "-"));
        System.out.println("| Processos finalizados : " + (processoFinalizado != null ? processoFinalizado.getpIdProcesso() : "-"));
        System.out.println("+------------------------------------------------------+");
        System.out.println("Processo " + this.processoAtual + " " + this.nomeProcessoAtual + " / Estado Atual : " + (processoPronto != null && processoExecucao == null ? "Pronto" : processoExecucao != null ? "Executando" : processoEspera != null ? "Esperando" : "Terminando"));
    }

    private void finishProcess() {
        if (processoExecucao != null) {
            processoFinalizado = processoExecucao;
        } else if (processoEspera != null) {
            processoFinalizado = processoEspera;
        }
        processoExecucao = null;
        processoEspera = null;
        showProcess();
    }
}
