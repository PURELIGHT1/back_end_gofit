package com.api.implement.services;

import com.api.models.entities.GenerateTabel;

public interface GenerateService {

    GenerateTabel createGenerate(GenerateTabel generateTabel);

    GenerateTabel updateGenerateId();

    GenerateTabel findById();

    GenerateTabel generateIdInstruktur(Integer id);
}
