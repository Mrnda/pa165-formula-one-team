package cz.muni.fi.pa165.rest.controllers.base;

import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.base.BaseEntityFacade;
import cz.muni.fi.pa165.rest.controllers.BaseControllerTests;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author mrnda (Michal Mrnuštík)
 */
public class BaseCrudControllerTests extends BaseControllerTests<TestController> {

    @Mock
    private BaseEntityFacade<TestDTO, TestEntity> testFacade;

    @InjectMocks
    private TestController controller;

    @Test
    public void getAll_withSomeItems_returnsFacadeGetAll() throws Exception {
        //Given
        List<TestDTO> testDtos = Arrays.asList(createTestDto(1, "test 1"),
                createTestDto(2, "test 2"));
        String testAsJson = convertToJson(testDtos);
        when(testFacade.getAll()).thenReturn(testDtos);

        //When

        //Then
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json(testAsJson));
    }

    @Test
    public void getWithId_withExistingItem_returnsFacadeFindById() throws Exception {
        //Given
        TestDTO dto = createTestDto(1, "test 1");
        dto.setId(1);
        String dtoAsJson = convertToJson(dto);
        when(testFacade.findById(dto.getId())).thenReturn(dto);

        //When

        //Then
        mockMvc.perform(get("/" + dto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(dtoAsJson));
    }

    @Test
    public void getWithId_withNonExistingItem_statusIsNotFound() throws Exception {
        //Given
        when(testFacade.findById(-1)).thenThrow(EntityNotFoundException.class);

        //When

        //Then
        mockMvc.perform(get("/" + -1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_withExistingItem_returnsDeleted() throws Exception {
        //Given
        TestDTO dto = createTestDto(1, "test 1");
        dto.setId(1);
        when(testFacade.findById(dto.getId())).thenReturn(dto);

        //When

        //Then
        mockMvc.perform(delete("/" + dto.getId()))
                .andExpect(status().isAccepted());
    }

    @Test
    public void delete_withNonExistingItem_returnsNotFound() throws Exception {
        //given
        doThrow(EntityNotFoundException.class).when(testFacade).remove(1);

        //then
        mockMvc.perform(delete("/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void put_withExistingItem_returnsOk() throws Exception {
        //Given
        TestDTO dto = createTestDto(1, "test 1");
        String dtoString = convertToJson(dto);
        when(testFacade.findById(dto.getId())).thenReturn(dto);

        //When

        //Then
        mockMvc.perform(put("/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(dtoString))
                .andExpect(status().isOk());
    }

    @Test
    public void put_withInvalidItem_returnsUnprocessableEntity() throws Exception {
        //Given
        TestDTO dto = createTestDto(1, "test 1");
        String dtoString = convertToJson(dto);
        doThrow(FormulaOneTeamException.class).when(testFacade).update(dto, dto.getId());

        //When

        //Then
        mockMvc.perform(put("/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(dtoString))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void post_withNewValidItem_returnsOk() throws Exception {
        //Given
        TestDTO noIdDTO = createTestDto(-1, "test 1");
        String dtoString = convertToJson(noIdDTO);
        TestDTO createdDTO = createTestDto(1, "test 1");
        createdDTO.setId(1);
        when(testFacade.add(noIdDTO)).thenReturn(createdDTO.getId());
        when(testFacade.findById(createdDTO.getId())).thenReturn(createdDTO);

        //When

        //Then
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(dtoString))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(createdDTO)));
    }

    @Test
    public void post_withInvalidItem_returnsOk() throws Exception {
        //Given
        TestDTO noIdDTO = createTestDto(-1, null);
        String dtoString = convertToJson(noIdDTO);
        when(testFacade.add(noIdDTO)).thenThrow(FormulaOneTeamException.class);

        //When

        //Then
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(dtoString))
                .andExpect(status().isUnprocessableEntity());
    }

    private TestDTO createTestDto(long id, String name) {
        TestDTO dto = new TestDTO();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }

    @Override
    protected TestController getController() {
        return controller;
    }
}

