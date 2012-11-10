package services;

import api.wadl.annotation.XMLCast;
import client.crud.Service;
import client.request.PlayRequest;
import com.google.gson.reflect.TypeToken;
import java.util.Date;
import java.util.List;
import java.util.Set;


@XMLCast(thisClassFrom="models.Projeto")
public class Projeto {


    public Long id;
    public String nome;
    public String descricao;
    public Date data_fim;
    public Date data_inicio;
    public Set<Analista> analistas;

    public static Service<Projeto> service = new Service<Projeto>(new PlayRequest("http://localhost:9001/api/projetos"), Projeto.class, new TypeToken<List<Projeto>>(){}.getType());
    
    @Override
    public String toString(){
        return nome;
    }
}
