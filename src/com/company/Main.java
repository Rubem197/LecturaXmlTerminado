package com.company;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        try {
            File arxXml = new File("src/compras.xml");
            //Creamos los objetos que nos permitiran leer el fichero
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //Le pasamos el XML
            Document doc = db.parse(arxXml);
            doc.getDocumentElement().normalize();
            System.out.println("Elemento raiz:" + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("compra");
            //Creamos un bucle para leer los datos del xml y los mostramos en la consola
            double precioTotal=0;
            double cantidadTotal=0;
            double totalDescuentos=0;
            double totalTodo=0;

            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    NodeList hijo1 = eElement.getChildNodes();

                    System.out.println(eElement.getElementsByTagName("fecha").item(0).getTextContent());
                    System.out.println(eElement.getElementsByTagName("ticket").item(0).getTextContent());

                    for (int i = 0; i < hijo1.getLength(); i++) {

                        Node node1 = hijo1.item(i);
                        if (node1.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement1 = (Element) node1;
                            NodeList hijo2 = eElement1.getChildNodes();

                            for (int j = 0; j < hijo2.getLength(); j++) {
                                Node node2 = hijo2.item(j);
                                if (node2.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement2 = (Element) node2;



                                    if (eElement2.getElementsByTagName("unidades").item(0)==null){
                                        cantidadTotal++;
                                        precioTotal+= Double.parseDouble(eElement2.getElementsByTagName("precio_unidad").item(0).getTextContent());
                                    }else{
                                        cantidadTotal+=Double.parseDouble(eElement2.getElementsByTagName("unidades").item(0).getTextContent());
                                        precioTotal+= Double.parseDouble(eElement2.getElementsByTagName("precio_unidad").item(0).getTextContent())*cantidadTotal;
                                    }
                                    if (eElement2.getElementsByTagName("descuento").item(0)!=null) {
                                        totalDescuentos+=Double.parseDouble(eElement2.getElementsByTagName("descuento").item(0).getTextContent());
                                    }

                                }
                            }
                        }

                    }
                }
                System.out.println("El precio es: " + precioTotal);

                System.out.println("La cantidad total es: " + cantidadTotal);

                System.out.println("El descuento total es: " + totalDescuentos);

                totalTodo = precioTotal+cantidadTotal+totalDescuentos;

                System.out.println("El total del todo es: " +totalTodo );

                precioTotal=0;
                cantidadTotal=0;
                totalDescuentos=0;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
