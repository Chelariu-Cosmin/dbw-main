package com.software.application.views.persons;

import com.software.application.data.entity.Person;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class PersonForm extends FormLayout {

    private Person person;

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    EmailField email = new EmailField("Email");
    TextField phone = new TextField("Phone");
    DatePicker dateOfBirth = new DatePicker ("DateOfBirth");
    TextField occupation = new TextField("Occupation");
    Binder<Person> binder = new BeanValidationBinder<> (Person.class);

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public PersonForm() {
        addClassName ("person-form");
        binder.bindInstanceFields (this);

        add(
                firstName,
                lastName,
                email,
                phone,
                dateOfBirth,
                occupation,
                createButtonLayout());
    }

    private HorizontalLayout createButtonLayout() {
        save.addThemeVariants (ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants (ButtonVariant.LUMO_ERROR);
        close.addThemeVariants (ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut (Key.ENTER);
        close.addClickShortcut (Key.ESCAPE);

        save.addClickListener (event-> validateAndSave());
        delete.addClickListener (event -> fireEvent (new DeleteEvent (this, person)));
        close.addClickListener (even -> fireEvent (new CloseEvent (this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout (save,delete,close);
    }

    public void setPerson(Person person){
        this.person = person;
        binder.readBean(person);
    }

    private void validateAndSave() {
        try {
            binder.writeBean (person);
            fireEvent (new SaveEvent (this, person));
        } catch (ValidationException e) {
            e.printStackTrace ();
        }
    }

    // Events
    public static abstract class PersonFormEvent extends ComponentEvent<PersonForm> {

        private final Person person;

        protected PersonFormEvent(PersonForm source, Person person) {
            super(source, false);
            this.person = person;
        }

        public Person getPerson() {
            return person;
        }
    }

    public static class SaveEvent extends PersonFormEvent {
        SaveEvent(PersonForm source, Person person) {
            super(source, person);
        }
    }

    public static class DeleteEvent extends PersonFormEvent {
        DeleteEvent(PersonForm source, Person person) {
            super(source, person);
        }

    }

    public static class CloseEvent extends PersonFormEvent {
        CloseEvent(PersonForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
