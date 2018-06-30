import java.util.Scanner;

public class Process {
    public static int PIDPROCESSO = 1;
    private int pidProcesso;
    private String infoNomeProcesso;
    private String cargaTrabalho;
    private int estadoAtual;

    public Process() {
        pidProcesso = PIDPROCESSO++;
    }

    public int getpIdProcesso() {
        return pidProcesso;
    }

    public void setInfoNomeProcesso(String infoNomeProcesso) {
        this.infoNomeProcesso = infoNomeProcesso;
    }

    public String getInfoNomeProcesso() {
        return infoNomeProcesso;
    }

    public void setCargaTrabalho(String infoCargaTrabalho) {
        this.cargaTrabalho = infoCargaTrabalho;
    }

    public String getCargaTrabalho() {
        return cargaTrabalho;
    }


}
