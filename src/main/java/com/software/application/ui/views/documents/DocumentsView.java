package com.software.application.ui.views.documents;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.software.application.MainLayout;
import com.software.application.data.entity.dto.EmployeeDTO;
import com.software.application.data.entity.dto.ProductDTO;
import com.software.application.data.service.EmployeeService;
import com.software.application.data.service.ProductService;
import com.vaadin.componentfactory.pdfviewer.PdfViewer;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.vaadin.flow.server.StreamResource;
import lombok.RequiredArgsConstructor;
import org.vaadin.reports.PrintPreviewReport;

import javax.annotation.security.PermitAll;

@PageTitle("Documents")
@Route(value = "", layout = MainLayout.class)
@PermitAll
public class DocumentsView extends Div {

    public DocumentsView() {

        PdfViewer pdfViewer = new PdfViewer();
        pdfViewer.setSizeFull ();
        StreamResource resource = new StreamResource("example.pdf", ()
                -> getClass().getResourceAsStream("/pdf/example.pdf"));
        pdfViewer.setSrc(resource);
        pdfViewer.setZoom("page-fit");
        pdfViewer.setAddPrintButton(true);
        pdfViewer.setRenderInteractiveForms(false);
        pdfViewer.setCustomTitle("I'm a custom title");
        add(pdfViewer);
    }
}
