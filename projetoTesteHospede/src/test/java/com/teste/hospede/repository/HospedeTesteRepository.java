package com.teste.hospede.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.teste.hospede.entity.Hospede;

@DataJpaTest
class HospedeTesteRepository {

	@Autowired
	private HospedeRepository HospedeRepository;
	@DisplayName("Testando o Save")
	@Test
	void testSalvarRepository() {
		Hospede Hospede1 = new Hospede(null, "isabely","isabely@gmail.com", "67876789" );
		Hospede saveHospede = HospedeRepository.save(Hospede1);
		assertNotNull(saveHospede);
		assertTrue(saveHospede.getId()>0);
	}
	@DisplayName("Testando Get para todos os Hospede")
	@Test
	void testGetAllRepository() {
		Hospede Hospede1 = new Hospede (null, "isabely","isabely@gmail.com", "67876789" );
		Hospede Hospede2 = new Hospede (null,"julia","julia@gmail.com", "789867890");
		HospedeRepository.save(Hospede1);
		HospedeRepository.save(Hospede2);
		List<Hospede> HospedeList = HospedeRepository.findAll();
		assertNotNull(HospedeList);
		assertEquals(2,HospedeList.size());
	}
	@DisplayName("Testando Get By ID")
	@Test
	void testGetById() {
		Hospede Hospede1 = new Hospede (null,"beatriz","biatrise@gmail.com", "67898767");
		HospedeRepository.save(Hospede1);
		Hospede saveHospede = HospedeRepository.findById(Hospede1.getId()).get();
		assertNotNull(saveHospede);
		assertEquals(Hospede1.getId(),saveHospede.getId());
	}
	@DisplayName("Testando Update")
	@Test
	void testUpdateHospede() {
		Hospede Hospede1 = new Hospede (null,"cainan","caina@gmail.com", "756785");
		HospedeRepository.save(Hospede1);
		Hospede saveHospede = HospedeRepository.findById(Hospede1.getId()).get();
		Hospede1.setNome("isabely");
		Hospede1.setEmail("isabely@gmail.com");
		Hospede1.setTelefone("878909876");
		Hospede updateHospede = HospedeRepository.save(saveHospede);
		assertNotNull(updateHospede);
		assertEquals("julia", updateHospede.getNome());
		assertEquals("julia@gmail.com", updateHospede.getEmail());
		assertEquals("1342342", updateHospede.getTelefone());
	}
	@DisplayName("Testando Delete")
	@Test
	void testDeleteHospede() {
		Hospede Hospede1 = new Hospede (null,"cainan","cainan@gmail.com", "767897");
		HospedeRepository.save(Hospede1);
		HospedeRepository.deleteById(Hospede1.getId());
		Optional<Hospede> HospedeOptional = HospedeRepository.findById(Hospede1.getId());
		assertTrue(HospedeOptional.isEmpty());
	}
}
