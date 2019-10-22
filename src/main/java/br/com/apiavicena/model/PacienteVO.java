package br.com.apiavicena.model;

import java.io.Serializable;

/**
 *
 * @author Marco
 */
public class PacienteVO implements Serializable{

    private static final long serialVersionUID = 4080481862936988306L;
          
    private int codigoPaciente;
    private String nomePaciente;
    private String celMensagemPaciente;
    private String foneResidencial;
    private String foneComercial;
    private String emailPaciente;
    private String cpfPaciente;
    private String cnpjPaciente;
    private String logradouro;
    private String numLogradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    
    public PacienteVO() {        
       
    }

    public int getCodigoPaciente() {
        return codigoPaciente;
    }

    public void setCodigoPaciente(int codigoPaciente) {
        this.codigoPaciente = codigoPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getCelMensagemPaciente() {
        return celMensagemPaciente;
    }

    public void setCelMensagemPaciente(String celMensagemPaciente) {
        this.celMensagemPaciente = celMensagemPaciente;
    }

    public String getFoneResidencial() {
        return foneResidencial;
    }

    public void setFoneResidencial(String foneResidencial) {
        this.foneResidencial = foneResidencial;
    }

    public String getFoneComercial() {
        return foneComercial;
    }

    public void setFoneComercial(String foneComercial) {
        this.foneComercial = foneComercial;
    }

    public String getEmailPaciente() {
        return emailPaciente;
    }

    public void setEmailPaciente(String emailPaciente) {
        this.emailPaciente = emailPaciente;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getCnpjPaciente() {
        return cnpjPaciente;
    }

    public void setCnpjPaciente(String cnpjPaciente) {
        this.cnpjPaciente = cnpjPaciente;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumLogradouro() {
        return numLogradouro;
    }

    public void setNumLogradouro(String numLogradouro) {
        this.numLogradouro = numLogradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "PacienteVO{" + "codigoPaciente=" + codigoPaciente + ", nomePaciente="
                + nomePaciente + ", celMensagemPaciente=" + celMensagemPaciente
                + ", foneResidencial=" + foneResidencial + ", foneComercial="
                + foneComercial + ", emailPaciente=" + emailPaciente + ", cpfPaciente="
                + cpfPaciente + ", cnpjPaciente=" + cnpjPaciente + ", logradouro="
                + logradouro + ", numLogradouro=" + numLogradouro + ", complemento="
                + complemento + ", bairro=" + bairro + ", cidade=" + cidade + ", uf="
                + uf + ", cep=" + cep + '}';
    }
    
}
