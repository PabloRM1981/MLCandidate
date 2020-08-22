package com.mercadolibre.mlcandidate.modelos;

import java.io.Serializable;
import java.util.ArrayList;

public class Productos implements Serializable {
    private String query;
    private ArrayList<Producto> results;

    public Productos() {
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ArrayList<Producto> getResults() {
        return results;
    }

    public void setResults(ArrayList<Producto> results) {
        this.results = results;
    }
}
