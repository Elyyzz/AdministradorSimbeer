package com.example.elyzzbarrueta.administrador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elyzz Barrueta on 27/10/2015.
 */
public class GrupoDeItems {
    public String string;
    public final List<String> children = new ArrayList<String>();
    public GrupoDeItems(String string) {
        this.string = string;
    }

}
