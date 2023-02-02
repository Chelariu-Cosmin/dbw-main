package com.software.application.views.persons;

import com.software.application.MainLayout;
import com.software.application.data.entity.Person;
import com.software.application.data.service.PersonService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Person")
@Route(value = "person", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@Uses(Icon.class)
public class PersonsView extends VerticalLayout {

    Grid<Person> grid = new Grid<> (Person.class);
    TextField filterText = new TextField();
    private final PersonService personService;
    PersonForm form;

    public PersonsView(PersonService PersonService) {
        this.personService = PersonService;
        addClassNames("person-view");
        setSizeFull();
        configureGrid ();

        form = new PersonForm ();
        form.setWidth ("25em");

        form.addListener (PersonForm.SaveEvent.class, this::savePerson);
        form.addListener (PersonForm.DeleteEvent.class, this::deletePerson);
        form.addListener (PersonForm.CloseEvent.class, e-> closeEditor ());

       // configureForm();
        FlexLayout content = new FlexLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setFlexShrink(0, form);
        content.addClassNames("content", "gap-m");
        content.setSizeFull();

        add(
                getToolbar(),
                content
        );

        updateList();
        closeEditor ();
        grid.asSingleSelect().addValueChangeListener(event ->
                editPerson (event.getValue()));
    }

//    private Component getContent() {
//
//        HorizontalLayout content = new HorizontalLayout (grid, form);
//        content.setFlexGrow (2,grid);
//        content.setFlexGrow (1,form);
//        content.addClassName ("content");
//        content.setSizeFull ();
//        return content;
//    }

    private void updateList() {
        grid.setItems (personService.findAll (filterText.getValue ()));
    }

    private void deletePerson(PersonForm.DeleteEvent event) {
        personService.deletePerson (event.getPerson ());
        updateList ();
        closeEditor ();
    }

    private void savePerson(PersonForm.SaveEvent event) {
        personService.addPerson (event.getPerson ());
        updateList ();
        closeEditor ();
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder ("Filter by name...");
        filterText.setClearButtonVisible (true);
        filterText.setValueChangeMode (ValueChangeMode.LAZY);
        filterText.addValueChangeListener (e-> updateList ());

        Button addPersonBtn = new Button ("Add Person");
        addPersonBtn.addClickListener (e -> addPerson ());

        HorizontalLayout toolbar = new HorizontalLayout (filterText, addPersonBtn);
        toolbar.addClassName ("toolbar");
        return toolbar;
    }

    private void addPerson() {
        grid.asSingleSelect ().clear ();
        editPerson (new Person ());
    }

    private void editPerson(Person person) {
        if (person == null) {
            closeEditor ();
        } else {
            form.setPerson (person);
            form.setVisible (true);
            addClassName ("editing");
        }
    }

    private void closeEditor() {
        form.setPerson (null);
        form.setVisible (false);
        removeClassName ("editing");
    }

    private void configureGrid() {
        grid.addClassName ("person-grid");
        grid.setSizeFull ();
        grid.setColumns ("firstName", "lastName", "email", "phone", "dateOfBirth", "occupation");
        grid.getColumns ().forEach(col ->col.setAutoWidth(true));
        //grid.asSingleSelect ().addValueChangeListener (e -> editPerson (e.getValue ()));

    }
}
