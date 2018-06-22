public class Process {
    private int contClicloExec;
    private int processosProntos;
    private int processoExec;
    private int tempoRestante;
    private int cargaTrabalho;
    private int processosEspera;
    private int processosFinalizados;

    private int pidProcesso;
    private String infoNomeProcesso;
    private int estadoAtual;
    private String infoCargaTrabalho;

    public int gsPidProcesso() {
        return this.pidProcesso++;
    }

    public void setInfoNomeProcesso(String infoNomeProcesso) {
        this.infoNomeProcesso = infoNomeProcesso;
    }

    public void setInfoCargaTrabalho(String infoCargaTrabalho) {
        this.infoCargaTrabalho = infoCargaTrabalho;
    }

    private void ShowProcess(){
        System.out.println("| Gerenciador de Processos ");
        System.out.println("|Contador de ciclo de execução : 0 ");
        System.out.println("|Processos na fila de pronto (1) : 12,");

/*
|Processo em execução : -
            | Tempo restante : -
            | Carga de trabalho : -
            |Processos esperando : -
            | Carga de trabalho : -
            |Processos finalizados : -
            ========================================================
    Processo 12 word Estado Atual : Pronto
    Carga de trabalho: ABBDABCCD
    Próxima ação: Selecionar um processo para executar
    Pressione qualquer Tecla para continuar*/
    }
}
