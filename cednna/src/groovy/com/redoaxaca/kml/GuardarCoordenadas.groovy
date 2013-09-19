package com.redoaxaca.kml

import java.text.Normalizer

import mx.gob.redoaxaca.cednna.domino.Coordenada
import mx.gob.redoaxaca.cednna.domino.Municipio
import de.micromata.opengis.kml.v_2_2_0.Boundary
import de.micromata.opengis.kml.v_2_2_0.Coordinate
import de.micromata.opengis.kml.v_2_2_0.Document
import de.micromata.opengis.kml.v_2_2_0.Feature
import de.micromata.opengis.kml.v_2_2_0.Folder
import de.micromata.opengis.kml.v_2_2_0.Kml
import de.micromata.opengis.kml.v_2_2_0.LinearRing
import de.micromata.opengis.kml.v_2_2_0.MultiGeometry
import de.micromata.opengis.kml.v_2_2_0.Placemark
import de.micromata.opengis.kml.v_2_2_0.Polygon

class GuardarCoordenadas {
	
	private ArrayList<Municipio> municipios;
	private ArrayList<Coordenada> coordenadas;	
	Object objeto;
	def obj;
	
	
	public GuardarCoordenadas(Object objeto){
		this.objeto = objeto
		municipios = new ArrayList<Municipio>();
		coordenadas = new ArrayList<Coordenada>();			
		obj = objeto.createCriteria().list{
			order("id", "asc")
		}		
	}
	
	public guardarCoordenadas(File file) {		
		Kml kml = Kml.unmarshal(file);
		Feature feature = kml.getFeature();
		parseFeature(feature);		
	}
	
	private int parseFeature(Feature feature) {
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
							
							obj.each { arr ->
								if(remplazarAcentos(arr.descripcion.toUpperCase()).equals(placemark.getName())){
									objeto = arr
								}
							}
							//objeto = Municipio.get(67)
							System.out.println(placemark.getName()+"="+objeto?.descripcion)
							//if(remplazarAcentos(objeto.descripcion.toUpperCase()).equals(placemark.getName())){							
							coordenadas = new ArrayList<Coordenada>();
							MultiGeometry multiGeometry = (MultiGeometry) placemark.getGeometry();						
							parseGeometry(multiGeometry);													
							//}									
						}
					}
					
				}
			}
		}
		return 0;
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
							for(Coordinate coordinate : coordinates) {
								this.coordenadas.add(parseCoordinate(coordinate));															
							}
							objeto.setCoordenadas(coordenadas);														
							if (!objeto.save(flush: true)) {
								System.out.println("Error al insertar")
							}
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
		
	public String remplazarAcentos(String s){
		String texto = Normalizer.normalize(s, Normalizer.Form.NFD)
		texto = texto.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
		return texto;
	}
}

