package com.example.mrlizard.myagenda.Model;

import java.io.Serializable;

/**
 * Created by mrlizard on 10/11/19.
 */

public class Lembrete implements Serializable {
    private Long id;
    private String titulo;
    private String descricao;
    private String data;
private String hora;
    public Long getId() {
        return id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
