package Projet.hotel.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReservationTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @After
    public void tearDown() throws Exception {
        em.getTransaction().rollback();
        em.close();
        emf.close();
    }

    @Test
    public void testReservationCRUD() {
        // Create
        Reservation reservation = new Reservation("Room101", 150, 2, 2, 1, "Yes", new Date(), 3, 1);
        em.persist(reservation);

        // Read
        Reservation savedReservation = em.find(Reservation.class, reservation.getId());
        assertNotNull(savedReservation);
        assertEquals("Room101", savedReservation.getRoom());
        assertEquals(150, savedReservation.getPrice());
        // Add more assertions for other fields

        // Update
        savedReservation.setPrice(200);
        em.flush();

        Reservation updatedReservation = em.find(Reservation.class, reservation.getId());
        assertNotNull(updatedReservation);
        assertEquals(200, updatedReservation.getPrice());
        // Add more assertions for other fields

        // Delete
        em.remove(updatedReservation);
        em.flush();

        Reservation deletedReservation = em.find(Reservation.class, reservation.getId());
        assertEquals(null, deletedReservation);
    }
}
