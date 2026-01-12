package ${conf.servicePackageName}.${table.lowerCamelName}.domain;

import com.finhub.framework.core.page.Page;
import com.finhub.framework.exception.MessageException;

import ${conf.servicePackageName}.BaseJUnitTester;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}AddReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}DTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ListReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ListResDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ModifyReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}PageReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}PageResDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}RemoveReqDTO;
import ${conf.servicePackageName}.${table.lowerCamelName}.dto.${table.className}ShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * ${table.comment} DOTest
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Slf4j
public class ${table.className}DOTest extends BaseJUnitTester<${table.className}DO> {

    @Test(expected = MessageException.class)
    public void testCheck${table.className}AddReqDTO() {
        domain.check${table.className}AddReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheck${table.className}AddReqDTOList() {
        domain.check${table.className}AddReqDTOList(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheckIds() {
        domain.checkIds(Lists.newArrayList());
    }

    @Test(expected = MessageException.class)
    public void testCheck${table.className}ModifyReqDTO() {
        domain.check${table.className}ModifyReqDTO(null);
    }

    @Test(expected = MessageException.class)
    public void testCheck${table.className}RemoveReqDTO() {
        domain.check${table.className}RemoveReqDTO(null);
    }

    @Test
    public void testBuildListParamsDTO() {
        ${table.className}ListReqDTO ${table.camelName}ListReqDTO = new ${table.className}ListReqDTO();
        // ${table.camelName}ListReqDTO.setId("1");

        ${table.className}DTO ${table.camelName}DTO = domain.buildListParamsDTO(${table.camelName}ListReqDTO);

        // Assert.assertEquals(${table.camelName}ListReqDTO.getId(), ${table.camelName}DTO.getId());
    }

    @Test
    public void testBuildPageParamsDTO() {
        ${table.className}PageReqDTO ${table.camelName}PageReqDTO = new ${table.className}PageReqDTO();
        // ${table.camelName}PageReqDTO.setId("1");

        ${table.className}DTO ${table.camelName}DTO = domain.buildPageParamsDTO(${table.camelName}PageReqDTO);

        // Assert.assertEquals(${table.camelName}PageReqDTO.getId(), ${table.camelName}DTO.getId());
    }

    @Test
    public void testBuildAdd${table.className}DTO() {
        ${table.className}AddReqDTO ${table.camelName}AddReqDTO = new ${table.className}AddReqDTO();

        ${table.className}DTO ${table.camelName}DTO = domain.buildAdd${table.className}DTO(${table.camelName}AddReqDTO);
    }

    @Test
    public void testBuildAddBatch${table.className}DTOList() {
        List<${table.className}AddReqDTO> ${table.camelName}AddReqDTOList = Lists.newArrayList();
        ${table.className}AddReqDTO ${table.camelName}AddReqDTO1 = new ${table.className}AddReqDTO();
        ${table.className}AddReqDTO ${table.camelName}AddReqDTO2 = new ${table.className}AddReqDTO();
        ${table.camelName}AddReqDTOList.add(${table.camelName}AddReqDTO1);
        ${table.camelName}AddReqDTOList.add(${table.camelName}AddReqDTO2);

        List<${table.className}DTO> ${table.camelName}DTOList = domain.buildAddBatch${table.className}DTOList(${table.camelName}AddReqDTOList);

        Assert.assertEquals(${table.camelName}AddReqDTOList.size(), ${table.camelName}DTOList.size());
    }

    @Test
    public void testBuildModify${table.className}DTO() {
        ${table.className}ModifyReqDTO ${table.camelName}ModifyReqDTO = new ${table.className}ModifyReqDTO();
        // ${table.camelName}ModifyReqDTO.setId("1");

        ${table.className}DTO ${table.camelName}DTO = domain.buildModify${table.className}DTO(${table.camelName}ModifyReqDTO);

        // Assert.assertEquals(${table.camelName}ModifyReqDTO.getId(), ${table.camelName}DTO.getId());
    }

    @Test
    public void testBuildRemove${table.className}DTO() {
        ${table.className}RemoveReqDTO ${table.camelName}RemoveReqDTO = new ${table.className}RemoveReqDTO();
        // ${table.camelName}RemoveReqDTO.setId("1");

        ${table.className}DTO ${table.camelName}DTO = domain.buildRemove${table.className}DTO(${table.camelName}RemoveReqDTO);

        // Assert.assertEquals(${table.camelName}RemoveReqDTO.getId(), ${table.camelName}DTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransfer${table.className}ListResDTOList() {
        List<${table.className}DTO> ${table.camelName}DTOList = Lists.newArrayList();

        domain.transfer${table.className}ListResDTOList(${table.camelName}DTOList);

        ${table.className}DTO ${table.camelName}DTO1 = new ${table.className}DTO();
        // ${table.camelName}DTO1.setId("1");
        ${table.className}DTO ${table.camelName}DTO2 = new ${table.className}DTO();
        // ${table.camelName}DTO1.setId("2");
        ${table.camelName}DTOList.add(${table.camelName}DTO1);
        ${table.camelName}DTOList.add(${table.camelName}DTO2);

        List<${table.className}ListResDTO> ${table.camelName}ListResDTOList = domain.transfer${table.className}ListResDTOList(${table.camelName}DTOList);

        Assert.assertEquals(${table.camelName}DTOList.size(), ${table.camelName}ListResDTOList.size());
        // Assert.assertEquals(${table.camelName}DTOList.get(0).getId(), ${table.camelName}ListResDTOList.get(0).getId());
        // Assert.assertEquals(${table.camelName}DTOList.get(1).getId(), ${table.camelName}ListResDTOList.get(1).getId());
    }

    @Test(expected = MessageException.class)
    public void testTransfer${table.className}ListResDTO() {
        domain.transfer${table.className}ListResDTO(null);

        ${table.className}DTO ${table.camelName}DTO = new ${table.className}DTO();
        // ${table.camelName}DTO.setId("1");

        ${table.className}ListResDTO ${table.camelName}ListResDTO = domain.transfer${table.className}ListResDTO(${table.camelName}DTO);

        // Assert.assertEquals(${table.camelName}DTO.getId(), ${table.camelName}ListResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransfer${table.className}PageResDTOPage() {
        Page<${table.className}DTO> ${table.camelName}DTOPage = new Page<>();

        domain.transfer${table.className}PageResDTOPage(${table.camelName}DTOPage);

        ${table.camelName}DTOPage.setTotal(100);
        ${table.camelName}DTOPage.setCurrent(10);
        ${table.camelName}DTOPage.setSize(10);

        List<${table.className}DTO> ${table.camelName}DTOList = Lists.newArrayList();
        ${table.className}DTO ${table.camelName}DTO1 = new ${table.className}DTO();
        // ${table.camelName}DTO1.setId("1");
        ${table.className}DTO ${table.camelName}DTO2 = new ${table.className}DTO();
        // ${table.camelName}DTO1.setId("2");
        ${table.camelName}DTOList.add(${table.camelName}DTO1);
        ${table.camelName}DTOList.add(${table.camelName}DTO2);

        ${table.camelName}DTOPage.setRecords(${table.camelName}DTOList);


        Page<${table.className}PageResDTO> ${table.camelName}PageResDTOPage = domain.transfer${table.className}PageResDTOPage(${table.camelName}DTOPage);

        Assert.assertEquals(${table.camelName}DTOPage.getTotal(), ${table.camelName}PageResDTOPage.getTotal());
        Assert.assertEquals(${table.camelName}DTOPage.getCurrent(), ${table.camelName}PageResDTOPage.getCurrent());
        Assert.assertEquals(${table.camelName}DTOPage.getSize(), ${table.camelName}PageResDTOPage.getSize());
        Assert.assertEquals(${table.camelName}DTOPage.getRecords().size(), ${table.camelName}PageResDTOPage.getRecords().size());
    }

    @Test(expected = MessageException.class)
    public void testTransfer${table.className}ShowResDTO() {
        domain.transfer${table.className}ShowResDTO(null);

        ${table.className}DTO ${table.camelName}DTO = new ${table.className}DTO();
        // ${table.camelName}DTO.setId("1");

        ${table.className}ShowResDTO ${table.camelName}ShowResDTO = domain.transfer${table.className}ShowResDTO(${table.camelName}DTO);

        // Assert.assertEquals(${table.camelName}DTO.getId(), ${table.camelName}ShowResDTO.getId());
    }

    @Test(expected = MessageException.class)
    public void testTransfer${table.className}ShowResDTOList() {
        List<${table.className}DTO> ${table.camelName}DTOList = Lists.newArrayList();

        domain.transfer${table.className}ShowResDTOList(${table.camelName}DTOList);

        ${table.className}DTO ${table.camelName}DTO1 = new ${table.className}DTO();
        // ${table.camelName}DTO1.setId("1");
        ${table.className}DTO ${table.camelName}DTO2 = new ${table.className}DTO();
        // ${table.camelName}DTO1.setId("2");
        ${table.camelName}DTOList.add(${table.camelName}DTO1);
        ${table.camelName}DTOList.add(${table.camelName}DTO2);

        List<${table.className}ShowResDTO> ${table.camelName}ShowResDTOList = domain.transfer${table.className}ShowResDTOList(${table.camelName}DTOList);

        Assert.assertEquals(${table.camelName}DTOList.size(), ${table.camelName}ShowResDTOList.size());
        // Assert.assertEquals(${table.camelName}DTOList.get(0).getId(), ${table.camelName}ShowResDTOList.get(0).getId());
        // Assert.assertEquals(${table.camelName}DTOList.get(1).getId(), ${table.camelName}ShowResDTOList.get(1).getId());
    }
}
