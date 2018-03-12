package com.stage.pfe.metier;

import java.nio.file.Path;
import java.util.Date;

import com.stage.pfe.entities.NoteFrais;

public interface INoteFrais {
	public NoteFrais consulterNoteFrais(long id);
	public NoteFrais enregister(String username, String name, Date dateupload, String chemin, Boolean etat, String motif);

}
