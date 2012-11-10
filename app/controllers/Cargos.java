package controllers;

import client.exception.ValidationException;
import client.request.FormParam;
import java.util.List;
import services.Cargo;

public class Cargos extends DefaultController {
    
    public static void index(){
        List<Cargo> cargos = Cargo.service.findAll();
        render(cargos);
    }

    public static void view(Long id) {
        if(id != null) {
            Cargo cargo = Cargo.service.findById(id);
            if(cargo!=null){
                render(cargo);
            }else{
                flash.error("Cargo não encontrado.");
                index();
            }
        }else{
            flash.error("É necessário informar um cargo.");
            index();
        }
    }


    public static void delete(Long id) {
        
        if(Cargo.service.delete(id)){
            flash.success("Registro apagado com sucesso.");
        } else {
            flash.error("Erro ao apagar registro.");
        }
        index();
    }
    
    
   public static void form(Long id) {

      if(id != null) {
          Cargo cargo = Cargo.service.findById(id);
          if (cargo != null) {
              render(cargo);
          } else {
              flash.error("Registro não encontrado.");
              index();
          }
      }else{
          render();
      }
  }
  
  public static void save(Long id, Cargo cargoVO){
      
      FormParam in_params = new FormParam("cargo",cargoVO);
      
      try{
          Cargo cargo = Cargo.service.save(id, in_params.getParams());
      }catch(ValidationException ex){
          Cargo cargo = cargoVO;
          if(id!=null){
              cargo.id = id;
          }
          cargoVO = null;
          ex.addErrorIn(validation);
          render("@form", cargo);
      }
      
      flash.success("Registro salvo com sucesso.");
      index();
    }
    
}
