package cz.cvut.fit.test;

import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cz.cvut.fit.genepi.businessLayer.service.PatientService;
import cz.cvut.fit.genepi.businessLayer.service.card.AnamnesisService;
import cz.cvut.fit.genepi.dataLayer.entity.PatientEntity;
import cz.cvut.fit.genepi.dataLayer.entity.card.AnamnesisEntity;
import cz.cvut.fit.genepi.presentationLayer.controller.card.AnamnesisController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:anamnesisControllerTestContext.xml" })
@WebAppConfiguration
public class AnamnesisControllerTest {

	private MockMvc mockMvc;

	@Autowired
	@Qualifier("patientService")
	private PatientService patientServiceMock;

	@Autowired
	@Qualifier("anamnesisService")
	private AnamnesisService anamnesisServiceMock;

	// Add WebApplicationContext field here
	@Autowired
	private WebApplicationContext webApplicationContext;

	// The setUp() method is omitted.
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(
				new AnamnesisController(patientServiceMock,
						anamnesisServiceMock)).build();
	}

	@After
	public void reset_mocks() {
		reset(patientServiceMock);
		reset(anamnesisServiceMock);
	}

	@Test
	public void anamnesisCreateGET() throws Exception {
		PatientEntity found = new PatientEntity();
		found.setId(1);

		when(patientServiceMock.findByID(PatientEntity.class, 1)).thenReturn(
				found);

		mockMvc.perform(get("/patient/{patientId}/anamnesis/create", 1))
				.andExpect(status().isOk())
				.andExpect(view().name("patient/anamnesis/createView"))
				.andExpect(
						model().attribute("patient", isA(PatientEntity.class)))
				.andExpect(
						model().attribute("anamnesis",
								isA(AnamnesisEntity.class)));

		verify(patientServiceMock, times(1)).findByID(PatientEntity.class, 1);
		verifyNoMoreInteractions(patientServiceMock);
	}

	// FIXME: set all properties which has validation. Anamnesis which is saved

//	@Test
//	public void anamnesisCreatePOST_AnamnesisEntityValid() throws Exception {
//
//		AnamnesisEntity anamnesis = new AnamnesisEntity();
//		anamnesis.setNonCnsComorbidity("nonCnsComorbidity");
//		anamnesis.setBeginningEpilepsy(new Date());
//		System.out.println(anamnesis.getBeginningEpilepsy());
//
//		mockMvc.perform(
//				post("/patient/{patientID}/anamnesis/create", 1)
//						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
//						.content(""
//								/*Util.convertObjectToFormUrlEncodedBytes(anamnesis)*/)
//						.sessionAttr("anamnesis", anamnesis))
//				.andExpect(model().attributeHasNoErrors("anamnesis"))
//				.andExpect(status().isMovedTemporarily())
//				.andExpect(view().name("redirect:/patient/1/anamnesis/list"));
//
//		verify(anamnesisServiceMock, times(1)).save(anamnesis);
//		verifyNoMoreInteractions(anamnesisServiceMock);
//	}

	@Test
	public void anamnesisCreatePOST_AnamnesisEntityNotValid() throws Exception {
		String nonCnsComorbidity = Util.createStringWithLength(900);

		AnamnesisEntity anamnesis = new AnamnesisEntity();
		anamnesis.setNonCnsComorbidity(nonCnsComorbidity);
		//System.out.println("ddd");
		//System.out.println(Util.convertObjectToFormUrlEncodedBytes(anamnesis));
		mockMvc.perform(
				post("/patient/{patientID}/anamnesis/create", 1)
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.content(
								Util.convertObjectToFormUrlEncodedBytes(anamnesis))
						.sessionAttr("anamnesis", anamnesis))
				.andExpect(status().isOk())
				.andExpect(view().name("patient/anamnesis/createView"))
				.andExpect(model().attributeHasFieldErrors("anamnesis", "date"));
		// .andExpect(model().attributeHasFieldErrors("anamnesis",
		// "nonCnsComorbidity"));
		verifyZeroInteractions(anamnesisServiceMock);
	}

	// anamnesisEditGET
	// anamnesisEditPOST
	// anamnesisHideGET
	// anamnesisUnhideGET

	@Test
	public void list_PatientEntityFound() throws Exception {
		PatientEntity found = new PatientEntity();
		found.setId(1);

		when(patientServiceMock.getPatientByIdWithAnamnesisList(1)).thenReturn(
				found);

		mockMvc.perform(get("/patient/{patientID}/anamnesis/list", 1))
				.andExpect(status().isOk())
				.andExpect(view().name("patient/anamnesis/listView"))
				.andExpect(
						model().attribute("patient", isA(PatientEntity.class)));

		verify(patientServiceMock, times(1)).getPatientByIdWithAnamnesisList(1);
		verifyNoMoreInteractions(patientServiceMock);
	}

	/*
	 * @Test public void list_PatientEntityNotFound() throws Exception {
	 * when(patientServiceMock.findByID(PatientEntity.class, 1)).thenReturn(
	 * null);
	 * 
	 * mockMvc.perform(get("/patient/{patientID}/anamnesis/list", 1))
	 * .andExpect(status().isNotFound()) .andExpect(view().name("error/404"))
	 * .andExpect(forwardedUrl("/WEB-INF/jsp/error/404.jsp"));
	 * 
	 * verify(patientServiceMock, times(1)).findByID(PatientEntity.class, 1);
	 * verifyZeroInteractions(anamnesisServiceMock); }
	 */

}
