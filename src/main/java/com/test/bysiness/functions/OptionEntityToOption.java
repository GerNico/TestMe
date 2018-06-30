package com.test.bysiness.functions;

import com.test.bysiness.dto.Option;
import com.test.bysiness.entities.OptionEntity;

import java.util.function.Function;

public class OptionEntityToOption implements Function<OptionEntity, Option> {
    @Override
    public Option apply(OptionEntity optionEntity) {
        return convert(optionEntity);
    }

    public static Option convert(OptionEntity optionEntity) {
        Option option = new Option();
        option.setId(optionEntity.getId());
        option.setText(optionEntity.getText());
        option.setCorrect(optionEntity.getIsCorrect());
        option.setSequenceBased(optionEntity.getIsSequenceBased());
        option.setNumberInSequence(optionEntity.getNumberInSequence());
        return option;
    }
}
