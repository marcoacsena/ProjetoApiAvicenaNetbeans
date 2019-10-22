
package br.com.apiavicena.model;

import java.io.Serializable;

/**
 *
 * @author Marco
 */
public class MedicoVO implements Serializable{

    private static final long serialVersionUID = -7520489788320701628L;
   
    private int codigoMedico;
    private String nomeMedico;
    private String crm;
    private String celMensagemMedico;
    private String celularMedico;
    private String emailMedico;
    private String cpfMedico;
    private String cnpjMedico;

    public MedicoVO() {
    }

    public MedicoVO(int codigoMedico, String nomeMedico, String crm, String celMensagemMedico, String celularMedico, String emailMedico, String cpfMedico, String cnpjMedico) {
        this.codigoMedico = codigoMedico;
        this.nomeMedico = nomeMedico;
        this.crm = crm;
        this.celMensagemMedico = celMensagemMedico;
        this.celularMedico = celularMedico;
        this.emailMedico = emailMedico;
        this.cpfMedico = cpfMedico;
        this.cnpjMedico = cnpjMedico;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getCelMensagemMedico() {
        return celMensagemMedico;
    }

    public void setCelMensagemMedico(String celMensagemMedico) {
        this.celMensagemMedico = celMensagemMedico;
    }

    public String getCelularMedico() {
        return celularMedico;
    }

    public void setCelularMedico(String celularMedico) {
        this.celularMedico = celularMedico;
    }

    public String getEmailMedico() {
        return emailMedico;
    }

    public void setEmailMedico(String emailMedico) {
        this.emailMedico = emailMedico;
    }

    public String getCpfMedico() {
        return cpfMedico;
    }

    public void setCpfMedico(String cpfMedico) {
        this.cpfMedico = cpfMedico;
    }

    public String getCnpjMedico() {
        return cnpjMedico;
    }

    @Override
    public String toString() {
        return "MedicoVO{" + "codigoMedico=" + codigoMedico + ", nomeMedico=" + nomeMedico + ", crm=" + crm + ", celMensagemMedico=" + celMensagemMedico + ", celularMedico=" + celularMedico + ", emailMedico=" + emailMedico + ", cpfMedico=" + cpfMedico + ", cnpjMedico=" + cnpjMedico + '}';
    }

    public void setCnpjMedico(String cnpjMedico) {
        this.cnpjMedico = cnpjMedico;
    }
    
}
