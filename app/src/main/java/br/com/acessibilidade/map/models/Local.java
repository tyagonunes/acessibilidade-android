package br.com.acessibilidade.map.models;

import java.util.Arrays;

/**
 * Created by Tyago on 26/03/2018.
 */

public class Local {

    private String _id;
    private String nome;
    private Double latitude;
    private Double longitude;
    private String descricao;
    private String tipo;
    private String criacao;
    private int _v;
    private String []acessos;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Local{" +
                "_id='" + _id + '\'' +
                ", nome='" + nome + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", descricao='" + descricao + '\'' +
                ", tipo='" + tipo + '\'' +
                ", criacao='" + criacao + '\'' +
                ", _v=" + _v +
                ", acessos=" + Arrays.toString(acessos) +
                '}';
    }
}
