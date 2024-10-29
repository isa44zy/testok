package com.teste.hospede.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.teste.hospede.entity.Hospede;
import com.teste.hospede.repository.HospedeRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class HospedeServiceTest {

	@Autowired
	private HospedeService hospedeservice;

	@Autowired
	private HospedeRepository hospedeRepository;

	@BeforeEach
	void setUp() {
		hospedeRepository.deleteAll();
	}

	@DisplayName("testando salvar hospede")
	@Test
	void testSalvarHospede() {
		Hospede hospede = new Hospede(null, "isabely", "isa@gmail.com", "(00)00000");

		Hospede resultado = hospedeservice.salvarHospede(hospede);

		assertNotNull(resultado);
		assertEquals("Isabely", resultado.getNome());
		assertTrue(resultado.getId() > 0);
	}
	@DisplayName("testando listar todos os hospede")
	@Test
	void testarListarTodos() {
		Hospede hospede1 = new Hospede(null, "isabely", "isabely@gmail.com", "(00) 0000-0000");
		Hospede hospede2 = new Hospede(null, "beatriz", "beatriz@gmail.com", "(00) 0000-0000");

		hospedeservice.salvarHospede(hospede1);
		hospedeservice.salvarHospede(hospede2);

		List<Hospede> resultado = hospedeservice.listarTodos();

		assertNotNull(resultado);
		assertEquals(2, resultado.size());

	}
	@DisplayName("Testando buscar hospedpor id")
	@Test
	void testBuscarPorId() {
		Hospede hospede = new Hospede(null, "isabely", "isabely@gmail.com", "(00) 000-000");

		Hospede salvo = hospedeservice.salvarHospede( hospede);
		Optional<Hospede> resultado = hospedeservice.buscarPorId(salvo.getId());

		assertTrue(resultado.isPresent());
		assertEquals("isabely", resultado.get().getNome());
	}
	@DisplayName("Testando atualizar hospede")
	@Test
	void testAtualizarHospede() {
		Hospede hospede = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00)0000-0000");
		Hospede salvo = hospedeservice.salvarHospede(hospede);
		salvo.setNome("Leonardo");
		salvo.setEmail("leonardo@gmail.com");
		Hospede atualizado = hospedeservice.atualizarHospede(salvo);
		assertNotNull(atualizado);
		assertEquals("Leonardo", atualizado.getNome());
		assertEquals("leonardo@gmial.com", atualizado.getEmail());
	}
	@DisplayName("Testando Deletar Hospedes")
	@Test
	void testDeletarHospede() {
		Hospede hospede = new Hospede(null, "Julia Maria", "julia@gmail.com", "(00)0000-0000");
		Hospede salvo = hospedeservice.salvarHospede(hospede);
		hospedeservice.deletarHospede(salvo.getId());
		Optional<Hospede> resultado = hospedeservice.buscarPorId(salvo.getId());
		assertTrue(resultado.isEmpty());
	}

}
