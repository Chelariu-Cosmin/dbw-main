package com.software.application.ui.views.about;

import com.software.application.MainLayout;
import com.software.application.data.entity.dto.CustomerDTO;
import com.software.application.data.service.CustomerService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.List;

@PageTitle("About")
@Route(value = "", layout = MainLayout.class)
@PermitAll
public class AboutView extends Div {

    public AboutView() {
    }
}
