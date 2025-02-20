import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ToDoItemManager {

    public void addToDoItem(String title) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Task task = new Task(title);
            entityManager.persist(task);
            transaction.commit();
            System.out.println("Item added: " + title);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void removeFromDoItem(int id) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Task task = entityManager.find(Task.class, id);
            if (task != null) {
                entityManager.remove(task);
                transaction.commit();
                System.out.println("Item removed: " + id);
            } else {
                System.out.println("Item not found");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void viewToDoItems() {
        EntityManager entityManager = HibernateUtil.getEntityManager();

        try {
            List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();

            if (tasks.isEmpty()) {
                System.out.println("No items found");
            } else {
                for (Task task : tasks) {
                    System.out.println(task);
                }
            }
        } finally {
            entityManager.close();
        }
    }
}