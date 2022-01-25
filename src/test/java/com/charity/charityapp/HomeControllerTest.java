package com.charity.charityapp;

import com.charity.charityapp.donation.DonationService;
import com.charity.charityapp.institution.Institution;
import com.charity.charityapp.institution.InstitutionService;
import com.charity.charityapp.user.UserService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HomeController.class)
class HomeControllerTest {

    @MockBean
    InstitutionService institutionService;
    @MockBean
    DonationService donationService;


    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String SHOW_MAIN_ACTION_VIEW = "/";


    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();

        List<Institution> institutions = Arrays.asList(new Institution("name1", "description1"),
                new Institution("name2", "description2"));
        when(this.institutionService.showAllInstitutions()).thenReturn(institutions);

       int donations = 5;
        when(this.donationService.countDonations()).thenReturn(donations);

        int bags = 10;
        when(this.donationService.countBags()).thenReturn(bags);
    }

    @Test
    void showForm() {
    }

    @Test
    void getForm() {
    }

    @Test
    void signIn() {
    }

    @Test
    void showMainPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(model().attributeExists("institutions")).andExpect(model().attribute("institutions", Matchers.hasSize(2)))
                .andExpect(model().attributeExists("donations")).andExpect(model().attribute("donations", Matchers.hasValue(5)))
                .andExpect(model().attributeExists("bags")).andExpect(model().attribute("bogs", Matchers.hasValue(10)))
                .andExpect(status().isOk())
                .andExpect(view().name(SHOW_MAIN_ACTION_VIEW)).andDo(MockMvcResultHandlers.print());

    }
}