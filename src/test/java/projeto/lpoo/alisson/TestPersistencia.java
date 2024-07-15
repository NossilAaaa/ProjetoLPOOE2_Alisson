/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.lpoo.alisson;

import org.junit.Test;
import projeto.lpoo.alisson.dao.PersistenciaJPA;

/**
 *
 * @author aliss
 */
public class TestPersistencia {
    @Test
    public void testeConexao(){
        PersistenciaJPA jpa = new PersistenciaJPA();
        if(jpa.conexaoAberta()){
            System.out.println("Conexão realizada com sucesso");
        } else {
            System.out.println("Falha ao conectar ao Banco");
        }
    }
}
