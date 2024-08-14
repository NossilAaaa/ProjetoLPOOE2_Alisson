/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto.lpoo.alisson.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import projeto.lpoo.alisson.models.Animal;
import projeto.lpoo.alisson.models.Clientes;

/**
 *
 * @author aliss
 */
public class PersistenciaJPA implements InterfacePersistencia {
    
    EntityManager entity;
    EntityManagerFactory factory;

    public PersistenciaJPA() {
    factory = Persistence.createEntityManagerFactory("projeto.lpoo_ProjetoLPOOE1_Alisson_jar_1.0-SNAPSHOTPU");
    entity = factory.createEntityManager();
}


    @Override
    public Boolean conexaoAberta() {
        if (entity == null || !entity.isOpen()) {
            entity = factory.createEntityManager();
        }
        return entity.isOpen();
    }

    @Override
    public void fecharConexao() {
        if (entity != null && entity.isOpen()) {
            entity.close();
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        EntityManager em = getEntityManager();
        return em.find(c, id);//encontra um determinado registro 
    }

    public void persist(Object o) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }

    public EntityManager getEntityManager() {
        if (entity == null || !entity.isOpen()) {
            entity = factory.createEntityManager();
        }
        return entity;
    }
    
    @Override
    public void remover(Object o) throws Exception {
//        No método remover, antes de chamar remove, usamos merge se o objeto não estiver gerenciado.
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (!em.contains(o)) {
                o = em.merge(o); // Anexa o objeto ao contexto de persistência, se necessário
            }
            em.remove(o);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }
    
    public List<Animal> getAnimais() {
    EntityManager em = getEntityManager();
    try {
        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
        List<Animal> animais = query.getResultList();
        System.out.println("Consulta executada com sucesso.");
        System.out.println("Número de registros encontrados: " + animais.size());
        for (Animal a : animais) {
            System.out.println("Animal: " + a);
        }
        return animais;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

    public List<Clientes> getClientes(){
       EntityManager em = getEntityManager();
    try {
        TypedQuery<Clientes> query = em.createQuery("SELECT c FROM Clientes c", Clientes.class);
        List<Clientes> cliente = query.getResultList();
        System.out.println("Clientes encontrados: " + cliente.size());
        return cliente;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    } 
    }
}
