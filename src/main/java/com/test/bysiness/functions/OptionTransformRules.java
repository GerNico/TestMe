package com.test.bysiness.functions;

import com.test.bysiness.dto.Option;
import com.test.bysiness.entities.OptionEntity;

import java.util.function.Function;

public class OptionTransformRules {

    public static Function<OptionEntity, Option> optionEntityToOption = optionEntity -> {
        Option option = new Option();
        option.setId(optionEntity.getId());
        option.setText(optionEntity.getText());
        option.setCorrect(optionEntity.getIsCorrect());
        option.setSequenceBased(optionEntity.getIsSequenceBased());
        option.setNumberInSequence(optionEntity.getNumberInSequence());
        return option;
    };

    public static Function<Option, OptionEntity> optionToOptionEntity = option -> {
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setId(option.getId());
        optionEntity.setText(option.getText());
        optionEntity.setIsCorrect(option.isCorrect());
        optionEntity.setIsSequenceBased(option.isSequenceBased());
        optionEntity.setNumberInSequence(option.getNumberInSequence());
        return optionEntity;
    };
}
