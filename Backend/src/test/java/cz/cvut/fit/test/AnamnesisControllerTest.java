package cz.cvut.fit.test;

import cz.cvut.fit.genepi.businessLayer.service.PatientService;
import cz.cvut.fit.genepi.businessLayer.service.card.AnamnesisService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:anamnesisControllerTestContext.xml"})
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
      /*  mockMvc = MockMvcBuilders.standaloneSetup(
                new AnamnesisController(patientServiceMock,
                        anamnesisServiceMock)).build();*/
    }

    @After
    public void reset_mocks() {
        reset(patientServiceMock);
        reset(anamnesisServiceMock);
    }
/*
    @Test
    public void anamnesisCreateGET() throws Exception {
        PatientDisplayBO found = new PatientDisplayBO();
        found.setId(1);

        found.setAnamnesisList(new ArrayList<AnamnesisDisplayBO>());

        when(patientServiceMock.getPatientDisplayByIdWithAnamnesisList(1)).thenReturn(found);

        mockMvc.perform(get("/patient/{patientId}/anamnesis/create", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/anamnesis/formView"))
                .andExpect(
                        model().attribute("patient", isA(PatientDisplayBO.class)))
                .andExpect(
                        model().attribute("anamnesis",
                                isA(AnamnesisFormBO.class)));

        verify(patientServiceMock, times(1)).getPatientDisplayByIdWithAnamnesisList(1);
        verifyNoMoreInteractions(patientServiceMock);
    }

    @Test
    public void anamnesisEditGET() throws Exception {
        PatientDisplayBO patient = new PatientDisplayBO();
        patient.setId(1);

        AnamnesisFormBO anamnesis = new AnamnesisFormBO();
        anamnesis.setId(1);

        when(patientServiceMock.getPatientDisplayByIdWithDoctor(1)).thenReturn(patient);
        when(anamnesisServiceMock.getById(AnamnesisFormBO.class, AnamnesisEntity.class, 1)).thenReturn(anamnesis);

        mockMvc.perform(get("/patient/{patientId}/anamnesis/{anamnesisId}/edit", 1, 1))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/anamnesis/formView"))
                .andExpect(
                        model().attribute("patient", isA(PatientDisplayBO.class)))
                .andExpect(
                        model().attribute("anamnesis",
                                isA(AnamnesisFormBO.class)));

        verify(patientServiceMock, times(1)).getPatientDisplayByIdWithDoctor(1);
        verifyNoMoreInteractions(patientServiceMock);

        verify(anamnesisServiceMock, times(1)).getById(AnamnesisFormBO.class, AnamnesisEntity.class, 1);
        verifyNoMoreInteractions(anamnesisServiceMock);
    }

    @Test
    public void anamnesisSavePOST_AnamnesisEntityValid() throws Exception {

        AnamnesisFormBO anamnesis = new AnamnesisFormBO();
        anamnesis.setDate(new Date());
        anamnesis.setBeginningEpilepsy(new Date());

        PatientEntity patient = new PatientEntity();
        patient.setBirthday(new Date());

        when(patientServiceMock.getPatientByIdWithDoctor(1)).thenReturn(patient);

        mockMvc.perform(
                post("/patient/{patientId}/anamnesis/save", 1)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(
                                Util.convertObjectToFormUrlEncodedBytes(anamnesis))
                        .sessionAttr("anamnesis", anamnesis))
                .andExpect(model().attributeHasNoErrors("anamnesis"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/patient/1/anamnesis/list"));

        verify(anamnesisServiceMock, times(1)).save(AnamnesisEntity.class, anamnesis);
        verifyNoMoreInteractions(anamnesisServiceMock);
    }

    @Test
    public void anamnesisSavePOST_AnamnesisEntityNotValid() throws Exception {
        String nonCnsComorbidity = Util.createStringWithLength(900);

        LocalDate now = new LocalDate();
        LocalDate date = now.plusDays(1);
        Date tomorrow = date.toDate();

        AnamnesisFormBO anamnesis = new AnamnesisFormBO();
        anamnesis.setNonCnsComorbidity(nonCnsComorbidity);
        anamnesis.setDate(tomorrow);
        anamnesis.setBeginningEpilepsy(tomorrow);

        mockMvc.perform(
                post("/patient/{patientId}/anamnesis/save", 1)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(
                                Util.convertObjectToFormUrlEncodedBytes(anamnesis))
                        .sessionAttr("anamnesis", anamnesis))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/anamnesis/formView"))
                .andExpect(model().attributeHasFieldErrors("anamnesis", "date"))
                .andExpect(
                        model().attributeHasFieldErrors("anamnesis",
                                "beginningEpilepsy"))
                .andExpect(
                        model().attributeHasFieldErrors("anamnesis",
                                "nonCnsComorbidity"));

        verifyZeroInteractions(anamnesisServiceMock);
    }
*/
//	@Test
//	public void anamnesisDeleteGET() throws Exception {
//		AnamnesisEntity anamnesis = new AnamnesisEntity();
//		anamnesis.setId(1);
//
//		mockMvc.perform(
//				get("/patient/{patientID}/anamnesis/{anamnesisId}/delete", 1, anamnesis.getId()))
//				.andExpect(status().isMovedTemporarily())
//				.andExpect(view().name("redirect:/patient/1/anamnesis/list"));
//
//		verify(anamnesisServiceMock, times(1)).delete(anamnesis);
//		verifyNoMoreInteractions(anamnesisServiceMock);
//	}

