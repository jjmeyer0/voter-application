package com.nerdery.voting.controller;

import com.nerdery.init.PersistenceConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { PersistenceConfiguration.class })
public class AddGameControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    AddGameController addGameController = new AddGameController();



    @Before
    public void setUp() throws Exception {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(addGameController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void makeSureModelIsCorrect() throws Exception {
        mockMvc.perform(get("/add-game"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-game"))
                .andExpect(model().attributeExists("game"));
    }


}
