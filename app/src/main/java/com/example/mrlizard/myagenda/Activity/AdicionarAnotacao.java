package com.example.mrlizard.myagenda.Activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mrlizard.myagenda.Helper.AnotaDao;
import com.example.mrlizard.myagenda.Model.Anotacao;
import com.example.mrlizard.myagenda.R;

import org.w3c.dom.Text;

public class AdicionarAnotacao extends AppCompatActivity {
    private TextInputEditText titulo;
    private TextInputEditText desc;
    private Anotacao anotacaos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_anotacao);
        titulo=findViewById(R.id.titul2);
        desc = findViewById(R.id.desc2);
        anotacaos =  (Anotacao) getIntent().getSerializableExtra("anotacaoselecionada");
        if (anotacaos!=null){
        titulo.setText(anotacaos.getTitulo());
        desc.setText(anotacaos.getDescricao());

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AnotaDao anotaDao = new AnotaDao(getApplicationContext());
        Anotacao anotacao = new Anotacao();
        if (item.getItemId()==R.id.salvarAnot) {
            if (!titulo.getText().toString().isEmpty()&& !titulo.getText().toString().equals("") && titulo.getText().toString().length() < 21) {
                anotacao.setTitulo(titulo.getText().toString());
                anotacao.setDescricao(desc.getText().toString());
                if (anotacaos != null) {
                    anotacao.setId(anotacaos.getId());
                    anotaDao.atualizar(anotacao);
                    Toast.makeText(getApplicationContext(), "Anotação atualizada com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    anotaDao.salvar(anotacao);
                    Toast.makeText(getApplicationContext(), "Anotação salva com sucesso!", Toast.LENGTH_LONG).show();
                }
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(), "Não é possível savar a anotação sem um título apropriado!", Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
