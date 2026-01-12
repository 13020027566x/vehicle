package com.vehicle.service.organization.manager;

import com.finhub.framework.common.manager.impl.BaseManagerImpl;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.page.Page;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vehicle.dao.organization.OrganizationDAO;
import com.vehicle.dao.organization.po.OrganizationPO;
import com.vehicle.service.organization.converter.OrganizationConverter;
import com.vehicle.service.organization.domain.OrganizationDO;
import com.vehicle.service.organization.dto.OrganizationAddReqDTO;
import com.vehicle.service.organization.dto.OrganizationDTO;
import com.vehicle.service.organization.dto.OrganizationListReqDTO;
import com.vehicle.service.organization.dto.OrganizationListResDTO;
import com.vehicle.service.organization.dto.OrganizationModifyReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageReqDTO;
import com.vehicle.service.organization.dto.OrganizationPageResDTO;
import com.vehicle.service.organization.dto.OrganizationRemoveReqDTO;
import com.vehicle.service.organization.dto.OrganizationShowResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 机构 Manager
 *
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Slf4j
@Component
public class OrganizationManager extends BaseManagerImpl<OrganizationDAO, OrganizationPO, OrganizationDTO, OrganizationConverter> {

    public static OrganizationManager me() {
        return SpringUtil.getBean(OrganizationManager.class);
    }

    public List<OrganizationListResDTO> list(final OrganizationListReqDTO organizationListReqDTO) {
        OrganizationDTO paramsDTO = OrganizationDO.me().buildListParamsDTO(organizationListReqDTO);

        List<OrganizationDTO> organizationDTOList = super.findList(paramsDTO);

        return OrganizationDO.me().transferOrganizationListResDTOList(organizationDTOList);
    }

    public OrganizationListResDTO listOne(final OrganizationListReqDTO organizationListReqDTO) {
        OrganizationDTO paramsDTO = OrganizationDO.me().buildListParamsDTO(organizationListReqDTO);

        OrganizationDTO organizationDTO = super.findOne(paramsDTO);

        return OrganizationDO.me().transferOrganizationListResDTO(organizationDTO);
    }

    public Page<OrganizationPageResDTO> pagination(final OrganizationPageReqDTO organizationPageReqDTO, final Integer current, final Integer size) {
        OrganizationDTO paramsDTO = OrganizationDO.me().buildPageParamsDTO(organizationPageReqDTO);

        Page<OrganizationDTO> organizationDTOPage = super.findPage(paramsDTO, current, size);

        return OrganizationDO.me().transferOrganizationPageResDTOPage(organizationDTOPage);
    }

    public Boolean add(final OrganizationAddReqDTO organizationAddReqDTO) {
        OrganizationDO.me().checkOrganizationAddReqDTO(organizationAddReqDTO);

        QueryWrapper<OrganizationPO> queryWrapper = OrganizationDO.me().buildOrganizationQueryWrapper(organizationAddReqDTO);
        Integer count = OrganizationManager.me().findCount(queryWrapper);

        OrganizationDO.me().checkResultCount(count);

        OrganizationDTO addOrganizationDTO = OrganizationDO.me().buildAddOrganizationDTO(organizationAddReqDTO);

        return super.saveDTO(addOrganizationDTO);
    }

    public Boolean addAllColumn(final OrganizationAddReqDTO organizationAddReqDTO) {
        OrganizationDO.me().checkOrganizationAddReqDTO(organizationAddReqDTO);

        OrganizationDTO addOrganizationDTO = OrganizationDO.me().buildAddOrganizationDTO(organizationAddReqDTO);

        return super.saveAllColumn(addOrganizationDTO);
    }

    public Boolean addBatchAllColumn(final List<OrganizationAddReqDTO> organizationAddReqDTOList) {
        OrganizationDO.me().checkOrganizationAddReqDTOList(organizationAddReqDTOList);

        List<OrganizationDTO> addBatchOrganizationDTOList = OrganizationDO.me().buildAddBatchOrganizationDTOList(organizationAddReqDTOList);

        return super.saveBatchAllColumn(addBatchOrganizationDTOList);
    }

    public OrganizationShowResDTO show(final String id) {
        OrganizationDTO organizationDTO = super.findById(id);

        OrganizationDO.me().checkOrganizationDTO(organizationDTO);

        OrganizationDTO parentOrganizationDTO = null;
        if (StrUtil.isNotBlank(organizationDTO.getPid())) {
            parentOrganizationDTO = super.findById(organizationDTO.getPid());
        }

        return OrganizationDO.me().transferOrganizationShowResDTO(organizationDTO, parentOrganizationDTO);
    }

    public List<OrganizationShowResDTO> showByIds(final List<String> ids) {
        OrganizationDO.me().checkIds(ids);

        List<OrganizationDTO> organizationDTOList = super.findBatchIds(ids);

        return OrganizationDO.me().transferOrganizationShowResDTOList(organizationDTOList);
    }

    public Boolean modify(final OrganizationModifyReqDTO organizationModifyReqDTO) {
        OrganizationDO.me().checkOrganizationModifyReqDTO(organizationModifyReqDTO);

        QueryWrapper<OrganizationPO> queryWrapper = OrganizationDO.me().buildOrganizationQueryWrapper(organizationModifyReqDTO);
        Integer count = OrganizationManager.me().findCount(queryWrapper);

        OrganizationDO.me().checkResultCount(count);

        OrganizationDTO modifyOrganizationDTO = OrganizationDO.me().buildModifyOrganizationDTO(organizationModifyReqDTO);

        return super.modifyById(modifyOrganizationDTO);
    }

    public Boolean modifyAllColumn(final OrganizationModifyReqDTO organizationModifyReqDTO) {
        OrganizationDO.me().checkOrganizationModifyReqDTO(organizationModifyReqDTO);

        OrganizationDTO modifyOrganizationDTO = OrganizationDO.me().buildModifyOrganizationDTO(organizationModifyReqDTO);

        return super.modifyAllColumnById(modifyOrganizationDTO);
    }

    public Boolean removeByParams(final OrganizationRemoveReqDTO organizationRemoveReqDTO) {
        OrganizationDO.me().checkOrganizationRemoveReqDTO(organizationRemoveReqDTO);

        OrganizationDTO removeOrganizationDTO = OrganizationDO.me().buildRemoveOrganizationDTO(organizationRemoveReqDTO);

        return super.remove(removeOrganizationDTO);
    }

    @Override
    public Boolean removeById(final String id) {
        QueryWrapper<OrganizationPO> parentOrganizationWrapper = OrganizationDO.me().buildParentQueryWrapper(id);

        if (Func.isNotEmpty(parentOrganizationWrapper)) {
            List<OrganizationDTO> subOrganizationDTOList = super.findList(parentOrganizationWrapper);
            OrganizationDO.me().checkSubOrganizationDTOList(subOrganizationDTOList);
        }

        return super.removeById(id);
    }

    @Override
    protected OrganizationPO mapToPO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new OrganizationPO();
        }

        return BeanUtil.toBean(map, OrganizationPO.class);
    }

    @Override
    protected OrganizationDTO mapToDTO(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            return new OrganizationDTO();
        }

        return BeanUtil.toBean(map, OrganizationDTO.class);
    }
}
