package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.controller.CommunityEmailController;
import com.safetynet.alerts.web.dto.CommunityEmailDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommunityEmailController.class)
public class CommunityEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonService personService;

    private static final String city = "/communityEmail?city=Culver";
    private static final String emailTest = "email";
    private static final List<String> listEmails = new ArrayList<>();
    private static final CommunityEmailDto communityEmailDto = new CommunityEmailDto();

    @Test
    public void communityEmail() throws Exception {
        listEmails.add(emailTest);
        communityEmailDto.setListEmail(listEmails);
        try {
            when(personService.communityEmail(any())).thenReturn(communityEmailDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(city))
                .andExpect(status().isOk());
    }

}
