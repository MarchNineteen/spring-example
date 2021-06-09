package com.wyb.exception.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Marcher丶
 */
public class ValidatedEntity implements Serializable {
    private static final long serialVersionUID = 5362436684009671309L;

    @NotNull
    private String message;

    @NotEmpty
    private List<String> ids;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
