package com.finhub.framework.web.vo;

import lombok.Data;


/**
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
public class ItemR<T> extends Result<T> {

    private static final long serialVersionUID = 5409185459234711691L;

    public void setMessage(final String message) {
        super.setMsg(message);
    }

    public void setItem(final T item) {
        super.setData(item);
    }

}
