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
        cliente.setAnimais(new ArrayList<>()); 
        
        jpa.persist(cliente);
        
        Clientes c = new Clientes();
        c.setNome("Henrique Almeida");
        c.setEndereco("Rua da ALegria, 280");
        c.setTelefone("9999-8888");
        c.setAnimais(new ArrayList<>()); 
        
        jpa.persist(c);
        
        // Animal
        Animal animal = new Animal();
        animal.setNome("Miky");
        animal.setTipo("Cachorro");
        animal.setIdade(10);
        animal.setCliente(cliente);
        
        jpa.persist(animal);
        
        cliente.getAnimais().add(animal);
        
        Animal animaal = new Animal();
        animaal.setNome("Firefox");
        animaal.setTipo("Cachorro");
        animaal.setIdade(5);
        animaal.setCliente(cliente);
        
        jpa.persist(animaal);
        
        cliente.getAnimais().add(animaal);
        
        Animal a = new Animal();
        a.setNome("Garfield");
        a.setTipo("Cachorro");
        a.setIdade(3);
        a.setCliente(c);
        
        jpa.persist(a);
        
        c.getAnimais().add(a);
        
        // Servico
        Servicos servico = new Servicos();
        servico.setDescricao("Banho e tosa");
        servico.setValor(200.00);
        servico.setData(Calendar.getInstance());
        servico.setAnimal(animal);
        
        jpa.persist(servico);
        
        Servicos servicoo = new Servicos();
        servicoo.setDescricao("Consulta com veterinário");
        servicoo.setValor(350.00);
        servicoo.setData(Calendar.getInstance());
        servicoo.setAnimal(animaal);
        
        jpa.persist(servicoo);
        
        Servicos serv = new Servicos();
        serv.setDescricao("Aplicação de Vacinas");
        serv.setValor(80.00);
        serv.setData(Calendar.getInstance());
        serv.setAnimal(a);
        
        jpa.persist(serv);
        
    } 
}
