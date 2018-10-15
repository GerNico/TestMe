package com.test.bysiness.functions;

import com.test.bysiness.dto.OptionData;
import com.test.bysiness.entities.OptionEntity;

import java.util.function.Function;

public class OptionTransformRules {

    public static Function<OptionEntity, OptionData> optionEntityToOption = optionEntity -> {
        OptionData option = new OptionData();
        option.setId(optionEntity.getId());
        option.setText(optionEntity.getText());
        option.setCorrect(optionEntity.isCorrect());
        option.setSequenceBased(optionEntity.isSequenceBased());
        option.setNumberInSequence(optionEntity.getNumberInSequence());
        return option;
    };

    public static Function<OptionData, OptionEntity> optionToOptionEntity = option -> {
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setId(option.getId());
        optionEntity.setText(option.getText());
        optionEntity.setCorrect(option.isCorrect());
        optionEntity.setSequenceBased(option.isSequenceBased());
        optionEntity.setNumberInSequence(option.getNumberInSequence());
        return optionEntity;
    };
}
