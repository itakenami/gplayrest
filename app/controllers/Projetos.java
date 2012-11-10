package controllers;

import client.exception.ValidationException;
import client.request.FormParam;
import java.util.List;
import services.Analista;
import services.Projeto;

public class Projetos extends DefaultController {
    
    public static void index(){
        List<Projeto> projetos = Projeto.service.findAll();
        render(projetos);
    }


    public static void view(Long id) {
        if(id != null) {
            Projeto projeto = Projeto.service.findById(id);
			
            if(projeto!=null){
                render(projeto);
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
        
        if(Projeto.service.delete(id)){
            flash.success("Registro apagado com sucesso.");
        } else {
            flash.error("Erro ao apagar registro.");
        }
        index();
        
    }
    
    
   public static void form(Long id) {

      List<Analista> analistas = Analista.service.findAll();
      if(id != null) {
          Projeto projeto = Projeto.service.findById(id);
          if (projeto != null) {
              render(projeto,analistas);
          } else {
              flash.error("Registro não encontrado.");
              index();
          }
      }else{
          render(analistas);
      }
  }
  
  public static void save(Long id, Projeto projetoVO){
      
      FormParam in_params = new FormParam("projeto",projetoVO);
      
      try{
          Projeto projeto = Projeto.service.save(id, in_params.getParams());
      }catch(ValidationException ex){
          
          Projeto projeto = projetoVO;
          if(id!=null){
              projeto.id = id;
          }
          projetoVO = null;
          ex.addErrorIn(validation);
          render("@form", projeto);
      }
      
      flash.success("Registro salvo com sucesso.");
      index();

    }
    
}
