package com.finhub.framework.web.controller;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.number.NumberConstants;
import com.finhub.framework.core.web.ResponseUtils;
import com.finhub.framework.exception.constant.CodeEnum;
import com.finhub.framework.exception.constant.enums.BusinessResponseEnum;
import com.finhub.framework.web.vo.ItemData;
import com.finhub.framework.web.vo.ItemR;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsData;
import com.finhub.framework.web.vo.ItemsR;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.MessageData;
import com.finhub.framework.web.vo.MessageR;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageD;
import com.finhub.framework.web.vo.PageData;
import com.finhub.framework.web.vo.PageR;
import com.finhub.framework.web.vo.PageResult;

import cn.hutool.core.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * HttpSupport
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
public class ControllerSupport {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    protected final String responseRedirect(final String url) {
        return "redirect:" + url;
    }

    protected final void responseExcelByte(final byte[] bytes, final String fileName) {
        ResponseUtils.renderExcelFile(response, bytes, fileName);
    }

    protected final void responseFile(final String content, final String fileName) {
        try {
            byte[] bytes = content.getBytes(CharsetUtil.defaultCharsetName());
            ResponseUtils.renderFile(response, bytes, fileName);
        } catch (UnsupportedEncodingException e) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR, e);
        }
    }

    /**
     * 处理响应信息
     *
     * @param code
     * @param message
     * @return
     */
    protected final MessageResult responseMessage(final int code, final String message) {
        MessageResult messageResult = new MessageResult();
        messageResult.setCode(code);

        MessageData data = new MessageData();
        data.setMessage(message);

        messageResult.setData(data);

        return messageResult;
    }

    protected final MessageResult responseMessage(final CodeEnum codeEnum) {
        return responseMessage(codeEnum.getCode(), codeEnum.getMessage());
    }

    protected final MessageResult responseMessage(final CodeEnum codeEnum, final String message) {
        return responseMessage(codeEnum.getCode(), message);
    }

    protected final MessageR responseMessageR(final int code, final String message) {
        MessageR messageR = new MessageR();

        messageR.setCode(code);
        messageR.setMessage(message);
        messageR.setCount(NumberConstants.N_ZERO);

        return messageR;
    }

    protected final MessageR responseMessageR(final CodeEnum codeEnum) {
        return responseMessageR(codeEnum.getCode(), codeEnum.getMessage());
    }

    protected final MessageR responseMessageR(final CodeEnum codeEnum, final String message) {
        return responseMessageR(codeEnum.getCode(), message);
    }

    /**
     * 处理响应单个实体
     *
     * @param code
     * @param message
     * @param item
     * @return
     */
    protected final <T> ItemResult<T> responseItem(final int code, final String message, final T item) {
        ItemResult<T> itemResult = new ItemResult<>();
        itemResult.setCode(code);

        ItemData<T> data = new ItemData<>();
        data.setMessage(message);
        data.setItem(item);

        itemResult.setData(data);

        return itemResult;
    }

    protected final <T> ItemResult<T> responseItem(final CodeEnum codeEnum, final T item) {
        return responseItem(codeEnum.getCode(), codeEnum.getMessage(), item);
    }

    protected final <T> ItemResult<T> responseItem(final CodeEnum codeEnum, final String message, final T item) {
        return responseItem(codeEnum.getCode(), message, item);
    }

    protected final <T> ItemR<T> responseItemR(final int code, final String message, final T item) {
        ItemR<T> itemR = new ItemR<>();

        itemR.setCode(code);
        itemR.setMessage(message);
        itemR.setItem(item);
        itemR.setCount(Func.isNull(item) ? NumberConstants.N_ZERO : NumberConstants.N_ONE);

        return itemR;
    }

    protected final <T> ItemR<T> responseItemR(final CodeEnum codeEnum, final T item) {
        return responseItemR(codeEnum.getCode(), codeEnum.getMessage(), item);
    }

    protected final <T> ItemR<T> responseItemR(final CodeEnum codeEnum, final String message, final T item) {
        return responseItemR(codeEnum.getCode(), message, item);
    }

    /**
     * 处理响应list
     *
     * @param code
     * @param message
     * @param items
     * @return
     */
    protected final <T> ItemsResult<T> responseItems(final int code, final String message, final Collection<T> items) {
        ItemsResult<T> itemsResult = new ItemsResult<>();
        itemsResult.setCode(code);

        ItemsData<T> data = new ItemsData<>();
        data.setMessage(message);
        data.setItems(items);

        itemsResult.setData(data);

        return itemsResult;
    }

    protected final <T> ItemsResult<T> responseItems(final CodeEnum codeEnum, final Collection<T> items) {
        return responseItems(codeEnum.getCode(), codeEnum.getMessage(), items);
    }

    protected final <T> ItemsResult<T> responseItems(final CodeEnum codeEnum, final String message,
        final Collection<T> items) {
        return responseItems(codeEnum.getCode(), message, items);
    }

    protected final <T> ItemsR<T> responseItemsR(final int code, final String message, final Collection<T> items) {
        ItemsR<T> itemsR = new ItemsR<>();

        itemsR.setCode(code);
        itemsR.setMessage(message);
        itemsR.setItems(Func.isEmpty(items) ? Collections.emptyList() : items);
        itemsR.setCount(Func.isEmpty(items) ? NumberConstants.N_ZERO : items.size());

        return itemsR;
    }

    protected final <T> ItemsR<T> responseItemsR(final CodeEnum codeEnum, final Collection<T> items) {
        return responseItemsR(codeEnum.getCode(), codeEnum.getMessage(), items);
    }

    protected final <T> ItemsR<T> responseItemsR(final CodeEnum codeEnum, final String message,
        final Collection<T> items) {
        return responseItemsR(codeEnum.getCode(), message, items);
    }

    /**
     * 处理响应page
     *
     * @param code
     * @param message
     * @param totalCount
     * @param items
     * @return
     */
    protected final <T> PageResult<T> responsePage(final int code, final String message, final Long totalCount,
        final Collection<T> items, final Integer pageSize, final Integer pageNo) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(code);

        PageData<T> data = new PageData<>();
        data.setMessage(message);
        data.setPageNo(pageNo);
        data.setPageSize(pageSize);
        data.setItems(items);
        data.setTotalCount(totalCount);
        data.setTotalPage(totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1);
        data.setRecordCount(items.size());

        pageResult.setData(data);

        return pageResult;
    }

    protected final <T> PageResult<T> responsePage(final CodeEnum codeEnum, final Long totalCount,
        final Collection<T> items,
        final Integer pageSize, final Integer pageNo) {
        return responsePage(codeEnum.getCode(), codeEnum.getMessage(), totalCount, items, pageSize, pageNo);
    }

    protected final <T> PageResult<T> responsePage(final CodeEnum codeEnum, final String message, final Long totalCount,
        final Collection<T> items, final Integer pageSize, final Integer pageNo) {
        return responsePage(codeEnum.getCode(), message, totalCount, items, pageSize, pageNo);
    }

    protected final <T> PageR<T> responsePageR(final int code, final String message, final Long totalCount,
        final Collection<T> items, final Integer pageSize, final Integer pageNo) {
        PageR<T> pageR = new PageR<>();

        pageR.setCode(code);
        pageR.setMessage(message);

        PageD<T> data = new PageD<>();
        data.setPageNo(pageNo);
        data.setPageSize(pageSize);
        data.setDataList(Func.isEmpty(items) ? Collections.emptyList() : items);
        data.setRecordCount(Func.isEmpty(items) ? NumberConstants.N_ZERO : items.size());
        data.setTotalCount(totalCount);
        data.setTotalPage(totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1);
        pageR.setData(data);

        int count = Func.toInt(data.getTotalCount(), NumberConstants.N_ZERO);
        pageR.setCount(count);

        return pageR;
    }

    protected final <T> PageR<T> responsePageR(final CodeEnum codeEnum, final Long totalCount,
        final Collection<T> items,
        final Integer pageSize, final Integer pageNo) {
        return responsePageR(codeEnum.getCode(), codeEnum.getMessage(), totalCount, items, pageSize, pageNo);
    }

    protected final <T> PageR<T> responsePageR(final CodeEnum codeEnum, final String message, final Long totalCount,
        final Collection<T> items, final Integer pageSize, final Integer pageNo) {
        return responsePageR(codeEnum.getCode(), message, totalCount, items, pageSize, pageNo);
    }
}
