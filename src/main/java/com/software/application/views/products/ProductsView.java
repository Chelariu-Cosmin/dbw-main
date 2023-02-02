package com.software.application.views.products;

import com.software.application.MainLayout;
import com.software.application.data.dto.ProductDTO;
import com.software.application.data.service.IProduct;
import com.software.application.data.service.ProductService;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.annotation.security.PermitAll;

@PageTitle("Product")
@Route(value = "product/:productsID?/:action?(edit)", layout = MainLayout.class)
@PermitAll
@Uses(Icon.class)
public class ProductsView extends VerticalLayout implements BeforeEnterObserver {

    public ProductsView(IProduct iProduct) {

        GridCrud<ProductDTO> crud = new GridCrud<> (ProductDTO.class);

        // additional components
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by name");
        filter.setClearButtonVisible(true);
        crud.getCrudLayout().addFilterComponent(filter);

        // grid configuration
        crud.getGrid().setColumns("codProduct", "name", "category", "description", "price", "unitMeasure");
        crud.getGrid().setColumnReorderingAllowed(true);

        // form configuration
       crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getCrudFormFactory().setVisibleProperties(
                "codProduct", "name", "category", "description", "price", "unitMeasure");
        crud.getCrudFormFactory().setVisibleProperties(
                CrudOperation.ADD
                , "name", "category", "description", "price", "unitMeasure");

        add (crud);
        setSizeFull ();

        crud.setOperations(
                () -> iProduct.findByNameContainingIgnoreCase(filter.getValue()),
                iProduct::addProduct,
                iProduct::update,
                iProduct::delete);

        filter.addValueChangeListener(e -> crud.refreshGrid());
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

    }
}
