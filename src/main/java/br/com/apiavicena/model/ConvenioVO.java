
package br.com.apiavicena.model;

import java.io.Serializable;

/**
 *
 * @author Marco
 */
public class ConvenioVO implements Serializable{

    private static final long serialVersionUID = 5528232307395605305L;
    private int codigoConvenio;
    private String nomeConvenio;
    private String cnpjConvenio;
    private String valor;

    public ConvenioVO(int codigoConvenio, String nomeConvenio, String cnpjConvenio, String valor) {
        this.codigoConvenio = codigoConvenio;
        this.nomeConvenio = nomeConvenio;
        this.cnpjConvenio = cnpjConvenio;
        this.valor = valor;
    }

    public ConvenioVO() {
    }

    public int getCodigoConvenio() {
        return codigoConvenio;
    }

    public void setCodigoConvenio(int codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }

    public String getNomeConvenio() {
        return nomeConvenio;
    }

    public void setNomeConvenio(String nomeConvenio) {
        this.nomeConvenio = nomeConvenio;
    }

    public String getCnpjConvenio() {
        return cnpjConvenio;
    }

    public void setCnpjConvenio(String cnpjConvenio) {
        this.cnpjConvenio = cnpjConvenio;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ConvenioVO{" + "codigoConvenio=" + codigoConvenio + ", nomeConvenio=" + nomeConvenio + ", cnpjConvenio=" + cnpjConvenio + ", valor=" + valor + '}';
    }
}
