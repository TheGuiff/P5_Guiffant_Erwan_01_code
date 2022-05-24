package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.ChildAlertService;
import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.controller.ChildAlertController;
import com.safetynet.alerts.web.dto.ChildAlertDto;
import com.safetynet.alerts.web.dto.ChildAlertMemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ChildAlertController.class)
public class ChildAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ChildAlertService childAlertService;

    @MockBean
    PersonService personService;

    private static final String addressTest = "/childAlert?address=\"1509 Culver St\"";
    private static final ChildAlertMemberDto adult = new ChildAlertMemberDto();
    private static final ChildAlertMemberDto child = new ChildAlertMemberDto();
    private static final List<ChildAlertMemberDto> listAdults = new ArrayList<>();
    private static final List<ChildAlertMemberDto> listChildren = new ArrayList<>();
    private static final ChildAlertDto childAlertDto = new ChildAlertDto();

    @Test
    public void phoneAlertTestOnGoodAddress() throws Exception {
        adult.setAge(25);
        adult.setFirstName("TestA");
        adult.setLastName("Test");
        child.setAge(8);
        child.setFirstName("TestC");
        child.setLastName("Test");
        listAdults.add(adult);
        listChildren.add(child);
        childAlertDto.setListAdults(listAdults);
        childAlertDto.setListChildren(listChildren);
        try {
            when(childAlertService.childAlert(any())).thenReturn(childAlertDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(addressTest))
                .andExpect(status().isOk());
    }
}
