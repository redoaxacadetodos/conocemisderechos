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

class ObtenerCoordenadas {
	private ArrayList<Municipio> municipios;
	private ArrayList<Coordenada> coordenadas;
	Municipio municipio;	
	def municipiosArr;	
	
	
	public ObtenerCoordenadas(){
		municipios = new ArrayList<Municipio>();
		coordenadas = new ArrayList<Coordenada>();
		municipiosArr = Municipio.createCriteria().list{
			order("id", "asc")
		}
	}
	
	public void parseKml() {
		Kml kml = Kml.unmarshal(new File("doc.kml"));
		Feature feature = kml.getFeature();
		parseFeature(feature);
	}
	
	public ArrayList<Coordenada> obtenerCoordenadas(File file, Long idMunicipio) {
		//municipio = Municipio.get(idMunicipio)		
		Kml kml = Kml.unmarshal(file);
		Feature feature = kml.getFeature();
		parseFeature(feature);
		return this.coordenadas;
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
							municipiosArr.each { muni ->
								if(remplazarAcentos(muni.descripcion.toUpperCase()).equals(placemark.getName())){
									municipio = muni
								}
							}
							System.out.println(placemark.getName())
							//municipio = Municipio.get(411)
							//System.out.println(placemark.getName()+ "="+remplazarAcentos(municipio.descripcion.toUpperCase()))
							//if(remplazarAcentos(municipio.descripcion.toUpperCase()).equals(placemark.getName())){
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
								//this.coordenadas.add(parseCoordinate(coordinate));
								parseCoordinate(coordinate).save(flush: true);
							}
							//municipio.setCoordenadas(coordenadas);
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
		coordenada.municipio = this.municipio		
		return coordenada;		
	}
		
	public String remplazarAcentos(String s){		
		String texto = Normalizer.normalize(s, Normalizer.Form.NFD)		
		texto = texto.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")		
		return texto;
	}
}
