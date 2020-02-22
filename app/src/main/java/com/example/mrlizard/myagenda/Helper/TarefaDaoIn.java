package com.example.mrlizard.myagenda.Helper;

import com.example.mrlizard.myagenda.Model.Tarefa;

import java.util.List;

/**
 * Created by mrlizard on 07/11/19.
 */

public interface TarefaDaoIn {
    public void salvar(Tarefa objeto);
    public void deletar(Tarefa objeto);
    public void atualizar(Tarefa objeto);
    public List<Tarefa> listar();

}
