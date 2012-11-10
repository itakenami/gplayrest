package controllers;

import client.exception.ValidationException;
import client.request.FormParam;
import java.util.List;
import services.Analista;
import services.Cargo;

public class Analistas extends DefaultController {
    
    public static void index(){
        List<Analista> analistas = Analista.service.findAll();
        render(analistas);
    }


    public static void view(Long id) {
        if(id != null) {
            Analista analista = Analista.service.findById(id);
            if(analista!=null){
                render(analista);
            }else{
                flash.error("Analista não encontrado.");
                index();
            }
        }else{
            flash.error("É necessário informar um analista.");
            index();
        }
    }


    public static void delete(Long id) {
        
        if(Analista.service.delete(id)){
            flash.success("Registro apagado com sucesso.");
        } else {
            flash.error("Erro ao apagar registro.");
        }
        index();
        
    }
    
    
   public static void form(Long id) {

      List<Cargo> cargos = Cargo.service.findAll();
      if(id != null) {
          Analista analista = Analista.service.findById(id);
          if (analista != null) {
              render(analista,cargos);
          } else {
              flash.error("Registro não encontrado.");
              index();
          }
      }else{
          render(cargos);
      }
  }
  
  public static void save(Long id, Analista analistaVO){
      
      FormParam in_params = new FormParam("analista",analistaVO);
      
      try{
          Analista analista = Analista.service.save(id, in_params.getParams());
      }catch(ValidationException ex){
          Analista analista = analistaVO;
          if(id!=null){
              analista.id = id;
          }
          analistaVO = null;
          ex.addErrorIn(validation);
          render("@form", analista);
      }
      
      flash.success("Registro salvo com sucesso.");
      index();

    }
    
}
