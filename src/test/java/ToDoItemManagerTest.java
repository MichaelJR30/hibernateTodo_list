import org.junit.jupiter.api.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ToDoItemManagerTest {

    private ToDoItemManager manager;
    private EntityManager entityManager;

    @BeforeAll
    void setup() {
        manager = new ToDoItemManager();
        entityManager = HibernateUtil.getEntityManager();
    }

    @BeforeEach
    void cleanDatabase() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.createQuery("DELETE FROM Task").executeUpdate();
        transaction.commit();
    }

    @Test
    void testAddToDoItem() {
        manager.addToDoItem("JUnit Task");

        List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        assertEquals(1, tasks.size());
        assertEquals("JUnit Task", tasks.get(0).getTitle());
    }

    @Test
    void testRemoveToDoItem() {
        manager.addToDoItem("Task to Remove");

        List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        assertEquals(1, tasks.size());

        manager.removeFromDoItem(tasks.get(0).getId());

        List<Task> updatedTasks = entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        assertEquals(0, updatedTasks.size());
    }

    @Test
    void testViewToDoItems() {
        manager.addToDoItem("Task 1");
        manager.addToDoItem("Task 2");

        List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        assertEquals(2, tasks.size());
    }

    @AfterAll
    void tearDown() {
        HibernateUtil.close();
    }
}
