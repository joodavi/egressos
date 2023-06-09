package br.ufma.egressos.model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufma.egressos.model.repository.ContactRepository;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class ContactTest {
    @Autowired
    ContactRepository repository;

    @Test
    public void shouldSaveContact() {
        Contact contact = Contact.builder().name("Name 1").urlLogo("url_logo").build();

        Contact contactSaved = repository.save(contact);

        Assertions.assertNotNull(contactSaved);
        Assertions.assertEquals(contact.getName(), contactSaved.getName());
        Assertions.assertEquals(contact.getUrlLogo(), contactSaved.getUrlLogo());
    }

    @Test
    public void shouldSaveMultipleContact() {
        Contact contact1 = Contact.builder().name("Name 1").urlLogo("url_logo").build();
        Contact contact2 = Contact.builder().name("Name 2").urlLogo("url_logo").build();

        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);

        List<Contact> test = new ArrayList<>();
        test.addAll(contacts);

        Assertions.assertNotNull(test);
        Assertions.assertEquals(contacts.size(), test.size());
    }

    @Test
    public void shouldFindContactByName() {
        Contact contact = Contact.builder().name("Name 1").urlLogo("url_logo").build();

        Contact contactSaved = repository.save(contact);

        Contact contactResult = repository.findByName(contactSaved.getName());

        Assertions.assertNotNull(contactResult);
        Assertions.assertEquals(contactSaved.getName(), contactResult.getName());
    }

    @Test
    public void shouldUpdateContact() {
        Contact contact = Contact.builder().name("Name 1").urlLogo("url_logo").build();

        Contact contactSaved = repository.save(contact);

        contactSaved.setName("Name 2");

        Assertions.assertNotNull(contactSaved);
        Assertions.assertFalse(contactSaved.getName().equals("Name 1"));
        Assertions.assertEquals(contactSaved.getName(), "Name 2");
    }

    @Test
    public void shouldDeleteContact() {
        Contact contact = Contact.builder().name("Name 1").urlLogo("url_logo").build();

        Contact contactSaved = repository.save(contact);
        UUID id = contactSaved.getId();
        repository.deleteById(id);

        Optional<Contact> test = repository.findById(id);
        Assertions.assertTrue(test.isEmpty());
    }
}
