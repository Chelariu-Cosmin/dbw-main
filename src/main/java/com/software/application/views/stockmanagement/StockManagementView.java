package com.software.application.views.stockmanagement;

import com.software.application.MainLayout;
import com.software.application.data.dto.ProductDTO;
import com.software.application.data.entity.Stock;
import com.software.application.data.service.ProductService;
import com.software.application.data.service.StockService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.CrudOperationException;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.CheckBoxGroupProvider;
import org.vaadin.crudui.form.impl.field.provider.ComboBoxProvider;

import javax.annotation.security.PermitAll;

@PageTitle("Stock Management")
@Route(value = "stock-management/:stockID?/:action?(edit)", layout = MainLayout.class)
@PermitAll
public class StockManagementView extends VerticalLayout {

        public StockManagementView(StockService stockService, ProductService productService) {

        GridCrud<Stock> crud = new GridCrud<> (Stock.class);

        // grid configuration
        crud.getGrid().setColumns("name", "product", "quantity", "statusStock");
        crud.getGrid().setColumnReorderingAllowed(true);

        // form configuration
        crud.getCrudFormFactory().setUseBeanValidation(true);
        crud.getCrudFormFactory().setVisibleProperties(
                "name", "products", "product", "quantity", "statusStock");

        crud.getCrudFormFactory().setVisibleProperties(
                CrudOperation.ADD,
                "name", "product", "quantity", "statusStock");
        crud.getCrudFormFactory().setFieldProvider("product",
                new ComboBoxProvider<> (productService.findAllProducts ()));

        crud.getCrudFormFactory().setFieldProvider("product",
                new CheckBoxGroupProvider<> ("Product", productService.findAllProducts (), ProductDTO::getName));

        // layout configuration
        setSizeFull();
        add(crud);
        crud.setFindAllOperationVisible(false);

        // logic configuration
        crud.setOperations(
                stockService::findAll,
                stockService::update,
                stock -> {
                    if(stock.getId().equals(10L)) {
                        throw new CrudOperationException ("Simulated error.");
                    }
                    return stockService.update (stock);
                },
                stockService::delete
        );
    }
}

