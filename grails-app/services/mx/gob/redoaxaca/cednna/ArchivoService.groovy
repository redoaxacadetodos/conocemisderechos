package mx.gob.redoaxaca.cednna

import grails.transaction.Transactional

import java.awt.image.BufferedImage

import javax.imageio.ImageIO

@Transactional
class ArchivoService {

    public static BufferedImage cargarImagen(String pathname) {
		BufferedImage bufim = null;
		bufim = ImageIO.read(new File(pathname));
		
		return bufim;
	}
	
	def mostrarImagen(imagen, tipo, salida){
		return ImageIO.write(imagen, tipo, salida)
	}
}
