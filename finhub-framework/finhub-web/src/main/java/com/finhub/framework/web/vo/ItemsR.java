package com.finhub.framework.web.vo;

import lombok.Data;

import java.util.Collection;


/**
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
public class ItemsR<T> extends Result<Collection<T>> {

    private static final long serialVersionUID = 5409185459234711691L;

    public void setMessage(final String message) {
        super.setMsg(message);
    }

    public void setItems(final Collection<T> items) {
        super.setData(items);
    }
}
