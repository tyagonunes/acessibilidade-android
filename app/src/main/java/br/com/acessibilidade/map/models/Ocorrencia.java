package br.com.acessibilidade.map.models;

/**
 * Created by Tyago on 24/03/2018.
 */

public class Ocorrencia {

    double latitude;
    double longitude;
    String local;
    String pontoReferencia;
    String tipoOcorrencia;
    String descricao;

    public Ocorrencia(){

    }

    public Ocorrencia(double latitude, double longitude, String local, String pontoReferencia, String tipoOcorrencia, String descricao) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.local = local;
        this.pontoReferencia = pontoReferencia;
        this.tipoOcorrencia = tipoOcorrencia;
        this.descricao = descricao;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(String tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
