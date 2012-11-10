/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import api.wadl.annotation.XMLCast;
import client.crud.Service;
import client.request.PlayRequest;
import com.google.gson.reflect.TypeToken;
import java.util.List;


/**
 *
 * @author itakenami
 */
@XMLCast(thisClassFrom="models.Cargo")
public class Cargo {
    
    public Long id;
    public String nome;
    public static Service<Cargo> service = new Service<Cargo>(new PlayRequest("http://localhost:9001/api/cargos"), Cargo.class, new TypeToken<List<Cargo>>(){}.getType());
    
    @Override
    public String toString(){
        return nome;
    }
    
}
