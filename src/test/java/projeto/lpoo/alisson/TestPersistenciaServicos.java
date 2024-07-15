/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.lpoo.alisson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import projeto.lpoo.alisson.dao.PersistenciaJPA;
import projeto.lpoo.alisson.models.Animal;
import projeto.lpoo.alisson.models.Clientes;
import projeto.lpoo.alisson.models.Servicos;

/**
 *
 * @author aliss
 */
public class TestPersistenciaServicos {
    
    PersistenciaJPA jpa = new PersistenciaJPA();
    
    @Before
    public void setUp() {
        jpa.conexaoAberta();
    }

    @After
    public void tearDown() {
        jpa.fecharConexao();
    }
    
    @Test
    public void testePersistenciaServ() throws Exception{
        
        // Instância Cliente
        Clientes cliente = new Clientes();
        cliente.setNome("Robson A");
        cliente.setEndereco("Rua da Esperanca, 80");
        cliente.setTelefone("9090-8080");
        //inicializar a collection
        cliente.setAnimais(new ArrayList<>()); 
        
        jpa.persist(cliente);
        
        // Animal
        Animal animal = new Animal();
        animal.setNome("Miky");
        animal.setTipo("Cachorro");
        animal.setIdade(10);
        animal.setCliente(cliente);
        
        // Adiciona o animal na coleção de animais do cliente
        cliente.getAnimais().add(animal);
        
        jpa.persist(animal);
        
        // Servico
        Servicos servico = new Servicos();
        servico.setDescricao("Banho e tosa");
        servico.setValor(200.00);
        servico.setData(Calendar.getInstance());
        servico.setAnimal(animal);
        
        jpa.persist(servico);
        
        
        
    } 
}
