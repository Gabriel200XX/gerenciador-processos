import java.util.Scanner;

public class Process {
    private static int PIDPROCESSO = 1;
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

    public boolean checkCargaTrabalhoCPU() {
        boolean retorno;
        if (this.cargaTrabalho.charAt(0) == 'A' || this.cargaTrabalho.charAt(0) == 'B') {
            retorno = true;
        } else {
            retorno = false;
        }
        return retorno;
    }

    public void execCargaTrabalho() {
        if (this.cargaTrabalho.charAt(0) == 'A' || this.cargaTrabalho.charAt(0) == 'C') {
            this.cargaTrabalho = this.cargaTrabalho.substring(1);
        } else if (this.cargaTrabalho.charAt(0) == 'B') {
            this.cargaTrabalho = this.cargaTrabalho.replaceFirst("B", "A");
        } else if (this.cargaTrabalho.charAt(0) == 'D') {
            this.cargaTrabalho = this.cargaTrabalho.replaceFirst("D", "C");
        }
    }

    public void setCargaTrabalho(String cargaTrabalho) {
        this.cargaTrabalho = cargaTrabalho;
    }

    public String getCargaTrabalho() {
        return cargaTrabalho;
    }


}
