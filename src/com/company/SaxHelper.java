package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHelper extends DefaultHandler {


    boolean esFecha = false;
    boolean esDescripcion = false;
    boolean esPrecioUnidad = false;
    boolean esUnidades = false;
    boolean esDescuento = false;
    boolean esTicket = false;

    double precioTotal = 0;
    double cantidadTotal = 0;
    double totalDescuentos = 0;

    @Override
    public void startElement(String uri, String localName, String elementos, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, elementos, attributes);
        switch (elementos) {
            case "ticket":
                esTicket = true;
                break;
            case "fecha":
                esFecha = true;
                break;
            case "descripcion":
                esDescripcion = true;
                break;
            case "precio_unidad":
                esPrecioUnidad = true;
                break;
            case "unidades":
                esUnidades = true;
                break;
            case "descuento":
                esDescuento = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (esFecha) {
            System.out.println("fecha: " + new String(ch, start, length));
            return;
        }
        if (esDescripcion) {
            System.out.println("descripcion: " + new String(ch, start, length));
            return;
        }
        if (esPrecioUnidad) {
            System.out.println("precio_unidad: " + new String(ch, start, length));
            return;
        }
        if (esUnidades) {
            System.out.println("unidades: " + new String(ch, start, length));
            return;
        }
        if (esDescuento) {
            System.out.println("descuento: " + new String(ch, start, length));
            return;

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        switch (qName) {
            case "ticket":
                esTicket = false;
                break;
            case "fecha":
                esFecha = false;
                break;
            case "descripcion":
                esDescripcion = false;
                break;
            case "precio_unidad":
                esPrecioUnidad = false;
                break;
            case "unidades":
                esUnidades = false;
                break;
            case "descuento":
                esDescuento = false;
                break;
            default:
                break;
        }


    }
}
