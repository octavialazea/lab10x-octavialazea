package domain.entities;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class BaseEntity<ID> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public String getVar(String var) throws Exception {
        return "";
    }


    @Override
    public String toString() {
        return "BaseEntity{" + "id=" + id;
    }

}