package com.example.mrlizard.myagenda.Helper;

import com.example.mrlizard.myagenda.Model.Evento;

import java.util.List;

/**
 * Created by mrlizard on 10/11/19.
 */

public interface EventoDaoIn {
    public void salvar(Evento objeto);
    public void atualizar(Evento objeto);
    public void deletar(Evento objeto);
    public List<Evento> listar();


}
