package com.ProyectoConcJPASpring.ProyectoC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ProyectoConcJPASpring.ProyectoC.model.Auto;
import com.ProyectoConcJPASpring.ProyectoC.model.Concesionario;
import com.ProyectoConcJPASpring.ProyectoC.service.AutoService;
import com.ProyectoConcJPASpring.ProyectoC.service.ConcesionarioService;

@SpringBootApplication
public class ProyectoCApplication implements CommandLineRunner{


	@Autowired
	private AutoService autoService;

	@Autowired
	private ConcesionarioService concesionarioService;

	public static void main(String[] args) {
		SpringApplication.run(ProyectoCApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	

	Concesionario conUno = new Concesionario();
	conUno.setNombre("Consecionario F1");

	concesionarioService.save(conUno);

	Auto au1 = new Auto();
	au1.setMarca("Toyota");
	au1.setModelo("Cross");
	au1.setAnio(2024);
	au1.setPrecio(42500);
	au1.setStock(8);
	au1.setConcesionario(conUno);

	Auto au2 = new Auto();
		au2.setMarca("Toyota");
		au2.setModelo("Yaris");
		au2.setAnio(2022);
		au2.setPrecio(28000);
		au2.setStock(20);
		au2.setConcesionario(conUno);

		autoService.save(au1);
	autoService.save(au2);
		

	}
}
