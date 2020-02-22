package com.example.mrlizard.myagenda.Helper;

import com.example.mrlizard.myagenda.Model.Lembrete;

import java.util.List;

/**
 * Created by mrlizard on 10/11/19.
 */

public interface LembreteDaoIn {
    public void salvar(Lembrete objeto);
    public void atualizar(Lembrete objeto);
    public void deletar(Lembrete objeto);
    public List<Lembrete> listar();


}
