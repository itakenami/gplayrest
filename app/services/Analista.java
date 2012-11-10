package services;

import api.wadl.annotation.XMLCast;
import client.crud.Service;
import client.request.PlayRequest;
import com.google.gson.reflect.TypeToken;
import java.util.List;


@XMLCast(thisClassFrom="models.Analista")
public class Analista {


    public Long id;
    public String nome;
    public String especialidade;
    public Cargo cargo;

    public static Service<Analista> service = new Service<Analista>(new PlayRequest("http://localhost:9001/api/analistas"), Analista.class, new TypeToken<List<Analista>>(){}.getType());
    
    @Override
    public String toString(){
        return nome;
    }
}
