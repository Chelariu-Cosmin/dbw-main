package com.software.application.ui.views.store;

import com.software.application.MainLayout;
import com.software.application.data.entity.dto.OrderDTO;
import com.software.application.data.entity.dto.ProductDTO;
import com.software.application.data.service.OrderService;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.annotation.security.PermitAll;
import java.text.DecimalFormat;
import java.util.Comparator;

@PageTitle("Store-Front")
@Route(value = "store", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class StoreFrontView extends VerticalLayout {

    public StoreFrontView(OrderService orderService) {

        GridCrud<OrderDTO> crud = new GridCrud<> (OrderDTO.class);
        Binder<OrderDTO> orderBinder = new Binder<>();
        final DecimalFormat decimalFormat = new DecimalFormat ();
        decimalFormat.setMaximumFractionDigits (2);
        decimalFormat.setMinimumFractionDigits (2);

        // grid configuration
        crud.getGrid ()
            .setColumns ("customerId", "createdDate", "totalPrice", "items");
        crud.getGrid ()
            .setColumnReorderingAllowed (true);

        //PRICE configuration
        crud.getGrid ()
            .addColumn (order -> decimalFormat.format (order.getTotalPrice ()) + " €")
            .setHeader ("Total Order")
            .setComparator (Comparator.comparing (OrderDTO::getTotalPrice))
            .setFlexGrow (0)
            .setKey ("order");


        // form configuration
        crud.getCrudFormFactory ()
            .setUseBeanValidation (true);
        crud.getCrudFormFactory ()
            .setVisibleProperties (
                    "customerId", "createdDate", "totalPrice", "items");
        crud.getCrudFormFactory ()
            .setVisibleProperties (
                    CrudOperation.ADD
                    , "customerId", "createdDate", "totalPrice", "items");

        add (crud);
        setSizeFull ();

        Grid<ProductDTO> items = new Grid<>();
        items.setSelectionMode (Grid.SelectionMode.MULTI);

//        crud.setOperations (
//                () -> iProduct.findByNameContainingIgnoreCase (filter.getValue ()),
//                iProduct::addProduct,
//                iProduct::update,
//                iProduct::delete);
//
//        filter.addValueChangeListener (e -> crud.refreshGrid ());
    }
}

//
//    private final OrderService orderService;
//    private final ProductService productService;
//
//
//    private Button saveButton = new Button("Save");
//
//    public StoreFrontView(OrderService orderService, ProductService productService) {
//        this.orderService = orderService;
////        add(firstName, lastName, email, phone, detailsOrder, saveButton);
////        saveButton.addClickListener(event -> saveOrder());
//        this.productService = productService;
//    }
//
//    protected void init(VaadinRequest request) {
//        FormLayout formLayout = new FormLayout();
//        TextField firstName = new TextField("First name");
//        formLayout.addComponentAsFirst (firstName);
//
//        TextField lastName = new TextField("Last name");
//        formLayout.addComponentAsFirst (lastName);
//
//        EmailField email = new EmailField ("Email");
//        formLayout.addComponentAsFirst (email);
//
//        TextField phone = new TextField("Phone");
//        formLayout.addComponentAsFirst (phone);
//
//        TextField detailsOrder = new TextField("Details");
//        formLayout.addComponentAsFirst (detailsOrder);
//
//        ComboBox<ProductDTO> productComboBox = new ComboBox<>("Product");
//        productComboBox.setItems (productService.findAllProducts ());
//        productComboBox.setItemLabelGenerator (ProductDTO::getName);
//
//        Button submitButton = new Button("Submit");
//        submitButton.addClickListener(event -> {
//            Order order = new Order();
//            orderService.addOrder (order);
//            Notification.show("Order created successfully");
//        });
//        formLayout.addComponentAsFirst (submitButton);
//
//        add (formLayout);
//    }



