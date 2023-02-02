package com.software.application.views.products;

import com.software.application.MainLayout;
import com.software.application.data.dto.ProductDTO;
import com.software.application.data.entity.Product;
import com.software.application.data.service.IProduct;
import com.software.application.data.service.ProductService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import javax.annotation.security.PermitAll;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.stream.Collectors;

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

        final DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMinimumFractionDigits(2);

        // grid configuration
        crud.getGrid().setColumns("codProduct", "name", "category", "description", "unitMeasure","availability");
        crud.getGrid().setColumnReorderingAllowed(true);

        //PRICE configuration
        crud.getGrid ().addColumn (product -> decimalFormat.format(product.getPrice()) + " €")
                .setHeader("Price")
                .setComparator(Comparator.comparing(ProductDTO::getPrice))
                .setFlexGrow(0).setKey("price");

// Add an traffic light icon in front of availability
        // Three css classes with the same names of three availability values,
        // Available, Coming and Discontinued, are defined in shared-styles.css
        // and are
        // used here in availabilityTemplate.
//        final String availabilityTemplate = "<iron-icon icon=\"vaadin:circle\" class-name=\"[[item.availability]]\"></iron-icon> [[item.availability]]";
//        crud.getGrid ().addColumn ((TemplateRenderer.<ProductDTO>of(availabilityTemplate)
//                                                                    .withProperty("availability",
//                                          product -> product.getAvailability().toString())))
//                .setHeader("Availability")
//                .setComparator(Comparator
//                        .comparing(ProductDTO::getAvailability))
//                .setTextAlign (ColumnTextAlign.END)
//                .setFlexGrow(1).setKey("availability");

        //stock config
        crud.getGrid ().addColumn(product -> product.getStockCount() == 0 ? "-"
                : Integer.toString(product.getStockCount()))
                .setHeader("Stock count")
                .setComparator(
                        Comparator.comparingInt(ProductDTO::getStockCount))
                .setTextAlign (ColumnTextAlign.CENTER)
                .setFlexGrow(1).setKey("stock");

        // form configuration
       crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getCrudFormFactory().setVisibleProperties(
                "codProduct", "name", "category", "description", "price", "unitMeasure, stockCount", "availability");
        crud.getCrudFormFactory().setVisibleProperties(
                CrudOperation.ADD
                , "name", "category", "description", "price", "unitMeasure", "stockCount", "availability");

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
