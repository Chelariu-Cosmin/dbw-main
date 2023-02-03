package com.software.application.ui.views.user;

import com.software.application.MainLayout;
import com.software.application.data.entity.User;
import com.software.application.data.service.UserService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.form.factory.DefaultCrudFormFactory;
import org.vaadin.crudui.layout.impl.HorizontalSplitCrudLayout;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@PageTitle("AddUser")
@Route(value = "adduser", layout = MainLayout.class)
@RouteAlias(value = "adduser", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@Uses(Icon.class)
public class AddUserView extends VerticalLayout {

    public AddUserView(UserService userService) {

        DefaultCrudFormFactory<User> formFactory = new DefaultCrudFormFactory<User> (User.class) {
            @Override
            protected void configureForm(FormLayout formLayout, List<HasValueAndElement> fields) {
                Component nameField = (Component) fields.get (0);
                formLayout.setColspan (nameField, 2);
            }
        };
//        formFactory.setUseBeanValidation(true);
//        formFactory.setVisibleProperties(
//                "name","birthDate","email","salary","phoneNumber","maritalStatus","groups","active","mainGroup");
//        formFactory.setVisibleProperties(
//    CrudOperation.ADD,
//            "name","birthDate","email","salary","phoneNumber","maritalStatus","groups","active","mainGroup",
//            "password");
//        formFactory.setFieldProvider("mainGroup",
//                new ComboBoxProvider<>(groupService.findAll()));
//        formFactory.setFieldProvider("groups",
//                new CheckBoxGroupProvider<>(groupService.findAll()));
//        formFactory.setFieldProvider("groups",
//                new CheckBoxGroupProvider<>("Groups",groupService.findAll(),Group::getName));
//        formFactory.setFieldProvider("mainGroup",
//                new ComboBoxProvider<>("Main Group",groupService.findAll(),new TextRenderer<>(Group::getName),Group::getName));

        // crud instance
        GridCrud<User> crud = new GridCrud<> (User.class, new HorizontalSplitCrudLayout (), formFactory);
        crud.setClickRowToUpdate (true);
        crud.setUpdateOperationVisible (false);

        // grid configuration
//        crud.getGrid ().
//
//            setColumns ("name", "birthDate", "maritalStatus", "email", "phoneNumber", "active");
        crud.getGrid ().

            setColumnReorderingAllowed (true);

        // layout configuration
        setSizeFull ();

        add (crud);

        // logic configuration
//        crud.setOperations(
//                ()->userService.findAll(),
//    user ->userService.save(user),
//    user ->userService.save(user),
//    user ->userService.delete(user)
//            );
    }
}
