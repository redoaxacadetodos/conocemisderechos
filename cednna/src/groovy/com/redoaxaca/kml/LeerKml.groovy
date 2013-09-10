package com.redoaxaca.kml

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Boundary;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LinearRing;
import de.micromata.opengis.kml.v_2_2_0.MultiGeometry;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Polygon;

import mx.gob.redoaxaca.cednna.domino.Coordenada
import mx.gob.redoaxaca.cednna.domino.Municipio

class LeerKml {
	
	private ArrayList<Municipio> municipios;
	Municipio municipio;
	
	public LeerKml(){
		municipios = new ArrayList<Municipio>();
	}
	
	public void parseKml() {
		Kml kml = Kml.unmarshal(new File("doc.kml"));
		Feature feature = kml.getFeature();
		parseFeature(feature);
	}
	
	public ArrayList<Municipio> obtenerMunicipios(File file) {
		Kml kml = Kml.unmarshal(file);
		Feature feature = kml.getFeature();
		parseFeature(feature);
		return this.municipios;
	}

	private void parseFeature(Feature feature) {
		if(feature != null) {
			if(feature instanceof Document) {
				Document document = (Document) feature;
				List<Feature> featureList = document.getFeature();
				for(Feature documentFeature : featureList) {
					Folder folder = (Folder) documentFeature;
					List<Feature> folderList = folder.getFeature();
					for(Feature folders: folderList){
						if(folders instanceof Placemark) {
							Placemark placemark = (Placemark) folders;
							municipio = new Municipio();
							municipio?.descripcion = placemark.getName();
							MultiGeometry multiGeometry = (MultiGeometry) placemark.getGeometry();
							parseGeometry(multiGeometry);							
						}
					}
					
				}
			}
		}
	}

	private void parseGeometry(MultiGeometry multiGeometry) {
		if(multiGeometry != null) {
			if(multiGeometry.getGeometry().get(0) instanceof Polygon) {
				Polygon polygon = (Polygon) multiGeometry.getGeometry().get(0);
				Boundary outerBoundaryIs = polygon.getOuterBoundaryIs();
				if(outerBoundaryIs != null) {
					LinearRing linearRing = outerBoundaryIs.getLinearRing();
					if(linearRing != null) {
						List<Coordinate> coordinates = linearRing.getCoordinates();
						if(coordinates != null) {
							ArrayList<Coordenada> coordenadas = new ArrayList<Coordenada>();
							for(Coordinate coordinate : coordinates) {
								coordenadas.add(parseCoordinate(coordinate));
							}
							municipio.setCoordenadas(coordenadas);
							municipios.add(municipio);							
						}
					}
				}
			}
		}
	}

	private Coordenada parseCoordinate(Coordinate coordinate) {
		Coordenada coordenada = null;		
		if(coordinate != null) {
			coordenada = new Coordenada();
			coordenada.setLatitud(coordinate.getLatitude()+"");
			coordenada.setLongitud(coordinate.getLongitude()+"");
			coordenada.setAltitud(coordinate.getAltitude()+"");
		}		
		return coordenada;
		
	}
	
	public String quitarCaracteres(String input) {
		// Cadena de caracteres original a sustituir.
		String original = "·‡‰ÈËÎÌÏÔÛÚˆ˙˘uÒ¡¿ƒ…»ÀÕÃœ”“÷⁄Ÿ‹—Á«";
		// Cadena de caracteres ASCII que reemplazar·n los originales.
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		String output = input;
		for (int i=0; i<original.length(); i++) {
			// Reemplazamos los caracteres especiales.
			output = output.replace(original.charAt(i), ascii.charAt(i));
			System.out.println(output);
		}//for i
		return output;
	}
}
