package com.stage.pfe.metier;

import java.nio.file.Path;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stage.pfe.dao.UploadRepository;
import com.stage.pfe.entities.NoteFrais;
@Service
public class NoteFraisMetier implements INoteFrais {
	@Autowired
	private UploadRepository uploadRepository;

	@Override
	public NoteFrais consulterNoteFrais(long id) {
		NoteFrais nf= uploadRepository.findOne(id);
		return nf;
	}

	@Override
	public NoteFrais enregister(String userename, String name, Date dateupload, String chemin, Boolean etat,
			String motif) {
		NoteFrais nf = new NoteFrais(userename, name, dateupload, chemin, etat, motif);
		return uploadRepository.save(nf);
	}



}