/*
    @Test
    public void anamnesisEditPOST_AnamnesisEntityValid() throws Exception {

        AnamnesisFormBO anamnesis = new AnamnesisFormBO();
        anamnesis.setDate(new Date());
        anamnesis.setBeginningEpilepsy(new Date());

        mockMvc.perform(
                post("/patient/{patientId}/anamnesis/save", 1)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(
                                Util.convertObjectToFormUrlEncodedBytes(anamnesis))
                        .sessionAttr("anamnesis", anamnesis))
                .andExpect(model().attributeHasNoErrors("anamnesis"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/patient/1/anamnesis/list"));

        verify(anamnesisServiceMock, times(1)).save(AnamnesisEntity.class,anamnesis);
        verifyNoMoreInteractions(anamnesisServiceMock);
    }

    @Test
    public void anamnesisEditPOST_AnamnesisEntityNotValid() throws Exception {
        String nonCnsComorbidity = Util.createStringWithLength(900);

        LocalDate now = new LocalDate();
        LocalDate date = now.plusDays(1);
        Date tomorrow = date.toDate();

        AnamnesisFormBO anamnesis = new AnamnesisFormBO();
        anamnesis.setNonCnsComorbidity(nonCnsComorbidity);
        anamnesis.setDate(tomorrow);
        anamnesis.setBeginningEpilepsy(tomorrow);

        mockMvc.perform(
                post("/patient/{patientId}/anamnesis/save", 1)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(
                                Util.convertObjectToFormUrlEncodedBytes(anamnesis))
                        .sessionAttr("anamnesis", anamnesis))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/anamnesis/formView"))
                .andExpect(model().attributeHasFieldErrors("anamnesis", "date"))
                .andExpect(
                        model().attributeHasFieldErrors("anamnesis",
                                "beginningEpilepsy"))
                .andExpect(
                        model().attributeHasFieldErrors("anamnesis",
                                "nonCnsComorbidity"));

        verifyZeroInteractions(anamnesisServiceMock);
    }*/

    // anamnesisHideGET
    // anamnesisUnhideGET
/*
    @Test
    public void list_PatientEntityFound() throws Exception {
        PatientDisplayBO found = new PatientDisplayBO();
        found.setId(1);
        found.setAnamnesisList(new ArrayList<AnamnesisDisplayBO>());

        when(patientServiceMock.getPatientDisplayByIdWithAnamnesisList(1)).thenReturn(
                found);

        mockMvc.perform(get("/patient/{patientId}/anamnesis/list", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("patient/anamnesis/listView"))
                .andExpect(
                        model().attribute("patient", isA(PatientDisplayBO.class)));

        verify(patientServiceMock, times(1)).getPatientDisplayByIdWithAnamnesisList(1);
        verifyNoMoreInteractions(patientServiceMock);
    }
*/
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
