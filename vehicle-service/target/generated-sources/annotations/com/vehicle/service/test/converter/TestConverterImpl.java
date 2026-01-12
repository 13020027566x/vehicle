package com.vehicle.service.test.converter;

import com.finhub.framework.core.page.Page;
import com.vehicle.dao.test.po.TestPO;
import com.vehicle.service.test.dto.TestAddReqDTO;
import com.vehicle.service.test.dto.TestDTO;
import com.vehicle.service.test.dto.TestListReqDTO;
import com.vehicle.service.test.dto.TestListResDTO;
import com.vehicle.service.test.dto.TestModifyReqDTO;
import com.vehicle.service.test.dto.TestPageReqDTO;
import com.vehicle.service.test.dto.TestPageResDTO;
import com.vehicle.service.test.dto.TestRemoveReqDTO;
import com.vehicle.service.test.dto.TestShowResDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class TestConverterImpl implements TestConverter {

    @Override
    public TestDTO poToDTO(TestPO po) {
        if ( po == null ) {
            return null;
        }

        TestDTO testDTO = new TestDTO();

        testDTO.setId( po.getId() );
        testDTO.setIsDel( po.getIsDel() );
        testDTO.setIsTest( po.getIsTest() );
        testDTO.setCreateAt( po.getCreateAt() );
        testDTO.setCreateTime( po.getCreateTime() );
        testDTO.setCreateBy( po.getCreateBy() );
        testDTO.setCreateName( po.getCreateName() );
        testDTO.setUpdateAt( po.getUpdateAt() );
        testDTO.setUpdateTime( po.getUpdateTime() );
        testDTO.setUpdateBy( po.getUpdateBy() );
        testDTO.setUpdateName( po.getUpdateName() );
        testDTO.setRemark( po.getRemark() );
        testDTO.setTestName( po.getTestName() );

        return testDTO;
    }

    @Override
    public List<TestDTO> poToDTOList(List<TestPO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<TestDTO> list = new ArrayList<TestDTO>( arg0.size() );
        for ( TestPO testPO : arg0 ) {
            list.add( poToDTO( testPO ) );
        }

        return list;
    }

    @Override
    public TestPO dtoToPO(TestDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        TestPO testPO = new TestPO();

        testPO.setId( arg0.getId() );
        testPO.setIsDel( arg0.getIsDel() );
        testPO.setIsTest( arg0.getIsTest() );
        testPO.setCreateAt( arg0.getCreateAt() );
        testPO.setCreateTime( arg0.getCreateTime() );
        testPO.setCreateBy( arg0.getCreateBy() );
        testPO.setCreateName( arg0.getCreateName() );
        testPO.setUpdateAt( arg0.getUpdateAt() );
        testPO.setUpdateTime( arg0.getUpdateTime() );
        testPO.setUpdateBy( arg0.getUpdateBy() );
        testPO.setUpdateName( arg0.getUpdateName() );
        testPO.setTestName( arg0.getTestName() );
        testPO.setRemark( arg0.getRemark() );

        return testPO;
    }

    @Override
    public List<TestPO> dtoToPOList(List<TestDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<TestPO> list = new ArrayList<TestPO>( arg0.size() );
        for ( TestDTO testDTO : arg0 ) {
            list.add( dtoToPO( testDTO ) );
        }

        return list;
    }

    @Override
    public void updatePO(TestDTO arg0, TestPO arg1) {
        if ( arg0 == null ) {
            return;
        }

        arg1.setId( arg0.getId() );
        arg1.setIsDel( arg0.getIsDel() );
        arg1.setIsTest( arg0.getIsTest() );
        arg1.setCreateAt( arg0.getCreateAt() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateName( arg0.getCreateName() );
        arg1.setUpdateAt( arg0.getUpdateAt() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateName( arg0.getUpdateName() );
        arg1.setTestName( arg0.getTestName() );
        arg1.setRemark( arg0.getRemark() );
    }

    @Override
    public void updateDto(TestPO arg0, TestDTO arg1) {
        if ( arg0 == null ) {
            return;
        }

        arg1.setId( arg0.getId() );
        arg1.setIsDel( arg0.getIsDel() );
        arg1.setIsTest( arg0.getIsTest() );
        arg1.setCreateAt( arg0.getCreateAt() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateName( arg0.getCreateName() );
        arg1.setUpdateAt( arg0.getUpdateAt() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateName( arg0.getUpdateName() );
        arg1.setRemark( arg0.getRemark() );
        arg1.setTestName( arg0.getTestName() );
    }

    @Override
    public TestDTO convertToTestDTO(TestAddReqDTO testAddReqDTO) {
        if ( testAddReqDTO == null ) {
            return null;
        }

        TestDTO testDTO = new TestDTO();

        testDTO.setRemark( testAddReqDTO.getRemark() );
        testDTO.setTestName( testAddReqDTO.getTestName() );

        return testDTO;
    }

    @Override
    public TestDTO convertToTestDTO(TestModifyReqDTO testModifyReqDTO) {
        if ( testModifyReqDTO == null ) {
            return null;
        }

        TestDTO testDTO = new TestDTO();

        testDTO.setRemark( testModifyReqDTO.getRemark() );
        testDTO.setTestName( testModifyReqDTO.getTestName() );

        return testDTO;
    }

    @Override
    public TestDTO convertToTestDTO(TestRemoveReqDTO testRemoveReqDTO) {
        if ( testRemoveReqDTO == null ) {
            return null;
        }

        TestDTO testDTO = new TestDTO();

        testDTO.setRemark( testRemoveReqDTO.getRemark() );
        testDTO.setTestName( testRemoveReqDTO.getTestName() );

        return testDTO;
    }

    @Override
    public TestDTO convertToTestDTO(TestListReqDTO testListReqDTO) {
        if ( testListReqDTO == null ) {
            return null;
        }

        TestDTO testDTO = new TestDTO();

        testDTO.setRemark( testListReqDTO.getRemark() );
        testDTO.setTestName( testListReqDTO.getTestName() );

        return testDTO;
    }

    @Override
    public TestDTO convertToTestDTO(TestPageReqDTO testPageReqDTO) {
        if ( testPageReqDTO == null ) {
            return null;
        }

        TestDTO testDTO = new TestDTO();

        testDTO.setRemark( testPageReqDTO.getRemark() );
        testDTO.setTestName( testPageReqDTO.getTestName() );

        return testDTO;
    }

    @Override
    public TestShowResDTO convertToTestShowResDTO(TestDTO testDTO) {
        if ( testDTO == null ) {
            return null;
        }

        TestShowResDTO testShowResDTO = new TestShowResDTO();

        testShowResDTO.setRemark( testDTO.getRemark() );
        testShowResDTO.setTestName( testDTO.getTestName() );

        return testShowResDTO;
    }

    @Override
    public List<TestShowResDTO> convertToTestShowResDTOList(List<TestDTO> testDTOList) {
        if ( testDTOList == null ) {
            return null;
        }

        List<TestShowResDTO> list = new ArrayList<TestShowResDTO>( testDTOList.size() );
        for ( TestDTO testDTO : testDTOList ) {
            list.add( convertToTestShowResDTO( testDTO ) );
        }

        return list;
    }

    @Override
    public TestListResDTO convertToTestListResDTO(TestDTO testDTO) {
        if ( testDTO == null ) {
            return null;
        }

        TestListResDTO testListResDTO = new TestListResDTO();

        testListResDTO.setRemark( testDTO.getRemark() );
        testListResDTO.setTestName( testDTO.getTestName() );

        return testListResDTO;
    }

    @Override
    public List<TestListResDTO> convertToTestListResDTOList(List<TestDTO> testDTOList) {
        if ( testDTOList == null ) {
            return null;
        }

        List<TestListResDTO> list = new ArrayList<TestListResDTO>( testDTOList.size() );
        for ( TestDTO testDTO : testDTOList ) {
            list.add( convertToTestListResDTO( testDTO ) );
        }

        return list;
    }

    @Override
    public List<TestDTO> convertToTestDTOList(List<TestAddReqDTO> testAddReqDTOList) {
        if ( testAddReqDTOList == null ) {
            return null;
        }

        List<TestDTO> list = new ArrayList<TestDTO>( testAddReqDTOList.size() );
        for ( TestAddReqDTO testAddReqDTO : testAddReqDTOList ) {
            list.add( convertToTestDTO( testAddReqDTO ) );
        }

        return list;
    }

    @Override
    public TestPageResDTO convertToTestPageResDTO(TestDTO testDTO) {
        if ( testDTO == null ) {
            return null;
        }

        TestPageResDTO testPageResDTO = new TestPageResDTO();

        testPageResDTO.setRemark( testDTO.getRemark() );
        testPageResDTO.setTestName( testDTO.getTestName() );

        return testPageResDTO;
    }

    @Override
    public List<TestPageResDTO> convertToTestPageResDTOList(List<TestDTO> testDTOList) {
        if ( testDTOList == null ) {
            return null;
        }

        List<TestPageResDTO> list = new ArrayList<TestPageResDTO>( testDTOList.size() );
        for ( TestDTO testDTO : testDTOList ) {
            list.add( convertToTestPageResDTO( testDTO ) );
        }

        return list;
    }

    @Override
    public Page<TestPageResDTO> convertToTestPageResDTOPage(Page<TestDTO> testDTOPage) {
        if ( testDTOPage == null ) {
            return null;
        }

        Page<TestPageResDTO> page = new Page<TestPageResDTO>();

        page.setRecords( convertToTestPageResDTOList( testDTOPage.getRecords() ) );
        page.setTotal( testDTOPage.getTotal() );
        page.setSize( testDTOPage.getSize() );
        page.setCurrent( testDTOPage.getCurrent() );

        return page;
    }
}
