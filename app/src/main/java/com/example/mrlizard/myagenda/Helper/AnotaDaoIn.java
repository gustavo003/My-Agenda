package com.example.mrlizard.myagenda.Helper;

import com.example.mrlizard.myagenda.Model.Anotacao;

import java.util.List;

/**
 * Created by mrlizard on 07/11/19.
 */

public interface AnotaDaoIn {
    public void salvar(Anotacao objeto);
    public void deletar(Anotacao objeto);
    public void atualizar(Anotacao objeto);
    public List<Anotacao> listar();


}
